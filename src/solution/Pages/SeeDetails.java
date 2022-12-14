package solution.Pages;

import input.files.ActionsInput;
import solution.*;

import java.util.ArrayList;
import java.util.stream.Collectors;

public final class SeeDetails implements Page {
    private static SeeDetails singletonInstance = null;
    private ArrayList<String> actionsChangePage;
    private ArrayList<String> actionsOnPage;
    private SeeDetails() {
        this.actionsChangePage = new ArrayList<>();
        this.actionsOnPage = new ArrayList<>();

        this.actionsChangePage.add("movies");
        this.actionsChangePage.add("upgrades");

        this.actionsOnPage.add("purchase");
        this.actionsOnPage.add("watch");
        this.actionsOnPage.add("like");
        this.actionsOnPage.add("rate");
    }
    public static SeeDetails getInstance() {
        if (singletonInstance == null) {
            singletonInstance = new SeeDetails();
        }
        return  singletonInstance;
    }

    @Override
    public boolean executeChangePage(ActionsInput input, AppLogic app, DataBase dataBase) {
        if (input.getPage().equals("logout")) {
            app.setCurrentUser(null);
            app.setCurrentPage(HomePageNeautentificat.getInstance());
            app.getCurrentMovies().clear();
            return true;
        }

        if (this.actionsChangePage.contains(input.getPage())) {
            if (input.getPage().equals("movies")) {

                ArrayList<Movie> userMovies = dataBase.getMovies().
                        stream().
                        filter(movie -> !movie.getCountriesBanned().contains(app.getCurrentUser().getCredentials().getCountry())).
                        collect(Collectors.toCollection(ArrayList::new));

                app.setCurrentMovies(userMovies);
            }

            app.setCurrentPage(ActionFunctions.changePage(input.getPage()));
            return true;
        }
        return false;
    }

    @Override
    public boolean executeOnPage(ActionsInput input, AppLogic app, DataBase dataBase) {
        switch (input.getFeature()) {
            case "purchase":
                return purchase(input, app, dataBase);

            case "watch":
                return watch(input, app, dataBase);

            case "like":
                return like(input, app, dataBase);

            case "rate":
                return rateTheMovie(input, app, dataBase);
        }
        return false;
    }

    private boolean purchase(ActionsInput input, AppLogic app, DataBase dataBase) {
        User user = app.getCurrentUser();
        if (user.getCredentials().getAccountType().equals("premium")) {
            if (user.getNumFreePremiumMovies() > 0) {
                user.setNumFreePremiumMovies(user.getNumFreePremiumMovies() - 1);
                user.getPurchasedMovies().add(app.getCurrentMovies().get(0));
                return true;
            }

            if (user.getTokensCount() >= 2) {
                user.setTokensCount(user.getTokensCount() - 2);
                user.getPurchasedMovies().add(app.getCurrentMovies().get(0));
                return true;
            }
        } else {
            if (user.getTokensCount() >= 2) {
                user.setTokensCount(user.getTokensCount() - 2);
                user.getPurchasedMovies().add(app.getCurrentMovies().get(0));
                return true;
            }
        }

        return false;
    }

    private boolean watch(ActionsInput input, AppLogic app, DataBase dataBase) {
        Movie movie = app.getCurrentMovies().get(0);

        if (app.getCurrentUser().getPurchasedMovies().contains(movie)) {
            app.getCurrentUser().getWatchedMovies().add(movie);
            return true;
        }
        return false;
    }

    private boolean like(ActionsInput input, AppLogic app, DataBase dataBase) {
        Movie movie = app.getCurrentMovies().get(0);

        if (app.getCurrentUser().getWatchedMovies().contains(movie)) {
            movie.setNumLikes(movie.getNumLikes() + 1);
            app.getCurrentUser().getLikedMovies().add(movie);
            return true;
        }
        return false;
    }

    private boolean rateTheMovie(ActionsInput input, AppLogic app, DataBase dataBase) {
        Movie movie = app.getCurrentMovies().get(0);

        if (input.getRate() > 5) {
            return false;
        }

        if (app.getCurrentUser().getWatchedMovies().contains(movie)) {
            movie.setNumRatings(movie.getNumRatings() + 1);
            movie.getRatings().add(input.getRate());
            int rating = movie.getRatings().stream().reduce(0, Integer::sum);
            movie.setRating((double) (rating / movie.getNumRatings()));
            app.getCurrentUser().getRatedMovies().add(movie);

            return true;
        }
        return false;
    }
}
