package solution.Commands;

import input.files.ActionsInput;
import solution.Factory;
import solution.AppLogic;
import solution.Movie;
import solution.data.DataBase;
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
    }

    @Override
    public boolean execute(final ActionsInput input, final AppLogic app, final DataBase dataBase) {
        this.oldPageName = this.getNewPageName();
        this.newPageName = input.getPage();
        this.oldMovieName = this.getNewMovieName();
        this.newMovieName = input.getMovie();

        return change(input.getMovie(), input.getPage(), app, dataBase);
    }

    @Override
    public void undo(final ActionsInput input, final AppLogic app, final DataBase dataBase) {
        this.newPageName = this.getOldPageName();
        this.oldPageName = app.getCurrentPage().getPageName();
        this.newMovieName = this.getOldMovieName();
        this.oldMovieName = app.getCurrentMovies().get(0).getName();

        change(this.newMovieName, this.newPageName, app, dataBase);
    }

    private boolean change(final String movieName, final String pageName,
                           final AppLogic app, final DataBase dataBase) {
        switch (pageName) {
            case "login":
            case "register":
                app.setCurrentPage(Factory.getPage(pageName));
                return true;

            case "logout":
                app.setCurrentUser(null);
                app.setCurrentPage(HomePageNeautentificat.getInstance());
                app.getCurrentMovies().clear();
                return true;

            case "movies":
            case "upgrades":
                ArrayList<Movie> userMovies = dataBase.getMovies().
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
