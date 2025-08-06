package com.proyectofinal.bazar.mapper;

import com.proyectofinal.bazar.dto.ClienteDTO;
import com.proyectofinal.bazar.model.Cliente;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface ClienteMapper {
    public ClienteDTO clienteToDto(Cliente cliente);
    public Cliente dtoToCliente(ClienteDTO clienteDTO);
    public List<ClienteDTO> ClientesToDto(List<Cliente> clientes);
}
