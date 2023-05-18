package io.github.robinhosz.unifg;

import com.google.gson.Gson;
import io.github.robinhosz.unifg.model.Movie;
import io.github.robinhosz.unifg.service.OmdbService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class UnifgApplication implements CommandLineRunner {

	private final OmdbService omdbService;

	public UnifgApplication(OmdbService omdbService) {
		this.omdbService = omdbService;
	}

	public static void main(String[] args) {
		SpringApplication.run(UnifgApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		// Obtenha os detalhes do filme desejado
		String movieTitle = "Forrest Gump"; // Substitua pelo título do filme que você deseja pesquisar - A ideia é que seja informado pelo usuario, então faça com o scanner ;)
		String response = omdbService.getMovieDetails(movieTitle);

		System.out.println(response);

		Gson gson = new Gson();
		Movie movie = gson.fromJson(response, Movie.class);


		System.out.println("NOME DO FILME -> " + movie.getTitle());
		System.out.println("GENERO DO FILME -> " + movie.getGenre());

	}
}
