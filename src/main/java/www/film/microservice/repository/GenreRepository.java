package www.film.microservice.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import www.film.microservice.model.Genre;

import java.util.List;

@Repository
public interface GenreRepository extends JpaRepository<Genre, String> {
    Genre findGenresById(Integer id);
    Genre findGenresByNaam(String naam);
}