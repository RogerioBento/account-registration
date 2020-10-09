package com.desafio.accountregistration.core.dto;

import java.io.Serializable;
import java.time.LocalDate;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import com.desafio.accountregistration.core.model.Cliente;

import org.hibernate.validator.constraints.Email;
import org.hibernate.validator.constraints.NotBlank;
import org.hibernate.validator.constraints.NotEmpty;
import org.hibernate.validator.constraints.br.CPF;

import lombok.Data;

@Data
public class ClienteDto implements Serializable {
    private static final long serialVersionUID = 1L;

    @NotBlank(message = "O Campo nome é obrigatório.")
    private String nome;
    
    @NotBlank(message = "O Campo sobrenome é obrigatório.")
    private String sobrenome;

    @NotBlank(message = "O Campo email é obrigatório.")
    @Email
    private String email;
    
    @NotBlank(message = "O Campo CNH é obrigatório.")
    @Size(min = 11, max = 11)
    private String cnh;

    @NotNull(message = "O Campo data de nascimento é obrigatório.")
    private LocalDate dataNascimento;

    @NotEmpty(message = "O Campo CPF é obrigatório.")
    @CPF
    private String cpf;

    // private String urlFoto;

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
