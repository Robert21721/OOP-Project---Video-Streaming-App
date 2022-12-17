package solution;

import input.files.UsersInput;
import solution.data.Credentials;

import java.util.ArrayList;


public final class User {
    private Credentials credentials;
    private int tokensCount;
    private int numFreePremiumMovies;
    private ArrayList<Movie> purchasedMovies;
    private ArrayList<Movie> watchedMovies;
    private ArrayList<Movie> likedMovies;
    private ArrayList<Movie> ratedMovies;

    public User() {
        this.credentials = new Credentials();
        this.tokensCount = 0;
        this.numFreePremiumMovies = 15;
        this.purchasedMovies = new ArrayList<>();
        this.watchedMovies = new ArrayList<>();
        this.likedMovies = new ArrayList<>();
        this.ratedMovies = new ArrayList<>();
    }

    public User(final User u) {
        this.credentials = new Credentials(u.getCredentials());
        this.tokensCount = u.getTokensCount();
        this.numFreePremiumMovies = u.getNumFreePremiumMovies();
        this.purchasedMovies = new ArrayList<>();
        this.watchedMovies = new ArrayList<>();
        this.likedMovies = new ArrayList<>();
        this.ratedMovies = new ArrayList<>();

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
    }
    public User(final UsersInput u) {
        this.credentials = new Credentials(u.getCredentials());
        this.tokensCount = 0;
        this.numFreePremiumMovies = 15;
        this.purchasedMovies = new ArrayList<>();
        this.watchedMovies = new ArrayList<>();
        this.likedMovies = new ArrayList<>();
        this.ratedMovies = new ArrayList<>();
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
