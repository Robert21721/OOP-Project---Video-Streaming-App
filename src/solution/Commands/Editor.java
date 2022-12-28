package solution.Commands;

import com.fasterxml.jackson.databind.node.ArrayNode;
import input.files.ActionsInput;
import solution.AppLogic;
import solution.Print;
import solution.data.DataBase;

import java.util.ArrayList;
import java.util.LinkedList;

public final class Editor {
    private LinkedList<Command> history = new LinkedList<>();

    public boolean edit(final Command command, final ActionsInput input,
                        final AppLogic app, final DataBase dataBase, ArrayNode output) {

        if (command.execute(input, app, dataBase, output)) {
            this.history.push(command);
            return true;
        }
        return false;
    }

    public void undo(final ActionsInput input, final AppLogic app, final DataBase dataBase, ArrayNode output) {
        ArrayList<String> pages = new ArrayList<>();
        pages.add("homePage A");
        pages.add("homePage N");
        pages.add("login");
        pages.add("register");

        if (pages.contains(app.getCurrentPage().getPageName())) {
            Print print = new Print();
            print.writeError(output);
            return;
        }

        if (history.isEmpty()) {
            return;
        }

        Command command = history.pop();
        if (command != null) {
            command.undo(input, app, dataBase, output);
        }
    }

    public LinkedList<Command> getHistory() {
        return history;
    }

    public void setHistory(LinkedList<Command> history) {
        this.history = history;
    }
}
