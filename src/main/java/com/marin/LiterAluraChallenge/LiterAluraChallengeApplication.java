package com.marin.LiterAluraChallenge;

import com.marin.LiterAluraChallenge.modelos.repository.LivroRepository;
import com.marin.LiterAluraChallenge.principal.Principal;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class LiterAluraChallengeApplication implements CommandLineRunner {
	@Autowired
	private LivroRepository repositorio;
	public static void main(String[] args) {
		SpringApplication.run(LiterAluraChallengeApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
	Principal principal = new Principal(repositorio);
	principal.exibeMenu();
	}
}
