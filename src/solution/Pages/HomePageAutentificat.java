package solution.Pages;

import input.files.ActionsInput;
import solution.AppLogic;
import solution.DataBase;

import java.util.ArrayList;

public final class HomePageAutentificat implements Page {
    private static HomePageAutentificat singletonInstance = null;
    private ArrayList<String> actionsChangePage;
    private ArrayList<String> actionsOnPage;

    private HomePageAutentificat() {
        this.actionsChangePage = new ArrayList<>();
        this.actionsOnPage = new ArrayList<>();

        // this.actionsChangePage.add()
    }
    public static HomePageAutentificat getInstance() {
        if (singletonInstance == null) {
            singletonInstance = new HomePageAutentificat();
        }
        return singletonInstance;
    }

    @Override
    public boolean executeChangePage(ActionsInput input, AppLogic app, DataBase dataBase) {
        if (input.getPage().equals("logout")) {
            app.setCurrentUser(null);
            app.setCurrentPage(HomePageNeautentificat.getInstance());
            return true;
        }
        return false;
    }

    @Override
    public boolean executeOnPage(ActionsInput input, AppLogic app, DataBase dataBase) {
        return false;
    }
}
