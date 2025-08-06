package com.proyectofinal.bazar.dto;

import io.swagger.v3.oas.annotations.media.Schema;

import java.time.LocalDate;

@Schema(description = "Representa el monto acumulado y la cantidad de ventas en un dia determinado")
public class DatosDeVentaPorDiaDTO {
    @Schema(description = "Fecha de la venta",example = "2025-08-06")
    private LocalDate fecha;
    @Schema(description = "Monto de ventas acumulado en el dia",example = "15.000")
    private Double montoAcumulado;
    @Schema(description = "Cantidad de ventas concretadas en el dia",example = "2")
    private int cantidadVentas;


    public DatosDeVentaPorDiaDTO() {
    }

    public DatosDeVentaPorDiaDTO(LocalDate fecha, int cantidadVentas, Double montoAcumulado) {
        this.fecha = fecha;
        this.cantidadVentas = cantidadVentas;
        this.montoAcumulado = montoAcumulado;
    }

    public LocalDate getFecha() {
        return fecha;
    }

    public void setFecha(LocalDate fecha) {
        this.fecha = fecha;
    }

    public Double getMontoAcumulado() {
        return montoAcumulado;
    }

    public void setMontoAcumulado(Double montoAcumulado) {
        this.montoAcumulado = montoAcumulado;
    }

    public int getCantidadVentas() {
        return cantidadVentas;
    }

    public void setCantidadVentas(int cantidadVentas) {
        this.cantidadVentas = cantidadVentas;
    }
}
