package com.proyectofinal.bazar.model;

import jakarta.persistence.*;

import java.time.LocalDate;
import java.util.List;

@Entity
public class Venta {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "codigo_venta")
    private Long id;
    private LocalDate fecha_venta;
    private Double total;
    @OneToMany(mappedBy = "venta", cascade = CascadeType.ALL,orphanRemoval = true )
    private List<DetalleVenta> listaDetalleVentas;
    @ManyToOne
    @JoinColumn(name = "cliente_id")
    private Cliente cliente;

    public Venta() {
    }

    public Venta(Long id, LocalDate fecha_venta, Double total, List<DetalleVenta> listaDetalleVentas, Cliente cliente) {
        this.id = id;
        this.fecha_venta = fecha_venta;
        this.total = total;
        this.listaDetalleVentas = listaDetalleVentas;
        this.cliente = cliente;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public LocalDate getFecha_venta() {
        return fecha_venta;
    }

    public void setFecha_venta(LocalDate fecha_venta) {
        this.fecha_venta = fecha_venta;
    }

    public List<DetalleVenta> getListaDetalleVentas() {
        return listaDetalleVentas;
    }

    public void setListaDetalleVentas(List<DetalleVenta> listaDetalleVentas) {
        this.listaDetalleVentas = listaDetalleVentas;
    }

    public Double getTotal() {
        return total;
    }

    public void setTotal(Double total) {
        this.total = total;
    }

    public Cliente getCliente() {
        return cliente;
    }

    public void setCliente(Cliente cliente) {
        this.cliente = cliente;
    }
}
