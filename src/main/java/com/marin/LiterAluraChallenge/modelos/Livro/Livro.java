package com.marin.LiterAluraChallenge.modelos.Livro;

import com.marin.LiterAluraChallenge.modelos.Autor.Autor;
import jakarta.persistence.*;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "livros")
public class Livro {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(unique = true)
    private String nome;
    @Enumerated(EnumType.STRING)
    private Linguagens idioma;
    private Integer downloads;
    @ManyToOne(cascade = CascadeType.MERGE, fetch = FetchType.EAGER)
    private Autor autor;
    public Livro(){}
    public Livro (DadosLivro dadosLivro){
        this.nome = dadosLivro.nome();
        this.idioma = dadosLivro.idiomas().get(0);
        this.downloads = dadosLivro.downloads();
    }

    public Autor getAutor() {
        return autor;
    }

    public void setAutor(Autor autor) {
        this.autor = autor;
    }

    public String getNome() {
        return nome;
    }

    @Override
    public String toString() {
        return "\n------------//--------------//------------\n" + "nome: " + nome + "\nIdioma: " + idioma + "\nTotal de downloads: " + downloads + "\nAutor: "+ autor + "\n------------//--------------//------------";
    }
}
