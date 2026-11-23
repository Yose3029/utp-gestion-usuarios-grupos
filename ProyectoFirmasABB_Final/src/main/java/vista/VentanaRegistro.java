/**
 * David Ureña, Lis Vasquez, Yoselin Perez, Oliver Oses, Pedro Zamora, Ethan Beltran
 */
package vista;

import archivos.GestorArchivo;
import java.awt.Color;
import java.time.LocalDate;
import javax.swing.JColorChooser;
import javax.swing.JOptionPane;
import modelo.ArbolFirmas;
import modelo.NodoFirma;

public class VentanaRegistro extends javax.swing.JDialog {

    private final ArbolFirmas arbol;

    public VentanaRegistro(java.awt.Frame padre, ArbolFirmas arbol) {
        super(padre, "Registrar Nueva Firma en ABB", true);
        this.arbol = arbol;
        initComponents();
        setLocationRelativeTo(padre);
        txtId.setText(arbol.generarSiguienteId());
        txtFecha.setText(LocalDate.now().toString());
        actualizarInfoTipo();
    }

    public VentanaRegistro() {
        this(null, new ArbolFirmas());
    }

    // Actualiza la descripcion del tipo de malware elegido
    private void actualizarInfoTipo() {
        String tipo = (String) cmbTipo.getSelectedItem();
        lblInfoTipo.setText("<html><b>Características del tipo:</b> " + NodoFirma.obtenerCaracteristicasTipo(tipo) + "</html>");
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        panelCentro = new javax.swing.JPanel();
        panelCampos = new javax.swing.JPanel();
        lblId = new javax.swing.JLabel();
        txtId = new javax.swing.JTextField();
        lblNombre = new javax.swing.JLabel();
        txtNombre = new javax.swing.JTextField();
        lblTipo = new javax.swing.JLabel();
        cmbTipo = new javax.swing.JComboBox<>();
        lblSeveridad = new javax.swing.JLabel();
        cmbSeveridad = new javax.swing.JComboBox<>();
        lblHash = new javax.swing.JLabel();
        txtHash = new javax.swing.JTextField();
        lblFecha = new javax.swing.JLabel();
        txtFecha = new javax.swing.JTextField();
        lblInfoTipo = new javax.swing.JLabel();
        panelBotones = new javax.swing.JPanel();
        btnGuardar = new javax.swing.JButton();
        btnCancelar = new javax.swing.JButton();
        btnColor = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("Registrar Nueva Firma en ABB");
        setPreferredSize(new java.awt.Dimension(520, 410));

        panelCentro.setLayout(new java.awt.BorderLayout(6, 6));

        panelCampos.setBorder(javax.swing.BorderFactory.createEmptyBorder(15, 20, 10, 20));
        panelCampos.setLayout(new java.awt.GridLayout(6, 2, 8, 8));

        lblId.setText("ID asignado:");
        panelCampos.add(lblId);

        txtId.setEditable(false);
        panelCampos.add(txtId);

        lblNombre.setText("Nombre de Amenaza (Clave ABB):");
        panelCampos.add(lblNombre);
        panelCampos.add(txtNombre);

        lblTipo.setText("Tipo de Amenaza:");
        panelCampos.add(lblTipo);

        cmbTipo.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Troyano", "Ransomware", "Gusano", "Botnet", "Spyware" }));
        cmbTipo.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cmbTipoActionPerformed(evt);
            }
        });
        panelCampos.add(cmbTipo);

        lblSeveridad.setText("Severidad (1-4):");
        panelCampos.add(lblSeveridad);

        cmbSeveridad.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "1 - Baja", "2 - Media", "3 - Alta", "4 - Crítica" }));
        cmbSeveridad.setSelectedIndex(2);
        panelCampos.add(cmbSeveridad);

        lblHash.setText("Hash Firma (64 hex):");
        panelCampos.add(lblHash);

        txtHash.setText("a1b2c3d4e5f60718293a4b5c6d7e8f90a1b2c3d4e5f60718293a4b5c6d7e8f90");
        panelCampos.add(txtHash);

        lblFecha.setText("Fecha (AAAA-MM-DD):");
        panelCampos.add(lblFecha);
        panelCampos.add(txtFecha);

        panelCentro.add(panelCampos, java.awt.BorderLayout.CENTER);

        lblInfoTipo.setFont(new java.awt.Font("SansSerif", 2, 11)); // NOI18N
        lblInfoTipo.setBorder(javax.swing.BorderFactory.createEmptyBorder(0, 20, 8, 20));
        panelCentro.add(lblInfoTipo, java.awt.BorderLayout.SOUTH);

        getContentPane().add(panelCentro, java.awt.BorderLayout.CENTER);

        btnGuardar.setText("Insertar en ABB");
        btnGuardar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnGuardarActionPerformed(evt);
            }
        });
        panelBotones.add(btnGuardar);

        btnCancelar.setText("Cerrar");
        btnCancelar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnCancelarActionPerformed(evt);
            }
        });
        panelBotones.add(btnCancelar);

        btnColor.setText("Color");
        btnColor.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnColorActionPerformed(evt);
            }
        });
        panelBotones.add(btnColor);

        getContentPane().add(panelBotones, java.awt.BorderLayout.SOUTH);

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void cmbTipoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cmbTipoActionPerformed
        actualizarInfoTipo();
    }//GEN-LAST:event_cmbTipoActionPerformed

    private void btnGuardarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnGuardarActionPerformed
        String id = txtId.getText().trim();
        String nombre = txtNombre.getText().trim();
        String tipo = (String) cmbTipo.getSelectedItem();
        int severidad = cmbSeveridad.getSelectedIndex() + 1;
        String hash = txtHash.getText().trim();
        String fecha = txtFecha.getText().trim();

        if (nombre.isEmpty()) {
            JOptionPane.showMessageDialog(this, "El nombre de la amenaza no puede estar vacío.", "Validación", JOptionPane.WARNING_MESSAGE);
            return;
        }
        if (!GestorArchivo.validarHashHexadecimal(hash)) {
            JOptionPane.showMessageDialog(this, "El valor de firma (hash) debe tener exactamente 64 caracteres hexadecimales (0-9, a-f).", "Validación", JOptionPane.WARNING_MESSAGE);
            return;
        }
        if (!GestorArchivo.validarFecha(fecha)) {
            JOptionPane.showMessageDialog(this, "La fecha debe cumplir el formato AAAA-MM-DD.", "Validación", JOptionPane.WARNING_MESSAGE);
            return;
        }

        NodoFirma nuevo = new NodoFirma(id, nombre, tipo, severidad, hash.toLowerCase(), fecha);
        if (!arbol.insertar(nuevo)) {
            JOptionPane.showMessageDialog(this, "No se pudo insertar: La amenaza '" + nombre + "' ya existe en el ABB.", "Clave Duplicada", JOptionPane.ERROR_MESSAGE);
            return;
        }

        GestorArchivo.guardarEnPreOrden(arbol);
        JOptionPane.showMessageDialog(this, "Firma insertada en el ABB y actualizada en firmas.csv (PreOrden).");
        dispose();
    }//GEN-LAST:event_btnGuardarActionPerformed

    private void btnCancelarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCancelarActionPerformed
        dispose();
    }//GEN-LAST:event_btnCancelarActionPerformed

    private void btnColorActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnColorActionPerformed
        Color c = JColorChooser.showDialog(this, "Color de fondo", panelCampos.getBackground());
        if (c != null) {
            panelCentro.setBackground(c);
            panelCampos.setBackground(c);
            panelBotones.setBackground(c);
        }
    }//GEN-LAST:event_btnColorActionPerformed

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnCancelar;
    private javax.swing.JButton btnColor;
    private javax.swing.JButton btnGuardar;
    private javax.swing.JComboBox<String> cmbSeveridad;
    private javax.swing.JComboBox<String> cmbTipo;
    private javax.swing.JLabel lblFecha;
    private javax.swing.JLabel lblHash;
    private javax.swing.JLabel lblId;
    private javax.swing.JLabel lblInfoTipo;
    private javax.swing.JLabel lblNombre;
    private javax.swing.JLabel lblSeveridad;
    private javax.swing.JLabel lblTipo;
    private javax.swing.JPanel panelBotones;
    private javax.swing.JPanel panelCampos;
    private javax.swing.JPanel panelCentro;
    private javax.swing.JTextField txtFecha;
    private javax.swing.JTextField txtHash;
    private javax.swing.JTextField txtId;
    private javax.swing.JTextField txtNombre;
    // End of variables declaration//GEN-END:variables
}
