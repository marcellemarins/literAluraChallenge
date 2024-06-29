package com.marin.LiterAluraChallenge.principal;

public class Principal {

        int opcao = -1;
    public void exibeMenu(){

        while(opcao != 0){
            System.out.println("Digite o número da opção que deseja realizar:" +
                    "1 - buscar livro pelo título" +
                    "2 - listar livros registrados" +
                    "3 - listar autores registrados" +
                    "4 - listar autores vivos em um determinado ano" +
                    "5 - listar livros em um determinado idioma" +
                    "0 - sair");
            switch(opcao){

                case 1:
                    buscarTitulo();
                    break;
                case 2:
                    listarLivros();
                    break;
                case 3:
                    listarAutores();
                    break;
                case 4:
                    listarAutoresPorAno();
                    break;
                case 5:
                    listarLivroPorIdioma();
                    break;

            }
        }

    }



}
