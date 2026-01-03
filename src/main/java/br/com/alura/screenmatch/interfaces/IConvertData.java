package br.com.alura.screenmatch.interfaces;

public interface IConvertData {
    <T> T convertData(String json, Class<T> classT);
}
