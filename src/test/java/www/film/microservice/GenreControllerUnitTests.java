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
    private FilmGenreRepository genreRepository;

    private ObjectMapper mapper = new ObjectMapper();

    @Test
    public void givenFilmGenre_whenGetFilmGenresByGenreId_thenReturnJsonFilmGenre() throws Exception {
        Film_Genre filmGenreGenre1Film1 = new Film_Genre(12,1);
        Film_Genre filmGenreGenre1Film2 = new Film_Genre(12,4);

        List<Film_Genre> film_genreList = new ArrayList<>();
        film_genreList.add(filmGenreGenre1Film1);
        film_genreList.add(filmGenreGenre1Film2);


        given(genreRepository.findAllByGenre_id(12)).willReturn(film_genreList);

        mockMvc.perform(get("/FilmGenres/genre/{id}",12))
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                //.andExpect(jsonPath("$", hasSize(2)))
                .andExpect(jsonPath("$[0].genre_id",is(12)))
                .andExpect(jsonPath("$[0].filmid",is(1)))
                .andExpect(jsonPath("$[1].genre_id",is(12)))
                .andExpect(jsonPath("$[1].filmid",is(4)));
    }
}
