package solution.pages;

import input.files.ActionsInput;
import solution.AppLogic;
import solution.data.DataBase;

public interface Page {
    /**
     * method that change the current page
     * @param input - current action
     * @param app - the app logic
     * @param dataBase - database where movies and users are stored
     * @return true in the action is allowed, false otherwise
     */
    boolean executeChangePage(ActionsInput input, AppLogic app, DataBase dataBase);

    /**
     * method that execute an action on the current page
     * @param input - current action
     * @param app - the app logic
     * @param dataBase - database where movies and users are stored
     * @return true in the action is allowed, false otherwise
     */
    boolean executeOnPage(ActionsInput input, AppLogic app, DataBase dataBase);

    String getPageName();
}
