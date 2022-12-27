package solution.Commands;

import com.fasterxml.jackson.databind.node.ArrayNode;
import input.files.ActionsInput;
import solution.AppLogic;
import solution.data.DataBase;

public interface Command {
    boolean execute(ActionsInput input, AppLogic app, DataBase dataBase, ArrayNode output);
    void undo(ActionsInput input, AppLogic app, DataBase dataBase, ArrayNode output);
}
