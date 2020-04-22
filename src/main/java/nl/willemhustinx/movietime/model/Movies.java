package nl.willemhustinx.movietime.model;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import java.util.List;

@XmlRootElement(name = "movies")
@XmlAccessorType(XmlAccessType.FIELD)
public class Movies {

    @XmlElement(name = "movie")
    private List<Movie> movieList = null;

    public List<Movie> getMovies() {
        return movieList;
    }

    public void setMovies(List<Movie> movies) {
        this.movieList = movies;
    }
}
