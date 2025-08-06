package com.proyectofinal.bazar.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import java.time.LocalDate;
import java.util.List;

@Schema(description = "DTO que representa la respuesta al consultar una venta")
public class VentaResponseDTO {

    @Schema(description = "ID de la venta", example = "1")
    private Long id_venta;

    @Schema(description = "ID del cliente", example = "4")
    private Long id_cliente;

    @Schema(description = "Nombre del cliente", example = "Laura")
    private String nombre;

    @Schema(description = "Apellido del cliente", example = "Gómez")
    private String apellido;

    @Schema(description = "Fecha de la venta", example = "2025-08-05")
    private LocalDate fecha;

    @Schema(description = "Total de la venta", example = "38000.0")
    private Double total;

    @Schema(description = "Detalles de la venta")
    private List<DetalleVentaDTO> detalles;

    public VentaResponseDTO() {}

    public VentaResponseDTO(Long id_venta, Long id_cliente, String nombre, String apellido, LocalDate fecha, Double total, List<DetalleVentaDTO> detalles) {
        this.id_venta = id_venta;
        this.id_cliente = id_cliente;
        this.nombre = nombre;
        this.apellido = apellido;
        this.fecha = fecha;
        this.total = total;
        this.detalles = detalles;
    }

    public Long getId_venta() { return id_venta; }
    public void setId_venta(Long id_venta) { this.id_venta = id_venta; }

    public Long getId_cliente() { return id_cliente; }
    public void setId_cliente(Long id_cliente) { this.id_cliente = id_cliente; }

    public LocalDate getFecha() { return fecha; }
    public void setFecha(LocalDate fecha) { this.fecha = fecha; }

    public Double getTotal() { return total; }
    public void setTotal(Double total) { this.total = total; }

    public List<DetalleVentaDTO> getDetalles() { return detalles; }
    public void setDetalles(List<DetalleVentaDTO> detalles) { this.detalles = detalles; }

    public String getNombre() { return nombre; }
    public void setNombre(String nombre) { this.nombre = nombre; }

    public String getApellido() { return apellido; }
    public void setApellido(String apellido) { this.apellido = apellido; }
}