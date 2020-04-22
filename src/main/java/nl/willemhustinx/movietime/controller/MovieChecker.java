package nl.willemhustinx.movietime.controller;


import nl.willemhustinx.movietime.model.Actor;
import nl.willemhustinx.movietime.model.Movie;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Set;

public class MovieChecker {

    private final List<Movie> movieList;

    public MovieChecker(List<Movie> movieList) {
        this.movieList = movieList;
    }

    public List<Movie> filterList(Map<String, String> interests) {

        List<Movie> returnList = new ArrayList<>();

        for (Movie movie : movieList) {
            if (checkMovie(movie, interests)) {
                returnList.add(movie);
            }
        }

        return returnList;
    }

    private boolean checkMovie(Movie movie, Map<String, String> interests) {

        for (Map.Entry<String, String> interest : interests.entrySet()) {
            if (checkInterest(movie, interest)) {
                return true;
            }
        }

        return false;
    }

    private boolean checkInterest(Movie movie, Map.Entry<String, String> interest) {

        switch (interest.getKey()) {
            case "genres":
                if (this.hasGenre(movie, interest.getValue())) {
                    return true;
                }
                break;
            case "actors":
                if (this.hasActor(movie, interest.getValue())) {
                    return true;
                }
                break;
            case "gender":
                if (this.hasActorWithGender(movie, interest.getValue())) {
                    return true;
                }
                break;
            case "ratings":
                if (this.hasRating(movie, interest.getValue())) {
                    return true;
                }
                break;
            case "runtime":
                if (this.hasRuntime(movie, interest.getValue())) {
                    return true;
                }
                break;
            default:
                break;
        }
        return false;

    }


    private boolean hasActor(Movie movie, String actorName) {
        Set<Actor> actors = movie.getActors();
        for (Actor actor : actors) {
            if (actor.getName().equals(actorName)) {
                return true;
            }
        }
        return false;
    }

    private boolean hasActorWithGender(Movie movie, String gender) {
        Set<Actor> actors = movie.getActors();
        for (Actor actor : actors) {
            if (actor.getGender().equals(gender)) {
                return true;
            }
        }
        return false;
    }

    private boolean hasGenre(Movie movie, String genreName) {
        Set<String> genres = movie.getGenres();
        for (String genre : genres) {
            if (genre.equals(genreName)) {
                return true;
            }
        }
        return false;
    }

    private boolean hasRating(Movie movie, String rating) {
        if (rating.charAt(rating.length() - 1) == '+') {
            rating = rating.replace(rating.substring(rating.length() - 1), "");
            int ratingNumber = Integer.parseInt(rating);
            return movie.getRating() > ratingNumber;
        }
        return false;
    }

    private boolean hasRuntime(Movie movie, String runtime) {
        if (runtime.charAt(0) == '<') {
            String[] runtimeWords = runtime.split(" ");
            int runtimeInt = Integer.parseInt(runtimeWords[1]);
            return movie.getRuntime() < runtimeInt;
        }
        return false;
    }

}
