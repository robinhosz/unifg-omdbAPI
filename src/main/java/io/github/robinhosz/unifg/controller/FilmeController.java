package io.github.robinhosz.unifg.controller;

import io.github.robinhosz.unifg.service.OmdbService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping
@CrossOrigin(origins = "*", maxAge = 3600)
@Slf4j
public class FilmeController {

    @Autowired
    private OmdbService omdbService;

    @GetMapping
    public String getMovies(@RequestHeader("filme") String filme) {

        log.info("O front end mandou esse filme: -> [{}]", filme);

        return omdbService.getMovieDetails(filme);

    }

}
