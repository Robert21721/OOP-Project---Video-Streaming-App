package solution.premiumUserNotification;

public final class GenreAndLikes {
    private String genre;
    private int nrOfLikes;

    public GenreAndLikes() { }

    @Override
    public String toString() {
        return "{ genre: " + genre
                + ", nrOfLikes: " + nrOfLikes
                + " }";
    }

    public String getGenre() {
        return genre;
    }

    public void setGenre(final String genre) {
        this.genre = genre;
    }

    public int getNrOfLikes() {
        return nrOfLikes;
    }

    public void setNrOfLikes(final int nrOfLikes) {
        this.nrOfLikes = nrOfLikes;
    }
}
