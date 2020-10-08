package com.desafio.accountregistration.api.controller;

import java.util.List;

import javax.validation.Valid;

import com.desafio.accountregistration.core.dto.EnderecoDto;
import com.desafio.accountregistration.core.exception.CustomInvalidArgumentException;
import com.desafio.accountregistration.core.model.Endereco;
import com.desafio.accountregistration.service.EnderecoService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class EnderecoController {
    
    @Autowired
    private EnderecoService enderecoService;

    @PostMapping(value = "/enderecos")
    public ResponseEntity<?> novoEndereco(@Valid @RequestBody EnderecoDto endereco) {
        try{
            Endereco newEndereco = enderecoService.novoEndereco(endereco);
            return ResponseEntity.status(HttpStatus.CREATED).body(newEndereco);
        } catch (CustomInvalidArgumentException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @GetMapping(value = "/enderecos")
    public List<Endereco> buscar(){
        return enderecoService.findAll();
    }
}
