package com.proyectofinal.bazar.mapper;

import com.proyectofinal.bazar.dto.ProductoDTO;
import com.proyectofinal.bazar.model.Producto;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface ProductoMapper {
    public ProductoDTO productoToDto(Producto producto);
    public Producto dtoToProducto(ProductoDTO productoDTO);
    public List<ProductoDTO> productosToDto(List<Producto> productos);
}
