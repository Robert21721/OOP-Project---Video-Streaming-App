package solution.data;

import input.files.ActionsInput;
import input.files.Input;
import input.files.MoviesInput;
import input.files.UsersInput;
import solution.Movie;
import solution.User;

import java.util.ArrayList;

public final class DataBase {
    private ArrayList<User> users;
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

    public ArrayList<User> getUsers() {
        return users;
    }

    public void setUsers(final ArrayList<User> users) {
        this.users = users;
    }

    public ArrayList<Movie> getMovies() {
        return movies;
    }

    public void setMovies(final ArrayList<Movie> movies) {
        this.movies = movies;
    }
}
