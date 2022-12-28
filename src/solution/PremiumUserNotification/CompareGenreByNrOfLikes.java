package solution.PremiumUserNotification;

import java.util.Comparator;

public class CompareGenreByNrOfLikes implements Comparator<GenreAndLikes> {
    @Override
    public int compare(GenreAndLikes o1, GenreAndLikes o2) {
        if (o1.getNrOfLikes() == o2.getNrOfLikes()) {
            return o1.getGenre().compareTo(o2.getGenre());
        } else {
            return o1.getNrOfLikes() - o2.getNrOfLikes();
        }
    }
}
