/**
 * David Ureña, Lis Vasquez, Yoselin Perez, Oliver Oses, Pedro Zamora, Ethan Beltran
 */
package archivos;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.time.LocalDate;
import java.util.List;
import modelo.ArbolFirmas;
import modelo.NodoFirma;

public class GestorArchivo {
    private static final String RUTA_ARCHIVO = "firmas.csv";

    // Crea el archivo CSV con cabecera si no existe
    public static void inicializarArchivoEjemploSiNoExiste() {
        File archivo = new File(RUTA_ARCHIVO);
        if (!archivo.exists()) {
            try (PrintWriter pw = new PrintWriter(new FileWriter(archivo))) {
                pw.println("idFirma,nombreAmenaza,tipo,severidad,hashFirma,fechaRegistro");
            } catch (IOException e) {
                System.err.println("No se pudo crear el archivo inicial: " + e.getMessage());
            }
        }
    }

    // Lee y valida linea por linea el archivo firmas.csv
    public static String cargarDesdeArchivo(ArbolFirmas arbol) {
        inicializarArchivoEjemploSiNoExiste();
        File archivo = new File(RUTA_ARCHIVO);
        arbol.vaciar();

        try (BufferedReader br = new BufferedReader(new FileReader(archivo))) {
            String linea;
            int numLinea = 0;

            while ((linea = br.readLine()) != null) {
                numLinea++;
                linea = linea.trim();
                if (linea.isEmpty()) continue;

                if (numLinea == 1 && linea.startsWith("idFirma,")) {
                    continue;
                }

                String[] campos = linea.split(",");
                if (campos.length != 6) {
                    arbol.vaciar();
                    return "Error en linea " + numLinea + ": Se esperaban 6 campos separados por coma.";
                }

                String id = campos[0].trim();
                String nombre = campos[1].trim();
                String tipo = campos[2].trim();
                String sevStr = campos[3].trim();
                String hash = campos[4].trim();
                String fecha = campos[5].trim();

                if (!validarId(id) || arbol.existeId(id)) {
                    arbol.vaciar();
                    return "Error en linea " + numLinea + ": ID invalido o duplicado (" + id + ").";
                }
                if (nombre.isEmpty() || arbol.buscarPorNombre(nombre) != null) {
                    arbol.vaciar();
                    return "Error en linea " + numLinea + ": Nombre vacio o repetido (" + nombre + ").";
                }
                int severidad;
                try {
                    severidad = Integer.parseInt(sevStr);
                    if (severidad < 1 || severidad > 4) throw new NumberFormatException();
                } catch (NumberFormatException e) {
                    arbol.vaciar();
                    return "Error en linea " + numLinea + ": La severidad debe ser un entero entre 1 y 4.";
                }
                if (!validarHashHexadecimal(hash)) {
                    arbol.vaciar();
                    return "Error en linea " + numLinea + ": El hash debe tener 64 caracteres hexadecimales.";
                }
                if (!validarFecha(fecha)) {
                    arbol.vaciar();
                    return "Error en linea " + numLinea + ": La fecha debe tener formato AAAA-MM-DD.";
                }

                arbol.insertar(new NodoFirma(id, nombre, tipo, severidad, hash.toLowerCase(), fecha));
            }
        } catch (IOException e) {
            arbol.vaciar();
            return "Error al leer firmas.csv: " + e.getMessage();
        }
        return null;
    }

    // Guarda el arbol en PreOrden para conservar su estructura exacta
    public static boolean guardarEnPreOrden(ArbolFirmas arbol) {
        try (PrintWriter pw = new PrintWriter(new FileWriter(RUTA_ARCHIVO))) {
            pw.println("idFirma,nombreAmenaza,tipo,severidad,hashFirma,fechaRegistro");
            List<NodoFirma> nodos = arbol.obtenerNodosPreOrden();
            for (NodoFirma n : nodos) {
                pw.println(n.toCSV());
            }
            return true;
        } catch (IOException e) {
            return false;
        }
    }

    // Valida formato MW- seguido de 4 digitos
    public static boolean validarId(String id) {
        if (id == null || id.length() != 7 || !id.startsWith("MW-")) return false;
        for (int i = 3; i < 7; i++) {
            char c = id.charAt(i);
            if (c < '0' || c > '9') return false;
        }
        return true;
    }

    // Valida 64 caracteres hexadecimales
    public static boolean validarHashHexadecimal(String hash) {
        if (hash == null || hash.length() != 64) return false;
        String h = hash.toLowerCase();
        for (int i = 0; i < h.length(); i++) {
            char c = h.charAt(i);
            boolean esDigito = (c >= '0' && c <= '9');
            boolean esHex = (c >= 'a' && c <= 'f');
            if (!esDigito && !esHex) return false;
        }
        return true;
    }

    // Valida formato AAAA-MM-DD
    public static boolean validarFecha(String fecha) {
        try {
            LocalDate.parse(fecha);
            return true;
        } catch (Exception e) {
            return false;
        }
    }
}
