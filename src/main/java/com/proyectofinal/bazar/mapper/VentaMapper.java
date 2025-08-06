package com.proyectofinal.bazar.mapper;

import com.proyectofinal.bazar.dto.VentaResponseDTO;
import com.proyectofinal.bazar.model.Venta;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.List;

@Mapper(componentModel = "spring",uses = {DetalleVentaMapper.class})
public interface VentaMapper {
    @Mapping(source = "id", target = "id_venta")
    @Mapping(source = "cliente.id", target = "id_cliente")
    @Mapping(source = "cliente.nombre",target = "nombre")
    @Mapping(source = "cliente.apellido",target = "apellido")
    @Mapping(source = "fecha_venta", target = "fecha")
    @Mapping(source = "listaDetalleVentas", target = "detalles")
    VentaResponseDTO ventaToResponseDTO(Venta venta);
    List<VentaResponseDTO> ventasToResponseDTO(List<Venta> ventas);

}
