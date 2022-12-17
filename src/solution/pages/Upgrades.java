package solution.pages;

import input.files.ActionsInput;
import solution.ActionFunctions;
import solution.AppLogic;
import solution.data.DataBase;
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

    /**
     * singleton for "Updates" page
     * @return an instance of "Updates" class
     */
    public static Upgrades getInstance() {
        if (singletonInstance == null) {
            singletonInstance = new Upgrades();
        }
        return  singletonInstance;
    }

    @Override
    public boolean executeChangePage(final ActionsInput input, final AppLogic app,
                                     final DataBase dataBase) {
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
    public boolean executeOnPage(final ActionsInput input, final AppLogic app,
                                 final DataBase dataBase) {
        switch (input.getFeature()) {
            case "buy tokens":
                return buyTokens(input, app);

            case "buy premium account":
                return buyPremiumAccount(app);

            default:
                return false;
        }
    }

    /**
     * method that allows the user to buy a premium account
     * @param app - the app logic
     * @return true if the user can buy the premium account, false otherwise
     */
    private boolean buyPremiumAccount(final AppLogic app) {
        if (app.getCurrentUser().getTokensCount() >= 10) {
            app.getCurrentUser().setTokensCount(app.getCurrentUser().getTokensCount()  - 10);
            app.getCurrentUser().getCredentials().setAccountType("premium");
            return true;
        }
        return false;
    }

    /**
     * method that allows the user to buy tokens
     * @param input - current action
     * @param app - the app logic
     * @return true if the user can buy tokens, false otherwise
     */
    private boolean buyTokens(final ActionsInput input, final AppLogic app) {

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
