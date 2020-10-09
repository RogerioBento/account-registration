package com.desafio.accountregistration.core.Questionamento;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Date;
import java.util.Optional;

import javax.annotation.PostConstruct;

import com.amazonaws.auth.AWSCredentials;
import com.amazonaws.auth.BasicAWSCredentials;
import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.AmazonS3Client;
import com.amazonaws.services.s3.model.CannedAccessControlList;
import com.amazonaws.services.s3.model.PutObjectRequest;
import com.desafio.accountregistration.core.exception.EntidadeNaoEncontradaException;
import com.desafio.accountregistration.core.model.Cliente;
import com.desafio.accountregistration.service.AmazonClientService;
import com.desafio.accountregistration.service.ClienteService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

/* 
*
*
Questionamento - Proposta de nova conta #3.
Uma outra abordagem para salvar os arquivos seria usar o S3 da amazon. 
Salvando o arquivo na amazon e apenas a URL do arquivo no banco de dados vinculado ao id do cliente. 
Deixei as classes para esse processo ja feitas, só nao esta configurado devidamente no bucket as politicas de segurança.
Tambem o campo urlFoto da entidade Cliente existe apenas para esse processo.
*
*
*/


@Service
public class AmazonClient {

    private AmazonS3 s3client;

    @Autowired
    private ClienteService clienteService;

    @Autowired
    private AmazonClientService amazonService;

    @Value("${amazonProperties.endpointUrl}")
    private String endpointUrl;
    @Value("${amazonProperties.bucketName}")
    private String bucketName;
    @Value("${amazonProperties.accessKey}")
    private String accessKey;
    @Value("${amazonProperties.secretKey}")
    private String secretKey;

    @PostConstruct
    private void initializeAmazon() {
       AWSCredentials credentials = new BasicAWSCredentials(this.accessKey, this.secretKey);
       this.s3client = new AmazonS3Client(credentials);
}

	private File convertMultiPartToFile(MultipartFile file) throws IOException {
        File convFile = new File(file.getOriginalFilename());
        FileOutputStream fos = new FileOutputStream(convFile);
        fos.write(file.getBytes());
        fos.close();
        return convFile;
    }

    private String generateFileName(MultipartFile multiPart) {
        return new Date().getTime() + "-" + multiPart.getOriginalFilename().replace(" ", "_");
    }

    private void uploadFileTos3bucket(String fileName, File file) {
        s3client.putObject(
                new PutObjectRequest(bucketName, fileName, file).withCannedAcl(CannedAccessControlList.PublicRead));
    }

    public String uploadFile(MultipartFile multipartFile, Long id) {

        Optional<Cliente> clienteId = clienteService.buscarCliente(id);

        if (clienteId.isEmpty()){
            throw new EntidadeNaoEncontradaException("Cliente informado com o ID: " + id + " ainda não foi cadastrado.");
        }

        String fileUrl = "";
        try {
            File file = convertMultiPartToFile(multipartFile);
            String fileName = generateFileName(multipartFile);
            fileUrl = endpointUrl + "/" + bucketName + "/" + fileName;
            uploadFileTos3bucket(fileName, file);
            file.delete();
        } catch (Exception e) {
            e.printStackTrace();
        }
        amazonService.atualizarArquivoS3Cliente(fileUrl, clienteId.get().getIdCliente());
        return fileUrl;
    }

}