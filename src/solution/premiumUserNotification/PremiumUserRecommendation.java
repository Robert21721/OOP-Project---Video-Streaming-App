package solution.premiumUserNotification;

import com.fasterxml.jackson.databind.node.ArrayNode;
import solution.*;
import solution.observer.DataBase;
import solution.observer.User;

import java.util.ArrayList;
import java.util.Collections;
import java.util.stream.Collectors;

public final class PremiumUserRecommendation {
    private DataBase dataBase;
    private ArrayList<GenreAndLikes> genreAndLikes;
    private ArrayList<Movie> movies;

    public PremiumUserRecommendation(final DataBase dataBase) {
        this.dataBase = dataBase;
        this.genreAndLikes = new ArrayList<>();
        this.movies = new ArrayList<>();
    }

    /**
     * send the right movie recommendation to premium user
     * @param app - the application status
     * @param output - object for writing to json file
     */
    public void giveRecommendation(final AppLogic app, final ArrayNode output) {
        User user = app.getCurrentUser();

        // find all genres
        for (Movie movie : user.getLikedMovies()) {
            for (String genre : movie.getGenres()) {
                GenreAndLikes existingGenre = this.genreAndLikes.stream().
                        filter(g -> g.getGenre().equals(genre)).findFirst().orElse(null);

                // if genre do not exist in the list, add it
                if (existingGenre == null) {
                    GenreAndLikes newGenere = new GenreAndLikes();
                    newGenere.setGenre(genre);
                    newGenere.setNrOfLikes(1);
                    this.genreAndLikes.add(newGenere);
                } else {
                    // else increment number of likes
                    existingGenre.setNrOfLikes(existingGenre.getNrOfLikes() + 1);
                }
            }
        }

        // sort the genre list
        Collections.sort(this.genreAndLikes, new CompareGenreByNrOfLikes());

        // get from database all movies that the user can see and had not seen them yet
        this.movies = this.dataBase.getMovies().
                stream().
                filter(movie -> !movie.getCountriesBanned()
                        .contains(user.getCredentials().getCountry())).
                filter(movie -> !user.getWatchedMovies().contains(movie)).
                collect(Collectors.toCollection(ArrayList::new));

        // sort the movies by number of likes
        Collections.sort(this.movies, new CompareMoviesByNrOfLikes());

        // find the right movie
        for (GenreAndLikes g : this.genreAndLikes) {
            for (Movie m : this.movies) {
                String genre = g.getGenre();

                if (m.getGenres().contains(genre)) {
                    Notification notification = new Notification(m.getName(), "Recommendation");
                    app.getCurrentUser().getNotifications().add(notification);
                    Print print = new Print(app);
                    print.writeInfoForNotification(output);
                    return;
                }
            }
        }

        Notification notification = new Notification("No recommendation", "Recommendation");
        app.getCurrentUser().getNotifications().add(notification);
        Print print = new Print(app);
        print.writeInfoForNotification(output);
    }
}
