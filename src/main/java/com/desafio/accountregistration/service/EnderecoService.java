package com.desafio.accountregistration.service;

import java.util.List;

import com.desafio.accountregistration.core.dto.EnderecoDto;
import com.desafio.accountregistration.core.model.Endereco;

public interface EnderecoService {
    
    public Endereco novoEndereco(EnderecoDto endereco);
    public List<Endereco> buscarTodos();
}
