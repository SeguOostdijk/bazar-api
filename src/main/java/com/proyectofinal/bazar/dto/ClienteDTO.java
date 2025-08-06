package com.proyectofinal.bazar.dto;

import io.swagger.v3.oas.annotations.media.Schema;

@Schema(description = "DTO que representa a un cliente del bazar")
public class ClienteDTO {

    @Schema(description = "ID único del cliente")
    private Long id;

    @Schema(description = "Nombre del cliente", example = "Juan")
    private String nombre;

    @Schema(description = "Apellido del cliente", example = "Pérez")
    private String apellido;

    @Schema(description = "Número de DNI del cliente", example = "12345678")
    private String dni;

    // constructor + getters/setters

    public ClienteDTO() {
    }

    public ClienteDTO(Long id, String nombre, String apellido, String dni) {
        this.id = id;
        this.nombre = nombre;
        this.apellido = apellido;
        this.dni = dni;
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

    public String getApellido() {
        return apellido;
    }

    public void setApellido(String apellido) {
        this.apellido = apellido;
    }

    public String getDni() {
        return dni;
    }

    public void setDni(String dni) {
        this.dni = dni;
    }
}
