package nl.willemhustinx.movietime.controller;

import nl.willemhustinx.movietime.model.Actor;
import nl.willemhustinx.movietime.model.Movie;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.*;

import static org.junit.jupiter.api.Assertions.assertEquals;

@DisplayName("MovieChecker Test")
public class MovieCheckerTest {

    private static List<Movie> movieList;

    @BeforeAll
    public static void init() {
        movieList = new ArrayList<>();

        Movie movie1 = new Movie();
        movie1.setTitle("MOVIE 1");
        Set<String> genres1 = new HashSet<>();
        genres1.add("Action");
        genres1.add("Thriller");
        genres1.add("Romcom");
        movie1.setGenres(genres1);
        Set<Actor> actors1 = new HashSet<>();
        actors1.add(new Actor("Willem Hustinx", "Male"));
        movie1.setActors(actors1);
        movie1.setRuntime(125);
        movie1.setRating(8.0);
        movieList.add(movie1);

        Movie movie2 = new Movie();
        movie2.setTitle("MOVIE 2");
        Set<String> genres2 = new HashSet<>();
        genres2.add("Drama");
        genres2.add("Comedy");
        genres2.add("Romcom");
        movie2.setGenres(genres2);
        Set<Actor> actors2 = new HashSet<>();
        actors2.add(new Actor("Minnie Mouse", "Female"));
        movie2.setActors(actors2);
        movie2.setRuntime(120);
        movie2.setRating(8.1);
        movieList.add(movie2);

        Movie movie3 = new Movie();
        movie3.setTitle("MOVIE 3");
        Set<String> genres3 = new HashSet<>();
        genres3.add("Thriller");
        genres3.add("Fantasy");
        genres3.add("Romcom");
        movie3.setGenres(genres3);
        Set<Actor> actors3 = new HashSet<>();
        actors3.add(new Actor("Minnie Mouse", "Female"));
        actors3.add(new Actor("Robin Hood", "Male"));
        movie3.setActors(actors3);
        movie3.setRuntime(15);
        movie3.setRating(6.5);
        movieList.add(movie3);
    }

    @Test
    public void movieCheckerRatingTest() {
        Map<String, String> interests = new HashMap<>();
        interests.put("ratings", "8+");

        MovieChecker movieChecker = new MovieChecker(movieList);
        List<Movie> movies = movieChecker.filterList(interests);

        assertEquals(1, movies.size());
    }

    @Test
    public void movieCheckerRuntimeTest() {
        Map<String, String> interests = new HashMap<>();
        interests.put("runtime", "< 125 minutes");

        MovieChecker movieChecker = new MovieChecker(movieList);
        List<Movie> movies = movieChecker.filterList(interests);

        assertEquals(2, movies.size());
    }

    @Test
    public void movieCheckerGenreTest() {
        Map<String, String> interests = new HashMap<>();
        interests.put("genres", "Romcom");

        MovieChecker movieChecker = new MovieChecker(movieList);
        List<Movie> movies = movieChecker.filterList(interests);

        assertEquals(3, movies.size());
    }

    @Test
    public void movieCheckerGenderTest() {
        Map<String, String> interests = new HashMap<>();
        interests.put("gender", "Male");

        MovieChecker movieChecker = new MovieChecker(movieList);
        List<Movie> movies = movieChecker.filterList(interests);

        assertEquals(2, movies.size());
    }

    @Test
    public void movieCheckerActorTest() {
        Map<String, String> interests = new HashMap<>();
        interests.put("actors", "Willem Hustinx");

        MovieChecker movieChecker = new MovieChecker(movieList);
        List<Movie> movies = movieChecker.filterList(interests);

        assertEquals(1, movies.size());
    }

    @Test
    public void movieCheckerActorAndGenreTest() {
        Map<String, String> interests = new HashMap<>();
        interests.put("actors", "Willem Hustinx");
        interests.put("genres", "Fantasy");

        MovieChecker movieChecker = new MovieChecker(movieList);
        List<Movie> movies = movieChecker.filterList(interests);

        assertEquals(2, movies.size());
    }

    @Test
    public void movieCheckerTitleTest() {
        Map<String, String> interests = new HashMap<>();
        interests.put("title", "Movie 2");

        MovieChecker movieChecker = new MovieChecker(movieList);
        List<Movie> movies = movieChecker.filterList(interests);

        assertEquals(0, movies.size());
    }


}
