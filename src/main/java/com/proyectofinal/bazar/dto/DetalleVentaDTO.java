package com.proyectofinal.bazar.dto;

import io.swagger.v3.oas.annotations.media.Schema;

@Schema(description = "Representa el detalle de una venta")
public class DetalleVentaDTO {
    @Schema(description = "ID del producto",example = "1")
    private Long id_producto;
    @Schema(description = "Nombre del producto",example = "Cafetera")
    private String nombreProducto;
    @Schema(description = "Cantidad solicitada por el cliente",example = "2")
    private Integer cantidad;
    @Schema(description = "Subtotal de la venta por producto",example = "15000.00")
    private Double subtotal;

    public DetalleVentaDTO() {
    }

    public DetalleVentaDTO(Long id_producto, Integer cantidad, String nombreProducto, Double subtotal) {
        this.id_producto = id_producto;
        this.cantidad = cantidad;
        this.nombreProducto = nombreProducto;
        this.subtotal = subtotal;
    }

    public Long getId_producto() {
        return id_producto;
    }

    public void setId_producto(Long id_producto) {
        this.id_producto = id_producto;
    }

    public String getNombreProducto() {
        return nombreProducto;
    }

    public void setNombreProducto(String nombreProducto) {
        this.nombreProducto = nombreProducto;
    }

    public Integer getCantidad() {
        return cantidad;
    }

    public void setCantidad(Integer cantidad) {
        this.cantidad = cantidad;
    }

    public Double getSubtotal() {
        return subtotal;
    }

    public void setSubtotal(Double subtotal) {
        this.subtotal = subtotal;
    }
}
