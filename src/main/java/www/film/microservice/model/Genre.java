package www.film.microservice.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import org.springframework.data.mongodb.core.mapping.Document;


@Entity
public class Genre {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;
    private String genreNaam;

    public Genre(){

    }

    public Genre(Integer id, String genreNaam) {
        this.id = id;
        this.genreNaam = genreNaam;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }



    public String getGenreNaam() {
        return genreNaam;
    }

    public void setGenreNaam(String genreNaam) {
        this.genreNaam = genreNaam;
    }
}
