package com.marin.LiterAluraChallenge.modelos.repository;

import com.marin.LiterAluraChallenge.modelos.Autor.Autor;
import com.marin.LiterAluraChallenge.modelos.Livro.Linguagens;
import com.marin.LiterAluraChallenge.modelos.Livro.Livro;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;

public interface LivroRepository extends JpaRepository<Livro, Long> {

    @Query("SELECT a FROM Livro l JOIN l.autor a")
    List<Autor> listaAutores();
    @Query("SELECT a FROM Livro l JOIN l.autor a WHERE a.nome ILIKE :nome")
    Optional<Autor> AutorNaLista(String nome);

    @Query ("SELECT a FROM Livro l JOIN l.autor a WHERE a.dataNascimento <= :ano and a.dataMorte >= :ano")
    List<Autor> buscarAutoresVivosNoAno(Integer ano);

    List<Livro> findByIdioma(Linguagens idioma);
}
