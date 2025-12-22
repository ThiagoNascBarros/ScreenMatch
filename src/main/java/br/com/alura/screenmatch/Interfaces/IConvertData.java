package br.com.alura.screenmatch.Interfaces;

import com.fasterxml.jackson.core.JsonProcessingException;

public interface IConvertData {
    <T> T convertData(String json, Class<T> classT);
}
