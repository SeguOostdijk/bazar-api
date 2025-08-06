package com.proyectofinal.bazar.service;

import com.proyectofinal.bazar.dto.ProductoDTO;
import com.proyectofinal.bazar.mapper.ProductoMapper;
import com.proyectofinal.bazar.model.Producto;
import com.proyectofinal.bazar.repository.IProductoRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.Period;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class ProductoService implements IProductoService{

    @Autowired
    private IProductoRepository productoRepository;

    @Autowired
    private ProductoMapper productoMapper;

    @Override
    public ProductoDTO saveProducto(ProductoDTO productoDTO) {
        if (productoDTO.getId()!=null)
            productoDTO.setId(null);
        Producto productoGuardado=productoRepository.save(productoMapper.dtoToProducto(productoDTO));
        return productoMapper.productoToDto(productoGuardado);
    }


    @Override
    public List<ProductoDTO> getProductos() {
        return productoMapper.productosToDto(productoRepository.findAll());
    }

    @Override
    public List<ProductoDTO> getProductosEscasos() {
        return productoMapper.productosToDto(productoRepository.findAll().
                stream().
                filter(producto -> producto.getCantidad_disponible()<5).
                collect(Collectors.toList()));
    }

    private Producto getProductoOrThrow(Long codigoProducto) {
        return productoRepository.findById(codigoProducto)
                .orElseThrow(() -> new EntityNotFoundException("ERROR. Producto con ID "+codigoProducto+" no encontrado"));
    }

    @Override
    public ProductoDTO getProductoDTO(Long codigo_producto) {
        Producto producto = getProductoOrThrow(codigo_producto);
        return productoMapper.productoToDto(producto);
    }

   @Override
    public Producto getProducto(Long codigo_producto) {
        return getProductoOrThrow(codigo_producto);
    }

    @Override
    public ProductoDTO deleteProducto(Long codigo_producto) {
        Producto producto=this.getProducto(codigo_producto);
        productoRepository.delete(producto);
        return productoMapper.productoToDto(producto);
    }

    @Override
    public ProductoDTO updateProducto(ProductoDTO productoDTO) {
        Producto producto=this.getProducto(productoDTO.getId());
        productoRepository.save(productoMapper.dtoToProducto(productoDTO));
        return productoDTO;
    }

    @Override
    public boolean hayStock(Long codigo_producto,int cantidad) {
        ProductoDTO productoDTO=this.getProductoDTO(codigo_producto);
        return productoDTO.getCantidad_disponible()>=cantidad;
    }

    @Override
    public void disminuirStock(Producto producto,int cantidad) {
        producto.setCantidad_disponible(producto.getCantidad_disponible()-cantidad);
        productoRepository.save(producto);
    }
    @Override
    public void aumentarStock(Producto producto,int cantidad){
        producto.setCantidad_disponible(producto.getCantidad_disponible()+cantidad);
        productoRepository.save(producto);
    }
}
