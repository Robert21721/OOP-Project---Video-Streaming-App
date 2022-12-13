package solution.Pages;

import input.files.ActionsInput;
import solution.ActionFunctions;
import solution.AppLogic;
import solution.DataBase;

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
    public static HomePageNeautentificat getInstance() {
        if (singletonInstance == null) {
            singletonInstance = new HomePageNeautentificat();
        }
        return  singletonInstance;
    }

    public boolean executeChangePage(ActionsInput input,AppLogic app, DataBase dataBase) {
        if (this.actionsChangePage.contains(input.getPage())) {
            app.setCurrentPage(ActionFunctions.changePage(input.getPage()));
            return true;
        }
        return false;
    }

    public boolean executeOnPage(ActionsInput input,AppLogic app, DataBase dataBase) {
            return false;
        }
}
