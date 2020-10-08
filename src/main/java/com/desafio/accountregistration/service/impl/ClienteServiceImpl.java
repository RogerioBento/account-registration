package com.desafio.accountregistration.service.impl;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import javax.transaction.Transactional;

import com.desafio.accountregistration.core.dto.ClienteDto;
import com.desafio.accountregistration.core.exception.DuplicidadeEncontradaException;
import com.desafio.accountregistration.core.exception.ErroDataException;
import com.desafio.accountregistration.core.model.Cliente;
import com.desafio.accountregistration.core.repository.ClienteRepository;
import com.desafio.accountregistration.service.ClienteService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ClienteServiceImpl implements ClienteService {

    @Autowired
    private ClienteRepository clienteRepository;

    @Override
    @Transactional
    public Cliente novoCliente(ClienteDto cliente) {
         Optional<ClienteDto> clienteCpf = clienteRepository.findByCpf(cliente.getCpf());
         Optional<ClienteDto> clienteEmail = clienteRepository.findByEmail(cliente.getEmail());
         Optional<ClienteDto> clienteCnh = clienteRepository.findByCnh(cliente.getCnh());
         LocalDate clienteDataNasc = cliente.getDataNascimento();
         if (clienteCpf.isPresent()) {
             throw new DuplicidadeEncontradaException(String.format("Ja existe cliente cadastrado com o CPF " + cliente.getCpf()));
         }
         if (clienteEmail.isPresent()) {
            throw new DuplicidadeEncontradaException(String.format("Ja existe cliente cadastrado com o Email " + cliente.getEmail()));
         }
         if (clienteCnh.isPresent()) {
            throw new DuplicidadeEncontradaException(String.format("Ja existe cliente cadastrado com esta CNH " + cliente.getCnh()));
         }
         if (!clienteDataNasc.isBefore(LocalDate.now().minusYears(18)) || clienteDataNasc.isAfter(LocalDate.now())) {
             throw new ErroDataException(String.format("Erros: " +
                                                       " Preencher apenas se for maior de 18. " +
                                                       " Não é permitido uma data futura. "));
         }
         
        Cliente entity = new Cliente();
        entity.setNome(cliente.getNome());
        entity.setSobrenome(cliente.getSobrenome());
        entity.setEmail(cliente.getEmail());
        entity.setCnh(cliente.getCnh());
        entity.setDataNascimento(cliente.getDataNascimento());
        entity.setCpf(cliente.getCpf());

        return clienteRepository.save(entity);
    }

    @Override
    public List<Cliente> findAll() {
        return clienteRepository.findAll();
    }
}
