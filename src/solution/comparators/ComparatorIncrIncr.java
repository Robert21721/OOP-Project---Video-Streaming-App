package solution.comparators;

import solution.Movie;

import java.util.Comparator;

public class ComparatorIncrIncr implements Comparator<Movie> {
    @Override
    public int compare(Movie o1, Movie o2) {
        if (o1.getRating() == o2.getRating()) {
            return o1.getDuration() - o2.getDuration();
        } else if (o1.getRating() > o2.getRating()) {
            return 1;
        } else return -1;
    }
}
