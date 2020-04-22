package nl.willemhustinx.movietime.service;

import nl.willemhustinx.movietime.model.Movie;
import nl.willemhustinx.movietime.repository.MovieRepository;
import org.junit.Before;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;


@ExtendWith(MockitoExtension.class)
@DisplayName("MovieService Test")
public class MovieServiceTest {

    @InjectMocks
    MovieService movieService;

    @Mock
    MovieRepository repository;

    @Before
    public void init() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void getAllMovies() {
        List<Movie> movieList = new ArrayList<>();
        Movie movie = new Movie();
        movie.setTitle("Filmpje");
        movieList.add(movie);

        Mockito.when(repository.findAll()).thenReturn(movieList);

        assertEquals(1, movieService.getAllMovies().size());
        assertEquals(movie.getTitle(), movieService.getAllMovies().get(0).getTitle());
    }
}
