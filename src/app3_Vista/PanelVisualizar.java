package app3_Vista;

import app3_Controlador.ListaClientes;
import app3_Modelo.Cliente;
import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

public class PanelVisualizar extends JPanel {

    private ListaClientes listaClientes;

    // Campos de texto para mostrar los datos del cliente (solo lectura)
    private JTextField txtNumero = new JTextField(15);
    private JTextField txtNombre = new JTextField(15);
    private JTextField txtFechaNac = new JTextField(15);
    private JTextField txtPuntos = new JTextField(15);

    // Botones de navegación y acción
    private JButton btnPrimero = new JButton("<< Primero");
    private JButton btnAnterior = new JButton("< Anterior");
    private JButton btnSiguiente = new JButton("Siguiente >");
    private JButton btnUltimo = new JButton("Último >>");
    private JButton btnBorrar = new JButton("Borrar Cliente");

    public PanelVisualizar(ListaClientes listaClientes) {
        this.listaClientes = listaClientes;

        // Deshabilitar la edición directa en los campos de visualización
        txtNumero.setEditable(false);
        txtNombre.setEditable(false);
        txtFechaNac.setEditable(false);
        txtPuntos.setEditable(false);

        // Usamos BorderLayout para el panel principal
        setLayout(new BorderLayout(10, 10));

        // 1. PANEL SUPERIOR (Datos del cliente: 4 filas x 2 columnas)
        JPanel panelDatos = new JPanel(new GridLayout(4, 2, 5, 5));
        panelDatos.add(new JLabel(" Número:"));
        panelDatos.add(txtNumero);
        panelDatos.add(new JLabel(" Nombre:"));
        panelDatos.add(txtNombre);
        panelDatos.add(new JLabel(" Fecha Nacimiento:"));
        panelDatos.add(txtFechaNac);
        panelDatos.add(new JLabel(" Puntos:"));
        panelDatos.add(txtPuntos);

        // 2. PANEL INFERIOR (Botones de navegación: 3 filas x 2 columnas)
        JPanel panelBotones = new JPanel(new GridLayout(3, 2, 5, 5));
        panelBotones.add(btnPrimero);
        panelBotones.add(btnUltimo);
        panelBotones.add(btnAnterior);
        panelBotones.add(btnSiguiente);
        
        // El botón de borrar ocupa la última fila
        panelBotones.add(btnBorrar);

        // Añadimos los subpaneles a la ventana principal
        add(panelDatos, BorderLayout.CENTER);
        add(panelBotones, BorderLayout.SOUTH);

        // --- Eventos de los botones ---
        btnPrimero.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                listaClientes.irAlPrimero();
                actualizarCampos();
            }
        });

        btnAnterior.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                listaClientes.retroceder();
                actualizarCampos();
            }
        });

        btnSiguiente.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                listaClientes.avanzar();
                actualizarCampos();
            }
        });

        btnUltimo.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                listaClientes.irAlUltimo();
                actualizarCampos();
            }
        });

        btnBorrar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (listaClientes.esVacia()) {
                    JOptionPane.showMessageDialog(PanelVisualizar.this, "La lista está vacía.", "Aviso", JOptionPane.WARNING_MESSAGE);
                    return;
                }
                
                int confirm = JOptionPane.showConfirmDialog(PanelVisualizar.this, "¿Estás seguro de borrar el cliente actual?", "Confirmar borrado", JOptionPane.YES_NO_OPTION);
                if (confirm == JOptionPane.YES_OPTION) {
                    listaClientes.borrarCliente();
                    actualizarCampos();
                }
            }
        });

        actualizarCampos();
    }

    public void actualizarCampos() {
        if (listaClientes.esVacia() || listaClientes.obtenerClienteActual() == null) {
            txtNumero.setText("");
            txtNombre.setText("");
            txtFechaNac.setText("");
            txtPuntos.setText("");
            
            btnPrimero.setEnabled(false);
            btnAnterior.setEnabled(false);
            btnSiguiente.setEnabled(false);
            btnUltimo.setEnabled(false);
            btnBorrar.setEnabled(false);
        } else {
            Cliente actual = listaClientes.obtenerClienteActual();
            txtNumero.setText(String.valueOf(actual.getNumero()));
            txtNombre.setText(actual.getNombre());
            txtFechaNac.setText(actual.getFechaNacimiento().toString());
            txtPuntos.setText(String.valueOf(actual.getPuntos()));

            btnPrimero.setEnabled(!listaClientes.esPrimero());
            btnAnterior.setEnabled(!listaClientes.esPrimero());
            btnSiguiente.setEnabled(!listaClientes.esUltimo());
            btnUltimo.setEnabled(!listaClientes.esUltimo());
            btnBorrar.setEnabled(true);
        }
    }
}