package com.marin.LiterAluraChallenge.principal;

import com.marin.LiterAluraChallenge.modelos.Autor.Autor;
import com.marin.LiterAluraChallenge.modelos.Livro.Dados;
import com.marin.LiterAluraChallenge.modelos.Livro.DadosLivro;
import com.marin.LiterAluraChallenge.modelos.Livro.Linguagens;
import com.marin.LiterAluraChallenge.modelos.Livro.Livro;
import com.marin.LiterAluraChallenge.modelos.repository.LivroRepository;
import com.marin.LiterAluraChallenge.services.API.ConsumoAPI;
import com.marin.LiterAluraChallenge.services.API.DesserializarDados;

import java.util.List;
import java.util.Optional;
import java.util.Scanner;
import java.util.stream.Collectors;

public class Principal {
       private Scanner entrada = new Scanner(System.in);
        private ConsumoAPI consultarAPI = new ConsumoAPI();
        private LivroRepository repositorio;
        private DesserializarDados desserializar = new DesserializarDados();
        private final String endereco_pt1 = "https://gutendex.com/books/?";
        private String nomeLivro;
        private String endereco;
        private String json;
        private Linguagens idioma;
        private Integer ano;
        private int opcao = -1;
        public Principal (LivroRepository repositorio){
            this.repositorio = repositorio;
        }
    public void exibeMenu(){

        while(opcao != 0){
            System.out.println("""
                 
                 -------------//-------------------------//-------------
                    Digite o número da opção que deseja realizar: 
                    1 - buscar livro pelo título 
                    2 - listar livros registrados 
                    3 - listar autores registrados 
                    4 - listar autores vivos em um determinado ano 
                    5 - listar livros em um determinado idioma 
                    0 - sair
                 -------------//-------------------------//-------------
                    """);
            opcao = entrada.nextInt();
            entrada.nextLine();
            switch(opcao){

                case 1:
                buscarLivro();
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




    private void buscarLivro() {

        System.out.println("Que livro deseja buscar?");
        nomeLivro = entrada.nextLine().replace(" ", "+");
        endereco = endereco_pt1 + "search=" + nomeLivro;

        json = consultarAPI.consultaAPI(endereco);
       var dados = desserializar.DesserializarJson(json, Dados.class);

       var dadosParaDadosLivro =  dados.dados().stream().map(d -> new DadosLivro(d.nome(), d.idiomas(), d.downloads(), d.autor())).collect(Collectors.toList());
       var dadosLivro = dadosParaDadosLivro.get(0);
       Livro livro = new Livro(dadosLivro);
       Autor autor = new Autor(dadosLivro.autor().get(0));

       Optional<Autor> autorDoLivroBuscado = repositorio.AutorNaLista(autor.getNome());

        if(autorDoLivroBuscado.isPresent()){
            livro.setAutor(autorDoLivroBuscado.get());
            repositorio.save(livro);
        } else {
            repositorio.save(livro);
            livro.setAutor(autor);
            repositorio.save(livro);
        }



    }
    private void listarLivros() {
        System.out.println(repositorio.findAll());
    }

    private void listarAutores() {
        List<Autor> autores = repositorio.listaAutores();
        autores.forEach(System.out::println);

    }

    private void listarAutoresPorAno() {
        System.out.println("Digite o ano que deseja buscar os autores vivos: ");
        ano = entrada.nextInt();
        entrada.nextLine();
        List<Autor> autoresVivosNoAno = repositorio.buscarAutoresVivosNoAno(ano);
        if (!autoresVivosNoAno.isEmpty()){
            System.out.println(autoresVivosNoAno);
        }else {
            System.out.println("Não há nenhum autor vivo nesse ano registrado.");
        }

    }

    private void listarLivroPorIdioma() {
        System.out.println
                ("""
            
            -------//-------------------------//-------
                Digite o idioma que deseja buscar:
                es - espanhol
                en - inglês
                fr - francês
                pt - português
            -------//-------------------------//-------    
                """);
        idioma = Linguagens.valueOf(entrada.nextLine());
        List<Livro> livrosDoIdioma = repositorio.findByIdioma(idioma);
        if(!livrosDoIdioma.isEmpty()){
            System.out.println(livrosDoIdioma);
        }else{
            System.out.println("Não há livros desse idioma registrados.");
        }
    }
}
