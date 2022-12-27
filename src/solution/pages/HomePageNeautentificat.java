package solution.pages;

import com.fasterxml.jackson.databind.node.ArrayNode;
import input.files.ActionsInput;
import solution.Commands.ChangePageCommand;
import solution.Factory;
import solution.AppLogic;
import solution.Print;
import solution.data.DataBase;

import java.util.ArrayList;

public final class HomePageNeautentificat implements Page {
    private static HomePageNeautentificat singletonInstance = null;
    private ArrayList<String> actionsChangePage;
    private ArrayList<String> actionsOnPage;
    private HomePageNeautentificat() {
        this.actionsChangePage = new ArrayList<>();
        this.actionsOnPage = new ArrayList<>();

        this.actionsChangePage.add("login");
        this.actionsChangePage.add("register");
    }

    /**
     * singleton for "homePage Neautentificat" page
     * @return an instance of "homePage Neautentificat" class
     */
    public static HomePageNeautentificat getInstance() {
        if (singletonInstance == null) {
            singletonInstance = new HomePageNeautentificat();
        }
        return  singletonInstance;
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
        return "homePage N";
    }
}
