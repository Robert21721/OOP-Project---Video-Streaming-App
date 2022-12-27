package solution.pages;

import com.fasterxml.jackson.databind.node.ArrayNode;
import input.files.ActionsInput;
import solution.Commands.ChangePageCommand;
import solution.Commands.Command;
import solution.Factory;
import solution.AppLogic;
import solution.Print;
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
        this.actionsChangePage.add("logout");

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
        switch (input.getFeature()) {
            case "buy tokens":
                buyTokens(input, app, output);
                break;

            case "buy premium account":
                buyPremiumAccount(app, output);
                break;

            default:
        }
    }

    /**
     * method that allows the user to buy a premium account
     * @param app - the app logic
     * @return true if the user can buy the premium account, false otherwise
     */
    private void buyPremiumAccount(final AppLogic app, ArrayNode output) {
        if (app.getCurrentUser().getTokensCount() >= 10) {
            app.getCurrentUser().setTokensCount(app.getCurrentUser().getTokensCount()  - 10);
            app.getCurrentUser().getCredentials().setAccountType("premium");
            return;
        }

        Print print = new Print();
        print.writeError(output);
    }

    /**
     * method that allows the user to buy tokens
     * @param input - current action
     * @param app - the app logic
     * @return true if the user can buy tokens, false otherwise
     */
    private void buyTokens(final ActionsInput input, final AppLogic app, ArrayNode output) {

        int balance = Integer.parseInt(app.getCurrentUser().getCredentials().getBalance());
        int nrTokens = input.getCount();

        if (balance >= nrTokens) {
            app.getCurrentUser().setTokensCount(nrTokens);
            app.getCurrentUser().getCredentials().setBalance(String.valueOf(balance - nrTokens));
            return;
        }

        Print print = new Print();
        print.writeError(output);
    }

    @Override
    public String getPageName() {
        return "upgrades";
    }
}
