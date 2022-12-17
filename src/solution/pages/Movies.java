package solution.pages;

import input.files.ActionsInput;
import solution.ActionFunctions;
import solution.AppLogic;
import solution.data.DataBase;
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

        this.actionsChangePage.add("see details");
        this.actionsChangePage.add("movies");

        this.actionsOnPage.add("search");
        this.actionsOnPage.add("filter");
    }

    /**
     * singleton for "Movies" page
     * @return an instance of "Movies" class
     */
    public static Movies getInstance() {
        if (singletonInstance == null) {
            singletonInstance = new Movies();
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
            if (input.getPage().equals("see details")) {

                Movie movie = app.getCurrentMovies().
                        stream().
                        filter(m -> m.getName().equals(input.getMovie())).
                        findFirst().
                        orElse(null);

                if (movie == null) {
                    return false;
                }

                app.getCurrentMovies().clear();
                app.getCurrentMovies().add(movie);
            }

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
        if (this.actionsOnPage.contains(input.getFeature())) {
            switch (input.getFeature()) {
                case "search":
                    search(input, app, dataBase);
                    break;

                case "filter":
                    filter(input, app, dataBase);
                    break;

                default:
            }

            return true;
        }
        return false;
    }

    /**
     * search a movie in the list and update the currentMovies list
     * @param input - current action
     * @param app - the app logic
     * @param dataBase - database where movies and users are stored
     */
    private void search(final ActionsInput input, final AppLogic app,
                        final DataBase dataBase) {
        ArrayList<Movie> userMovies = dataBase.getMovies().
                stream().
                filter(movie -> !movie.getCountriesBanned()
                        .contains(app.getCurrentUser().getCredentials().getCountry())).
                collect(Collectors.toCollection(ArrayList::new));

        userMovies = userMovies.
                stream().
                filter(movie -> movie.getName().startsWith(input.getStartsWith())).
                collect(Collectors.toCollection(ArrayList::new));
        app.setCurrentMovies(userMovies);
    }

    /**
     * filter the currentMovies list according to some criteria
     * @param input - current action
     * @param app - the app logic
     * @param dataBase - database where movies and users are stored
     */
    private void filter(final ActionsInput input, final AppLogic app,
                        final DataBase dataBase) {
        ArrayList<Movie> userMovies = dataBase.
                getMovies().
                stream().
                filter(movie -> !movie.getCountriesBanned()
                        .contains(app.getCurrentUser().getCredentials().getCountry())).
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
                filter(movie -> ((inputActors == null || movie.getActors().containsAll(inputActors))
                        && (inputGenre == null || movie.getGenres().containsAll(inputGenre)))).
                collect(Collectors.toCollection(ArrayList::new));

        if (input.getFilters().getSort() == null) {
            app.setCurrentMovies(userMovies);
            return;
        }

        if (input.getFilters().getSort().getRating() == null) {
            if (input.getFilters().getSort().getDuration().equals("increasing")) {
                userMovies.sort((m1, m2) -> m1.getDuration() - m2.getDuration());
                app.setCurrentMovies(userMovies);
                return;
            }

            if (input.getFilters().getSort().getDuration().equals("decreasing")) {
                userMovies.sort((m1, m2) -> m2.getDuration() - m1.getDuration());
                app.setCurrentMovies(userMovies);
                return;
            }
        }

        if (input.getFilters().getSort().getDuration() == null) {
            if (input.getFilters().getSort().getRating().equals("increasing")) {
                userMovies.sort((m1, m2) -> Double.compare(m1.getRating(), m2.getRating()));
                app.setCurrentMovies(userMovies);
                return;
            }

            if (input.getFilters().getSort().getRating().equals("decreasing")) {
                userMovies.sort((m1, m2) -> Double.compare(m2.getRating(), m1.getRating()));
                app.setCurrentMovies(userMovies);
                return;
            }
        }


        if (input.getFilters().getSort().getRating().equals("increasing")) {
            if (input.getFilters().getSort().getDuration().equals("increasing")) {
                Collections.sort(userMovies, new ComparatorIncrIncr());
                app.setCurrentMovies(userMovies);
                return;
            }

              if (input.getFilters().getSort().getDuration().equals("decreasing")) {
                  Collections.sort(userMovies, new ComparatorIncrDecr());
                  app.setCurrentMovies(userMovies);
                  return;
              }
        }

        if (input.getFilters().getSort().getRating().equals("decreasing")) {
            if (input.getFilters().getSort().getDuration().equals("increasing")) {
                Collections.sort(userMovies, new ComparatorDecrIncr());
                app.setCurrentMovies(userMovies);
                return;
            }

            if (input.getFilters().getSort().getDuration().equals("decreasing")) {
                Collections.sort(userMovies, new ComparatorDecrDecr());
                app.setCurrentMovies(userMovies);
                return;
            }
        }
    }
}
