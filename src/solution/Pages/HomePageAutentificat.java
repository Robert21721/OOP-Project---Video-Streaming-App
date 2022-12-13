package solution.Pages;

import input.files.ActionsInput;
import solution.ActionFunctions;
import solution.AppLogic;
import solution.DataBase;
import solution.Movie;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public final class HomePageAutentificat implements Page {
    private static HomePageAutentificat singletonInstance = null;
    private ArrayList<String> actionsChangePage;
    private ArrayList<String> actionsOnPage;

    private HomePageAutentificat() {
        this.actionsChangePage = new ArrayList<>();
        this.actionsOnPage = new ArrayList<>();

        this.actionsChangePage.add("movies");
        this.actionsChangePage.add("upgrades");
    }
    public static HomePageAutentificat getInstance() {
        if (singletonInstance == null) {
            singletonInstance = new HomePageAutentificat();
        }
        return singletonInstance;
    }

    @Override
    public boolean executeChangePage(ActionsInput input, AppLogic app, DataBase dataBase) {
        if (input.getPage().equals("logout")) {
            app.setCurrentUser(null);
            app.setCurrentPage(HomePageNeautentificat.getInstance());
            return true;
        }

        if (this.actionsChangePage.contains(input.getPage())) {
            app.setCurrentPage(ActionFunctions.changePage(input.getPage()));

            if (input.getPage().equals("movies")) {
                ArrayList<Movie> userMovies = dataBase.getMovies().
                        stream().
                        filter(movie -> !movie.getCountriesBanned().contains(app.getCurrentUser().getCredentials().getCountry())).
                        collect(Collectors.toCollection(ArrayList::new));

                app.setCurrentMovies(userMovies);
                // app.setCurrentPage(ActionFunctions.changePage(input.getPage()));
            }

            app.setCurrentPage(ActionFunctions.changePage(input.getPage()));
            return true;
        }

        return false;
    }

    @Override
    public boolean executeOnPage(ActionsInput input, AppLogic app, DataBase dataBase) {
        return false;
    }
}
