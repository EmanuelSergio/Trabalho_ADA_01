package br.com.livraria;

public class TipoEntregaInvalidaException extends RuntimeException {
    public TipoEntregaInvalidaException(String message) {
        super(message);
    }
}