package com.desafio.accountregistration.api.controller;

import java.io.IOException;
import java.util.List;
import java.util.stream.Collectors;

import com.amazonaws.services.applicationdiscovery.model.ResourceNotFoundException;
import com.desafio.accountregistration.core.exception.CustomInvalidArgumentException;
import com.desafio.accountregistration.core.exception.EntidadeNaoEncontradaException;
import com.desafio.accountregistration.core.model.ResponseFile;
import com.desafio.accountregistration.service.ArquivoClienteService;
import com.desafio.accountregistration.util.ResponseMessage;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

@Controller
@CrossOrigin("http://localhost:8080")
public class ArquivoController {

    @Autowired
    private ArquivoClienteService fileService;

    @PostMapping("/upload/{id}")
    public ResponseEntity<?> uploadFile(@RequestParam("file") MultipartFile file, @PathVariable Long id)
            throws IOException {
        String message = "";
        try {
            fileService.salvarArquivo(file, id);
            message = "Arquivo salvo com sucesso: " + file.getOriginalFilename();
            return ResponseEntity.status(HttpStatus.CREATED).body(new ResponseMessage(message));
        } catch (CustomInvalidArgumentException e) {
            message = "Não foi possivel salvar o arquivo: " + file.getOriginalFilename() + "!";
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
        } catch (EntidadeNaoEncontradaException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
        }
    }

    @GetMapping("/upload")
    public ResponseEntity<?> getListFiles() {
        try {

            List<ResponseFile> files = fileService.buscarTodosArq().map(dbFile -> {
                String fileDownloadUri = ServletUriComponentsBuilder.fromCurrentContextPath().path("/files/")
                        .path(dbFile.getId()).toUriString();
                return new ResponseFile(dbFile.getName(), fileDownloadUri, dbFile.getType(), dbFile.getData().length);
            }).collect(Collectors.toList());
            
            return ResponseEntity.status(HttpStatus.OK).body(files);

        } catch (ResourceNotFoundException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
        }
    }
}