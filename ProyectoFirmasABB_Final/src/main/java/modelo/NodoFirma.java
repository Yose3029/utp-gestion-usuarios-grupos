/**
 * David Ureña, Lis Vasquez, Yoselin Perez, Oliver Oses, Pedro Zamora, Ethan Beltran
 */
package modelo;

public class NodoFirma {
    // Atributos de la firma de malware
    private String idFirma;
    private String nombreAmenaza; // Clave de ordenamiento del ABB
    private String tipo;
    private int severidad;
    private String hashFirma;
    private String fechaRegistro;

    // Punteros a subarboles izquierdo y derecho
    private NodoFirma izquierdo;
    private NodoFirma derecho;

    public NodoFirma(String idFirma, String nombreAmenaza, String tipo, int severidad, String hashFirma, String fechaRegistro) {
        this.idFirma = idFirma;
        this.nombreAmenaza = nombreAmenaza;
        this.tipo = tipo;
        this.severidad = severidad;
        this.hashFirma = hashFirma;
        this.fechaRegistro = fechaRegistro;
        this.izquierdo = null;
        this.derecho = null;
    }

    public String getIdFirma() { return idFirma; }
    public void setIdFirma(String idFirma) { this.idFirma = idFirma; }

    public String getNombreAmenaza() { return nombreAmenaza; }
    public void setNombreAmenaza(String nombreAmenaza) { this.nombreAmenaza = nombreAmenaza; }

    public String getTipo() { return tipo; }
    public void setTipo(String tipo) { this.tipo = tipo; }

    public int getSeveridad() { return severidad; }
    public void setSeveridad(int severidad) { this.severidad = severidad; }

    public String getHashFirma() { return hashFirma; }
    public void setHashFirma(String hashFirma) { this.hashFirma = hashFirma; }

    public String getFechaRegistro() { return fechaRegistro; }
    public void setFechaRegistro(String fechaRegistro) { this.fechaRegistro = fechaRegistro; }

    public NodoFirma getIzquierdo() { return izquierdo; }
    public void setIzquierdo(NodoFirma izquierdo) { this.izquierdo = izquierdo; }

    public NodoFirma getDerecho() { return derecho; }
    public void setDerecho(NodoFirma derecho) { this.derecho = derecho; }

    // Convierte el nodo a formato CSV para firmas.csv
    public String toCSV() {
        return idFirma + "," + nombreAmenaza + "," + tipo + "," + severidad + "," + hashFirma + "," + fechaRegistro;
    }

    // Devuelve las caracteristicas principales segun el tipo de malware
    public static String obtenerCaracteristicasTipo(String tipo) {
        if (tipo == null) {
            return "Sin descripcion disponible.";
        }
        switch (tipo.trim().toLowerCase()) {
            case "troyano":
                return "Se disfraza de software legitimo para enganar al usuario y abrir puertas traseras sin autorreplicarse.";
            case "ransomware":
                return "Cifra archivos o bloquea el acceso al sistema y exige un rescate para liberar la clave.";
            case "gusano":
                return "Se replica automaticamente a traves de redes y vulnerabilidades sin necesitar archivo anfitrion.";
            case "botnet":
                return "Convierte el equipo infectado en un nodo controlado remotamente para ataques coordinados o spam.";
            case "spyware":
                return "Recopila informacion del usuario en segundo plano sin su consentimiento.";
            default:
                return "Amenaza informatica registrada en el catalogo de firmas.";
        }
    }
}
