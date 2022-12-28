package solution;

import solution.pages.*;

public final class Factory {
    private Factory() { }

    /**
     * receives as a parameter the name of a page and
     * returns an instance of the corresponding page
     */
    public static Page getPage(final String pageName) {
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

            case "homePage A":
                return HomePageAutentificat.getInstance();

            case "homePage N":
                return HomePageNeautentificat.getInstance();

            default:
                return null;
        }
    }
}
