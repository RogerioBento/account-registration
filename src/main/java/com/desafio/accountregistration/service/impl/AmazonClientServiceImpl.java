package com.desafio.accountregistration.service.impl;

import com.desafio.accountregistration.core.exception.CustomInvalidArgumentException;
import com.desafio.accountregistration.core.model.Cliente;
import com.desafio.accountregistration.core.repository.ClienteRepository;
import com.desafio.accountregistration.service.AmazonClientService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AmazonClientServiceImpl implements AmazonClientService {

    @Autowired
    private ClienteRepository clienteRepository;

    @Override
    public Cliente atualizarArquivoS3Cliente(String urlFile, Long id) {
        Cliente clienteId = clienteRepository.findByIdCliente(id);

        if (clienteId == null) {
            StringBuilder sBuilder = new StringBuilder();
            sBuilder.append("Cliente informado não esta cadastrado.");
            throw new CustomInvalidArgumentException(sBuilder.toString());
        }
        // setUrl usado na rotina para atualizar o campo urlFoto com o endereço da foto
        // salva no S3.
        // clienteId.setUrlFoto(fileUrl);
        clienteId.setIdCliente(clienteId.getIdCliente());

        return clienteRepository.save(clienteId);
    }
    
}
