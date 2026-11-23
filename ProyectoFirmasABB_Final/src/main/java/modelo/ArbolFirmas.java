/**
 * David Ureña, Lis Vasquez, Yoselin Perez, Oliver Oses, Pedro Zamora, Ethan Beltran
 */
package modelo;

import java.util.ArrayList;
import java.util.List;

public class ArbolFirmas {
    private NodoFirma raiz;
    private static final int LIMITE_DATOS = 1000;

    public ArbolFirmas() {
        this.raiz = null;
    }

    public NodoFirma getRaiz() {
        return raiz;
    }

    public void vaciar() {
        this.raiz = null;
    }

    // Insercion O(h) ordenando por nombreAmenaza sin distinguir mayusculas/minusculas
    public boolean insertar(NodoFirma nuevo) {
        if (contarNodos() >= LIMITE_DATOS) {
            return false;
        }
        if (raiz == null) {
            raiz = nuevo;
            return true;
        }
        return insertarRecursivo(raiz, nuevo);
    }

    private boolean insertarRecursivo(NodoFirma actual, NodoFirma nuevo) {
        int cmp = nuevo.getNombreAmenaza().compareToIgnoreCase(actual.getNombreAmenaza());

        if (cmp == 0) {
            return false; // Rechaza claves repetidas
        } else if (cmp < 0) {
            if (actual.getIzquierdo() == null) {
                actual.setIzquierdo(nuevo);
                return true;
            }
            return insertarRecursivo(actual.getIzquierdo(), nuevo);
        } else {
            if (actual.getDerecho() == null) {
                actual.setDerecho(nuevo);
                return true;
            }
            return insertarRecursivo(actual.getDerecho(), nuevo);
        }
    }

    // Busqueda por nombre exacto O(h)
    public NodoFirma buscarPorNombre(String nombre) {
        if (nombre == null) return null;
        return buscarRecursivo(raiz, nombre.trim());
    }

    private NodoFirma buscarRecursivo(NodoFirma actual, String nombre) {
        if (actual == null) {
            return null;
        }
        int cmp = nombre.compareToIgnoreCase(actual.getNombreAmenaza());
        if (cmp == 0) {
            return actual;
        } else if (cmp < 0) {
            return buscarRecursivo(actual.getIzquierdo(), nombre);
        } else {
            return buscarRecursivo(actual.getDerecho(), nombre);
        }
    }

    // Validacion de ID repetido al cargar archivo
    public boolean existeId(String id) {
        return existeIdRec(raiz, id);
    }

    private boolean existeIdRec(NodoFirma actual, String id) {
        if (actual == null) return false;
        if (actual.getIdFirma().equalsIgnoreCase(id)) return true;
        return existeIdRec(actual.getIzquierdo(), id) || existeIdRec(actual.getDerecho(), id);
    }

    // Generador automatico del siguiente ID (MW-000X)
    public String generarSiguienteId() {
        int max = obtenerMaxNumeroId(raiz);
        return String.format("MW-%04d", max + 1);
    }

    private int obtenerMaxNumeroId(NodoFirma actual) {
        if (actual == null) return 0;
        int numActual = 0;
        try {
            numActual = Integer.parseInt(actual.getIdFirma().substring(3));
        } catch (Exception e) {
            numActual = 0;
        }
        int maxIzq = obtenerMaxNumeroId(actual.getIzquierdo());
        int maxDer = obtenerMaxNumeroId(actual.getDerecho());
        return Math.max(numActual, Math.max(maxIzq, maxDer));
    }

    // Recorridos del ABB O(n)
    public String recorridoInOrden() {
        StringBuilder sb = new StringBuilder();
        inOrdenRec(raiz, sb);
        return sb.length() == 0 ? "Arbol vacio" : sb.toString();
    }

    private void inOrdenRec(NodoFirma actual, StringBuilder sb) {
        if (actual != null) {
            inOrdenRec(actual.getIzquierdo(), sb);
            if (sb.length() > 0) sb.append(" -> ");
            sb.append(actual.getNombreAmenaza()).append(" (").append(actual.getIdFirma()).append(")");
            inOrdenRec(actual.getDerecho(), sb);
        }
    }

    public String recorridoPreOrden() {
        StringBuilder sb = new StringBuilder();
        preOrdenRec(raiz, sb);
        return sb.length() == 0 ? "Arbol vacio" : sb.toString();
    }

    private void preOrdenRec(NodoFirma actual, StringBuilder sb) {
        if (actual != null) {
            if (sb.length() > 0) sb.append(" -> ");
            sb.append(actual.getNombreAmenaza()).append(" (").append(actual.getIdFirma()).append(")");
            preOrdenRec(actual.getIzquierdo(), sb);
            preOrdenRec(actual.getDerecho(), sb);
        }
    }

    public String recorridoPostOrden() {
        StringBuilder sb = new StringBuilder();
        postOrdenRec(raiz, sb);
        return sb.length() == 0 ? "Arbol vacio" : sb.toString();
    }

    private void postOrdenRec(NodoFirma actual, StringBuilder sb) {
        if (actual != null) {
            postOrdenRec(actual.getIzquierdo(), sb);
            postOrdenRec(actual.getDerecho(), sb);
            if (sb.length() > 0) sb.append(" -> ");
            sb.append(actual.getNombreAmenaza()).append(" (").append(actual.getIdFirma()).append(")");
        }
    }

    // Lista en PreOrden para guardar en firmas.csv conservando la forma del arbol
    public List<NodoFirma> obtenerNodosPreOrden() {
        List<NodoFirma> lista = new ArrayList<>();
        llenarPreOrden(raiz, lista);
        return lista;
    }

    private void llenarPreOrden(NodoFirma actual, List<NodoFirma> lista) {
        if (actual != null) {
            lista.add(actual);
            llenarPreOrden(actual.getIzquierdo(), lista);
            llenarPreOrden(actual.getDerecho(), lista);
        }
    }

    // Estadisticas basicas recursivas O(n)
    public int contarNodos() {
        return contarNodosRec(raiz);
    }

    private int contarNodosRec(NodoFirma actual) {
        if (actual == null) return 0;
        return 1 + contarNodosRec(actual.getIzquierdo()) + contarNodosRec(actual.getDerecho());
    }

    public int contarHojas() {
        return contarHojasRec(raiz);
    }

    private int contarHojasRec(NodoFirma actual) {
        if (actual == null) return 0;
        if (actual.getIzquierdo() == null && actual.getDerecho() == null) return 1;
        return contarHojasRec(actual.getIzquierdo()) + contarHojasRec(actual.getDerecho());
    }

    public int calcularAltura() {
        return calcularAlturaRec(raiz);
    }

    private int calcularAlturaRec(NodoFirma actual) {
        if (actual == null) return 0;
        return 1 + Math.max(calcularAlturaRec(actual.getIzquierdo()), calcularAlturaRec(actual.getDerecho()));
    }

    // Eliminacion en ABB (3 casos: hoja, 1 hijo, 2 hijos con sucesor InOrden)
    public boolean eliminar(String nombreAmenaza) {
        if (nombreAmenaza == null || nombreAmenaza.trim().isEmpty() || raiz == null) {
            return false;
        }
        boolean[] eliminado = {false};
        raiz = eliminarRec(raiz, nombreAmenaza.trim(), eliminado);
        return eliminado[0];
    }

    private NodoFirma eliminarRec(NodoFirma actual, String nombre, boolean[] eliminado) {
        if (actual == null) return null;

        int cmp = nombre.compareToIgnoreCase(actual.getNombreAmenaza());
        if (cmp < 0) {
            actual.setIzquierdo(eliminarRec(actual.getIzquierdo(), nombre, eliminado));
        } else if (cmp > 0) {
            actual.setDerecho(eliminarRec(actual.getDerecho(), nombre, eliminado));
        } else {
            eliminado[0] = true;
            if (actual.getIzquierdo() == null) return actual.getDerecho();
            if (actual.getDerecho() == null) return actual.getIzquierdo();

            NodoFirma sucesor = buscarMinimo(actual.getDerecho());
            copiarDatosNodo(sucesor, actual);
            actual.setDerecho(eliminarRec(actual.getDerecho(), sucesor.getNombreAmenaza(), new boolean[1]));
        }
        return actual;
    }

    private NodoFirma buscarMinimo(NodoFirma nodo) {
        while (nodo.getIzquierdo() != null) {
            nodo = nodo.getIzquierdo();
        }
        return nodo;
    }

    private void copiarDatosNodo(NodoFirma origen, NodoFirma destino) {
        destino.setIdFirma(origen.getIdFirma());
        destino.setNombreAmenaza(origen.getNombreAmenaza());
        destino.setTipo(origen.getTipo());
        destino.setSeveridad(origen.getSeveridad());
        destino.setHashFirma(origen.getHashFirma());
        destino.setFechaRegistro(origen.getFechaRegistro());
    }

    // Busqueda por prefijo aprovechando el orden del ABB
    public List<NodoFirma> buscarPorPrefijo(String prefijo) {
        List<NodoFirma> resultados = new ArrayList<>();
        if (prefijo == null || prefijo.trim().isEmpty() || raiz == null) {
            return resultados;
        }
        buscarPrefijoRec(raiz, prefijo.trim().toLowerCase(), resultados);
        return resultados;
    }

    private void buscarPrefijoRec(NodoFirma actual, String prefijoLower, List<NodoFirma> resultados) {
        if (actual == null) return;

        String nombreLower = actual.getNombreAmenaza().toLowerCase();
        if (nombreLower.compareTo(prefijoLower) >= 0 || nombreLower.startsWith(prefijoLower)) {
            buscarPrefijoRec(actual.getIzquierdo(), prefijoLower, resultados);
        }
        if (nombreLower.startsWith(prefijoLower)) {
            resultados.add(actual);
        }
        if (nombreLower.compareTo(prefijoLower) <= 0 || nombreLower.startsWith(prefijoLower)) {
            buscarPrefijoRec(actual.getDerecho(), prefijoLower, resultados);
        }
    }

    // Metodos secundarios: busqueda por ID, tipo, severidad, hash y conteo
    public NodoFirma buscarPorId(String idBuscado) {
        if (idBuscado == null || idBuscado.trim().isEmpty()) return null;
        return buscarPorIdRec(raiz, idBuscado.trim());
    }

    private NodoFirma buscarPorIdRec(NodoFirma actual, String idBuscado) {
        if (actual == null) return null;
        if (actual.getIdFirma().equalsIgnoreCase(idBuscado)) return actual;
        NodoFirma izq = buscarPorIdRec(actual.getIzquierdo(), idBuscado);
        if (izq != null) return izq;
        return buscarPorIdRec(actual.getDerecho(), idBuscado);
    }

    public List<NodoFirma> buscarPorTipo(String tipoBuscado) {
        List<NodoFirma> resultados = new ArrayList<>();
        if (tipoBuscado == null || tipoBuscado.trim().isEmpty()) return resultados;
        buscarPorTipoRec(raiz, tipoBuscado.trim(), resultados);
        return resultados;
    }

    private void buscarPorTipoRec(NodoFirma actual, String tipoBuscado, List<NodoFirma> resultados) {
        if (actual == null) return;
        buscarPorTipoRec(actual.getIzquierdo(), tipoBuscado, resultados);
        if (actual.getTipo().equalsIgnoreCase(tipoBuscado)) {
            resultados.add(actual);
        }
        buscarPorTipoRec(actual.getDerecho(), tipoBuscado, resultados);
    }

    public List<NodoFirma> buscarPorSeveridad(int severidadBuscada) {
        List<NodoFirma> resultados = new ArrayList<>();
        buscarPorSeveridadRec(raiz, severidadBuscada, resultados);
        return resultados;
    }

    private void buscarPorSeveridadRec(NodoFirma actual, int severidadBuscada, List<NodoFirma> resultados) {
        if (actual == null) return;
        buscarPorSeveridadRec(actual.getIzquierdo(), severidadBuscada, resultados);
        if (actual.getSeveridad() == severidadBuscada) {
            resultados.add(actual);
        }
        buscarPorSeveridadRec(actual.getDerecho(), severidadBuscada, resultados);
    }

    public List<NodoFirma> buscarPorHash(String hashBuscado) {
        List<NodoFirma> resultados = new ArrayList<>();
        if (hashBuscado == null || hashBuscado.trim().isEmpty()) return resultados;
        buscarPorHashRec(raiz, hashBuscado.trim().toLowerCase(), resultados);
        return resultados;
    }

    private void buscarPorHashRec(NodoFirma actual, String target, List<NodoFirma> resultados) {
        if (actual == null) return;
        buscarPorHashRec(actual.getIzquierdo(), target, resultados);
        if (actual.getHashFirma().toLowerCase().contains(target)) {
            resultados.add(actual);
        }
        buscarPorHashRec(actual.getDerecho(), target, resultados);
    }

    public int contarPorSeveridad(int severidad) {
        return buscarPorSeveridad(severidad).size();
    }
}
