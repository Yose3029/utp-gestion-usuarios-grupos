/**
 * David Ureña, Lis Vasquez, Yoselin Perez, Oliver Oses, Pedro Zamora, Ethan Beltran
 */
package vista;

import java.awt.BorderLayout;
import java.awt.Color;
import javax.swing.JColorChooser;
import modelo.ArbolFirmas;

public class VentanaVisualizacion extends javax.swing.JDialog {

    private final ArbolFirmas arbol;
    private final PanelArbol panelArbol;

    public VentanaVisualizacion(java.awt.Frame padre, ArbolFirmas arbol) {
        super(padre, "Visualización Gráfica del ABB y Recorridos", true);
        this.arbol = arbol;
        initComponents();
        setLocationRelativeTo(padre);

        panelArbol = new PanelArbol(arbol);
        panelContenedorArbol.add(panelArbol, BorderLayout.CENTER);
    }

    public VentanaVisualizacion() {
        this(null, new ArbolFirmas());
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        panelContenedorArbol = new javax.swing.JPanel();
        panelInferior = new javax.swing.JPanel();
        panelBotones = new javax.swing.JPanel();
        btnInOrden = new javax.swing.JButton();
        btnPreOrden = new javax.swing.JButton();
        btnPostOrden = new javax.swing.JButton();
        btnColor = new javax.swing.JButton();
        scrollRecorrido = new javax.swing.JScrollPane();
        txtRecorrido = new javax.swing.JTextArea();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("Visualización Gráfica del ABB y Recorridos");
        setPreferredSize(new java.awt.Dimension(820, 540));

        panelContenedorArbol.setLayout(new java.awt.BorderLayout());
        getContentPane().add(panelContenedorArbol, java.awt.BorderLayout.CENTER);

        panelInferior.setLayout(new java.awt.BorderLayout(5, 5));

        btnInOrden.setText("InOrden (Alfabético)");
        btnInOrden.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnInOrdenActionPerformed(evt);
            }
        });
        panelBotones.add(btnInOrden);

        btnPreOrden.setText("PreOrden (Persistencia)");
        btnPreOrden.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnPreOrdenActionPerformed(evt);
            }
        });
        panelBotones.add(btnPreOrden);

        btnPostOrden.setText("PostOrden");
        btnPostOrden.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnPostOrdenActionPerformed(evt);
            }
        });
        panelBotones.add(btnPostOrden);

        btnColor.setText("Color Lienzo");
        btnColor.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnColorActionPerformed(evt);
            }
        });
        panelBotones.add(btnColor);

        panelInferior.add(panelBotones, java.awt.BorderLayout.NORTH);

        txtRecorrido.setEditable(false);
        txtRecorrido.setColumns(60);
        txtRecorrido.setLineWrap(true);
        txtRecorrido.setRows(3);
        txtRecorrido.setText("Seleccione un recorrido para mostrar el orden de visita de los nodos...");
        txtRecorrido.setWrapStyleWord(true);
        scrollRecorrido.setViewportView(txtRecorrido);

        panelInferior.add(scrollRecorrido, java.awt.BorderLayout.CENTER);

        getContentPane().add(panelInferior, java.awt.BorderLayout.SOUTH);

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btnInOrdenActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnInOrdenActionPerformed
        txtRecorrido.setText("InOrden: " + arbol.recorridoInOrden());
    }//GEN-LAST:event_btnInOrdenActionPerformed

    private void btnPreOrdenActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnPreOrdenActionPerformed
        txtRecorrido.setText("PreOrden: " + arbol.recorridoPreOrden());
    }//GEN-LAST:event_btnPreOrdenActionPerformed

    private void btnPostOrdenActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnPostOrdenActionPerformed
        txtRecorrido.setText("PostOrden: " + arbol.recorridoPostOrden());
    }//GEN-LAST:event_btnPostOrdenActionPerformed

    private void btnColorActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnColorActionPerformed
        Color c = JColorChooser.showDialog(this, "Elige color del lienzo", panelArbol.getBackground());
        if (c != null) {
            panelArbol.setBackground(c);
            panelArbol.repaint();
        }
    }//GEN-LAST:event_btnColorActionPerformed

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnColor;
    private javax.swing.JButton btnInOrden;
    private javax.swing.JButton btnPostOrden;
    private javax.swing.JButton btnPreOrden;
    private javax.swing.JPanel panelBotones;
    private javax.swing.JPanel panelContenedorArbol;
    private javax.swing.JPanel panelInferior;
    private javax.swing.JScrollPane scrollRecorrido;
    private javax.swing.JTextArea txtRecorrido;
    // End of variables declaration//GEN-END:variables
}
