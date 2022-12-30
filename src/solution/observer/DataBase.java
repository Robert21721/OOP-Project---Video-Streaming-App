package solution.observer;

import com.fasterxml.jackson.databind.node.ArrayNode;
import input.files.Input;
import input.files.MoviesInput;
import input.files.UsersInput;
import solution.Movie;
import solution.Notification;
import solution.Print;

import java.util.ArrayList;

public final class DataBase {
    private Notification notification;
    private ArrayList<Observer> users;
    private ArrayList<Movie> movies;

    public DataBase(final Input input) {
        ArrayList<UsersInput> usersInput = input.getUsers();
        ArrayList<MoviesInput> moviesInput = input.getMovies();

        this.users = new ArrayList<>();
        this.movies = new ArrayList<>();

        for (UsersInput u : usersInput) {
            User user = new User(u);
            this.users.add(user);
        }

        for (MoviesInput m : moviesInput) {
            Movie movie = new Movie(m);
            this.movies.add(movie);
        }

    }

    /**
     * add user to database
     * @param user
     */
    public void addUser(final Observer user) {
        this.users.add(user);
    }

    /**
     * remove user from database
     * @param user
     */
    public void removeUser(final Observer user) {
        this.users.remove(user);
    }

    /**
     * set notification, update database and notify observers
     * @param notification - notification for users
     * @param addedMovie - movie to be added to database
     * @param output - object for writing to json file
     */
    public void setNotification(final Notification notification,
                                final Movie addedMovie, final ArrayNode output) {
        this.notification = notification;

        // update database
        if (notification.getMessage().equals("ADD")) {
            Movie existingMovie = this.movies.stream().
                    filter(movie -> movie.getName().equals(addedMovie.getName())).
                    findFirst().orElse(null);

            // verify if the movie already exists
            if (existingMovie == null) {
                this.movies.add(addedMovie);
            } else {
                Print print = new Print();
                print.writeError(output);
                return;
            }
        }

        if (notification.getMessage().equals("DELETE")) {

            String movieName = notification.getMovieName();
            Movie movieToBeDeleted = this.movies.stream().
                    filter(movie -> movie.getName().equals(movieName)).
                    findFirst().orElse(null);

            // verify if the movie exists
            if (movieToBeDeleted != null) {
                this.movies.remove(movieToBeDeleted);
            } else {
                Print print = new Print();
                print.writeError(output);
                return;
            }
        }

        // notify all observers
        for (Observer o : this.users) {
            o.update(this.notification, addedMovie);
        }
    }



    public ArrayList<Observer> getUsers() {
        return users;
    }

    public void setUsers(final ArrayList<Observer> users) {
        this.users = users;
    }

    public ArrayList<Movie> getMovies() {
        return movies;
    }

    public void setMovies(final ArrayList<Movie> movies) {
        this.movies = movies;
    }

    public Notification getNotification() {
        return notification;
    }

    public void setNotification(final Notification notification) {
        this.notification = notification;
    }
}
