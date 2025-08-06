package com.proyectofinal.bazar.service;

import com.proyectofinal.bazar.dto.*;
import com.proyectofinal.bazar.exception.InsufficientStockException;
import com.proyectofinal.bazar.mapper.VentaMapper;
import com.proyectofinal.bazar.model.Cliente;
import com.proyectofinal.bazar.model.DetalleVenta;
import com.proyectofinal.bazar.model.Producto;
import com.proyectofinal.bazar.model.Venta;
import com.proyectofinal.bazar.repository.IVentaRepository;
import jakarta.persistence.EntityNotFoundException;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Service
public class VentaService implements IVentaService{

    @Autowired
    private IVentaRepository ventaRepository;

    @Autowired
    private IClienteService clienteService;

    @Autowired
    private IProductoService productoService;

    @Autowired
    private VentaMapper ventaMapper;

    @Transactional
    @Override
    public VentaResponseDTO saveVenta(VentaRequestDTO ventaRequestDTO) {
        Cliente cliente=clienteService.getCliente(ventaRequestDTO.getId_cliente());
        Venta venta=new Venta();
        double total=0;
        double subtotal;
        venta.setFecha_venta(LocalDate.now());
        venta.setCliente(cliente);
        mapearItemsAVenta(venta,ventaRequestDTO.getItems());
        ventaRepository.save(venta);
        return ventaMapper.ventaToResponseDTO(venta);
    }

    @Override
    public List<VentaResponseDTO> getVentas() {
        return ventaMapper.ventasToResponseDTO(ventaRepository.findAll());
    }

    private Venta findVentaByID(Long id_venta){
        return ventaRepository.findById(id_venta).orElseThrow(()->new EntityNotFoundException("ERROR. Venta con ID "+id_venta+" no encontrada"));
    }

    @Override
    public VentaResponseDTO getVenta(Long id_venta) {
        Venta venta=findVentaByID(id_venta);
        return ventaMapper.ventaToResponseDTO(venta);
    }

    @Override
    public MayorVentaDTO getMayorVenta() {
        MayorVentaDTO mayorVentaDTO=new MayorVentaDTO();
        Venta mayorVenta=ventaRepository.findTopByOrderByTotalDesc();
        mayorVentaDTO.setId_venta(mayorVenta.getId());
        mayorVentaDTO.setTotal(mayorVenta.getTotal());
        mayorVentaDTO.setCantidadProductos(mayorVenta.getListaDetalleVentas().size());
        mayorVentaDTO.setNomApeCliente(mayorVenta.getCliente().getNombre()+" "+mayorVenta.getCliente().getApellido());
        return mayorVentaDTO;
    }

    @Override
    public VentaResponseDTO deleteVenta(Long id_venta) {
        Venta venta=findVentaByID(id_venta);
        ventaRepository.delete(venta);
        return ventaMapper.ventaToResponseDTO(venta);
    }

    @Transactional
    @Override
    public VentaResponseDTO updateVenta(VentaRequestDTO ventaRequestDTO) {
        Venta ventaExistente = this.findVentaByID(ventaRequestDTO.getId_venta());
        Cliente cliente = clienteService.getCliente(ventaRequestDTO.getId_cliente());
        ventaExistente.setCliente(cliente);
        ventaExistente.getListaDetalleVentas().forEach(dv -> {
            productoService.aumentarStock(dv.getProducto(), dv.getCantidad());
        });
        ventaExistente.getListaDetalleVentas().clear(); // Esto activa el orphanRemoval
        mapearItemsAVenta(ventaExistente,ventaRequestDTO.getItems());
        ventaRepository.save(ventaExistente);
        return ventaMapper.ventaToResponseDTO(ventaExistente);
    }

    @Override
    public DatosDeVentaPorDiaDTO getVentasPorDia(LocalDate fecha) {
        DatosDeVentaPorDiaDTO datos=new DatosDeVentaPorDiaDTO();
        List<Venta> ventasDelDia=ventaRepository.findAll()
                .stream()
                .filter(venta -> venta.getFecha_venta().isEqual(fecha))
                .toList();
        Double monto=0.0;
        int cantVentas=0;
        for (Venta venta:ventasDelDia){
            monto+=venta.getTotal();
            cantVentas++;
        }
        datos.setCantidadVentas(cantVentas);
        datos.setMontoAcumulado(monto);
        datos.setFecha(fecha);
        return datos;
    }

    private void mapearItemsAVenta(Venta venta, List<VentaItemDTO> items) {
        List<DetalleVenta> detalles = new ArrayList<>();
        double total = 0;
        for (VentaItemDTO itemDTO : items) {
            Producto producto = productoService.getProducto(itemDTO.getId_producto());
            if (productoService.hayStock(producto.getId(), itemDTO.getCantidad())) {
                productoService.disminuirStock(producto, itemDTO.getCantidad());
                DetalleVenta dv = new DetalleVenta();
                dv.setCantidad(itemDTO.getCantidad());
                double subtotal = itemDTO.getCantidad() * producto.getCosto();
                dv.setSubtotal(subtotal);
                dv.setVenta(venta);
                dv.setProducto(producto);
                detalles.add(dv);
                total += subtotal;
            } else {
                throw new InsufficientStockException("ERROR. Stock insuficiente del producto con ID " + producto.getId());
            }
        }
        if (venta.getListaDetalleVentas()!=null)
          venta.getListaDetalleVentas().addAll(detalles);
        else
            venta.setListaDetalleVentas(detalles);
        venta.setTotal(total);
    }

}
