package solution.Pages;

import input.files.ActionsInput;
import solution.AppLogic;
import solution.DataBase;

public final class SeeDetails implements Page {
    private static SeeDetails singletonInstance = null;
    private SeeDetails() { }
    public static SeeDetails getInstance() {
        if (singletonInstance == null) {
            singletonInstance = new SeeDetails();
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
