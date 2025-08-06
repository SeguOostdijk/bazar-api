package com.proyectofinal.bazar.service;

import com.proyectofinal.bazar.dto.ClienteDTO;
import com.proyectofinal.bazar.mapper.ClienteMapper;
import com.proyectofinal.bazar.model.Cliente;
import com.proyectofinal.bazar.repository.IClienteRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ClienteService implements IClienteService{

    @Autowired
    private IClienteRepository clienteRepository;

    @Autowired
    private ClienteMapper clienteMapper;

    @Override
    public ClienteDTO saveCliente(ClienteDTO clienteDTO) {
        if (clienteDTO.getId()!=null)
            clienteDTO.setId(null);
        Cliente clienteGuardado=clienteRepository.save(clienteMapper.dtoToCliente(clienteDTO));
        return clienteMapper.clienteToDto(clienteGuardado);
    }

    @Override
    public List<ClienteDTO> getClientes() {
        return clienteMapper.ClientesToDto(clienteRepository.findAll());
    }

    @Override
    public ClienteDTO getClienteDTO(Long id_cliente) {
        Cliente c=getClienteOrThrow(id_cliente);
        return clienteMapper.clienteToDto(c);
    }

    @Override
    public Cliente getCliente(Long id_cliente) {
        return getClienteOrThrow(id_cliente);
    }

    private Cliente getClienteOrThrow(Long id_cliente){
        return clienteRepository.findById(id_cliente).orElseThrow(()->new EntityNotFoundException("ERROR.Cliente con ID "+id_cliente+" no encontrado"));
    }

    @Override
    public ClienteDTO deleteCliente(Long id_cliente) {
        ClienteDTO cliente=this.getClienteDTO(id_cliente);
        clienteRepository.delete(clienteMapper.dtoToCliente(cliente));
        return cliente;
    }

    @Override
    public ClienteDTO updateCliente(ClienteDTO clienteDTO) {
            Cliente cliente = this.getCliente(clienteDTO.getId());
            clienteRepository.save(clienteMapper.dtoToCliente(clienteDTO));
            return clienteDTO;
    }
}
