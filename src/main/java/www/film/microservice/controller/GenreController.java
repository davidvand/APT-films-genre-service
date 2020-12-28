package www.film.microservice.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import www.film.microservice.model.Genre;
import www.film.microservice.repository.GenreRepository;

import java.util.List;

@RestController
public class GenreController {

    @Autowired
    private GenreRepository genreRepository;

    @PostMapping
    public void fillDB(){
        if(genreRepository.count()==0){
            genreRepository.save(new Genre(003,"Actie"));
            genreRepository.save(new Genre(002,"Drama"));
            genreRepository.save(new Genre(001,"Horror"));
        }
    }

    @GetMapping("/genres/naam/{naam}")
    public List<Genre> getBooksByTitle(@PathVariable String naam){
        return genreRepository.findGenresByGenreNaam(naam);
    }

    @GetMapping("/genres/{id}")
    public Genre getGenreByID(@PathVariable Integer id){
        return genreRepository.findGenresById(id);
    }

    @PostMapping("/genres")
    public Genre addGenre(@RequestBody Genre genre){
        genreRepository.save(genre);
        return genre;
    }


}
