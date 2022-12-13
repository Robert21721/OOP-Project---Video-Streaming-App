package solution.Pages;

import input.files.ActionsInput;
import org.w3c.dom.ls.LSOutput;
import solution.AppLogic;
import solution.DataBase;
import solution.Movie;
import solution.comparators.ComparatorDecrDecr;
import solution.comparators.ComparatorDecrIncr;
import solution.comparators.ComparatorIncrDecr;
import solution.comparators.ComparatorIncrIncr;

import java.util.ArrayList;
import java.util.Collections;
import java.util.stream.Collectors;

public final class Movies implements Page {
    private static Movies singletonInstance = null;
    private ArrayList<String> actionsChangePage;
    private ArrayList<String> actionsOnPage;
    private Movies() {
        this.actionsChangePage = new ArrayList<>();
        this.actionsOnPage = new ArrayList<>();

        // this.actionsChangePage.add("")
        this.actionsOnPage.add("search");
        this.actionsOnPage.add("filter");
    }
    public static Movies getInstance() {
        if (singletonInstance == null) {
            singletonInstance = new Movies();
        }
        return  singletonInstance;
    }

    @Override
    public boolean executeChangePage(ActionsInput input, AppLogic app, DataBase dataBase) {
        return false;
    }

    @Override
    public boolean executeOnPage(ActionsInput input, AppLogic app, DataBase dataBase) {
        if (this.actionsOnPage.contains(input.getFeature())) {
            switch (input.getFeature()) {
                case "search":
                    search(input, app, dataBase);
                    break;

                case "filter":
                    System.out.println("neatza sefu");
                    filter(input, app, dataBase);
                    break;
            }

            return true;
        }
        return false;
    }

    private void search(ActionsInput input, AppLogic app, DataBase dataBase) {
        ArrayList<Movie> userMovies = dataBase.getMovies().
                stream().
                filter(movie -> !movie.getCountriesBanned().contains(app.getCurrentUser().getCredentials().getCountry())).
                collect(Collectors.toCollection(ArrayList::new));

        userMovies = userMovies.
                stream().
                filter(movie -> movie.getName().startsWith(input.getStartsWith())).
                collect(Collectors.toCollection(ArrayList::new));
        app.setCurrentMovies(userMovies);
    }

    private void filter(ActionsInput input, AppLogic app, DataBase dataBase) {
        ArrayList<Movie> userMovies = dataBase.
                getMovies().
                stream().
                filter(movie -> !movie.getCountriesBanned().contains(app.getCurrentUser().getCredentials().getCountry())).
                collect(Collectors.toCollection(ArrayList::new));

        ArrayList<String> inputActors;
        ArrayList<String> inputGenre;

        if (input.getFilters().getContains() != null) {
            inputActors = input.getFilters().getContains().getActors();
            inputGenre = input.getFilters().getContains().getGenre();
        } else {
            inputGenre = null;
            inputActors = null;
        }

        userMovies = userMovies.
                stream().
                filter(movie -> (inputActors == null || movie.getActors().containsAll(inputActors) &&
                        (inputGenre == null || movie.getGenres().containsAll(inputGenre)))).
                collect(Collectors.toCollection(ArrayList::new));

        if (input.getFilters().getSort().getRating().equals("increasing") &&
                input.getFilters().getSort().getDuration().equals("increasing")) {
            Collections.sort(userMovies, new ComparatorIncrIncr());
        }

        if (input.getFilters().getSort().getRating().equals("increasing") &&
                input.getFilters().getSort().getDuration().equals("decreasing")) {
            Collections.sort(userMovies, new ComparatorIncrDecr());
        }

        if (input.getFilters().getSort().getRating().equals("decreasing") &&
                input.getFilters().getSort().getDuration().equals("increasing")) {
            Collections.sort(userMovies, new ComparatorDecrIncr());
        }

        if (input.getFilters().getSort().getRating().equals("decreasing") &&
                input.getFilters().getSort().getDuration().equals("decreasing")) {
            Collections.sort(userMovies, new ComparatorDecrDecr());
        }

        app.setCurrentMovies(userMovies);
    }
}
