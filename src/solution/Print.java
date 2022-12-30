package solution;

import com.fasterxml.jackson.databind.node.ArrayNode;
import solution.observer.User;

import java.util.ArrayList;

public final class Print {
    private AppLogic app;

    public Print() { }
    public Print(final AppLogic app) {
        this.app = new AppLogic(app);
    }

    /**
     * print in case of success
     @param output - object for writing to json file
     */
    public void writeInfo(final ArrayNode output) {
        output.addObject().putPOJO("error", null).
                putPOJO("currentMoviesList", this.app.getCurrentMovies()).
                putPOJO("currentUser", this.app.getCurrentUser());
    }

    /**
     * print premium user notification
     * @param output - object for writing to json file
     */
    public void writeInfoForNotification(final ArrayNode output) {
        output.addObject().putPOJO("error", null).
                putPOJO("currentMoviesList", null).
                putPOJO("currentUser", this.app.getCurrentUser());

    }

    /**
     * print in case of error
     * @param output - object for writing to json file
     */
    public void writeError(final ArrayNode output) {
        ArrayList<Movie> errorMovieList = new ArrayList<>();
        User errorUser = null;

        output.addObject().put("error", "Error").
                putPOJO("currentMoviesList", errorMovieList).
                putPOJO("currentUser", errorUser);
    }
}
