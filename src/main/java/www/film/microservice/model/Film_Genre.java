package www.film.microservice.model;

import javax.persistence.*;

@Entity
public class Film_Genre {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private String film_naam;
    private  String genre_naam;




    public  Film_Genre()
    {

    }


    public Film_Genre(Integer id, String film_naam, String genre_naam) {
        this.id = id;
        this.film_naam = film_naam;
        this.genre_naam = genre_naam;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }


    public String getFilm_naam() {
        return film_naam;
    }

    public void setFilm_naam(String film_naam) {
        this.film_naam = film_naam;
    }

    public String getGenre_naam() {
        return genre_naam;
    }

    public void setGenre_naam(String genre_naam) {
        this.genre_naam = genre_naam;
    }
}
