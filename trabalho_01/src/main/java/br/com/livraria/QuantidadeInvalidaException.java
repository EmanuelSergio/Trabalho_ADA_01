package br.com.livraria;

public class QuantidadeInvalidaException extends RuntimeException {
    public QuantidadeInvalidaException(String message) {
        super(message);
    }
}