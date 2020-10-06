package com.desafio.accountregistration.core.exception;

public class ErroDataException extends RuntimeException {

    private static final long serialVersionUID = 1L;

    public ErroDataException(String mensagem) {
        super(mensagem);
    }
}
