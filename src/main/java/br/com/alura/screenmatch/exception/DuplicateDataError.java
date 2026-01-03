package br.com.alura.screenmatch.exception;

public class DuplicateDataError extends RuntimeException {
    public DuplicateDataError(String message) {
        super(message);
    }
}
