package solution.Commands;

import input.files.ActionsInput;
import solution.AppLogic;
import solution.data.DataBase;

public interface Command {
    boolean execute(ActionsInput input, AppLogic app, DataBase dataBase);
    void undo(ActionsInput input, AppLogic app, DataBase dataBase);
}
