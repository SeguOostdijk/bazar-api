package com.proyectofinal.bazar.mapper;

import com.proyectofinal.bazar.dto.DetalleVentaDTO;
import com.proyectofinal.bazar.model.DetalleVenta;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.List;

@Mapper(componentModel = "spring")
public interface DetalleVentaMapper {

    @Mapping(source = "producto.id", target = "id_producto")
    @Mapping(source = "producto.nombre", target = "nombreProducto")
    DetalleVentaDTO toDTO(DetalleVenta detalleVenta);
}
