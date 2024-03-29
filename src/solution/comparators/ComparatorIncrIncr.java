package solution.comparators;

import solution.Movie;

import java.util.Comparator;

public final class ComparatorIncrIncr implements Comparator<Movie> {
    @Override
    public int compare(final Movie o1, final Movie o2) {
        if (o1.getDuration() == o2.getDuration()) {
            return Double.compare(o1.getRating(), o2.getRating());
        }
        return o1.getDuration() - o2.getDuration();
    }
}
