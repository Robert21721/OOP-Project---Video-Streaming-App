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

    public Movie(final MoviesInput m) {
        this.name = m.getName();
        this.year = m.getYear();
        this.duration = m.getDuration();
        this.genres = new ArrayList<>();
        this.actors = new ArrayList<>();
        this.countriesBanned = new ArrayList<>();

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
}
