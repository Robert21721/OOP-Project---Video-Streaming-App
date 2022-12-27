package solution;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import input.files.MoviesInput;

import java.util.ArrayList;
@JsonIgnoreProperties(value = {
        "ratings"
})

public final class Movie {
    private String name;
    private String year;
    private int duration;
    private ArrayList<String> genres;
    private ArrayList<String> actors;
    private ArrayList<String> countriesBanned;
    private ArrayList<Integer> ratings;
    private int numLikes;
    private double rating;
    private int  numRatings;

    public Movie(final MoviesInput m) {
        this.name = m.getName();
        this.year = m.getYear();
        this.duration = m.getDuration();
        this.genres = new ArrayList<>();
        this.actors = new ArrayList<>();
        this.countriesBanned = new ArrayList<>();
        this.ratings = new ArrayList<>();
        this.numLikes = 0;
        this.rating = 0.00f;
        this.numRatings = 0;

        for (String genre : m.getGenres()) {
            this.genres.add(genre);
        }

        for (String actor : m.getActors()) {
            this.actors.add(actor);
        }

        for (String countryBanned : m.getCountriesBanned()) {
            this.countriesBanned.add(countryBanned);
        }
    }

    public Movie(final Movie m) {
        this.name = m.getName();
        this.year = m.getYear();
        this.duration = m.getDuration();
        this.genres = new ArrayList<>();
        this.actors = new ArrayList<>();
        this.countriesBanned = new ArrayList<>();
        this.numLikes = m.getNumLikes();
        this.rating = m.getRating();
        this.numRatings = m.getNumRatings();

        for (String genre : m.getGenres()) {
            this.genres.add(genre);
        }

        for (String actor : m.getActors()) {
            this.actors.add(actor);
        }

        for (String countryBanned : m.getCountriesBanned()) {
            this.countriesBanned.add(countryBanned);
        }
    }

    public String getName() {
        return name;
    }

    public void setName(final String name) {
        this.name = name;
    }

    public String getYear() {
        return year;
    }

    public void setYear(String year) {
        this.year = year;
    }

    public int getDuration() {
        return duration;
    }

    public void setDuration(final int duration) {
        this.duration = duration;
    }

    public ArrayList<String> getGenres() {
        return genres;
    }

    public void setGenres(final ArrayList<String> genres) {
        this.genres = genres;
    }

    public ArrayList<String> getActors() {
        return actors;
    }

    public void setActors(final ArrayList<String> actors) {
        this.actors = actors;
    }

    public ArrayList<String> getCountriesBanned() {
        return countriesBanned;
    }

    public void setCountriesBanned(final ArrayList<String> countriesBanned) {
        this.countriesBanned = countriesBanned;
    }

    public int getNumLikes() {
        return numLikes;
    }

    public void setNumLikes(final int numLikes) {
        this.numLikes = numLikes;
    }

    public double getRating() {
        return rating;
    }

    public void setRating(final double rating) {
        this.rating = rating;
    }

    public int getNumRatings() {
        return numRatings;
    }

    public void setNumRatings(final int numRatings) {
        this.numRatings = numRatings;
    }

    public ArrayList<Integer> getRatings() {
        return ratings;
    }

    public void setRatings(final ArrayList<Integer> ratings) {
        this.ratings = ratings;
    }

    @Override
    public String toString() {
        return "{ name: " + this.name
                + ", year: " + this.year
                + ", duration: " + this.duration
                + ", genres: " + this.genres
                + ", actors: " + this.actors
                + ", countriesBanned: " + this.countriesBanned
                + ", ratings: " + this.ratings
                + ", numLikes: " + this.numLikes
                + ", rating: " + rating
                + ", numRatings: " + numRatings
                + " }";
    }
}
