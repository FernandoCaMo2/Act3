package app3_Vista;

import app3_Controlador.ListaClientes;
import java.awt.CardLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPanel;

public class VentanaPrincipal extends JFrame {

    private ListaClientes listaClientes;
    private PanelVisualizar panelVisualizar;
    private PanelNuevo panelNuevo;

    // Gestor de diseño CardLayout y contenedor principal de tarjetas
    private CardLayout cardLayout;
    private JPanel panelContenedor;

    // Identificadores (claves) para cada tarjeta
    public static final String PANEL_VISUALIZAR = "VISUALIZAR";
    public static final String PANEL_NUEVO = "NUEVO";

    public VentanaPrincipal() {
        listaClientes = new ListaClientes();

        panelVisualizar = new PanelVisualizar(listaClientes);
        panelNuevo = new PanelNuevo(listaClientes);

        setTitle("Gestión de Clientes - MVC");
        setSize(450, 300);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setResizable(false); // No redimensionable según enunciado

        // Inicializar el CardLayout y el contenedor principal
        cardLayout = new CardLayout();
        panelContenedor = new JPanel(cardLayout);

        // Añadir las vistas al contenedor asignándoles su identificador de tarjeta
        panelContenedor.add(panelVisualizar, PANEL_VISUALIZAR);
        panelContenedor.add(panelNuevo, PANEL_NUEVO);

        // Añadir el panel contenedor al JFrame
        add(panelContenedor);

        // Crear la barra de menú
        JMenuBar menuBar = new JMenuBar();
        JMenu menuOpciones = new JMenu("Opciones");

        JMenuItem itemVisualizar = new JMenuItem("Visualizar");
        JMenuItem itemNuevo = new JMenuItem("Nuevo");
        JMenuItem itemSalir = new JMenuItem("Salir");

        menuOpciones.add(itemVisualizar);
        menuOpciones.add(itemNuevo);
        menuOpciones.addSeparator();
        menuOpciones.add(itemSalir);

        menuBar.add(menuOpciones);
        setJMenuBar(menuBar);

        // Acciones del menú
        itemVisualizar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                panelVisualizar.actualizarCampos();
                cardLayout.show(panelContenedor, PANEL_VISUALIZAR);
            }
        });

        itemNuevo.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                cardLayout.show(panelContenedor, PANEL_NUEVO);
            }
        });

        itemSalir.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.exit(0);
            }
        });
    }
}