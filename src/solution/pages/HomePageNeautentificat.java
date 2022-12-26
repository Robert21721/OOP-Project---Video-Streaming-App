package solution.pages;

import input.files.ActionsInput;
import solution.Commands.ChangePageCommand;
import solution.Factory;
import solution.AppLogic;
import solution.data.DataBase;

import java.util.ArrayList;

public final class HomePageNeautentificat implements Page {
    private static HomePageNeautentificat singletonInstance = null;
    private ArrayList<String> actionsChangePage;
    private ArrayList<String> actionsOnPage;
    private HomePageNeautentificat() {
        this.actionsChangePage = new ArrayList<>();
        this.actionsOnPage = new ArrayList<>();

        this.actionsChangePage.add("login");
        this.actionsChangePage.add("register");
    }

    /**
     * singleton for "homePage Neautentificat" page
     * @return an instance of "homePage Neautentificat" class
     */
    public static HomePageNeautentificat getInstance() {
        if (singletonInstance == null) {
            singletonInstance = new HomePageNeautentificat();
        }
        return  singletonInstance;
    }

    @Override
    public boolean executeChangePage(final ActionsInput input, final AppLogic app,
                                     final DataBase dataBase) {
        if (this.actionsChangePage.contains(input.getPage())) {
            ChangePageCommand command = new ChangePageCommand(input.getPage());
            return app.getEditor().edit(command, input, app, dataBase);
        }
        return false;
    }

    @Override
    public boolean executeOnPage(final ActionsInput input, final AppLogic app,
                                 final DataBase dataBase) {
        return false;
    }

    @Override
    public String getPageName() {
        return "homePage N";
    }
}
