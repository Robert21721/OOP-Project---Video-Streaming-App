package solution;

import solution.Pages.Login;
import solution.Pages.Logout;
import solution.Pages.Page;
import solution.Pages.Register;

public class ActionFunctions {
    private ActionFunctions() { }

    public static Page changePage(String pageName) {
        switch (pageName) {
            case "login":
                return Login.getInstance();

            case "register":
                return Register.getInstance();
        }

        return null;
    }
}
