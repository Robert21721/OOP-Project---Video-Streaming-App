package solution.Pages;

import input.files.ActionsInput;
import solution.AppLogic;
import solution.DataBase;

public final class Movies implements Page {
    private static Movies singletonInstance = null;
    private Movies() { }
    public static Movies getInstance() {
        if (singletonInstance == null) {
            singletonInstance = new Movies();
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
