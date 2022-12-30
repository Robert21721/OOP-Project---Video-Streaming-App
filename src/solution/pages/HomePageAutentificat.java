package solution.pages;

import com.fasterxml.jackson.databind.node.ArrayNode;
import input.files.ActionsInput;
import solution.command.ChangePageCommand;
import solution.AppLogic;
import solution.Print;
import solution.observer.DataBase;

import java.util.ArrayList;

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
    public void executeChangePage(final ActionsInput input, final AppLogic app,
                                     final DataBase dataBase, final ArrayNode output) {

        if (this.actionsChangePage.contains(input.getPage())) {
            ChangePageCommand command = new ChangePageCommand(this.getPageName());

            if (app.getEditor().edit(command, input, app, dataBase, output)) {
                return;
            }
        }

        Print print = new Print();
        print.writeError(output);
    }

    @Override
    public void executeOnPage(final ActionsInput input, final AppLogic app,
                                 final DataBase dataBase, final ArrayNode output) {

        Print print = new Print();
        print.writeError(output);
    }

    @Override
    public String getPageName() {
        return "homePage A";
    }
}
