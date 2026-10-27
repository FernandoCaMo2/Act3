package app3_psp;

import app3_Vista.VentanaPrincipal;
import javax.swing.SwingUtilities;

public class App3_PSP {

    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                VentanaPrincipal ventana = new VentanaPrincipal();
                ventana.setVisible(true);
            }
        });
    }
}