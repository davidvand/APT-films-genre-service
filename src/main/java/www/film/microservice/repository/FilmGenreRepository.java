package www.film.microservice.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import www.film.microservice.model.Film_Genre;
import www.film.microservice.model.Genre;

import java.util.List;

public interface FilmGenreRepository extends JpaRepository<Film_Genre, String> {
    @Query("SELECT u FROM Film_Genre u WHERE u.film_naam = ?1")
    List<Film_Genre> findAllByFilm(String film_naam);
    @Query("SELECT u FROM Film_Genre u WHERE u.genre_naam = ?1")
    List<Film_Genre> findAllByGenre(String genre_naam);
    @Query("SELECT u FROM Film_Genre u WHERE u.film_naam = ?1 AND u.genre_naam = ?2")
    Film_Genre findByFilm_naamAndGenre_naam(String film_naam, String genre_naam);
    @Query("SELECT u FROM Film_Genre u WHERE u.id = ?1")
    Film_Genre findById(Integer id);


}
