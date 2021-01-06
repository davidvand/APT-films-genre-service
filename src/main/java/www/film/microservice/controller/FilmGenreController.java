package www.film.microservice.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import www.film.microservice.model.Film_Genre;
import www.film.microservice.model.Genre;
import www.film.microservice.repository.FilmGenreRepository;
import www.film.microservice.repository.GenreRepository;

import javax.annotation.PostConstruct;
import java.util.List;

@RestController
public class FilmGenreController {

    @Autowired
    private FilmGenreRepository FilmGenreRepository;

    @Autowired
    private GenreRepository genreRepository;


    @GetMapping("/FilmGenres/film/{naam}")
    public List<Film_Genre> getFilmGenreByFilmId(@PathVariable String naam){
        return FilmGenreRepository.findAllByFilm_id(naam);
    }

    @GetMapping("/FilmGenres/genre/{naam}")
    public List<Film_Genre> getFilmGenresByGenreId(@PathVariable String naam){
        return FilmGenreRepository.findAllByGenre_id(naam);
    }

    @PostMapping("/FilmGenres")
    public Film_Genre addFilmGenre(@RequestBody Film_Genre FilmGenre){
        FilmGenreRepository.save(FilmGenre);
        return FilmGenre;
    }

}
