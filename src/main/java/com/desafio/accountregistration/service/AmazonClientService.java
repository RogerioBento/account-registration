package com.desafio.accountregistration.service;

import com.desafio.accountregistration.core.model.Cliente;

public interface AmazonClientService {

	public Cliente atualizarArquivoS3Cliente(String urlFile, Long id);
}
