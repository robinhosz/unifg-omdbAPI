package io.github.robinhosz.unifg.service;

import io.github.robinhosz.unifg.model.Movie;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;

@Service
public class OmdbService {

    private static final String OMDB_API_URL = "http://www.omdbapi.com/";

    private final RestTemplate restTemplate;
    private final String apiKey;

    public OmdbService(RestTemplate restTemplate, @Value("${omdb.api.key}") String apiKey) {
        this.restTemplate = restTemplate;
        this.apiKey = apiKey;
    }

    public String getMovieDetails(String title) {
        String encodedTitle;
        try {
            encodedTitle = URLEncoder.encode(title, "UTF-8");
        } catch (UnsupportedEncodingException e) {
            // Lidar com a exceção de codificação caso ocorra
            encodedTitle = title; // Usar o título não codificado como alternativa
        }
        String url = UriComponentsBuilder.fromHttpUrl(OMDB_API_URL)
                .queryParam("apikey", "f154af53")
                .queryParam("t", encodedTitle)
                .toUriString();

        ResponseEntity<String> response = restTemplate.exchange(url, HttpMethod.GET, null, String.class);
         String  responseBody = response.getBody(); // Obtém o corpo da resposta como uma String
        return responseBody;
    }
}
