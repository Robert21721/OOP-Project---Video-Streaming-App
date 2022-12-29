package solution.data;

import solution.Movie;
import solution.Notification;

public interface Observer {
    public void update(Object notification, Movie movie);
}
