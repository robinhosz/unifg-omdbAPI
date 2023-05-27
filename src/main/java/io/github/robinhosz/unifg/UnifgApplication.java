package io.github.robinhosz.unifg;

import com.google.gson.Gson;
import io.github.robinhosz.unifg.cache.MovieCache;
import io.github.robinhosz.unifg.model.Movie;
import io.github.robinhosz.unifg.service.OmdbService;
import io.github.robinhosz.unifg.utils.MenuUtil;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import java.util.InputMismatchException;
import java.util.Scanner;

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

		Scanner sc = new Scanner(System.in);
		MovieCache movieCache = new MovieCache();
		Gson gson = new Gson();
		boolean fimDaExecucao = true;

		while (fimDaExecucao) {

			try {
				System.out.println("\n\nMENU PRINCIPAL PARA INFORMAÇÕES SOBRE FILMES! \n\n" +
						"1: Pesquisar filme por titulo \n" +
						"2: Sair do programa\n ");
				Integer escolha = sc.nextInt();

				switch (escolha) {
					case 1 -> {
						sc.nextLine();
						System.out.println("Digite o nome do filme \n");
						String movieTitle = sc.nextLine();

						System.out.println("Verificando se o filme existe em cache....");

						String cachedResponse = movieCache.getFromCache(movieTitle);

						Thread.sleep(5); //Simulando uma busca com o Thread.sleep ;)

						if (cachedResponse != null) {
							System.out.println("Achamos o filme em cache! \n");

							Movie movie = gson.fromJson(cachedResponse, Movie.class);
							MenuUtil.imprimeMenu(movie);

						} else {
							System.out.println("Filme não encontrado em cache, fazendo requisição a API...");
							//Chamada API
							String response = omdbService.getMovieDetails(movieTitle, false);

							if (response.contains("\"Response\":\"False\"")) {

								System.err.println("Filme não encontrado na base de dados do OMDB, verifique se ele existe! \n");

							} else {
								movieCache.addToCache(movieTitle, response);
								Movie movie = gson.fromJson(response, Movie.class);
								MenuUtil.imprimeMenu(movie);
							}
						}
					}
					case 2 -> {
						System.out.println("Obrigado por utilizar o nosso sistema, volte sempre!");
						fimDaExecucao = false;
					}

					default -> {
						System.err.println("Insira um número correto entre 1 e 2! \n");
					}
				}

			} catch (InputMismatchException e) {
				System.err.println("Insira um número ao invez de letra! \n");
				sc.nextLine();
			}

		}
	}

}
