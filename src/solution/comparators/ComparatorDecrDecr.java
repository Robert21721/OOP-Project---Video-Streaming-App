package solution.comparators;

import solution.Movie;

import java.util.Comparator;

public class ComparatorDecrDecr implements Comparator<Movie> {
    @Override
    public int compare(Movie o1, Movie o2) {
        if (o1.getRating() == o2.getRating()) {
            return o2.getDuration() - o1.getDuration();
        } else if (o2.getRating() > o1.getRating()) {
            return 1;
        } else return -1;
    }
}
