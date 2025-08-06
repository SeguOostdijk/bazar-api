package com.proyectofinal.bazar.controller;

import com.proyectofinal.bazar.dto.ClienteDTO;
import com.proyectofinal.bazar.service.IClienteService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.ArraySchema;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@Tag(name = "Clientes", description = "Operaciones relacionadas con los clientes")
public class ClienteController {

    @Autowired
    private IClienteService clienteService;

    @Operation(summary = "Crear un nuevo cliente")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Cliente creado correctamente",content = @Content(mediaType = "application/json", schema = @Schema(implementation = ClienteDTO.class))),
            @ApiResponse(responseCode = "500", description = "Error interno del servidor", content = @Content)
    })
    @PostMapping("/clientes/crear")
    public ResponseEntity<?> saveCliente(@RequestBody ClienteDTO clienteDTO){
        try {
            return new ResponseEntity<>(clienteService.saveCliente(clienteDTO), HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity<>("Error al crear cliente",HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @Operation(summary = "Obtener todos los clientes")
    @ApiResponse(responseCode = "200", description = "Lista completa de clientes",content = @Content(mediaType = "application/json",array = @ArraySchema(schema = @Schema(implementation = ClienteDTO.class))))
    @GetMapping("/clientes")
    public ResponseEntity<List<ClienteDTO>> getClientes(){
        return new ResponseEntity<>(clienteService.getClientes(),HttpStatus.OK);
    }

    @Operation(summary = "Obtener un cliente por su ID")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Cliente encontrado", content = @Content(mediaType = "application/json", schema = @Schema(implementation = ClienteDTO.class))),
            @ApiResponse(responseCode = "404", description = "Cliente no encontrado", content = @Content)
    })
    @GetMapping("/cliente")
    public ResponseEntity<?> getCliente(@RequestParam @Parameter(description = "ID del cliente") Long id_cliente){
        try {
            return new ResponseEntity<>(clienteService.getClienteDTO(id_cliente),HttpStatus.OK);
        } catch (EntityNotFoundException e) {
            return new ResponseEntity<>(e.getMessage(),HttpStatus.NOT_FOUND);
        }
    }

    @Operation(summary = "Eliminar un cliente por su ID")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Cliente eliminado", content = @Content(mediaType = "application/json", schema = @Schema(implementation = ClienteDTO.class))),
            @ApiResponse(responseCode = "404", description = "Cliente no encontrado", content = @Content)
    })
    @DeleteMapping("/clientes/eliminar")
    public ResponseEntity<?> deleteCliente(@RequestParam @Parameter(description = "ID del cliente") Long id_cliente){
        try {
            return new ResponseEntity<>(clienteService.deleteCliente(id_cliente),HttpStatus.OK);
        } catch (EntityNotFoundException e) {
            return new ResponseEntity<>(e.getMessage(),HttpStatus.NOT_FOUND);
        }
    }

    @Operation(summary = "Editar un cliente")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Cliente actualizado", content = @Content(mediaType = "application/json", schema = @Schema(implementation = ClienteDTO.class))),
            @ApiResponse(responseCode = "404", description = "Cliente no encontrado", content = @Content)
    })
    @PutMapping("/clientes/editar")
    public ResponseEntity<?> updateCliente(@RequestBody  ClienteDTO clienteDTO){
        try {
            return new ResponseEntity<>(clienteService.updateCliente(clienteDTO),HttpStatus.OK);
        } catch (EntityNotFoundException e) {
            return new ResponseEntity<>(e.getMessage(),HttpStatus.NOT_FOUND);
        }
    }
}
