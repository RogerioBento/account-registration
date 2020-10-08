package com.desafio.accountregistration.core.dto;

import java.io.Serializable;
import java.time.LocalDate;

import javax.validation.constraints.NotNull;

import com.desafio.accountregistration.core.model.Cliente;

import org.hibernate.validator.constraints.Email;
import org.hibernate.validator.constraints.NotEmpty;
import org.hibernate.validator.constraints.br.CPF;

import lombok.Data;

@Data
public class ClienteDto implements Serializable {
    private static final long serialVersionUID = 1L;

    @NotEmpty(message = "O Campo nome é obrigatório.")
    private String nome;
    
    @NotEmpty(message = "O Campo sobrenome é obrigatório.")
    private String sobrenome;

    @NotEmpty(message = "O Campo email é obrigatório.")
    @Email
    private String email;
    
    @NotEmpty(message = "O Campo CNH é obrigatório.")
    private String cnh;

    @NotNull(message = "O Campo data de nascimento é obrigatório.")
    private LocalDate dataNascimento;

    @NotEmpty(message = "O Campo CPF é obrigatório.")
    @CPF
    private String cpf;

    public ClienteDto() {

    }

    public ClienteDto(Cliente entity) {
        this.nome = entity.getNome();
        this.sobrenome = entity.getSobrenome();
        this.email = entity.getEmail();
        this.cnh = entity.getCnh();
        this.dataNascimento = entity.getDataNascimento();
        this.cpf = entity.getCpf();
    }

    
}
