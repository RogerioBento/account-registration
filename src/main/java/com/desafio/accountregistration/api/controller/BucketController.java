package com.desafio.accountregistration.api.controller;

import com.desafio.accountregistration.core.Questionamento.AmazonClient;
import com.desafio.accountregistration.core.exception.EntidadeNaoEncontradaException;

import org.apache.http.HttpStatus;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

@RestController
@RequestMapping("/storage/")
public class BucketController {

    /*
    *
    *
    Esta classe faz parte da rotina para salvar arquivos no S3 referente ao questionamento
    da Proposta de nova conta 3#
    *
    *
    */

    @Autowired
    private AmazonClient amazonClient;

    @Autowired
    BucketController(AmazonClient amazonClient) {
        this.amazonClient = amazonClient;
    }
     // Esta PathVariable é para referenciar qual o Id do cliente que vai ser armazenado a URL do arquivo no S3.
    @PatchMapping("/uploadFileAmazon/{id}")
    public ResponseEntity<?> uploadFile(@RequestPart(value = "file") MultipartFile file, @PathVariable Long id) {
        try{
            this.amazonClient.uploadFile(file, id);
            return ResponseEntity.status(HttpStatus.SC_CREATED).body(file);
        } catch (EntidadeNaoEncontradaException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }
}