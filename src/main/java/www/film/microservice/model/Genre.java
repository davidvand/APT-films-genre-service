package www.film.microservice.model;

import javax.persistence.*;

import org.springframework.data.mongodb.core.mapping.Document;

import java.util.List;


@Entity
public class Genre {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;
    private String naam;



    public Genre(){

    }

    public Genre( String naam) {
        this.naam = naam;
    }




    public String getNaam() {
        return naam;
    }

    public void setNaam(String naam) {
        this.naam = naam;
    }




    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }


}
