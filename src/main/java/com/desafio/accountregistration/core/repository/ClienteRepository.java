package com.desafio.accountregistration.core.repository;

import java.util.Optional;

import com.desafio.accountregistration.core.dto.ClienteDto;
import com.desafio.accountregistration.core.model.Cliente;

import org.springframework.data.jpa.repository.JpaRepository;

public interface ClienteRepository extends JpaRepository<Cliente, Long>{

	Optional<ClienteDto> findByCpf(String cpf);

	Optional<ClienteDto> findByEmail(String email);

	Optional<ClienteDto> findByCnh(String cnh);
    
}
