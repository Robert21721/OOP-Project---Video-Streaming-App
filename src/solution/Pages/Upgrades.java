package solution.Pages;

import input.files.ActionsInput;
import solution.ActionFunctions;
import solution.AppLogic;
import solution.DataBase;

import java.util.ArrayList;

public final class Upgrades implements Page {
    private static Upgrades singletonInstance = null;
    private ArrayList<String> actionsChangePage;
    private ArrayList<String> actionsOnPage;
    private Upgrades() {
        this.actionsChangePage = new ArrayList<>();
        this.actionsOnPage = new ArrayList<>();

        this.actionsChangePage.add("movies");

        // todo actions on page
    }
    public static Upgrades getInstance() {
        if (singletonInstance == null) {
            singletonInstance = new Upgrades();
        }
        return  singletonInstance;
    }

    @Override
    public boolean executeChangePage(ActionsInput input, AppLogic app, DataBase dataBase) {
        if (input.getPage().equals("logout")) {
            app.setCurrentUser(null);
            app.setCurrentPage(HomePageNeautentificat.getInstance());
            return true;
        }

        if (this.actionsChangePage.contains(input.getPage())) {
            app.setCurrentPage(ActionFunctions.changePage(input.getPage()));
            return true;
        }
        return false;
    }

    @Override
    public boolean executeOnPage(ActionsInput input, AppLogic app, DataBase dataBase) {
        // todo
        return false;
    }

    private void buyPremiumAccount(ActionsInput input, AppLogic app, DataBase dataBase) {
        // todo
    }

    private void buyTockens(ActionsInput input, AppLogic app, DataBase dataBase) {
        // todo
    }
}
