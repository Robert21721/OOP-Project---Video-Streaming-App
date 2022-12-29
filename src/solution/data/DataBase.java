package solution.data;

import com.fasterxml.jackson.databind.node.ArrayNode;
import input.files.Input;
import input.files.MoviesInput;
import input.files.UsersInput;
import solution.Movie;
import solution.Notification;
import solution.Print;

import java.util.ArrayList;
import java.util.Iterator;

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

    public void addUser(Observer user) {
        this.users.add(user);
    }

    public void removeUser(Observer user) {
        this.users.remove(user);
    }

    public void setNotification(Notification notification, Movie addedMovie, ArrayNode output) {
        this.notification = notification;

        if (notification.getMessage().equals("ADD")) {
            Movie existingMovie = this.movies.stream().
                    filter(movie -> movie.getName().equals(addedMovie.getName())).
                    findFirst().orElse(null);

            if (existingMovie == null) {
                this.movies.add(addedMovie);
            } else {
                Print print = new Print();
                print.writeError(output);
                return;
            }
        }

        if (notification.getMessage().equals("DELETE")){

            String movieName = notification.getMovieName();
            Movie movieToBeDeleted = this.movies.stream().
                    filter(movie -> movie.getName().equals(movieName)).
                    findFirst().orElse(null);

            if (movieToBeDeleted != null) {
                this.movies.remove(movieToBeDeleted);
            } else {
                Print print = new Print();
                print.writeError(output);
                return;
            }
        }

        for (Observer o : this.users) {
            o.update(this.notification, addedMovie);
        }
    }



    public ArrayList<Observer> getUsers() {
        return users;
    }

    public void setUsers(ArrayList<Observer> users) {
        this.users = users;
    }

    public ArrayList<Movie> getMovies() {
        return movies;
    }

    public void setMovies(final ArrayList<Movie> movies) {
        this.movies = movies;
    }
}
