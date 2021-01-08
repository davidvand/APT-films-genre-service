package www.film.microservice;


import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import www.film.microservice.model.Film_Genre;

import www.film.microservice.repository.FilmGenreRepository;

import static org.hamcrest.Matchers.is;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;



@SpringBootTest
@AutoConfigureMockMvc
public class FilmGenreControllerIntegrationTests {
    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private FilmGenreRepository filmGenreRepository;

    private Film_Genre FilmGenre1 = new Film_Genre(001,"Taken","Actie");
    private Film_Genre FilmGenre2 = new Film_Genre(002,"21 jump street","Comedy");

    private ObjectMapper mapper = new ObjectMapper();


    @BeforeEach
    public void beforeAllTests() {
        //FilmGenreRepository.deleteAll();
        filmGenreRepository.save(FilmGenre1);
        filmGenreRepository.save(FilmGenre2);
    }

    @AfterEach
    public void afterAllTests() {
        //Watch out with deleteAll() methods when you have other data in the test database!
        filmGenreRepository.delete(FilmGenre1);
        filmGenreRepository.delete(FilmGenre2);
    }

    @Test
    public void givenFilmGenre_whenGetFilmGenreByFilmNaam_thenReturnJsonFilmGenre() throws Exception {

        mockMvc.perform(get("/filmGenres/film/{naam}","Taken"))
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].film_naam",is("Taken")));
    }

    @Test
    public void givenFilmGenre_whenGetFilmGenreByGenreNaam_thenReturnJsonFilmGenre() throws Exception {

        mockMvc.perform(get("/filmGenres/genre/{naam}","Actie"))
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].genre_naam",is("Actie")));
    }

    @Test
    public void givenFilmGenre_whenGetFilmGenreByGenreNaamAndFilmNaam_thenReturnJsonFilmGenre() throws Exception {

        mockMvc.perform(get("/filmGenres/{film}/{genre}","Taken","Actie"))
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.film_naam",is("Taken")))
                .andExpect(jsonPath("$.genre_naam",is("Actie")));
    }



    @Test
    public void givenFilmGenre_whenGetFilmGenreById_thenReturnJsonFilmGenre() throws Exception {

        mockMvc.perform(get("/filmGenres/{id}",1))
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.film_naam",is("Taken")))
                .andExpect(jsonPath("$.genre_naam",is("Actie")));
    }

    @Test
    public void whenPostFilmGenre_thenReturnJsonFilmGenre() throws Exception {
        Film_Genre FilmGenreWestern = new Film_Genre(3,"Extraction","Thriller");

        mockMvc.perform(post("/filmGenres")
                .content(mapper.writeValueAsString(FilmGenreWestern))
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id", is(4)))
                .andExpect(jsonPath("$.film_naam", is("Extraction")))
                .andExpect(jsonPath("$.genre_naam", is("Thriller")));
    }

    @Test
    public void givenFilmGenre_whenPutFilmGenre_thenReturnJsonFilmGenre() throws Exception {

        Film_Genre updatedFilmGenre = new Film_Genre(1,"Taken","Thriller");

        mockMvc.perform(put("/filmGenres")
                .content(mapper.writeValueAsString(updatedFilmGenre))
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id", is(1)))
                .andExpect(jsonPath("$.film_naam", is("Taken")))
                .andExpect(jsonPath("$.genre_naam", is("Thriller")));
    }

    @Test
    public void givenFilmGenre_whenDeleteFilmGenre_thenStatusOk() throws Exception {

        mockMvc.perform(delete("/filmGenres/{id}/", 4)
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());
    }

    @Test
    public void givenNoFilmGenre_whenDeleteFilmGenre_thenStatusNotFound() throws Exception {

        mockMvc.perform(delete("/filmGenres/{id}", 999)
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isMethodNotAllowed());
    }


}
