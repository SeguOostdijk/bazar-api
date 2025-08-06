package com.proyectofinal.bazar.dto;

import io.swagger.v3.oas.annotations.media.Schema;

@Schema(description = "Representa un producto del bazar")
public class ProductoDTO {

    @Schema(description = "ID del producto",example = "1")
    private Long id;
    @Schema(description = "Nombre",example = "Cafetera")
    private String nombre;
    @Schema(description = "Marca",example = "Nespresso")
    private String marca;
    @Schema(description = "Costo",example = "7500.00")
    private Double costo;
    @Schema(description = "Stock",example = "25")
    private Double cantidad_disponible;

    public ProductoDTO() {
    }

    public ProductoDTO(Long id, String nombre, String marca, Double costo, Double cantidad_disponible) {
        this.id = id;
        this.nombre = nombre;
        this.marca = marca;
        this.costo = costo;
        this.cantidad_disponible = cantidad_disponible;
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

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }
}
