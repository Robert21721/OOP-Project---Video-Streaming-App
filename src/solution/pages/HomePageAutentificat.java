package solution.pages;

import input.files.ActionsInput;
import solution.Commands.ChangePageCommand;
import solution.Factory;
import solution.AppLogic;
import solution.data.DataBase;
import solution.Movie;
import java.util.ArrayList;
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
        this.actionsChangePage.add("logout");
    }

    /**
     * singleton for "homePage Autentificat" page
     * @return an instance of "homePage Autentificat" class
     */

    public static HomePageAutentificat getInstance() {
        if (singletonInstance == null) {
            singletonInstance = new HomePageAutentificat();
        }
        return singletonInstance;
    }

    @Override
    public boolean executeChangePage(final ActionsInput input, final AppLogic app,
                                     final DataBase dataBase) {

        if (this.actionsChangePage.contains(input.getPage())) {
            ChangePageCommand command = new ChangePageCommand(input.getPage());
            return app.getEditor().edit(command, input, app, dataBase);
        }

        return false;
    }

    @Override
    public boolean executeOnPage(final ActionsInput input,
                                 final AppLogic app, final DataBase dataBase) {
        return false;
    }

    @Override
    public String getPageName() {
        return "homePage A";
    }
}
