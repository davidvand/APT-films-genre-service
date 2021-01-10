package www.film.microservice;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;
import www.film.microservice.model.Film_Genre;
import www.film.microservice.model.Genre;
import www.film.microservice.repository.FilmGenreRepository;
import www.film.microservice.repository.GenreRepository;
import static org.hamcrest.Matchers.is;
import static org.hamcrest.collection.IsCollectionWithSize.hasSize;
import static org.mockito.BDDMockito.given;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
import org.springframework.http.MediaType;

import java.util.ArrayList;
import java.util.List;


@SpringBootTest
@AutoConfigureMockMvc
public class GenreControllerUnitTests {
    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private GenreRepository genreRepository;

    private ObjectMapper mapper = new ObjectMapper();

    @Test
    public void givenGenre_whenGetGenreByNaam_thenReturnJsonGenres() throws Exception {
        Genre genre1 = new Genre(001,"Actie");



        given(genreRepository.findGenresByNaam("Actie")).willReturn(genre1);


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

        Genre genreComedy = new Genre(2,"Comedy");


        given(genreRepository.findGenresById(002)).willReturn(genreComedy);

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

        Genre genreToBeDeleted = new Genre(4,"Western");


        given(genreRepository.findGenresById(4)).willReturn(genreToBeDeleted);

        mockMvc.perform(delete("/genres/{id}/", 4)
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());
    }

    @Test
    public void givenNoGenre_whenDeleteGenre_thenStatusNotFound() throws Exception {
        given(genreRepository.findGenresById(999)).willReturn(null);


        mockMvc.perform(delete("/genres/{id}", 999)
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isNotFound());
    }
}
