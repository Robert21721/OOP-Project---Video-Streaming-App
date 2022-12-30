package solution.observer;

import solution.Movie;

public interface Observer {
    /**
     * method called for each observer when something change in the database
     * @param notification - notification for user
     * @param movie - movie added to database
     */
    void update(Object notification, Movie movie);
}
