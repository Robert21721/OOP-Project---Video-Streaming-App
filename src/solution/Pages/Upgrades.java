package solution.Pages;

import input.files.ActionsInput;
import solution.ActionFunctions;
import solution.AppLogic;
import solution.DataBase;
import solution.Movie;

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
        this.actionsOnPage.add("buy tokens");
        this.actionsOnPage.add("buy premium account");
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
            app.getCurrentMovies().clear();
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
        switch (input.getFeature()) {
            case "buy tokens":
                return buyTockens(input, app, dataBase);

            case "buy premium account":
                return buyPremiumAccount(input, app, dataBase);
        }
        return false;
    }

    private boolean buyPremiumAccount(ActionsInput input, AppLogic app, DataBase dataBase) {
        if (app.getCurrentUser().getTokensCount() >= 10) {
            app.getCurrentUser().setTokensCount(app.getCurrentUser().getTokensCount()  - 10);
            app.getCurrentUser().getCredentials().setAccountType("premium");
            return true;
        }
        return false;
    }

    private boolean buyTockens(ActionsInput input, AppLogic app, DataBase dataBase) {

        int balance = Integer.parseInt(app.getCurrentUser().getCredentials().getBalance());
        int nrTokens = input.getCount();

        if (balance >= nrTokens) {
            app.getCurrentUser().setTokensCount(nrTokens);
            app.getCurrentUser().getCredentials().setBalance(String.valueOf(balance - nrTokens));
            return true;
        }
        return false;
    }
}
