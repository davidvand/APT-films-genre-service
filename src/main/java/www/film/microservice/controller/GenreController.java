package www.film.microservice.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import www.film.microservice.model.Genre;
import www.film.microservice.repository.GenreRepository;

import javax.annotation.PostConstruct;
import java.util.List;

@RestController
public class GenreController {

    @Autowired
    private GenreRepository genreRepository;

    @PostConstruct
    public void fillDB(){
        if(genreRepository.count()==0){
            genreRepository.save(new Genre("Actie"));
            genreRepository.save(new Genre("Comedy"));
            genreRepository.save(new Genre("Thriller"));
        }
    }

    @GetMapping("/genres/naam/{naam}")
    public List<Genre> getGenreByNaam(@PathVariable String naam){
        return genreRepository.findGenresByNaam(naam);
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
