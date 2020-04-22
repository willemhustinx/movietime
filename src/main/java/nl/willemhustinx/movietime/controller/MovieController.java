package nl.willemhustinx.movietime.controller;

import nl.willemhustinx.movietime.controller.dto.MovieDTO;
import nl.willemhustinx.movietime.controller.mapper.MovieMapper;
import nl.willemhustinx.movietime.exception.NotFoundException;
import nl.willemhustinx.movietime.model.Customer;
import nl.willemhustinx.movietime.model.Movie;
import nl.willemhustinx.movietime.service.CustomerService;
import nl.willemhustinx.movietime.service.MovieService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/movie")
public class MovieController {

    private final MovieService movieService;
    private final CustomerService customerService;
    private final MovieMapper mapper;

    @Autowired
    public MovieController(MovieService movieService, CustomerService customerService, MovieMapper mapper) {
        this.movieService = movieService;
        this.customerService = customerService;
        this.mapper = mapper;
    }

    @GetMapping
    public ResponseEntity<List<MovieDTO>> getAllMovies() {
        List<Movie> list = movieService.getAllMovies();
        List<MovieDTO> returnDTOList = list.stream().map(mapper::convertToDTO).collect(Collectors.toList());
        return new ResponseEntity<>(returnDTOList, new HttpHeaders(), HttpStatus.OK);
    }

    @GetMapping("/suggestion/customer/id/{customerID}")
    public ResponseEntity<List<MovieDTO>> getSuggestionMovies(@PathVariable Long customerID) {

        Customer customer = customerService.getCustomerById(customerID);
        if (customer == null) {
            throw new NotFoundException("Customer not found");
        }

        Map<String, String> interests = customerService.getCustomerById(customerID).getInterests();

        List<Movie> movieList = movieService.getAllMovies();
        MovieChecker movieChecker = new MovieChecker(movieList);

        List<Movie> returnList = movieChecker.filterList(interests);
        List<MovieDTO> returnDTOList = returnList.stream().map(mapper::convertToDTO).collect(Collectors.toList());
        return new ResponseEntity<>(returnDTOList, new HttpHeaders(), HttpStatus.OK);
    }
}
