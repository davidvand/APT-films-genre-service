package www.film.microservice.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import www.film.microservice.model.Film_Genre;
import www.film.microservice.model.Genre;

import java.util.List;

public interface FilmGenreRepository extends JpaRepository<Film_Genre, String> {
    @Query("SELECT u FROM Film_Genre u WHERE u.film_id = ?1")
    List<Film_Genre> findAllByFilm_id(Integer film_id);
    @Query("SELECT u FROM Film_Genre u WHERE u.genre_id = ?1")
    List<Film_Genre> findAllByGenre_id(Integer genre_id);
}