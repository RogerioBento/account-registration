package com.desafio.accountregistration.core.repository;

import com.desafio.accountregistration.core.model.ArquivoCliente;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ArquivoRepository extends JpaRepository<ArquivoCliente, String> {
    
}
