package com.proyectofinal.bazar.service;

import com.proyectofinal.bazar.dto.DatosDeVentaPorDiaDTO;
import com.proyectofinal.bazar.dto.MayorVentaDTO;
import com.proyectofinal.bazar.dto.VentaRequestDTO;
import com.proyectofinal.bazar.dto.VentaResponseDTO;

import java.time.LocalDate;
import java.util.List;

public interface IVentaService {
    public VentaResponseDTO saveVenta(VentaRequestDTO ventaRequestDTO);
    public List<VentaResponseDTO> getVentas();
    public VentaResponseDTO getVenta(Long id_venta);
    public MayorVentaDTO getMayorVenta();
    public VentaResponseDTO deleteVenta(Long id_venta);
    public VentaResponseDTO updateVenta(VentaRequestDTO ventaRequestDTO);
    public DatosDeVentaPorDiaDTO getVentasPorDia(LocalDate fecha);
}
