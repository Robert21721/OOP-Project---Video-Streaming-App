package solution.Pages;

import input.files.ActionsInput;
import solution.AppLogic;
import solution.DataBase;

public final class Upgrades implements Page {
    private static Upgrades singletonInstance = null;
    private Upgrades() { }
    public static Upgrades getInstance() {
        if (singletonInstance == null) {
            singletonInstance = new Upgrades();
        }
        return  singletonInstance;
    }

    @Override
    public boolean executeChangePage(ActionsInput input, AppLogic app, DataBase dataBase) {
        return false;
    }

    @Override
    public boolean executeOnPage(ActionsInput input, AppLogic app, DataBase dataBase) {
        return false;
    }
}
