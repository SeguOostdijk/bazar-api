package com.proyectofinal.bazar.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.v3.oas.annotations.media.Schema;

@Schema(description = "Representa la venta con mayor monto total registrado")
public class MayorVentaDTO {
    @Schema(description = "ID de la venta",example = "1")
    private Long id_venta;
    @Schema(description = "Monto total de la venta",example = "20.750.00")
    private Double total;
    @Schema(description = "Cantidad de productos que incluye la venta",example = "4")
    private int cantidadProductos;
    @JsonProperty("nombre_apellido")
    @Schema(description = "Nombre y apellido del cliente",example = "Juan Perez")
    private String nomApeCliente;

    public MayorVentaDTO() {
    }

    public MayorVentaDTO(int cantidadProductos, Double total, Long id_venta, String nomApeCliente) {
        this.cantidadProductos = cantidadProductos;
        this.total = total;
        this.id_venta = id_venta;
        this.nomApeCliente = nomApeCliente;
    }

    public Long getId_venta() {
        return id_venta;
    }

    public void setId_venta(Long id_venta) {
        this.id_venta = id_venta;
    }

    public Double getTotal() {
        return total;
    }

    public void setTotal(Double total) {
        this.total = total;
    }

    public int getCantidadProductos() {
        return cantidadProductos;
    }

    public void setCantidadProductos(int cantidadProductos) {
        this.cantidadProductos = cantidadProductos;
    }

    public String getNomApeCliente() {
        return nomApeCliente;
    }

    public void setNomApeCliente(String nomApeCliente) {
        this.nomApeCliente = nomApeCliente;
    }
}
