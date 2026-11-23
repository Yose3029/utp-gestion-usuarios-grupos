/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */


/**
 *
 * David Ureña, Lis Vasquez, Yoselin Perez, Oliver Oses, Pedro Zamora, Ethan Beltran
 */

package vista;

import java.awt.Color;
import java.awt.Font;
import java.awt.FontMetrics;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import javax.swing.JPanel;
import modelo.ArbolFirmas;
import modelo.NodoFirma;

public class PanelArbol extends JPanel {
    private final ArbolFirmas arbol;
    private static final int RADIO = 26;
    private static final int DISTANCIA_VERTICAL = 75;

    public PanelArbol(ArbolFirmas arbol) {
        this.arbol = arbol;
        setBackground(Color.WHITE);
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2 = (Graphics2D) g;
        g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

        NodoFirma raiz = arbol.getRaiz();
        if (raiz == null) {
            g2.setFont(new Font("SansSerif", Font.BOLD, 14));
            g2.drawString("El Árbol Binario de Búsqueda está vacío.", 30, 40);
            return;
        }

        dibujarNodo(g2, raiz, getWidth() / 2, 45, Math.max(getWidth() / 4, 60), 1);
    }

    private void dibujarNodo(Graphics2D g2, NodoFirma nodo, int x, int y, int separacionH, int nivel) {
        if (nodo.getIzquierdo() != null) {
            int xIzq = x - separacionH;
            int yIzq = y + DISTANCIA_VERTICAL;
            g2.setColor(Color.DARK_GRAY);
            g2.drawLine(x, y, xIzq, yIzq);
            dibujarNodo(g2, nodo.getIzquierdo(), xIzq, yIzq, Math.max(separacionH / 2, 35), nivel + 1);
        }

        if (nodo.getDerecho() != null) {
            int xDer = x + separacionH;
            int yDer = y + DISTANCIA_VERTICAL;
            g2.setColor(Color.DARK_GRAY);
            g2.drawLine(x, y, xDer, yDer);
            dibujarNodo(g2, nodo.getDerecho(), xDer, yDer, Math.max(separacionH / 2, 35), nivel + 1);
        }

        // Círculo del nodo
        g2.setColor(new Color(225, 238, 255));
        g2.fillOval(x - RADIO, y - RADIO, RADIO * 2, RADIO * 2);
        g2.setColor(new Color(40, 80, 140));
        g2.drawOval(x - RADIO, y - RADIO, RADIO * 2, RADIO * 2);

        // Nombre de la amenaza y su ID
        g2.setColor(Color.BLACK);
        g2.setFont(new Font("SansSerif", Font.BOLD, 11));
        FontMetrics fm = g2.getFontMetrics();
        String nombre = nodo.getNombreAmenaza();
        g2.drawString(nombre, x - fm.stringWidth(nombre) / 2, y - 2);

        g2.setFont(new Font("SansSerif", Font.PLAIN, 9));
        FontMetrics fmId = g2.getFontMetrics();
        String id = nodo.getIdFirma() + " (N" + nivel + ")";
        g2.drawString(id, x - fmId.stringWidth(id) / 2, y + 12);
    }
}
