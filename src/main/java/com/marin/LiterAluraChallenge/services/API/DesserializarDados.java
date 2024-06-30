package com.marin.LiterAluraChallenge.services.API;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.type.CollectionType;

import java.util.List;


public class DesserializarDados {

    ObjectMapper mapper = new ObjectMapper();
   public <T> T DesserializarJson(String json, Class<T> classe){

        try {
            return mapper.readValue(json, classe);
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }

    }

    public <T> List<T> ObterLista(String json, Class<T> classe){
        CollectionType lista = mapper.getTypeFactory().constructCollectionType(List.class, classe);
        return null;
    }
}
