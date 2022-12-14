package solution.Pages;

import input.files.ActionsInput;
import solution.AppLogic;
import solution.Credentials;
import solution.DataBase;
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
    public static Register getInstance() {
        if (singletonInstance == null) {
            singletonInstance = new Register();
        }
        return  singletonInstance;
    }

    public boolean executeChangePage(ActionsInput input, AppLogic app, DataBase dataBase) {
        return false;
    }

    public boolean executeOnPage(ActionsInput input, AppLogic app, DataBase dataBase) {
        if (this.actionsOnPage.contains(input.getFeature())) {
            return userRegister(input, app, dataBase);
        }
        return false;
    }

    private boolean userRegister(ActionsInput input, AppLogic app, DataBase dataBase) {
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
}
