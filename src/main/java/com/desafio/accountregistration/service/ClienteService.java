package com.desafio.accountregistration.service;

import java.util.List;

import com.desafio.accountregistration.core.dto.ClienteDto;
import com.desafio.accountregistration.core.model.Cliente;

public interface ClienteService {
    
    public Cliente novoCliente(ClienteDto cliente);
    public List<Cliente> findAll();
}
