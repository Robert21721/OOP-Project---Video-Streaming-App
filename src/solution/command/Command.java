package solution.command;

import com.fasterxml.jackson.databind.node.ArrayNode;
import input.files.ActionsInput;
import solution.AppLogic;
import solution.observer.DataBase;

public interface Command {

    /**
     * execute a given command
     * @param input - user input
     * @param app - the application status
     * @param dataBase - database with users and movies
     * @param output - object for writing to json file
     * @return true if the execution ended wit success
     */
    boolean execute(ActionsInput input, AppLogic app, DataBase dataBase, ArrayNode output);

    /**
     * cancels the effect of a command
     * @param input - user input
     * @param app - the application status
     * @param dataBase - database with users and movies
     * @param output - object for writing to json file
     */
    void undo(ActionsInput input, AppLogic app, DataBase dataBase, ArrayNode output);
}
