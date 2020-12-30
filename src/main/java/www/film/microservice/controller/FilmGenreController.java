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

    @PostConstruct
    public void fillDB(){
        if(FilmGenreRepository.count()==0){
            FilmGenreRepository.save(new Film_Genre(12,1));
            FilmGenreRepository.save(new Film_Genre(13,2));
            FilmGenreRepository.save(new Film_Genre(14,3));
            FilmGenreRepository.save(new Film_Genre(12,4));

        }
    }

    @GetMapping("/FilmGenres/film/{id}")
    public List<Film_Genre> getFilmGenreByFilmId(@PathVariable Integer id){
        return FilmGenreRepository.findAllByFilm_id(id);
    }

    @GetMapping("/FilmGenres/genre/{id}")
    public List<Film_Genre> getFilmGenresByGenreId(@PathVariable Integer id){
        return FilmGenreRepository.findAllByGenre_id(id);
    }

    @PostMapping("/FilmGenres")
    public Film_Genre addFilmGenre(@RequestBody Film_Genre FilmGenre){
        FilmGenreRepository.save(FilmGenre);
        return FilmGenre;
    }

}
