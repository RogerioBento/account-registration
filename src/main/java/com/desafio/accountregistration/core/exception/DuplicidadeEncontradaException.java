package com.desafio.accountregistration.core.exception;

public class DuplicidadeEncontradaException extends RuntimeException {

    private static final long serialVersionUID = 1L;

    public DuplicidadeEncontradaException(String mensagem) {
        super(mensagem);
    }
}
