package br.com.alura.screenmatch.service;

import br.com.alura.screenmatch.interfaces.IConvertData;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

public class ConvertData implements IConvertData {

    private final ObjectMapper mapper = new ObjectMapper();

    @Override
    public <T> T convertData(String json, Class<T> classT) {
        try {
            return mapper.readValue(json, classT);
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e.getMessage());
        }
    }


}
