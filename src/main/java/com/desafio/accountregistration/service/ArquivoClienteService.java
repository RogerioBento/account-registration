package com.desafio.accountregistration.service;

import java.io.IOException;
import java.util.stream.Stream;

import com.desafio.accountregistration.core.model.ArquivoCliente;

import org.springframework.web.multipart.MultipartFile;

public interface ArquivoClienteService {
    
    public ArquivoCliente salvarArquivo(MultipartFile File, Long id) throws IOException;

    public Stream<ArquivoCliente> buscarTodosArq();
}
