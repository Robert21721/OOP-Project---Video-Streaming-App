package solution.pages;

import com.fasterxml.jackson.databind.node.ArrayNode;
import input.files.ActionsInput;
import solution.AppLogic;
import solution.Print;
import solution.observer.DataBase;
import solution.observer.User;
import java.util.ArrayList;

public final class Login implements Page {
    private static Login singletonInstance = null;
    private ArrayList<String> actionsChangePage;
    private ArrayList<String> actionsOnPage;
    private Login() {
        this.actionsChangePage = new ArrayList<>();
        this.actionsOnPage = new ArrayList<>();

        this.actionsOnPage.add("login");
    }

    /**
     * singleton for "Login" page
     * @return an instance of "Login" class
     */
    public static Login getInstance() {
        if (singletonInstance == null) {
            singletonInstance = new Login();
        }
        return  singletonInstance;
    }

    @Override
    public void executeChangePage(final ActionsInput input, final AppLogic app,
                                     final DataBase dataBase, final ArrayNode output) {

        Print print = new Print(app);
        print.writeError(output);
    }

    @Override
    public void executeOnPage(final ActionsInput input, final AppLogic app,
                                 final DataBase dataBase, final ArrayNode output) {

        if (this.actionsOnPage.contains(input.getFeature())) {
            if (userLogin(input, app, dataBase)) {
                Print print = new Print(app);
                print.writeInfo(output);
                return;
            }
        }

        Print print = new Print();
        print.writeError(output);
    }

    /**
     * method that allows the user to authenticate
     * @param input - current action
     * @param app - the app logic
     * @param dataBase - database where movies and users are stored
     * @return true if the login was successful, false otherwise
     */
    private boolean userLogin(final ActionsInput input, final AppLogic app,
                              final DataBase dataBase) {
        User user = (User) dataBase.getUsers().
                stream().
                filter((u -> ((User) u).getCredentials().getName().equals(input.getCredentials().getName())
                        && ((User) u).getCredentials().getPassword().equals(input.getCredentials()
                        .getPassword()))).
                findFirst().
                orElse(null);

        if (user != null) {
            app.setCurrentUser(user);
            app.setCurrentPage(HomePageAutentificat.getInstance());
            return true;
        } else {
            app.setCurrentUser(null);
            app.setCurrentPage(HomePageNeautentificat.getInstance());
            return false;
        }
    }

    @Override
    public String getPageName() {
        return "login";
    }
}
