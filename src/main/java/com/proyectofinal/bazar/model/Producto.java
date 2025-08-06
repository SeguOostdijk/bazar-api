package com.proyectofinal.bazar.model;

import jakarta.persistence.*;

import java.util.List;

@Entity
public class Producto {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "codigo_producto")
    private Long id;
    private String nombre;
    private String marca;
    private Double costo;
    private Double cantidad_disponible;
    @OneToMany(mappedBy = "producto")
    private List<DetalleVenta> listaDetalleVentas;

    public Producto() {
    }

    public Producto(Long id, String nombre, String marca, Double costo, Double cantidad_disponible, List<DetalleVenta> listaDetalleVentas) {
        this.id = id;
        this.nombre = nombre;
        this.marca = marca;
        this.costo = costo;
        this.cantidad_disponible = cantidad_disponible;
        this.listaDetalleVentas = listaDetalleVentas;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getMarca() {
        return marca;
    }

    public void setMarca(String marca) {
        this.marca = marca;
    }

    public Double getCosto() {
        return costo;
    }

    public void setCosto(Double costo) {
        this.costo = costo;
    }

    public Double getCantidad_disponible() {
        return cantidad_disponible;
    }

    public void setCantidad_disponible(Double cantidad_disponible) {
        this.cantidad_disponible = cantidad_disponible;
    }

    public List<DetalleVenta> getListaDetalleVentas() {
        return listaDetalleVentas;
    }

    public void setListaDetalleVentas(List<DetalleVenta> listaDetalleVentas) {
        this.listaDetalleVentas = listaDetalleVentas;
    }
}
