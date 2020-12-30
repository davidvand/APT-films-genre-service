package www.film.microservice;

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
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;

@SpringBootTest
@AutoConfigureMockMvc
public class GenreControllerIntegrationTests {
    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private GenreRepository genreRepository;

    private Genre genre1 = new Genre("Romantiek");
    private Genre genre2 = new Genre("sci-fi");

    @BeforeEach
    public void beforeAllTests() {
        genreRepository.deleteAll();
        genreRepository.save(genre1);
        genreRepository.save(genre2);
    }

    @AfterEach
    public void afterAllTests() {
        //Watch out with deleteAll() methods when you have other data in the test database!
        genreRepository.deleteAll();
    }

    @Test
    public void givenGenre_whenGetGenreByNaam_thenReturnJsonGenre() throws Exception {

        mockMvc.perform(get("/genres/naam/{naam}","Romantiek"))
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].naam",is("Romantiek")));
    }



}
