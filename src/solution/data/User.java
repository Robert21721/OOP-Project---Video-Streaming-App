package solution.data;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import input.files.UsersInput;
import solution.Movie;
import solution.MovieRate;
import solution.Notification;

import java.util.ArrayList;

@JsonIgnoreProperties(value = {
        "subscribedGenres",
        "ratingGivenToAllMovies",
})

public final class User implements Observer{
    private Credentials credentials;
    private int tokensCount;
    private int numFreePremiumMovies;
    private ArrayList<Movie> purchasedMovies;
    private ArrayList<Movie> watchedMovies;
    private ArrayList<Movie> likedMovies;
    private ArrayList<Movie> ratedMovies;
    private ArrayList<Notification> notifications;
    private ArrayList<String> subscribedGenres;
    private ArrayList<MovieRate> ratingGivenToAllMovies;

    public User() {
        this.credentials = new Credentials();
        this.tokensCount = 0;
        this.numFreePremiumMovies = 15;
        this.purchasedMovies = new ArrayList<>();
        this.watchedMovies = new ArrayList<>();
        this.likedMovies = new ArrayList<>();
        this.ratedMovies = new ArrayList<>();
        this.notifications = new ArrayList<>();
        this.subscribedGenres = new ArrayList<>();
        this.ratingGivenToAllMovies = new ArrayList<>();
    }

    public User(final User u) {
        this.credentials = new Credentials(u.getCredentials());
        this.tokensCount = u.getTokensCount();
        this.numFreePremiumMovies = u.getNumFreePremiumMovies();
        this.purchasedMovies = new ArrayList<>();
        this.watchedMovies = new ArrayList<>();
        this.likedMovies = new ArrayList<>();
        this.ratedMovies = new ArrayList<>();
        this.notifications = new ArrayList<>();
        this.subscribedGenres = new ArrayList<>();
        this.ratingGivenToAllMovies = new ArrayList<>();

        for (Movie m : u.getPurchasedMovies()) {
            this.purchasedMovies.add(new Movie(m));
        }

        for (Movie m : u.getWatchedMovies()) {
            this.watchedMovies.add(new Movie(m));
        }

        for (Movie m : u.getLikedMovies()) {
            this.likedMovies.add(new Movie(m));
        }

        for (Movie m : u.getRatedMovies()) {
            this.ratedMovies.add(new Movie(m));
        }

        for (Notification n : u.getNotifications()) {
            this.notifications.add(new Notification(n));
        }

        for (String sg : u.getSubscribedGenres()) {
            this.subscribedGenres.add(sg);
        }
    }
    public User(final UsersInput u) {
        this.credentials = new Credentials(u.getCredentials());
        this.tokensCount = 0;
        this.numFreePremiumMovies = 15;
        this.purchasedMovies = new ArrayList<>();
        this.watchedMovies = new ArrayList<>();
        this.likedMovies = new ArrayList<>();
        this.ratedMovies = new ArrayList<>();
        this.notifications = new ArrayList<>();
        this.subscribedGenres = new ArrayList<>();
        this.ratingGivenToAllMovies = new ArrayList<>();
    }

    @Override
    public void update(Object o, Movie addedMovie) {
        Notification notification = (Notification) o;

        if (notification.getMessage().equals("ADD")) {
            if (addedMovie.getCountriesBanned().contains(this.credentials.getCountry())) {
                return;
            }

            for (String genre : this.subscribedGenres) {
                if (addedMovie.getGenres().contains(genre)) {
                    this.notifications.add(notification);
                    return;
                }
            }
        }

        if (notification.getMessage().equals("DELETE")) {
            Movie movieToBeDeleted = this.purchasedMovies.stream().
                    filter(movie -> movie.getName().equals(notification.getMovieName())).
                    findFirst().orElse(null);

            if (movieToBeDeleted != null) {
                this.purchasedMovies.remove(movieToBeDeleted);
                this.watchedMovies.remove(movieToBeDeleted);
                this.likedMovies.remove(movieToBeDeleted);
                this.ratedMovies.remove(movieToBeDeleted);

                if (this.getCredentials().getAccountType().equals("premium")) {
                    this.numFreePremiumMovies++;
                } else {
                    this.tokensCount += 2;
                }
            }
        }
    }

    public Credentials getCredentials() {
        return credentials;
    }

    public void setCredentials(final Credentials credentials) {
        this.credentials = credentials;
    }

    public int getTokensCount() {
        return tokensCount;
    }

    public void setTokensCount(final int tokensCount) {
        this.tokensCount = tokensCount;
    }

    public int getNumFreePremiumMovies() {
        return numFreePremiumMovies;
    }

    public void setNumFreePremiumMovies(final int numFreePremiumMovies) {
        this.numFreePremiumMovies = numFreePremiumMovies;
    }

    public ArrayList<Movie> getPurchasedMovies() {
        return purchasedMovies;
    }

    public void setPurchasedMovies(final ArrayList<Movie> purchasedMovies) {
        this.purchasedMovies = purchasedMovies;
    }

    public ArrayList<Movie> getWatchedMovies() {
        return watchedMovies;
    }

    public void setWatchedMovies(final ArrayList<Movie> watchedMovies) {
        this.watchedMovies = watchedMovies;
    }

    public ArrayList<Movie> getLikedMovies() {
        return likedMovies;
    }

    public void setLikedMovies(final ArrayList<Movie> likedMovies) {
        this.likedMovies = likedMovies;
    }

    public ArrayList<Movie> getRatedMovies() {
        return ratedMovies;
    }

    public void setRatedMovies(final ArrayList<Movie> ratedMovies) {
        this.ratedMovies = ratedMovies;
    }

    public ArrayList<Notification> getNotifications() {
        return notifications;
    }

    public void setNotifications(ArrayList<Notification> notifications) {
        this.notifications = notifications;
    }

    public ArrayList<String> getSubscribedGenres() {
        return subscribedGenres;
    }

    public void setSubscribedGenres(ArrayList<String> subscribedGenres) {
        this.subscribedGenres = subscribedGenres;
    }

    public ArrayList<MovieRate> getRatingGivenToAllMovies() {
        return ratingGivenToAllMovies;
    }

    public void setRatingGivenToAllMovies(ArrayList<MovieRate> ratingGivenToAllMovies) {
        this.ratingGivenToAllMovies = ratingGivenToAllMovies;
    }

    @Override
    public String toString() {
        return "credentials: { "
                + "name : " + this.credentials.getName()
                + ", password: " + this.credentials.getPassword()
                + ", accountType: " + this.credentials.getAccountType()
                + ", country: " + this.credentials.getCountry()
                + ", balance: " + this.credentials.getBalance()
                + " }";
    }
}
