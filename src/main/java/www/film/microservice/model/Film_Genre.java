package www.film.microservice.model;

import javax.persistence.*;

@Entity
public class Film_Genre {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;
    private Integer film_id;
    private  Integer genre_id;






    public  Film_Genre()
    {

    }

    public Film_Genre(Integer genre_id, Integer film_id) {
        this.genre_id = genre_id;
        this.film_id = film_id;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }


    public Integer getFilmid() {
        return film_id;
    }

    public void setFilmid(Integer filmid) {
        this.film_id = filmid;
    }

    public Integer getGenre_id() {
        return genre_id;
    }

    public void setGenre_id(Integer genre_id) {
        this.genre_id = genre_id;
    }
}
