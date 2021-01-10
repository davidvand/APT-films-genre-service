package www.film.microservice.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
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
            genreRepository.save(new Genre(001,"Actie"));
            genreRepository.save(new Genre(002,"Comedy"));
            genreRepository.save(new Genre(003,"Thriller"));
        }
    }

    @GetMapping("/genres/naam/{naam}")
    public Genre getGenreByNaam(@PathVariable String naam){
        return genreRepository.findGenresByNaam(naam);
    }


    @PostMapping("/genres")
    public Genre addGenre(@RequestBody Genre genre){
        genreRepository.save(genre);
        return genre;
    }

    @PutMapping("/genres")
    public Genre updateGenre(@RequestBody Genre updatedGenre){
        Genre retrievedGenre = genreRepository.findGenresById(updatedGenre.getId());

        retrievedGenre.setNaam(updatedGenre.getNaam());

        genreRepository.save(retrievedGenre);

        return retrievedGenre;
    }

    @DeleteMapping("/genres/{id}/")
    public ResponseEntity deleteGenre(@PathVariable Integer id){
        Genre genre = genreRepository.findGenresById(id);
        if(genre!=null){
            genreRepository.delete(genre);
            return ResponseEntity.ok().build();
        }else{
            return ResponseEntity.notFound().build();
        }
    }


}
