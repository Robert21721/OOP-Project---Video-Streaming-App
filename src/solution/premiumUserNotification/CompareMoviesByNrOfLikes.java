package solution.premiumUserNotification;

import solution.Movie;

import java.util.Comparator;

public final class CompareMoviesByNrOfLikes implements Comparator<Movie> {
    @Override
    public int compare(final Movie o1, final Movie o2) {
        return o2.getNumLikes() - o1.getNumLikes();
    }
}
