package solution.Pages;

import input.files.ActionsInput;
import solution.AppLogic;
import solution.DataBase;

public final class Logout implements Page {
    private static Logout singletonInstance = null;
    private Logout() { }
    public static Logout getInstance() {
        if (singletonInstance == null) {
            singletonInstance = new Logout();
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
