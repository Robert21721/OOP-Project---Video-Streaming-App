package solution.pages;

import com.fasterxml.jackson.databind.node.ArrayNode;
import input.files.ActionsInput;
import solution.AppLogic;
import solution.Print;
import solution.data.Credentials;
import solution.data.DataBase;
import solution.User;

import java.util.ArrayList;

public final class Register implements Page {
    private static Register singletonInstance = null;
    private ArrayList<String> actionsChangePage;
    private ArrayList<String> actionsOnPage;
    private Register() {
        this.actionsChangePage = new ArrayList<>();
        this.actionsOnPage = new ArrayList<>();

        this.actionsOnPage.add("register");
    }

    /**
     * singleton for "Register" page
     * @return an instance of "Register" class
     */
    public static Register getInstance() {
        if (singletonInstance == null) {
            singletonInstance = new Register();
        }
        return  singletonInstance;
    }

    @Override
    public void executeChangePage(final ActionsInput input, final AppLogic app,
                                     final DataBase dataBase, final ArrayNode output) {

        Print print = new Print();
        print.writeError(output);
    }

    @Override
    public void executeOnPage(final ActionsInput input, final AppLogic app,
                                 final DataBase dataBase, final ArrayNode output) {

        if (this.actionsOnPage.contains(input.getFeature())) {
            if (userRegister(input, app, dataBase)) {
                Print print = new Print(app);
                print.writeInfo(output);
                return;
            }
        }

        Print print = new Print();
        print.writeError(output);
    }

    /**
     * method that allows the user to register
     * @param input - current action
     * @param app - the app logic
     * @param dataBase - database where movies and users are stored
     * @return true if the register was successful, false otherwise
     */
    private boolean userRegister(final ActionsInput input, final AppLogic app,
                                 final DataBase dataBase) {
        User existentUser = dataBase.getUsers().
                stream().
                filter(u -> u.getCredentials().getName().equals(input.getCredentials().getName())).
                findFirst().
                orElse(null);

        if (existentUser != null) {
            return false;
        } else {
            User newUser = new User();
            Credentials newUserCredentials = new Credentials(input.getCredentials());
            newUser.setCredentials(newUserCredentials);

            dataBase.getUsers().add(newUser);
            app.setCurrentPage(HomePageAutentificat.getInstance());
            app.setCurrentUser(newUser);
           return true;
        }
    }

    @Override
    public String getPageName() {
        return "register";
    }
}
