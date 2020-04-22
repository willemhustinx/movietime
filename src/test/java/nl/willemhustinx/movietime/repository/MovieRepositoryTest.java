package nl.willemhustinx.movietime.repository;

import nl.willemhustinx.movietime.model.Movie;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;


@ExtendWith(SpringExtension.class)
@DataJpaTest
@DisplayName("MovieRepository Test")
public class MovieRepositoryTest {

    @Autowired
    private TestEntityManager entityManager;

    @Autowired
    private MovieRepository movieRepository;

    @Test
    public void findAllMovies() {
        Movie movie = new Movie();
        movie.setTitle("Test");
        entityManager.persist(movie);

        Movie movie2 = new Movie();
        movie2.setTitle("Filmpje");
        entityManager.persist(movie2);

        entityManager.flush();

        List<Movie> moviesFound = movieRepository.findAll();

        assertEquals(2, moviesFound.size());
    }

    @Test
    public void findAllMoviesEmpty() {
        List<Movie> moviesFound = movieRepository.findAll();

        assertEquals(0, moviesFound.size());
    }

}
