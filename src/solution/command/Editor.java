package solution.command;

import com.fasterxml.jackson.databind.node.ArrayNode;
import input.files.ActionsInput;
import solution.AppLogic;
import solution.observer.DataBase;

import java.util.LinkedList;

public final class Editor {
    private LinkedList<Command> history = new LinkedList<>();

    /**
     * call the execute method of a given command and add it in the history
     * @param command - command that will be executed
     * @param input - user input
     * @param app - the application status
     * @param dataBase - database with users and movies
     * @param output - object for writing to json file
     * @return true if the command's execution succeeds
     */
    public boolean edit(final Command command, final ActionsInput input,
                final AppLogic app, final DataBase dataBase, final ArrayNode output) {

        if (command.execute(input, app, dataBase, output)) {
            this.history.push(command);
            return true;
        }
        return false;
    }

    /**
     * call undo method for the last command that was given
     * @param input - user input
     * @param app - the application status
     * @param dataBase - database with users and movies
     * @param output - object for writing to json file
     */
    public void undo(final ActionsInput input, final AppLogic app,
                            final DataBase dataBase, final ArrayNode output) {

        if (history.isEmpty()) {
            return;
        }

        Command command = history.pop();
        if (command != null) {
            if (!command.undo(input, app, dataBase, output)) {
                history.push(command);
            }
        }
    }

    public LinkedList<Command> getHistory() {
        return history;
    }

    public void setHistory(final LinkedList<Command> history) {
        this.history = history;
    }
}
