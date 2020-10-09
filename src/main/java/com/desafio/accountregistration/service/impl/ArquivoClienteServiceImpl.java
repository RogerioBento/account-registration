package com.desafio.accountregistration.service.impl;

import java.io.IOException;
import java.util.Optional;
import java.util.stream.Stream;

import javax.transaction.Transactional;

import com.desafio.accountregistration.core.exception.EntidadeNaoEncontradaException;
import com.desafio.accountregistration.core.model.Cliente;
import com.desafio.accountregistration.core.model.ArquivoCliente;
import com.desafio.accountregistration.core.repository.ArquivoRepository;
import com.desafio.accountregistration.service.ClienteService;
import com.desafio.accountregistration.service.ArquivoClienteService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;

@Service
public class ArquivoClienteServiceImpl implements ArquivoClienteService {

    @Autowired
    private ArquivoRepository fileRepository;

    @Autowired
    private ClienteService clienteService;

    @Override
    @Transactional
    public ArquivoCliente salvarArquivo(MultipartFile file, Long id) throws IOException {
        Optional<Cliente> clienteId = clienteService.buscarCliente(id);
        
        if (clienteId.isEmpty()) {
            throw new EntidadeNaoEncontradaException("O cliente codigo: " + id + " não existe cadastrado.");
        }

        String fileName = StringUtils.cleanPath(file.getOriginalFilename());
        ArquivoCliente newFile = new ArquivoCliente(fileName, file.getContentType(), file.getBytes());

        newFile.setIdCliente(clienteId.get().getIdCliente());
       // clienteService.atualizarArquivoDBCliente(newFile, id);
        return fileRepository.save(newFile);
    }

    @Override
    public Stream<ArquivoCliente> buscarTodosArq() {
        return fileRepository.findAll().stream();
    }

}
