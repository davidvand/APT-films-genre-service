package www.film.microservice.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import www.film.microservice.model.Film_Genre;
import www.film.microservice.model.Genre;
import www.film.microservice.repository.FilmGenreRepository;
import www.film.microservice.repository.GenreRepository;
import org.springframework.http.ResponseEntity;


import javax.annotation.PostConstruct;
import java.util.List;

@RestController
public class FilmGenreController {

    @Autowired
    private FilmGenreRepository FilmGenreRepository;

    @Autowired
    private GenreRepository genreRepository;


    @GetMapping("/filmGenres/film/{naam}")
    public List<Film_Genre> getFilmGenreByFilm(@PathVariable String naam){
        return FilmGenreRepository.findAllByFilm(naam);
    }

    @GetMapping("/filmGenres/genre/{naam}")
    public List<Film_Genre> getFilmGenresByGenre(@PathVariable String naam){
        return FilmGenreRepository.findAllByGenre(naam);
    }

    @GetMapping("/filmGenres/{film}/{genre}")
    public Film_Genre getFilmGenresByFilmNaamGenreNaam(@PathVariable String film, @PathVariable String genre){
        return FilmGenreRepository.findByFilm_naamAndGenre_naam(genre,film);
    }



    @PostMapping("/filmGenres")
    public Film_Genre addFilmGenre(@RequestBody Film_Genre FilmGenre){
        FilmGenreRepository.save(FilmGenre);
        return FilmGenre;
    }

    @PutMapping("/filmGenres")
    public Film_Genre updateFilmGenre(@RequestBody Film_Genre updatedGenre){
        Film_Genre retrievedFilmGenre = FilmGenreRepository.findById(updatedGenre.getId());

        retrievedFilmGenre.setGenre_naam(updatedGenre.getGenre_naam());

        FilmGenreRepository.save(retrievedFilmGenre);

        return retrievedFilmGenre;
    }

    @DeleteMapping("/filmGenres/{id}/")
    public ResponseEntity deleteGenre(@PathVariable Integer id){
        Film_Genre filmGenre = FilmGenreRepository.findById(id);
        if(filmGenre!=null){
            FilmGenreRepository.delete(filmGenre);
            return ResponseEntity.ok().build();
        }else{
            return ResponseEntity.notFound().build();
        }
    }

}
