package solution.Pages;

import input.files.ActionsInput;
import solution.ActionFunctions;
import solution.AppLogic;
import solution.DataBase;
import solution.User;

import javax.xml.crypto.Data;
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
    public static Login getInstance() {
        if (singletonInstance == null) {
            singletonInstance = new Login();
        }
        return  singletonInstance;
    }

    public boolean executeChangePage(ActionsInput input,AppLogic app, DataBase dataBase) {
        return false;
    }

    public boolean executeOnPage(ActionsInput input,AppLogic app, DataBase dataBase) {
        // System.out.println("input in login " + input.getFeature());
        if (this.actionsOnPage.contains(input.getFeature())) {
            // System.out.println("aaaaaaaaaaaaaaaaaaaaaa");
            return userLogin(input, app, dataBase);
        }
        return false;
    }

    private boolean userLogin(ActionsInput input, AppLogic app, DataBase dataBase) {
        // System.out.println("merge");
        User user = dataBase.getUsers().
                stream().
                filter(u -> u.getCredentials().getName().equals(input.getCredentials().getName()) &&
                        u.getCredentials().getPassword().equals(input.getCredentials().getPassword())).
                findFirst().
                orElse(null);

        // System.out.println("nu mai merge");
        // System.out.println(user);

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
