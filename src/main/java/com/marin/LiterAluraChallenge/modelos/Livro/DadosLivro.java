package com.marin.LiterAluraChallenge.modelos.Livro;

import com.fasterxml.jackson.annotation.JsonAlias;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.marin.LiterAluraChallenge.modelos.Autor.DadosAutor;

import java.util.List;

@JsonIgnoreProperties(ignoreUnknown = true)
public record DadosLivro(@JsonAlias("title") String nome,
                         @JsonAlias("languages") List<Linguagens> idiomas,
                         @JsonAlias("download_count") Integer downloads,
       @JsonAlias("authors") List<DadosAutor> autor
) {
}
