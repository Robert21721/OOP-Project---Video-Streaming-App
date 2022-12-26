package solution.Commands;

import input.files.ActionsInput;
import solution.AppLogic;
import solution.data.DataBase;

import java.util.LinkedList;

public final class Editor {
    private LinkedList<Command> history = new LinkedList<>();

    public boolean edit(final Command command, final ActionsInput input,
                        final AppLogic app, final DataBase dataBase) {
        history.push(command);
        return command.execute(input, app, dataBase);
    }

    public void undo(final ActionsInput input, final AppLogic app, final DataBase dataBase) {
        if (history.isEmpty()) {
            return;
        }

        Command command = history.pop();
        if (command != null) {
            command.undo(input, app, dataBase);
        }
    }
}
