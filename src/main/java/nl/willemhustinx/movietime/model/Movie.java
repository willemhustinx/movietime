package nl.willemhustinx.movietime.model;

import javax.persistence.*;
import javax.xml.bind.annotation.*;
import java.util.HashSet;
import java.util.Set;

@Entity
@XmlRootElement(name = "movie")
@XmlAccessorType(XmlAccessType.FIELD)
public class Movie {

    @Id
    @GeneratedValue
    private long id;

    private String title;

    @ElementCollection
    @XmlElementWrapper(name = "genres")
    @XmlElement(name = "genre")
    private Set<String> genres = new HashSet<>();

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "movie", orphanRemoval = true, cascade = CascadeType.ALL)
    @XmlElementWrapper(name = "actors")
    @XmlElement(name = "actor")
    private Set<Actor> actors = new HashSet<>();

    private double rating;
    private int runtime;
    private String imdb;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Set<String> getGenres() {
        return genres;
    }

    public void setGenres(Set<String> genres) {
        this.genres = genres;
    }

    public Set<Actor> getActors() {
        return actors;
    }

    public void setActors(Set<Actor> actors) {
        this.actors = actors;
    }

    public double getRating() {
        return rating;
    }

    public void setRating(double rating) {
        this.rating = rating;
    }

    public int getRuntime() {
        return runtime;
    }

    public void setRuntime(int runtime) {
        this.runtime = runtime;
    }

    public String getImdb() {
        return imdb;
    }

    public void setImdb(String imdb) {
        this.imdb = imdb;
    }

    @Override
    public String toString() {
        return "Movie{" +
                "id=" + id +
                ", title='" + title + '\'' +
                ", genres=" + genres +
                ", actors=" + actors +
                ", rating=" + rating +
                ", runtime=" + runtime +
                ", imdb='" + imdb + '\'' +
                '}';
    }
}
