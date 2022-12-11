package solution.Pages;

import input.files.ActionsInput;
import solution.ActionFunctions;
import solution.AppLogic;
import solution.DataBase;

public interface Page {
    public boolean executeChangePage(ActionsInput input,AppLogic app, DataBase dataBase);
    public boolean executeOnPage(ActionsInput input,AppLogic app, DataBase dataBase);
}
