package solution.PremiumUserNotification;

import com.fasterxml.jackson.databind.node.ArrayNode;
import solution.*;
import solution.data.DataBase;

import java.util.ArrayList;
import java.util.Collections;
import java.util.stream.Collectors;

public class PremiumUserRecommendation {
    private DataBase dataBase;
    //private User user;
    private ArrayList<GenreAndLikes> genreAndLikes;
    private ArrayList<Movie> movies;

    public PremiumUserRecommendation(DataBase dataBase) {
        this.dataBase = dataBase;
        //this.user = user;
        this.genreAndLikes = new ArrayList<>();
        this.movies = new ArrayList<>();
    }

    public void giveRecommendation(AppLogic app, ArrayNode output) {
        User user = app.getCurrentUser();

        for (Movie movie : user.getLikedMovies()) {
            for (String genre : movie.getGenres()) {
                GenreAndLikes existingGenre = this.genreAndLikes.stream().
                        filter(g -> g.getGenre().equals(genre)).findFirst().orElse(null);

                if (existingGenre == null) {
                    GenreAndLikes newGenere = new GenreAndLikes();
                    newGenere.setGenre(genre);
                    newGenere.setNrOfLikes(1);
                    this.genreAndLikes.add(newGenere);
                } else {
                    existingGenre.setNrOfLikes(existingGenre.getNrOfLikes() + 1);
                }
            }
        }

        // avem lista cu genuri si like uri
        Collections.sort(this.genreAndLikes, new CompareGenreByNrOfLikes());

        // avem lista cu filmele posibile
        this.movies = this.dataBase.getMovies().
                stream().
                filter(movie -> !movie.getCountriesBanned()
                        .contains(user.getCredentials().getCountry())).
                filter(movie -> !user.getWatchedMovies().contains(movie)).
                collect(Collectors.toCollection(ArrayList::new));

        Collections.sort(this.movies, new CompareMoviesByNrOfLikes());

        for (GenreAndLikes g : this.genreAndLikes) {
            for (Movie m : this.movies) {
                String genre = g.getGenre();

                if (m.getGenres().contains(genre)) {
                    // print whatever
                    Notification notification = new Notification(m.getName(), "Recommendation");
                    app.getCurrentUser().getNotifications().add(notification);
                    Print print = new Print(app);
                    print.writeInfoForNotification(output);
                    return;
                }
            }
        }

        // print error
        Notification notification = new Notification("No recommendation", "Recommendation");
        app.getCurrentUser().getNotifications().add(notification);
        Print print = new Print(app);
        print.writeInfoForNotification(output);
    }
}