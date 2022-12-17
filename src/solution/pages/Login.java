package solution.pages;

import input.files.ActionsInput;
import solution.AppLogic;
import solution.data.DataBase;
import solution.User;
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
    public boolean executeChangePage(final ActionsInput input, final AppLogic app,
                                     final DataBase dataBase) {
        return false;
    }

    @Override
    public boolean executeOnPage(final ActionsInput input, final AppLogic app,
                                 final DataBase dataBase) {
        if (this.actionsOnPage.contains(input.getFeature())) {
            return userLogin(input, app, dataBase);
        }
        return false;
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
        User user = dataBase.getUsers().
                stream().
                filter(u -> (u.getCredentials().getName().equals(input.getCredentials().getName())
                        && u.getCredentials().getPassword().equals(input.getCredentials()
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
}
