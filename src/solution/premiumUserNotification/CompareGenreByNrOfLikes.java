package solution.premiumUserNotification;

import java.util.Comparator;

public final class CompareGenreByNrOfLikes implements Comparator<GenreAndLikes> {
    @Override
    public int compare(final GenreAndLikes o1, final GenreAndLikes o2) {
        if (o1.getNrOfLikes() == o2.getNrOfLikes()) {
            return o1.getGenre().compareTo(o2.getGenre());
        } else {
            return o1.getNrOfLikes() - o2.getNrOfLikes();
        }
    }
}
