package solution;

public final class MovieRate {
    private Movie movie;
    private int rate;
    public MovieRate(final Movie movie, final int rate) {
        this.movie = movie;
        this.rate = rate;
    }

    public Movie getMovie() {
        return movie;
    }

    public void setMovie(final Movie movie) {
        this.movie = movie;
    }

    public int getRate() {
        return rate;
    }

    public void setRate(final int rate) {
        this.rate = rate;
    }
}
