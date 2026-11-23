/**
 * David Ureña, Lis Vasquez, Yoselin Perez, Oliver Oses, Pedro Zamora, Ethan Beltran
 */
package vista;

import archivos.GestorArchivo;
import java.awt.Color;
import java.util.List;
import javax.swing.JColorChooser;
import javax.swing.JOptionPane;
import modelo.ArbolFirmas;
import modelo.NodoFirma;

public class VentanaReportes extends javax.swing.JDialog {

    private final ArbolFirmas arbol;

    public VentanaReportes(java.awt.Frame padre, ArbolFirmas arbol) {
        super(padre, "Búsquedas, Filtros, Eliminación y Reportes", true);
        this.arbol = arbol;
        initComponents();
        setLocationRelativeTo(padre);
        actualizarEstadisticasGenerales();
    }

    public VentanaReportes() {
        this(null, new ArbolFirmas());
    }

    // Muestra estadisticas generales, severidades y recorrido InOrden
    private void actualizarEstadisticasGenerales() {
        StringBuilder sb = new StringBuilder();
        sb.append("=== ESTADÍSTICAS ACTUALES DEL ÁRBOL ===\n");
        sb.append("• Total de nodos registrados: ").append(arbol.contarNodos()).append("\n");
        sb.append("• Altura actual del árbol: ").append(arbol.calcularAltura()).append(" niveles\n");
        sb.append("• Cantidad de nodos hoja: ").append(arbol.contarHojas()).append("\n");
        sb.append("• Severidad -> 1: ").append(arbol.contarPorSeveridad(1))
          .append(" | 2: ").append(arbol.contarPorSeveridad(2))
          .append(" | 3: ").append(arbol.contarPorSeveridad(3))
          .append(" | 4: ").append(arbol.contarPorSeveridad(4)).append("\n\n");
        sb.append("=== RECORRIDO ORDENADO ALFABÉTICAMENTE (InOrden) ===\n");
        sb.append(arbol.recorridoInOrden()).append("\n");
        txtAreaInfo.setText(sb.toString());
    }

    // Imprime una lista de firmas encontradas
    private void mostrarLista(String titulo, List<NodoFirma> lista) {
        if (lista.isEmpty()) {
            txtAreaInfo.setText(titulo + "\nNo se encontraron coincidencias.");
            return;
        }
        StringBuilder sb = new StringBuilder();
        sb.append("=== ").append(titulo).append(" (").append(lista.size()).append(") ===\n\n");
        for (NodoFirma n : lista) {
            sb.append(formatearFirma(n)).append("----------------------------------------\n");
        }
        txtAreaInfo.setText(sb.toString());
    }

    // Formatea los atributos de una firma y las caracteristicas de su tipo
    private String formatearFirma(NodoFirma n) {
        return "ID Firma: " + n.getIdFirma() + "\n"
                + "Nombre: " + n.getNombreAmenaza() + "\n"
                + "Tipo: " + n.getTipo() + "\n"
                + "Características: " + NodoFirma.obtenerCaracteristicasTipo(n.getTipo()) + "\n"
                + "Severidad: " + n.getSeveridad() + "\n"
                + "Hash (64 hex): " + n.getHashFirma() + "\n"
                + "Fecha Registro: " + n.getFechaRegistro() + "\n";
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        panelSuperior = new javax.swing.JPanel();
        filaBusqueda = new javax.swing.JPanel();
        lblCriterio = new javax.swing.JLabel();
        cmbCriterio = new javax.swing.JComboBox<>();
        lblValor = new javax.swing.JLabel();
        txtBuscarNombre = new javax.swing.JTextField();
        btnBuscar = new javax.swing.JButton();
        filaAcciones = new javax.swing.JPanel();
        btnEliminar = new javax.swing.JButton();
        btnEstadisticas = new javax.swing.JButton();
        btnLimpiar = new javax.swing.JButton();
        btnColor = new javax.swing.JButton();
        scrollInfo = new javax.swing.JScrollPane();
        txtAreaInfo = new javax.swing.JTextArea();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("Búsquedas, Filtros, Eliminación y Reportes");
        setPreferredSize(new java.awt.Dimension(760, 480));

        panelSuperior.setBorder(javax.swing.BorderFactory.createTitledBorder("Consultas, Filtros y Eliminación en ABB"));
        panelSuperior.setLayout(new java.awt.GridLayout(2, 1, 5, 5));

        filaBusqueda.setLayout(new java.awt.FlowLayout(java.awt.FlowLayout.LEFT, 8, 2));

        lblCriterio.setText("Criterio:");
        filaBusqueda.add(lblCriterio);

        cmbCriterio.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Nombre exacto", "Prefijo", "ID", "Tipo", "Severidad", "Hash" }));
        filaBusqueda.add(cmbCriterio);

        lblValor.setText("Valor:");
        filaBusqueda.add(lblValor);

        txtBuscarNombre.setColumns(18);
        filaBusqueda.add(txtBuscarNombre);

        btnBuscar.setText("Buscar");
        btnBuscar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnBuscarActionPerformed(evt);
            }
        });
        filaBusqueda.add(btnBuscar);

        panelSuperior.add(filaBusqueda);

        filaAcciones.setLayout(new java.awt.FlowLayout(java.awt.FlowLayout.LEFT, 8, 2));

        btnEliminar.setForeground(new java.awt.Color(165, 30, 30));
        btnEliminar.setText("Eliminar Firma");
        btnEliminar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnEliminarActionPerformed(evt);
            }
        });
        filaAcciones.add(btnEliminar);

        btnEstadisticas.setText("Estadísticas e InOrden");
        btnEstadisticas.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnEstadisticasActionPerformed(evt);
            }
        });
        filaAcciones.add(btnEstadisticas);

        btnLimpiar.setText("Limpiar");
        btnLimpiar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnLimpiarActionPerformed(evt);
            }
        });
        filaAcciones.add(btnLimpiar);

        btnColor.setText("Color");
        btnColor.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnColorActionPerformed(evt);
            }
        });
        filaAcciones.add(btnColor);

        panelSuperior.add(filaAcciones);

        getContentPane().add(panelSuperior, java.awt.BorderLayout.NORTH);

        txtAreaInfo.setEditable(false);
        txtAreaInfo.setFont(new java.awt.Font("Monospaced", 0, 13)); // NOI18N
        txtAreaInfo.setBorder(javax.swing.BorderFactory.createEmptyBorder(10, 10, 10, 10));
        scrollInfo.setViewportView(txtAreaInfo);

        getContentPane().add(scrollInfo, java.awt.BorderLayout.CENTER);

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btnBuscarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnBuscarActionPerformed
        String criterio = (String) cmbCriterio.getSelectedItem();
        String valor = txtBuscarNombre.getText().trim();

        if (valor.isEmpty()) {
            actualizarEstadisticasGenerales();
            return;
        }

        if ("Nombre exacto".equals(criterio)) {
            NodoFirma encontrado = arbol.buscarPorNombre(valor);
            if (encontrado == null) {
                JOptionPane.showMessageDialog(this, "La amenaza '" + valor + "' no se encuentra en el ABB.");
            } else {
                txtAreaInfo.setText("=== AMENAZA LOCALIZADA EN EL ABB ===\n" + formatearFirma(encontrado));
            }
        } else if ("Prefijo".equals(criterio)) {
            mostrarLista("COINCIDENCIAS POR PREFIJO '" + valor + "'", arbol.buscarPorPrefijo(valor));
        } else if ("ID".equals(criterio)) {
            NodoFirma encontrado = arbol.buscarPorId(valor);
            if (encontrado == null) {
                JOptionPane.showMessageDialog(this, "No se encontró ninguna firma con ID '" + valor + "'.");
            } else {
                txtAreaInfo.setText("=== FIRMA LOCALIZADA POR ID ===\n" + formatearFirma(encontrado));
            }
        } else if ("Tipo".equals(criterio)) {
            mostrarLista("FIRMAS DE TIPO '" + valor + "'", arbol.buscarPorTipo(valor));
        } else if ("Severidad".equals(criterio)) {
            try {
                int sev = Integer.parseInt(valor);
                mostrarLista("FIRMAS CON SEVERIDAD " + sev, arbol.buscarPorSeveridad(sev));
            } catch (NumberFormatException ex) {
                JOptionPane.showMessageDialog(this, "La severidad debe ser un número del 1 al 4.", "Validación", JOptionPane.WARNING_MESSAGE);
            }
        } else if ("Hash".equals(criterio)) {
            mostrarLista("COINCIDENCIAS DE HASH '" + valor + "'", arbol.buscarPorHash(valor));
        }
    }//GEN-LAST:event_btnBuscarActionPerformed

    private void btnEliminarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnEliminarActionPerformed
        String nombre = txtBuscarNombre.getText().trim();
        if (nombre.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Escribe el nombre exacto de la firma a eliminar.", "Validación", JOptionPane.WARNING_MESSAGE);
            return;
        }

        int confirm = JOptionPane.showConfirmDialog(
                this,
                "¿Eliminar la firma '" + nombre + "' del ABB?",
                "Confirmar eliminación",
                JOptionPane.YES_NO_OPTION
        );

        if (confirm == JOptionPane.YES_OPTION) {
            if (arbol.eliminar(nombre)) {
                GestorArchivo.guardarEnPreOrden(arbol);
                txtBuscarNombre.setText("");
                JOptionPane.showMessageDialog(this, "Firma eliminada y guardada en firmas.csv.");
                actualizarEstadisticasGenerales();
            } else {
                JOptionPane.showMessageDialog(this, "No se encontró la firma '" + nombre + "'.");
            }
        }
    }//GEN-LAST:event_btnEliminarActionPerformed

    private void btnEstadisticasActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnEstadisticasActionPerformed
        actualizarEstadisticasGenerales();
    }//GEN-LAST:event_btnEstadisticasActionPerformed

    private void btnLimpiarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnLimpiarActionPerformed
        txtAreaInfo.setText("");
    }//GEN-LAST:event_btnLimpiarActionPerformed

    private void btnColorActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnColorActionPerformed
        Color c = JColorChooser.showDialog(this, "Color de fondo", panelSuperior.getBackground());
        if (c != null) {
            panelSuperior.setBackground(c);
            filaBusqueda.setBackground(c);
            filaAcciones.setBackground(c);
        }
    }//GEN-LAST:event_btnColorActionPerformed

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnBuscar;
    private javax.swing.JButton btnColor;
    private javax.swing.JButton btnEliminar;
    private javax.swing.JButton btnEstadisticas;
    private javax.swing.JButton btnLimpiar;
    private javax.swing.JComboBox<String> cmbCriterio;
    private javax.swing.JPanel filaAcciones;
    private javax.swing.JPanel filaBusqueda;
    private javax.swing.JLabel lblCriterio;
    private javax.swing.JLabel lblValor;
    private javax.swing.JPanel panelSuperior;
    private javax.swing.JScrollPane scrollInfo;
    private javax.swing.JTextArea txtAreaInfo;
    private javax.swing.JTextField txtBuscarNombre;
    // End of variables declaration//GEN-END:variables
}
