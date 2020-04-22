package nl.willemhustinx.movietime.controller.mapper;

import nl.willemhustinx.movietime.controller.dto.MovieDTO;
import nl.willemhustinx.movietime.model.Movie;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;


@DisplayName("MovieMapper Test")
public class MovieMapperTest {

    private static MovieMapper mapper;

    @BeforeAll
    public static void init() {
        mapper = new MovieMapper();
    }

    @Test
    public void movieToMovieDTO() {
        Movie movie = new Movie();
        movie.setImdb("movieToMovieDTO");
        movie.setTitle("movieToMovieDTO");

        MovieDTO movieDTO = mapper.convertToDTO(movie);

        assertEquals(movie.getTitle(), movieDTO.getTitle());
        assertEquals(movie.getImdb(), movieDTO.getImdb());
    }

    @Test
    public void movieDTOToMovie() {
        MovieDTO movieDTO = new MovieDTO();
        movieDTO.setImdb("movieDTOToMovie");
        movieDTO.setTitle("movieDTOToMovie");

        Movie movie = mapper.convertToNewEntity(movieDTO);

        assertEquals(movieDTO.getTitle(), movie.getTitle());
        assertEquals(movieDTO.getImdb(), movie.getImdb());
    }

    @Test
    public void movieToMovieDTOToMovie() {
        Movie movie = new Movie();
        movie.setImdb("imdbString");
        movie.setTitle("MovieTitle");
        movie.setRuntime(120);
        MovieDTO movieDTO = mapper.convertToDTO(movie);

        Movie newMovie = mapper.convertToNewEntity(movieDTO);

        assertNotEquals(movie.getRuntime(), newMovie.getRuntime());
        assertEquals(movie.getTitle(), newMovie.getTitle());
        assertEquals(movie.getImdb(), newMovie.getImdb());
    }
}
