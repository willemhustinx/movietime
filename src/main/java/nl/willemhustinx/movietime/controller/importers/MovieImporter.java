package nl.willemhustinx.movietime.controller.importers;

import nl.willemhustinx.movietime.model.Actor;
import nl.willemhustinx.movietime.model.Movie;
import nl.willemhustinx.movietime.model.Movies;
import nl.willemhustinx.movietime.repository.MovieRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Unmarshaller;
import java.io.File;
import java.util.Objects;
import java.util.logging.Level;
import java.util.logging.Logger;

@Component
public class MovieImporter {

    private static final Logger LOG
            = Logger.getLogger(String.valueOf(MovieRepository.class));
    private final MovieRepository repository;

    @Autowired
    public MovieImporter(MovieRepository movieRepository) {
        this.repository = movieRepository;
        this.init();
    }

    private void init() {
        LOG.info("Start importing Movies");

        File xmlFile;
        ClassLoader classLoader = ClassLoader.getSystemClassLoader();

        try {
            xmlFile = new File(Objects.requireNonNull(classLoader.getResource("movies.xml")).getPath());

            JAXBContext jaxbContext = JAXBContext.newInstance(Movies.class);
            Unmarshaller jaxbUnmarshaller = jaxbContext.createUnmarshaller();

            Movies movies = (Movies) jaxbUnmarshaller.unmarshal(xmlFile);

            for (Movie movie : movies.getMovies()) {
                for (Actor actor : movie.getActors()) {
                    actor.setMovie(movie);
                }
                repository.save(movie);
            }
            LOG.info("importing Movies complete");
        } catch (JAXBException e) {
            LOG.log(Level.SEVERE, "Exception!", e);
        }
        LOG.info("End importing Movies");
    }


}
