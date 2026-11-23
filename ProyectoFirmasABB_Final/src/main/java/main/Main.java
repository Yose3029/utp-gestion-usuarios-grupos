/**
 * David Ureña, Lis Vasquez, Yoselin Perez, Oliver Oses, Pedro Zamora, Ethan Beltran
 */
package main;

import archivos.GestorArchivo;
import javax.swing.JOptionPane;
import javax.swing.SwingUtilities;
import modelo.ArbolFirmas;
import vista.VentanaPrincipal;

public class Main {
    public static void main(String[] args) {
        // Inicia la aplicacion grafica en el hilo de Swing
        SwingUtilities.invokeLater(() -> {
            ArbolFirmas arbol = new ArbolFirmas();
            String errorCarga = GestorArchivo.cargarDesdeArchivo(arbol);

            if (errorCarga != null) {
                JOptionPane.showMessageDialog(
                        null,
                        errorCarga + "\nEl arbol iniciara vacio por seguridad.",
                        "Error de Integridad en firmas.csv",
                        JOptionPane.ERROR_MESSAGE
                );
            }

            VentanaPrincipal vp = new VentanaPrincipal(arbol);
            vp.setVisible(true);
        });
    }
}
