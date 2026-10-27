package app3_Vista;

import app3_Controlador.ListaClientes;
import app3_Modelo.Cliente;
import java.awt.Color;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.time.LocalDate;
import java.time.format.DateTimeParseException;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

public class PanelNuevo extends JPanel {

    private ListaClientes listaClientes;

    private JTextField txtNumero = new JTextField(15);
    private JTextField txtNombre = new JTextField(15);
    private JTextField txtFechaNac = new JTextField(15); // Formato YYYY-MM-DD
    private JTextField txtPuntos = new JTextField(15);

    private JLabel lblError = new JLabel("", JLabel.CENTER);
    private JButton btnGuardar = new JButton("Guardar Cliente");

    public PanelNuevo(ListaClientes listaClientes) {
        this.listaClientes = listaClientes;

        // Configurar la etiqueta de error en color rojo
        lblError.setForeground(Color.RED);

        setLayout(new GridLayout(6, 2, 5, 5));

        add(new JLabel(" Número:"));
        add(txtNumero);
        add(new JLabel(" Nombre:"));
        add(txtNombre);
        add(new JLabel(" Fecha Nacimiento (AAAA-MM-DD):"));
        add(txtFechaNac);
        add(new JLabel(" Puntos (0 - 1000):"));
        add(txtPuntos);

        add(btnGuardar);
        add(lblError);

        btnGuardar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                guardarCliente();
            }
        });
    }

    private void guardarCliente() {
        lblError.setText(""); // Limpiar mensaje de error previo

        try {
            int numero = Integer.parseInt(txtNumero.getText().trim());
            String nombre = txtNombre.getText().trim();
            LocalDate fechaNac = LocalDate.parse(txtFechaNac.getText().trim());
            float puntos = Float.parseFloat(txtPuntos.getText().trim());

            if (nombre.isEmpty()) {
                lblError.setText("Error: El nombre no puede estar vacío.");
                return;
            }

            // Validar rango de fecha (1900 - 2015)
            if (!ListaClientes.validarFecha(fechaNac)) {
                lblError.setText("Error: El año debe estar entre 1900 y 2015.");
                return;
            }

            // Validar rango de puntos (0 - 1000)
            if (!ListaClientes.validarPuntos(puntos)) {
                lblError.setText("Error: Los puntos deben estar entre 0 y 1000.");
                return;
            }

            // Crear objeto y añadir a la lista
            Cliente nuevoCliente = new Cliente(numero, nombre, fechaNac, puntos);
            listaClientes.anadirCliente(nuevoCliente);
            
            // Ordenar la lista por número de cliente
            listaClientes.ordenarPorNumero();

            JOptionPane.showMessageDialog(this, "Cliente guardado correctamente.", "Éxito", JOptionPane.INFORMATION_MESSAGE);
            limpiarFormulario();

        } catch (NumberFormatException ex) {
            lblError.setText("Error: Número o puntos con formato incorrecto.");
        } catch (DateTimeParseException ex) {
            lblError.setText("Error: Formato de fecha inválido (AAAA-MM-DD).");
        }
    }

    private void limpiarFormulario() {
        txtNumero.setText("");
        txtNombre.setText("");
        txtFechaNac.setText("");
        txtPuntos.setText("");
        lblError.setText("");
    }
}