package solution;

import com.fasterxml.jackson.databind.node.ArrayNode;
import solution.data.User;

import java.util.ArrayList;

public final class Print {
    private AppLogic app;

    public Print() { }
    public Print(AppLogic app) {
        this.app = new AppLogic(app);
    }

    public void writeInfo(ArrayNode output) {
        output.addObject().putPOJO("error", null).
                putPOJO("currentMoviesList", this.app.getCurrentMovies()).
                putPOJO("currentUser", this.app.getCurrentUser());
    }

    public void writeInfoForNotification(ArrayNode output) {
        output.addObject().putPOJO("error", null).
                putPOJO("currentMoviesList", null).
                putPOJO("currentUser", this.app.getCurrentUser());

    }

    public void writeError(ArrayNode output) {
        ArrayList<Movie> errorMovieList = new ArrayList<>();
        User errorUser = null;

        output.addObject().put("error", "Error").
                putPOJO("currentMoviesList", errorMovieList).
                putPOJO("currentUser", errorUser);
    }
}
