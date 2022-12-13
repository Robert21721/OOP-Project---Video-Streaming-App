package solution.Pages;

import input.files.ActionsInput;
import solution.ActionFunctions;
import solution.AppLogic;
import solution.DataBase;

import java.util.ArrayList;

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
    }
    public static SeeDetails getInstance() {
        if (singletonInstance == null) {
            singletonInstance = new SeeDetails();
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
        return false;
    }

    private void purchase(ActionsInput input, AppLogic app, DataBase dataBase) {

    }

    private void watch(ActionsInput input, AppLogic app, DataBase dataBase) {

    }

    private void like(ActionsInput input, AppLogic app, DataBase dataBase) {

    }

    private void rateTheMovie(ActionsInput input, AppLogic app, DataBase dataBase) {

    }
}
