package app3_Controlador;

import app3_Modelo.Cliente;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

public class ListaClientes {

    // La lista de clientes ahora es un atributo interno en lugar de ser heredada
    private ArrayList<Cliente> lista;
    private int actual; // Índice del cliente actual

    public ListaClientes() {
        this.lista = new ArrayList<>();
        this.actual = -1; // -1 indica que la lista está vacía
    }

    public ArrayList<Cliente> getLista() {
        return lista;
    }

    public int getActual() {
        return actual;
    }

    public void setActual(int actual) {
        this.actual = actual;
    }

    // Método para obtener el cliente actual
    public Cliente obtenerClienteActual() {
        if (lista.isEmpty() || actual < 0 || actual >= lista.size()) {
            return null;
        }
        return lista.get(actual);
    }

    // Comprobar si la lista está vacía
    public boolean esVacia() {
        return lista.isEmpty();
    }

    // Métodos de navegación
    public boolean avanzar() {
        if (actual < lista.size() - 1) {
            actual++;
            return true;
        }
        return false;
    }

    public boolean retroceder() {
        if (actual > 0) {
            actual--;
            return true;
        }
        return false;
    }

    public void irAlPrimero() {
        if (!lista.isEmpty()) {
            actual = 0;
        }
    }

    public void irAlUltimo() {
        if (!lista.isEmpty()) {
            actual = lista.size() - 1;
        }
    }

    public boolean esPrimero() {
        return actual == 0;
    }

    public boolean esUltimo() {
        return actual == lista.size() - 1;
    }

    // Añadir y borrar cliente
    public void anadirCliente(Cliente cliente) {
        lista.add(cliente);
        actual = lista.size() - 1; // Apuntar al recién añadido
    }

    public boolean borrarCliente() {
        if (!lista.isEmpty() && actual >= 0 && actual < lista.size()) {
            lista.remove(actual);
            if (lista.isEmpty()) {
                actual = -1;
            } else if (actual >= lista.size()) {
                actual = lista.size() - 1;
            }
            return true;
        }
        return false;
    }

    // Ordenar lista por número de cliente (Forma tradicional y clara)
    public void ordenarPorNumero() {
        Collections.sort(lista, new Comparator<Cliente>() {
            @Override
            public int compare(Cliente c1, Cliente c2) {
                if (c1.getNumero() < c2.getNumero()) {
                    return -1; // c1 es menor
                } else if (c1.getNumero() > c2.getNumero()) {
                    return 1;  // c1 es mayor
                } else {
                    return 0;  // son iguales
                }
            }
        });
    }

    // Validación de Fecha (1900 a 2015)
    public static boolean validarFecha(LocalDate fecha) {
        if (fecha == null) return false;
        int anio = fecha.getYear();
        return anio >= 1900 && anio <= 2015;
    }

    // Validación de Puntos (0 a 1000)
    public static boolean validarPuntos(float puntos) {
        return puntos >= 0 && puntos <= 1000;
    }
}