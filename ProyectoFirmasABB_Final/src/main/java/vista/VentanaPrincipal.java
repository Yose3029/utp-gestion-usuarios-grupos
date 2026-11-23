/**
 * David Ureña, Lis Vasquez, Yoselin Perez, Oliver Oses, Pedro Zamora, Ethan Beltran
 */
package vista;

import archivos.GestorArchivo;
import java.awt.Color;
import javax.swing.JColorChooser;
import javax.swing.JOptionPane;
import modelo.ArbolFirmas;

public class VentanaPrincipal extends javax.swing.JFrame {

    private final ArbolFirmas arbol;

    // Constructor usado por Main
    public VentanaPrincipal(ArbolFirmas arbol) {
        this.arbol = arbol;
        initComponents();
        setLocationRelativeTo(null);
    }

    // Constructor vacio requerido por el editor Design de NetBeans
    public VentanaPrincipal() {
        this(new ArbolFirmas());
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        panelEncabezado = new javax.swing.JPanel();
        lblTitulo = new javax.swing.JLabel();
        lblSubtitulo = new javax.swing.JLabel();
        panelBotones = new javax.swing.JPanel();
        btnRegistrar = new javax.swing.JButton();
        btnVisualizar = new javax.swing.JButton();
        btnReportes = new javax.swing.JButton();
        btnSalir = new javax.swing.JButton();
        panelPersonalizar = new javax.swing.JPanel();
        btnColorFondo = new javax.swing.JButton();
        btnColorBotones = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("Base de Firmas de Malware - ABB");
        setPreferredSize(new java.awt.Dimension(520, 420));

        panelEncabezado.setBorder(javax.swing.BorderFactory.createEmptyBorder(15, 10, 5, 10));
        panelEncabezado.setLayout(new java.awt.GridLayout(2, 1));

        lblTitulo.setFont(new java.awt.Font("SansSerif", 1, 18)); // NOI18N
        lblTitulo.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblTitulo.setText("Catálogo de Firmas de Malware (ABB)");
        panelEncabezado.add(lblTitulo);

        lblSubtitulo.setFont(new java.awt.Font("SansSerif", 0, 12)); // NOI18N
        lblSubtitulo.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblSubtitulo.setText("Estructura de Datos - Grupo 6S3111");
        panelEncabezado.add(lblSubtitulo);

        getContentPane().add(panelEncabezado, java.awt.BorderLayout.NORTH);

        panelBotones.setBorder(javax.swing.BorderFactory.createEmptyBorder(10, 50, 15, 50));
        panelBotones.setLayout(new java.awt.GridLayout(4, 1, 10, 10));

        btnRegistrar.setText("1. Registrar firma");
        btnRegistrar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnRegistrarActionPerformed(evt);
            }
        });
        panelBotones.add(btnRegistrar);

        btnVisualizar.setText("2. Ver árbol y recorridos");
        btnVisualizar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnVisualizarActionPerformed(evt);
            }
        });
        panelBotones.add(btnVisualizar);

        btnReportes.setText("3. Buscar, filtrar, eliminar y reportes");
        btnReportes.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnReportesActionPerformed(evt);
            }
        });
        panelBotones.add(btnReportes);

        btnSalir.setText("4. Guardar y salir");
        btnSalir.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnSalirActionPerformed(evt);
            }
        });
        panelBotones.add(btnSalir);

        getContentPane().add(panelBotones, java.awt.BorderLayout.CENTER);

        panelPersonalizar.setLayout(new java.awt.FlowLayout(java.awt.FlowLayout.CENTER, 10, 8));

        btnColorFondo.setText("Color de fondo");
        btnColorFondo.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnColorFondoActionPerformed(evt);
            }
        });
        panelPersonalizar.add(btnColorFondo);

        btnColorBotones.setText("Color de botones");
        btnColorBotones.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnColorBotonesActionPerformed(evt);
            }
        });
        panelPersonalizar.add(btnColorBotones);

        getContentPane().add(panelPersonalizar, java.awt.BorderLayout.SOUTH);

        pack();
    }// </editor-fold>//GEN-END:initComponents

    // Abre ventana de registro
    private void btnRegistrarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnRegistrarActionPerformed
        VentanaRegistro vr = new VentanaRegistro(this, arbol);
        vr.setVisible(true);
    }//GEN-LAST:event_btnRegistrarActionPerformed

    // Abre visualizacion grafica del arbol
    private void btnVisualizarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnVisualizarActionPerformed
        VentanaVisualizacion vv = new VentanaVisualizacion(this, arbol);
        vv.setVisible(true);
    }//GEN-LAST:event_btnVisualizarActionPerformed

    // Abre busquedas, filtros, eliminacion y reportes
    private void btnReportesActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnReportesActionPerformed
        VentanaReportes vrep = new VentanaReportes(this, arbol);
        vrep.setVisible(true);
    }//GEN-LAST:event_btnReportesActionPerformed

    // Guarda en PreOrden y cierra
    private void btnSalirActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSalirActionPerformed
        boolean ok = GestorArchivo.guardarEnPreOrden(arbol);
        if (ok) {
            JOptionPane.showMessageDialog(this, "Árbol guardado en PreOrden en firmas.csv correctamente.");
        }
        System.exit(0);
    }//GEN-LAST:event_btnSalirActionPerformed

    // Cambia el color de fondo en ejecucion
    private void btnColorFondoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnColorFondoActionPerformed
        Color nuevo = JColorChooser.showDialog(this, "Elige color de fondo", panelBotones.getBackground());
        if (nuevo != null) {
            getContentPane().setBackground(nuevo);
            panelEncabezado.setBackground(nuevo);
            panelBotones.setBackground(nuevo);
            panelPersonalizar.setBackground(nuevo);
        }
    }//GEN-LAST:event_btnColorFondoActionPerformed

    // Cambia el color de los botones principales en ejecucion
    private void btnColorBotonesActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnColorBotonesActionPerformed
        Color nuevo = JColorChooser.showDialog(this, "Elige color de botones", btnRegistrar.getBackground());
        if (nuevo != null) {
            btnRegistrar.setBackground(nuevo);
            btnVisualizar.setBackground(nuevo);
            btnReportes.setBackground(nuevo);
            btnSalir.setBackground(nuevo);
        }
    }//GEN-LAST:event_btnColorBotonesActionPerformed

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnColorBotones;
    private javax.swing.JButton btnColorFondo;
    private javax.swing.JButton btnRegistrar;
    private javax.swing.JButton btnReportes;
    private javax.swing.JButton btnSalir;
    private javax.swing.JButton btnVisualizar;
    private javax.swing.JLabel lblSubtitulo;
    private javax.swing.JLabel lblTitulo;
    private javax.swing.JPanel panelBotones;
    private javax.swing.JPanel panelEncabezado;
    private javax.swing.JPanel panelPersonalizar;
    // End of variables declaration//GEN-END:variables
}
