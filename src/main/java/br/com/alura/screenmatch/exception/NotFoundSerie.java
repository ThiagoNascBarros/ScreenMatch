package br.com.alura.screenmatch.exception;

public class NotFoundSerie extends RuntimeException {
    public NotFoundSerie(String message) {
        super(message);
    }
}
