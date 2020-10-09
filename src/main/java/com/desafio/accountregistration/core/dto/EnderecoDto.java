package com.desafio.accountregistration.core.dto;

import java.io.Serializable;

import javax.validation.constraints.NotNull;

import com.desafio.accountregistration.core.model.Endereco;

import org.hibernate.validator.constraints.NotBlank;

import lombok.Data;

@Data
public class EnderecoDto implements Serializable {
    private static final long serialVersionUID = 1L;
    
    @NotBlank(message = "Favor informar o CEP.")
    private String cep;
    
    @NotBlank(message = "Favor informar a Rua.")
    private String rua;

    @NotBlank(message = "Favor informar o Bairro.")
    private String bairro;

    @NotBlank(message = "Favor informar o Complemento.")
    private String complemento;

    @NotBlank(message = "Favor informar a Cidade.")
    private String cidade;

    @NotBlank(message = "Favor informar o Estado.")
    private String estado;

    @NotNull(message = "Favor informar o ID do Cliente.")
    private Long idCliente;

    public EnderecoDto() {

    }

    public EnderecoDto(Endereco endereco) {
        this.cep = endereco.getCep();
        this.rua = endereco.getRua();
        this.bairro = endereco.getBairro();
        this.complemento = endereco.getComplemento();
        this.cidade = endereco.getCidade();
        this.estado = endereco.getEstado();
    }
}
