package com.desafio.accountregistration.api.controller;

import java.util.List;

import javax.validation.Valid;

import com.desafio.accountregistration.core.dto.ClienteDto;
import com.desafio.accountregistration.core.exception.DuplicidadeEncontradaException;
import com.desafio.accountregistration.core.exception.ErroDataException;
import com.desafio.accountregistration.core.model.Cliente;
import com.desafio.accountregistration.service.ClienteService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@Validated
public class ClienteController {
    
    @Autowired
    private ClienteService clienteService;

    @PostMapping(value = "/clientes")
    public ResponseEntity<?> novoCliente(@Valid @RequestBody ClienteDto cliente) {
        try{
            ClienteDto newCliente = clienteService.novoCliente(cliente);
            return ResponseEntity.status(HttpStatus.CREATED).body(newCliente);
        } catch (DuplicidadeEncontradaException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        } catch (ErroDataException e) {
            return ResponseEntity.badRequest().body(e.getMessage());        
        }
    }

    @GetMapping(value = "/clientes")
    public List<Cliente> buscar(){
        return clienteService.findAll();
    }
}   
