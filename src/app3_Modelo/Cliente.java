package app3_Modelo;

import java.time.LocalDate;

public class Cliente {
    private int numero;
    private String nombre;
    private LocalDate fechaNacimiento;
    private float puntos;

    public Cliente(int numero, String nombre, LocalDate fechaNacimiento, float puntos) {
        this.numero = numero;
        this.nombre = nombre;
        this.fechaNacimiento = fechaNacimiento;
        this.puntos = puntos;
    }

    public int getNumero() {
        return numero;
    }

    public void setNumero(int numero) {
        this.numero = numero;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public LocalDate getFechaNacimiento() {
        return fechaNacimiento;
    }

    public void setFechaNacimiento(LocalDate fechaNacimiento) {
        this.fechaNacimiento = fechaNacimiento;
    }

    public float getPuntos() {
        return puntos;
    }

    public void setPuntos(float puntos) {
        this.puntos = puntos;
    }

    @Override
    public String toString() {
        return "Cliente{" +
            "numero=" + numero +
            ", nombre='" + nombre + '\'' +
            ", fechaNacimiento=" + fechaNacimiento +
            ", puntos=" + puntos +
            '}';
    }

    
}
