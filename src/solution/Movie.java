package solution;

import input.files.MoviesInput;

import java.util.ArrayList;
public final class Movie {
    private String name;
    private int year;
    private int duration;
    private ArrayList<String> genres;
    private ArrayList<String> actors;
    private ArrayList<String> countriesBanned;
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

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getYear() {
        return year;
    }

    public void setYear(int year) {
        this.year = year;
    }

    public int getDuration() {
        return duration;
    }

    public void setDuration(int duration) {
        this.duration = duration;
    }

    public ArrayList<String> getGenres() {
        return genres;
    }

    public void setGenres(ArrayList<String> genres) {
        this.genres = genres;
    }

    public ArrayList<String> getActors() {
        return actors;
    }

    public void setActors(ArrayList<String> actors) {
        this.actors = actors;
    }

    public ArrayList<String> getCountriesBanned() {
        return countriesBanned;
    }

    public void setCountriesBanned(ArrayList<String> countriesBanned) {
        this.countriesBanned = countriesBanned;
    }

    public int getNumLikes() {
        return numLikes;
    }

    public void setNumLikes(int numLikes) {
        this.numLikes = numLikes;
    }

    public double getRating() {
        return rating;
    }

    public void setRating(double rating) {
        this.rating = rating;
    }

    public int getNumRatings() {
        return numRatings;
    }

    public void setNumRatings(int numRatings) {
        this.numRatings = numRatings;
    }

    @Override
    public String toString() {
        return "{ name: " + this.name +
            ", year: " + this.year +
            ", duration: " + this.duration +
            ", genres: " + this.genres +
            ", actors: " + this.actors +
            ", countriesBanned: " + this.countriesBanned +
            " }";
    }
}
