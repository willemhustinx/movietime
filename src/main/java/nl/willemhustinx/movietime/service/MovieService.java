package nl.willemhustinx.movietime.service;

import nl.willemhustinx.movietime.controller.mapper.MovieMapper;
import nl.willemhustinx.movietime.model.Movie;
import nl.willemhustinx.movietime.repository.MovieRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MovieService {

    private final MovieRepository repository;

    @Autowired
    public MovieService(MovieRepository repository, MovieMapper mapper) {
        this.repository = repository;
    }

    public List<Movie> getAllMovies() {
        return repository.findAll();
    }
}
