package nl.willemhustinx.movietime.controller;

import nl.willemhustinx.movietime.controller.dto.MovieDTO;
import nl.willemhustinx.movietime.controller.mapper.MovieMapper;
import nl.willemhustinx.movietime.exception.NotFoundException;
import nl.willemhustinx.movietime.model.Customer;
import nl.willemhustinx.movietime.model.Movie;
import nl.willemhustinx.movietime.service.CustomerService;
import nl.willemhustinx.movietime.service.MovieService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Spy;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.ResponseEntity;
import org.springframework.mock.web.MockHttpServletRequest;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import java.util.*;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.when;


@ExtendWith(MockitoExtension.class)
@DisplayName("MovieController Test")
public class MovieControllerTest {
    @InjectMocks
    MovieController movieController;

    @Mock
    MovieService movieService;

    @Mock
    CustomerService customerService;

    @Spy
    MovieMapper mapper;

    @Test
    public void getAllMovies() {
        List<Movie> movieList = new ArrayList<>();
        Movie movie = new Movie();
        movie.setTitle("getAllMovies");
        movieList.add(movie);

        MockHttpServletRequest request = new MockHttpServletRequest();
        RequestContextHolder.setRequestAttributes(new ServletRequestAttributes(request));

        when(movieService.getAllMovies()).thenReturn(movieList);

        ResponseEntity<List<MovieDTO>> responseEntity = movieController.getAllMovies();

        assertEquals(200, responseEntity.getStatusCodeValue());
        assertEquals(1, responseEntity.getBody().size());
    }

    @Test
    public void getSuggetionMovie() {
        List<Movie> movieList = new ArrayList<>();
        Movie movie = new Movie();
        movie.setTitle("getSuggetionMovie");
        Set<String> genres = new HashSet<>();
        genres.add("Drama");
        genres.add("Comedy");
        genres.add("Romcom");
        movie.setGenres(genres);
        movieList.add(movie);

        Customer customer = new Customer();
        customer.setName("Willem");
        Map<String, String> interests = new HashMap<>();
        interests.put("genres", "Romcom");
        customer.setInterests(interests);

        MockHttpServletRequest request = new MockHttpServletRequest();
        RequestContextHolder.setRequestAttributes(new ServletRequestAttributes(request));

        when(movieService.getAllMovies()).thenReturn(movieList);
        when(customerService.getCustomerById(1L)).thenReturn(customer);

        ResponseEntity<List<MovieDTO>> responseEntity = movieController.getSuggestionMovies(1L);

        assertEquals(200, responseEntity.getStatusCodeValue());
        assertEquals(1, responseEntity.getBody().size());
    }

    @Test
    public void getSuggetionMovieNoCustomer() {
        MockHttpServletRequest request = new MockHttpServletRequest();
        RequestContextHolder.setRequestAttributes(new ServletRequestAttributes(request));

        when(customerService.getCustomerById(1L)).thenReturn(null);

        Assertions.assertThrows(NotFoundException.class, () -> movieController.getSuggestionMovies(1L));
    }
}
