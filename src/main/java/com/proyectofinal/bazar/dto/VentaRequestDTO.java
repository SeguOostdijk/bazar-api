package com.proyectofinal.bazar.dto;

import java.util.List;
import io.swagger.v3.oas.annotations.media.Schema;

@Schema(description = "DTO para enviar datos al crear o editar una venta")
public class VentaRequestDTO {

    @Schema(description = "ID de la venta", example = "1")
    private Long id_venta;

    @Schema(description = "ID del cliente", example = "5")
    private Long id_cliente;

    @Schema(description = "Lista de productos vendidos")
    private List<VentaItemDTO> items;

    public VentaRequestDTO() {}

    public VentaRequestDTO(Long id_venta, Long id_cliente, List<VentaItemDTO> items) {
        this.id_venta = id_venta;
        this.id_cliente = id_cliente;
        this.items = items;
    }

    public Long getId_cliente() { return id_cliente; }
    public void setId_cliente(Long id_cliente) { this.id_cliente = id_cliente; }

    public List<VentaItemDTO> getItems() { return items; }
    public void setItems(List<VentaItemDTO> items) { this.items = items; }

    public Long getId_venta() { return id_venta; }
    public void setId_venta(Long id_venta) { this.id_venta = id_venta; }
}

