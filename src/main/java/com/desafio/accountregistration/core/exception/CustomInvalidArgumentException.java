package com.desafio.accountregistration.core.exception;

public class CustomInvalidArgumentException extends RuntimeException {

    private static final long serialVersionUID = 1L;

    public CustomInvalidArgumentException(String mensagem) {
        super(mensagem);
    }
}
