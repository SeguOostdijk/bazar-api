package com.proyectofinal.bazar.controller;

import com.proyectofinal.bazar.dto.ClienteDTO;
import com.proyectofinal.bazar.dto.ProductoDTO;
import com.proyectofinal.bazar.service.IProductoService;
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
@Tag(name = "Productos", description = "Gestión de productos del bazar")
public class ProductoController {

    @Autowired
    private IProductoService productoService;

    @Operation(summary = "Crear un nuevo producto")
    @ApiResponses({
            @ApiResponse(responseCode = "201", description = "Producto creado correctamente", content = @Content(mediaType = "application/json", schema = @Schema(implementation = ProductoDTO.class))),
            @ApiResponse(responseCode = "500", description = "Error interno", content = @Content)
    })
    @PostMapping("/productos/crear")
    public ResponseEntity<?> setProducto(@RequestBody ProductoDTO productoDTO){
        try {
            return new ResponseEntity<>(productoService.saveProducto(productoDTO), HttpStatus.CREATED);
        } catch (Exception e){
            return new ResponseEntity<>("Error al crear producto",HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @Operation(summary = "Obtener todos los productos")
    @ApiResponse(responseCode = "200", description = "Lista completa de productos",content = @Content(mediaType = "application/json",array = @ArraySchema(schema = @Schema(implementation = ProductoDTO.class))))
    @GetMapping("/productos")
    public ResponseEntity<List<ProductoDTO>> getProductos(){
        return new ResponseEntity<>(productoService.getProductos(),HttpStatus.OK);
    }

    @Operation(summary = "Obtener producto por código")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Producto encontrado", content = @Content(mediaType = "application/json", schema = @Schema(implementation = ProductoDTO.class))),
            @ApiResponse(responseCode = "404", description = "Producto no encontrado", content = @Content)
    })
    @GetMapping("/producto")
    public ResponseEntity<?> getProducto(@RequestParam @Parameter(description = "Código del producto") Long codigo_producto){
        try {
            return new ResponseEntity<>(productoService.getProductoDTO(codigo_producto),HttpStatus.OK);
        }
        catch (EntityNotFoundException e){
            return new ResponseEntity<>(e.getMessage(),HttpStatus.NOT_FOUND);
        }
    }

    @Operation(summary = "Obtener productos con stock menor a 5")
    @ApiResponse(responseCode = "200", description = "Lista de productos con stock limitado",content = @Content(mediaType = "application/json",array = @ArraySchema(schema = @Schema(implementation = ProductoDTO.class))))
    @GetMapping("/productos/falta_stock")
    public ResponseEntity<List<ProductoDTO>> getProductosEscasos(){
        return new ResponseEntity<>(productoService.getProductosEscasos(),HttpStatus.OK);
    }

    @Operation(summary = "Eliminar un producto por su código")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Producto eliminado", content = @Content(mediaType = "application/json", schema = @Schema(implementation = ProductoDTO.class))),
            @ApiResponse(responseCode = "404", description = "Producto no encontrado", content = @Content)
    })
    @DeleteMapping("/productos/eliminar")
    public ResponseEntity<?> deleteProducto(@RequestParam @Parameter(description = "Código del producto") Long codigo_producto){
        try {
            return new ResponseEntity<>(productoService.deleteProducto(codigo_producto),HttpStatus.OK);
        } catch (EntityNotFoundException e){
            return new ResponseEntity<>(e.getMessage(),HttpStatus.NOT_FOUND);
        }
    }

    @Operation(summary = "Editar un producto")
    @PutMapping("/productos/editar")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Producto actualizado", content = @Content(mediaType = "application/json", schema = @Schema(implementation = ProductoDTO.class))),
            @ApiResponse(responseCode = "404", description = "Producto no encontrado", content = @Content)
    })
    public ResponseEntity<?> updateProducto(@RequestBody ProductoDTO productoDTO){
        try {
            return new ResponseEntity<>(productoService.updateProducto(productoDTO),HttpStatus.OK);
        } catch (EntityNotFoundException e){
            return new ResponseEntity<>(e.getMessage(),HttpStatus.NOT_FOUND);
        }
    }
}
