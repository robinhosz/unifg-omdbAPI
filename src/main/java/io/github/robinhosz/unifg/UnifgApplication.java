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
public class UnifgApplication {


	public static void main(String[] args) {
		SpringApplication.run(UnifgApplication.class, args);
	}


	}

