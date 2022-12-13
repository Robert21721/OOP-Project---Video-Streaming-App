package solution;

import solution.Pages.*;

public class ActionFunctions {
    private ActionFunctions() { }

    public static Page changePage(String pageName) {
        switch (pageName) {
            case "login":
                return Login.getInstance();

            case "register":
                return Register.getInstance();

            case "movies":
                return Movies.getInstance();

            case "upgrades":
                return Upgrades.getInstance();
        }

        return null;
    }
}
