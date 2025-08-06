package com.proyectofinal.bazar.service;

import com.proyectofinal.bazar.dto.ClienteDTO;
import com.proyectofinal.bazar.model.Cliente;

import java.util.List;

public interface IClienteService {
    public ClienteDTO saveCliente(ClienteDTO clienteDTO);
    public List<ClienteDTO> getClientes();
    public ClienteDTO getClienteDTO(Long id_cliente);
    public Cliente getCliente(Long id_cliente);
    public ClienteDTO deleteCliente(Long id_cliente);
    public ClienteDTO updateCliente(ClienteDTO clienteDTO);
}
