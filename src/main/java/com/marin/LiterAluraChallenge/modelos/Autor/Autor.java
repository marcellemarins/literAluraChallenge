package com.marin.LiterAluraChallenge.modelos.Autor;

import com.marin.LiterAluraChallenge.modelos.Livro.Livro;
import jakarta.persistence.*;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Entity
@Table(name = "autores")
public class Autor {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(unique = true)
    private String nome;
    private Integer dataNascimento;
    private Integer dataMorte;

    @OneToMany(mappedBy = "autor", cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    private List<Livro> livros = new ArrayList<>();

    public Autor(){}

    public Autor(DadosAutor dadosAutor) {
        this.nome = dadosAutor.nome();
        this.dataNascimento = dadosAutor.DataNascimento();
        this.dataMorte = dadosAutor.DataMorte();
    }

    public Long getId() {
        return id;
    }



    public List<Livro> getLivros() {
        return livros;
    }
    public void setLivros(List<Livro> livros) {
        this.livros = livros;
    }

    public String getNome() {
        return nome;
    }

    @Override
    public String toString() {

        return nome +" (" + dataNascimento + ")"+ " - " + "(" +dataMorte + ") " + "\n" + livros.stream().map(l -> l.getNome()).collect(Collectors.toList()) + "\n";
    }

}
