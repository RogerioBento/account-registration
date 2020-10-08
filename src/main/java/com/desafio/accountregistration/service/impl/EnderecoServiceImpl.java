package com.desafio.accountregistration.service.impl;

import java.util.List;
import java.util.Optional;

import javax.transaction.Transactional;

import com.desafio.accountregistration.core.dto.EnderecoDto;
import com.desafio.accountregistration.core.exception.CustomInvalidArgumentException;
import com.desafio.accountregistration.core.model.Cliente;
import com.desafio.accountregistration.core.model.Endereco;
import com.desafio.accountregistration.core.repository.ClienteRepository;
import com.desafio.accountregistration.core.repository.EnderecoRepository;
import com.desafio.accountregistration.service.EnderecoService;
import com.desafio.accountregistration.util.FormatCepClass;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class EnderecoServiceImpl implements EnderecoService {

    @Autowired
    private EnderecoRepository enderecoRepository;

    @Autowired
    private ClienteRepository clienteRepository;

    @Override
    @Transactional
    public Endereco novoEndereco(EnderecoDto endereco) {
        String cep = FormatCepClass.FormatCep(endereco.getCep());
        Optional<Cliente> idCliente = clienteRepository.findById(endereco.getIdCliente());

        if (idCliente.isEmpty()){
            throw new CustomInvalidArgumentException("O Id do cliente informado não existe na base de dados.");
        }

        Endereco entity = new Endereco();
        entity.setCep(cep);
        entity.setRua(endereco.getRua());
        entity.setBairro(endereco.getBairro());
        entity.setComplemento(endereco.getComplemento());
        entity.setCidade(endereco.getCidade());
        entity.setEstado(endereco.getEstado());
        entity.setIdCliente(idCliente.get());

        return enderecoRepository.save(entity);
 
    }

    @Override
    public List<Endereco> findAll() {
        return enderecoRepository.findAll();
    }
    
}
