/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.servidor_binario;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

/**
 *
 * @author PC
 */
public class Producto {

    private String clave;
    private String nombre;
    private double precio;
    private int cantidad;
    private String marca;
    private LocalDate fechaReg;

    private static final DateTimeFormatter formatter =
            DateTimeFormatter.ofPattern("dd/MM/yyyy");

    public Producto(String clave, String nombre, double precio,
                    int cantidad, String marca, LocalDate fechaReg) {
        this.clave = clave;
        this.nombre = nombre;
        this.precio = precio;
        this.cantidad = cantidad;
        this.marca = marca;
        this.fechaReg = fechaReg;
    }

    // ===== Getters =====

    public String getClave() { return clave; }
    public String getNombre() { return nombre; }
    public double getPrecio() { return precio; }
    public int getCantidad() { return cantidad; }
    public String getMarca() { return marca; }
    public LocalDate getFechaReg() { return fechaReg; }

    // ===== TEXTO =====

    public String toTextFormat() {
        return clave + "|" + nombre + "|" + precio + "|" +
               cantidad + "|" + marca + "|" +
               fechaReg.format(formatter);
    }

    public static Producto fromText(String data) {
        String[] parts = data.split("\\|");
        return new Producto(
                parts[0],
                parts[1],
                Double.parseDouble(parts[2]),
                Integer.parseInt(parts[3]),
                parts[4],
                LocalDate.parse(parts[5], formatter)
        );
    }

    @Override
    public String toString() {
        return toTextFormat();
    }
}
