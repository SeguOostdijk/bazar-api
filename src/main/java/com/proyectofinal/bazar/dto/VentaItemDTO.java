package com.proyectofinal.bazar.dto;

import io.swagger.v3.oas.annotations.media.Schema;

@Schema(description = "Representa un item incluido en una venta")
public class VentaItemDTO {

    @Schema(description = "ID del producto", example = "1")
    private Long id_producto;
    @Schema(description = "Cantidad solicitada por el cliente", example = "2")
    private Integer cantidad;

    public VentaItemDTO() {
    }

    public VentaItemDTO(Long id_producto, Integer cantidad) {
        this.id_producto = id_producto;
        this.cantidad = cantidad;
    }

    public Long getId_producto() {
        return id_producto;
    }

    public void setId_producto(Long id_producto) {
        this.id_producto = id_producto;
    }

    public Integer getCantidad() {
        return cantidad;
    }

    public void setCantidad(Integer cantidad) {
        this.cantidad = cantidad;
    }
}
