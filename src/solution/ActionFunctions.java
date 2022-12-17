package solution;

import solution.pages.*;

public final class ActionFunctions {
    private ActionFunctions() { }

    /**
     * receives as a parameter the name of a page and
     * returns an instance of the corresponding page
     */
    public static Page changePage(final String pageName) {
        switch (pageName) {
            case "login":
                return Login.getInstance();

            case "register":
                return Register.getInstance();

            case "movies":
                return Movies.getInstance();

            case "upgrades":
                return Upgrades.getInstance();

            case "see details":
                return SeeDetails.getInstance();

            default:
                return null;
        }
    }
}
