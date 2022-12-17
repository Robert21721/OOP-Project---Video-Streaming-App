package solution.pages;

import input.files.ActionsInput;
import solution.*;
import solution.data.DataBase;

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

    /**
     * singleton for "see details" page
     * @return an instance of "see details" class
     */
    public static SeeDetails getInstance() {
        if (singletonInstance == null) {
            singletonInstance = new SeeDetails();
        }
        return  singletonInstance;
    }

    @Override
    public boolean executeChangePage(final ActionsInput input, final AppLogic app,
                                     final DataBase dataBase) {
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
                        filter(movie -> !movie.getCountriesBanned()
                                .contains(app.getCurrentUser().getCredentials().getCountry())).
                        collect(Collectors.toCollection(ArrayList::new));

                app.setCurrentMovies(userMovies);
            }

            app.setCurrentPage(ActionFunctions.changePage(input.getPage()));
            return true;
        }
        return false;
    }

    @Override
    public boolean executeOnPage(final ActionsInput input, final AppLogic app,
                                 final DataBase dataBase) {
        switch (input.getFeature()) {
            case "purchase":
                return purchase(app);

            case "watch":
                return watch(app);

            case "like":
                return like(app);

            case "rate":
                return rateTheMovie(input, app);

            default:
                return false;
        }
    }

    /**
     * method that allows the user to buy a movie
     * @param app - app logic
     * @return true if the movie can be purchased, false otherwise
     */
    private boolean purchase(final AppLogic app) {
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

    /**
     * method that allows the user to watch a movie
     * @param app - app logic
     * @return true if the movie can be watched, false otherwise
     */
    private boolean watch(final AppLogic app) {
        Movie movie = app.getCurrentMovies().get(0);

        if (app.getCurrentUser().getPurchasedMovies().contains(movie)) {
            app.getCurrentUser().getWatchedMovies().add(movie);
            return true;
        }
        return false;
    }

    /**
     * method that allows the user to like a movie
     * @param app - app logic
     * @return true if the movie can be liked, false otherwise
     */
    private boolean like(final AppLogic app) {
        Movie movie = app.getCurrentMovies().get(0);

        if (app.getCurrentUser().getWatchedMovies().contains(movie)) {
            movie.setNumLikes(movie.getNumLikes() + 1);
            app.getCurrentUser().getLikedMovies().add(movie);
            return true;
        }
        return false;
    }

    /**
     * method that allows the user to rate a movie
     * @param input - current action
     * @param app - the app logic
     * @return true if the movie can be rated, false otherwise
     */
    private boolean rateTheMovie(final ActionsInput input, final AppLogic app) {
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
