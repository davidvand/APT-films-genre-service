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
import www.film.microservice.model.Genre;
import www.film.microservice.repository.GenreRepository;

import static org.hamcrest.Matchers.is;
import static org.hamcrest.collection.IsCollectionWithSize.hasSize;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;

@SpringBootTest
@AutoConfigureMockMvc
public class GenreControllerIntegrationTests {
    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private GenreRepository genreRepository;

    private Genre genre1 = new Genre(001,"Actie");
    private Genre genre2 = new Genre(002,"Comedy");

    private ObjectMapper mapper = new ObjectMapper();


    @BeforeEach
    public void beforeAllTests() {
        //genreRepository.deleteAll();
        genreRepository.save(genre1);
        genreRepository.save(genre2);
    }

    @AfterEach
    public void afterAllTests() {
        //Watch out with deleteAll() methods when you have other data in the test database!
        genreRepository.delete(genre1);
        genreRepository.delete(genre2);
    }

    @Test
    public void givenGenre_whenGetGenreByNaam_thenReturnJsonGenre() throws Exception {

        mockMvc.perform(get("/genres/naam/{naam}","Actie"))
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.naam",is("Actie")));
    }


    @Test
    public void whenPostGenre_thenReturnJsonGenre() throws Exception {
        Genre genreWestern = new Genre(4,"Western");

        mockMvc.perform(post("/genres")
                .content(mapper.writeValueAsString(genreWestern))
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id", is(4)))
                .andExpect(jsonPath("$.naam", is("Western")));
    }

    @Test
    public void givenGenre_whenPutGenre_thenReturnJsonGenre() throws Exception {

        Genre updatedGenre = new Genre(2,"Comedy");

        mockMvc.perform(put("/genres")
                .content(mapper.writeValueAsString(updatedGenre))
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id", is(2)))
                .andExpect(jsonPath("$.naam", is("Comedy")));
    }

    @Test
    public void givenGenre_whenDeleteGenre_thenStatusOk() throws Exception {

        mockMvc.perform(delete("/genres/{id}/", 4)
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());
    }

    @Test
    public void givenNoGenre_whenDeleteGenre_thenStatusNotFound() throws Exception {

        mockMvc.perform(delete("/genres/{id}", 999)
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isNotFound());
    }







}
