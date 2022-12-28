package solution.pages;

import com.fasterxml.jackson.databind.node.ArrayNode;
import input.files.ActionsInput;
import input.files.MoviesInput;
import solution.*;
import solution.Commands.ChangePageCommand;
import solution.Commands.Command;
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
        this.actionsChangePage.add("logout");

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
    public void executeChangePage(final ActionsInput input, final AppLogic app,
                                     final DataBase dataBase, final ArrayNode output) {
        if (this.actionsChangePage.contains(input.getPage())) {
            ChangePageCommand command = new ChangePageCommand(this.getPageName());

            if (!input.getPage().equals("upgrades")) {
                if (app.getEditor().edit(command, input, app, dataBase, output)) {
                    return;
                }
            } else {
                app.setCurrentPage(Factory.getPage(input.getPage()));
                return;
            }
        }

        Print print = new Print();
        print.writeError(output);
    }

    @Override
    public void executeOnPage(final ActionsInput input, final AppLogic app,
                                 final DataBase dataBase, final ArrayNode output) {

        if (input.getType().equals("subscribe")) {
            subscribe(input, app, output);
            return;
        }

        switch (input.getFeature()) {
            case "purchase":
                purchase(app, output);
                break;

            case "watch":
                watch(app, output);
                break;

            case "like":
                like(app, output);
                break;

            case "rate":
                rateTheMovie(input, app, output);
                break;

            default:
        }
    }

    /**
     * method that allows the user to buy a movie
     * @param app - app logic
     * @return true if the movie can be purchased, false otherwise
     */
    private void purchase(final AppLogic app, ArrayNode output) {
        User user = app.getCurrentUser();
        Movie movie = app.getCurrentMovies().get(0);

        Movie moviePurchased = app.getCurrentUser().getPurchasedMovies().
                stream().filter(m -> m.getName().equals(movie.getName())).
                findFirst().orElse(null);

        if (moviePurchased != null) {
            Print print = new Print(app);
            print.writeError(output);
            return;
        }

        if (user.getCredentials().getAccountType().equals("premium") && user.getNumFreePremiumMovies() > 0) {
                user.setNumFreePremiumMovies(user.getNumFreePremiumMovies() - 1);
                user.getPurchasedMovies().add(app.getCurrentMovies().get(0));

                Print print = new Print(app);
                print.writeInfo(output);
                return;
            }

        if (user.getTokensCount() >= 2) {
            user.setTokensCount(user.getTokensCount() - 2);
            user.getPurchasedMovies().add(app.getCurrentMovies().get(0));

            Print print = new Print(app);
            print.writeInfo(output);
            return;
        }

        Print print = new Print();
        print.writeError(output);
    }

    /**
     * method that allows the user to watch a movie
     * @param app - app logic
     * @return true if the movie can be watched, false otherwise
     */
    private void watch(final AppLogic app, ArrayNode output) {
        Movie movie = app.getCurrentMovies().get(0);

        Movie movieWatched = app.getCurrentUser().getWatchedMovies().
                stream().filter(m -> m.getName().equals(movie.getName())).
                findFirst().orElse(null);

        // if the movie was already seen, do nothing
        if (movieWatched != null) {
            return;
        }

        if (app.getCurrentUser().getPurchasedMovies().contains(movie)) {
            app.getCurrentUser().getWatchedMovies().add(movie);

            Print print = new Print(app);
            print.writeInfo(output);
            return;
        }

        Print print = new Print();
        print.writeError(output);
    }

    /**
     * method that allows the user to like a movie
     * @param app - app logic
     * @return true if the movie can be liked, false otherwise
     */
    private void like(final AppLogic app, ArrayNode output) {
        Movie movie = app.getCurrentMovies().get(0);

        Movie movieLiked = app.getCurrentUser().getLikedMovies().
                stream().filter(m -> m.getName().equals(movie.getName())).
                findFirst().orElse(null);

        if (movieLiked != null) {
            Print print = new Print(app);
            print.writeError(output);
            return;
        }

        if (app.getCurrentUser().getWatchedMovies().contains(movie)) {
            movie.setNumLikes(movie.getNumLikes() + 1);
            app.getCurrentUser().getLikedMovies().add(movie);

            Print print = new Print(app);
            print.writeInfo(output);
            return;
        }

        Print print = new Print();
        print.writeError(output);
    }

    /**
     * method that allows the user to rate a movie
     * @param input - current action
     * @param app - the app logic
     * @return true if the movie can be rated, false otherwise
     */
    private void rateTheMovie(final ActionsInput input, final AppLogic app, ArrayNode output) {
        Movie movie = app.getCurrentMovies().get(0);
        User user = app.getCurrentUser();

        if (input.getRate() > 5) {
            Print print = new Print(app);
            print.writeError(output);
            return;
        }

        Movie movieRated = user.getRatedMovies().
                        stream().filter(m -> m.getName().equals(movie.getName())).
                        findFirst().orElse(null);

        if (movieRated != null) {
            // if the user already gave a rating to the movie, find that rate
            MovieRate movieRate = user.getRatingGivenToAllMovies().
                        stream().filter(r -> r.getMovie().getName().equals(movieRated.getName())).
                        findFirst().orElse(null);

            // remove it form the list and add the new rating
            int oldRating = movieRate.getRate();
            movie.getRatings().remove(Integer.valueOf(oldRating));
            movie.getRatings().add(input.getRate());

            // recalculate the movie rating
            int rating = movie.getRatings().stream().reduce(0, Integer::sum);
            movie.setRating((double) (rating) / movie.getNumRatings());

            Print print = new Print(app);
            print.writeInfo(output);
            return;
        }

        if (app.getCurrentUser().getWatchedMovies().contains(movie)) {
            movie.setNumRatings(movie.getNumRatings() + 1);
            movie.getRatings().add(input.getRate());

            // add the movie and its rating in the user MovieRate list
            MovieRate movieRate = new MovieRate(movie, input.getRate());
            user.getRatingGivenToAllMovies().add(movieRate);

            // calculate the movie rating
            int rating = movie.getRatings().stream().reduce(0, Integer::sum);
            movie.setRating((double) (rating) / movie.getNumRatings());
            app.getCurrentUser().getRatedMovies().add(movie);

            Print print = new Print(app);
            print.writeInfo(output);
            return;
        }

        Print print = new Print();
        print.writeError(output);
    }

    private void subscribe(final ActionsInput input, final AppLogic app, final ArrayNode output) {
        Movie movie = app.getCurrentMovies().get(0);

        if (movie != null && movie.getGenres().contains(input.getSubscribedGenre()) &&
                    !app.getCurrentUser().getSubscribedGenres().contains(input.getSubscribedGenre())) {

            app.getCurrentUser().getSubscribedGenres().add(input.getSubscribedGenre());
        } else {
            Print print = new Print();
            print.writeError(output);
        }
    }

    @Override
    public String getPageName() {
        return "see details";
    }
}
