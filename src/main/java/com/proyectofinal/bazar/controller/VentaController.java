package com.proyectofinal.bazar.controller;

import com.proyectofinal.bazar.dto.*;
import com.proyectofinal.bazar.exception.InsufficientStockException;
import com.proyectofinal.bazar.service.IVentaService;
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

import java.time.LocalDate;
import java.util.List;

@RestController
@Tag(name = "Ventas", description = "Venta de productos de bazar")
public class VentaController {

    @Autowired
    private IVentaService ventaService;

    @PostMapping("/ventas/crear")
    @Operation(summary = "Registrar una venta")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201",description = "Venta creada exitosamente", content = @Content(mediaType = "application/json", schema = @Schema(implementation = VentaResponseDTO.class))),
            @ApiResponse(responseCode = "404",description = "Id de cliente o producto asociado a la venta no encontrado", content = @Content),
            @ApiResponse(responseCode = "409",description = "Stock insufuciente de algún producto con ID específico", content = @Content)
    })
    public ResponseEntity<?> saveVenta(@RequestBody VentaRequestDTO ventaRequestDTO){
        try {
            return new ResponseEntity<>(ventaService.saveVenta(ventaRequestDTO), HttpStatus.CREATED);
        }
        catch (EntityNotFoundException e){
            return new ResponseEntity<>(e.getMessage(),HttpStatus.NOT_FOUND);
        }
        catch (InsufficientStockException e){
            return new ResponseEntity<>(e.getMessage(),HttpStatus.CONFLICT);
        }
    }

    @Operation(summary = "Obtener todas las ventas")
    @GetMapping("/ventas")
    @ApiResponse(responseCode = "200", description = "Lista completa de ventas",content = @Content(mediaType = "application/json",array = @ArraySchema(schema = @Schema(implementation = VentaResponseDTO.class))))
    public ResponseEntity<List<VentaResponseDTO>> getVentas(){
        return new ResponseEntity<>(ventaService.getVentas(),HttpStatus.OK);
    }

    @Operation(summary = "Obtener una venta por su id")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200",description = "Producto encontrado", content = @Content(mediaType = "application/json", schema = @Schema(implementation = VentaResponseDTO.class))),
            @ApiResponse(responseCode = "404",description = "Producto no encontrado", content = @Content)
    })
    @GetMapping("/venta")
    public ResponseEntity<?> getVenta(@RequestParam @Parameter(description = "ID de la venta") Long id_venta){
        try {
            return new ResponseEntity<>(ventaService.getVenta(id_venta),HttpStatus.OK);
        }
        catch (EntityNotFoundException e){
            return new ResponseEntity<>(e.getMessage(),HttpStatus.NOT_FOUND);
        }
    }

    @Operation(summary = "Obtener monto total y cantidad de ventas por fecha")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Ventas encontradas", content = @Content(mediaType = "application/json",schema = @Schema(implementation = DatosDeVentaPorDiaDTO.class))),
            @ApiResponse(responseCode = "404", description = "No se encontraron ventas para esa fecha", content = @Content)
    })
    @GetMapping("/ventas/{fecha_venta}")
    public ResponseEntity<DatosDeVentaPorDiaDTO> getVentasPorDia(
            @PathVariable @Parameter(description = "Fecha de la venta (YYYY-MM-DD)") LocalDate fecha_venta){
        return new ResponseEntity<>(ventaService.getVentasPorDia(fecha_venta),HttpStatus.OK);
    }

    @GetMapping("/ventas/mayor_venta")
    @Operation(summary = "Obtener la venta de mayor monto total registrado")
    @ApiResponse(responseCode = "200",description = "Venta encontrada", content = @Content(mediaType = "application/json", schema = @Schema(implementation = MayorVentaDTO.class)))
    public ResponseEntity<MayorVentaDTO> getMayorVenta(){
        return new ResponseEntity<>(ventaService.getMayorVenta(),HttpStatus.OK);
    }

    @DeleteMapping("/ventas/eliminar")
    @Operation(summary = "Eliminar una venta por su ID")
    @ApiResponse(responseCode = "200",description = "Venta eliminada", content = @Content(mediaType = "application/json", schema = @Schema(implementation = VentaResponseDTO.class)))
    @ApiResponse(responseCode = "404",description = "Id de cliente o producto asociado a la venta no encontrado", content = @Content)
    public ResponseEntity<?> deleteVenta(@RequestParam @Parameter(description = "ID de la venta") Long id_venta) {
        try {
            return new ResponseEntity<>(ventaService.deleteVenta(id_venta), HttpStatus.OK);
        }
        catch (EntityNotFoundException e){
            return new ResponseEntity<>(e.getMessage(),HttpStatus.NOT_FOUND);
        }
    }

    @PutMapping("/ventas/editar")
    @Operation(summary = "Editar una venta")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Venta actualizada", content = @Content(mediaType = "application/json", schema = @Schema(implementation = VentaResponseDTO.class))),
            @ApiResponse(responseCode = "404",description = "Id de cliente o producto asociado a la venta no encontrado", content = @Content),
            @ApiResponse(responseCode = "409",description = "Stock insufuciente de algún producto con ID específico", content = @Content)
    })
    public ResponseEntity<?> updateVenta(@RequestBody VentaRequestDTO ventaRequestDTO){
        try {
            return new ResponseEntity<>(ventaService.updateVenta(ventaRequestDTO),HttpStatus.OK);
        }
        catch (EntityNotFoundException e){
            return new ResponseEntity<>(e.getMessage(),HttpStatus.NOT_FOUND);
        }
        catch (InsufficientStockException e){
            return new ResponseEntity<>(e.getMessage(),HttpStatus.CONFLICT);
        }
    }

}
