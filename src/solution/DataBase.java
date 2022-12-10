package solution;

import input.files.ActionsInput;
import input.files.Input;
import input.files.MoviesInput;
import input.files.UsersInput;

import java.util.ArrayList;

public final class DataBase {
    private ArrayList<User> users;
    private ArrayList<Movie> movies;

    public DataBase(final Input input) {
        ArrayList<UsersInput> usersInput = input.getUsers();
        ArrayList<MoviesInput> moviesInput = input.getMovies();
        ArrayList<ActionsInput> actionsInput = input.getActions();

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
}
