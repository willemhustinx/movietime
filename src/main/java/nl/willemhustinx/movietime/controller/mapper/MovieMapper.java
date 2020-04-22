package nl.willemhustinx.movietime.controller.mapper;

import nl.willemhustinx.movietime.controller.dto.MovieDTO;
import nl.willemhustinx.movietime.model.Movie;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

@Service
public class MovieMapper {
    private final ModelMapper modelMapper = new ModelMapper();

    public Movie convertToNewEntity(final MovieDTO movieDTO) {
        return modelMapper.map(movieDTO, Movie.class);
    }

    public MovieDTO convertToDTO(final Movie movie) {
        return modelMapper.map(movie, MovieDTO.class);
    }
}
