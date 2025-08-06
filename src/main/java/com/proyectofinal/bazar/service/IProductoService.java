package com.proyectofinal.bazar.service;

import com.proyectofinal.bazar.dto.ProductoDTO;
import com.proyectofinal.bazar.model.Producto;

import java.util.List;

public interface IProductoService {
    public ProductoDTO saveProducto(ProductoDTO productoDTO);
    public List<ProductoDTO> getProductos();
    public List<ProductoDTO> getProductosEscasos();
    public ProductoDTO getProductoDTO(Long codigo_producto);
    public Producto getProducto(Long codigo_producto);
    public ProductoDTO deleteProducto(Long codigo_producto);
    public ProductoDTO updateProducto(ProductoDTO productoDTO);
    public boolean hayStock(Long codigo_producto,int cantidad);
    public void aumentarStock(Producto producto,int cantidad);
    public void disminuirStock(Producto producto,int cantidad);
}
