package solution.command;

import com.fasterxml.jackson.databind.node.ArrayNode;
import input.files.ActionsInput;
import solution.Factory;
import solution.AppLogic;
import solution.Movie;
import solution.Print;
import solution.observer.DataBase;
import solution.pages.HomePageNeautentificat;

import java.util.ArrayList;
import java.util.stream.Collectors;

public final class ChangePageCommand implements Command {
    private String oldPageName;
    private String newPageName;
    private String oldMovieName;
    private String newMovieName;

    public ChangePageCommand(final String newPageName) {
        this.newPageName = newPageName;
        this.newMovieName = null;
    }

    @Override
    public boolean execute(final ActionsInput input, final AppLogic app,
                           final DataBase dataBase, final ArrayNode output) {

        if (change(input.getMovie(), input.getPage(), app, dataBase, output)) {
            this.oldPageName = this.newPageName;
            this.oldMovieName = this.newMovieName;
            this.newPageName = app.getCurrentPage().getPageName();

            if (!app.getCurrentMovies().isEmpty()) {
                this.newMovieName = app.getCurrentMovies().get(0).getName();
            }

            return true;
        }

        return false;
    }

    @Override
    public void undo(final ActionsInput input, final AppLogic app,
                     final DataBase dataBase, final ArrayNode output) {
        this.newPageName = this.oldPageName;
        this.oldPageName = app.getCurrentPage().getPageName();
        this.newMovieName = this.oldMovieName;

        if (!app.getCurrentMovies().isEmpty()) {
            this.oldMovieName = app.getCurrentMovies().get(0).getName();
        }

        change(this.newMovieName, this.newPageName, app, dataBase, output);
    }

    private boolean change(final String movieName, final String pageName,
                           final AppLogic app, final DataBase dataBase, ArrayNode output) {
        switch (pageName) {
            case "login":
            case "register":
            case "homePage A":
            case "homePage N":
                app.setCurrentPage(Factory.getPage(pageName));
                return true;

            case "logout":
                app.setCurrentUser(null);
                app.setCurrentPage(HomePageNeautentificat.getInstance());
                app.getCurrentMovies().clear();
                app.getEditor().getHistory().clear();
                return true;

            case "movies":
                ArrayList<Movie> userMovies = dataBase.getMovies().
                        stream().
                        filter(movie -> !movie.getCountriesBanned()
                                .contains(app.getCurrentUser().getCredentials().getCountry())).
                        collect(Collectors.toCollection(ArrayList::new));

                app.setCurrentMovies(userMovies);
                app.setCurrentPage(Factory.getPage(pageName));

                Print print = new Print(app);
                print.writeInfo(output);
                return true;

            case "upgrades":
                userMovies = dataBase.getMovies().
                        stream().
                        filter(movie -> !movie.getCountriesBanned()
                                .contains(app.getCurrentUser().getCredentials().getCountry())).
                        collect(Collectors.toCollection(ArrayList::new));

                app.setCurrentMovies(userMovies);
                app.setCurrentPage(Factory.getPage(pageName));
                return true;

            case "see details":
                Movie movie = app.getCurrentMovies().
                        stream().
                        filter(m -> m.getName().equals(movieName)).
                        findFirst().
                        orElse(null);

                if (movie == null) {
                    return false;
                }

                app.getCurrentMovies().clear();
                app.getCurrentMovies().add(movie);
                app.setCurrentPage(Factory.getPage(pageName));

                Print print1 = new Print(app);
                print1.writeInfo(output);
                return true;

            default:
        }
        return false;
    }

    public String getOldPageName() {
        return oldPageName;
    }

    public void setOldPageName(final String oldPageName) {
        this.oldPageName = oldPageName;
    }

    public String getNewPageName() {
        return newPageName;
    }

    public void setNewPageName(final String newPageName) {
        this.newPageName = newPageName;
    }

    public String getOldMovieName() {
        return oldMovieName;
    }

    public void setOldMovieName(final String oldMovieName) {
        this.oldMovieName = oldMovieName;
    }

    public String getNewMovieName() {
        return newMovieName;
    }

    public void setNewMovieName(final String newMovieName) {
        this.newMovieName = newMovieName;
    }
}
