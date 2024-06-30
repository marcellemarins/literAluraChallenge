package com.marin.LiterAluraChallenge.modelos.Autor;

import com.fasterxml.jackson.annotation.JsonAlias;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public record DadosAutor(@JsonAlias("name") String nome, @JsonAlias("birth_year") Integer DataNascimento, @JsonAlias("death_year") Integer DataMorte) {
}
