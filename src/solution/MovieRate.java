package solution;

public class MovieRate {
    private Movie movie;
    private int rate;
    
    public MovieRate(Movie movie, int rate) {
        this.movie = movie;
        this.rate = rate;
    }

    public Movie getMovie() {
        return movie;
    }

    public void setMovie(Movie movie) {
        this.movie = movie;
    }

    public int getRate() {
        return rate;
    }

    public void setRate(int rate) {
        this.rate = rate;
    }
}
