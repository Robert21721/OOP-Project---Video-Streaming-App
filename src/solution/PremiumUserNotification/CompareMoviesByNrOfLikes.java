package solution.PremiumUserNotification;

import solution.Movie;

import java.util.Comparator;

public class CompareMoviesByNrOfLikes implements Comparator<Movie> {
    @Override
    public int compare(Movie o1, Movie o2) {
        return o1.getNumLikes() - o2.getNumLikes();
    }
}
