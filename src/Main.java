import input.files.ActionsInput;
import input.files.Input;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectWriter;
import com.fasterxml.jackson.databind.node.ArrayNode;
import solution.*;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

public class Main {
    public static void main(String[] args) throws IOException {
        ObjectMapper objectMapper = new ObjectMapper();
        Input inputData = objectMapper.readValue(new File(args[0]), Input.class);

        ArrayNode output = objectMapper.createArrayNode();

        DataBase dataBase = new DataBase(inputData);
        AppLogic appLogic = new AppLogic();

        ArrayList<Movie> errorMovieList = new ArrayList<>();
        User errorUser = null;

        for (ActionsInput action : inputData.getActions()) {

            if (action.getType().equals("change page")) {
                if (!appLogic.getCurrentPage().executeChangePage(action, appLogic, dataBase)) {
                    output.addObject().put("error", "Error").
                            putPOJO("currentMoviesList", errorMovieList).
                            putPOJO("currentUser", errorUser);
                } else if (action.getPage().equals("movies") || action.getPage().equals("see details")) {
                    AppLogic copy = new AppLogic(appLogic);

                    output.addObject().putPOJO("error", null).
                            putPOJO("currentMoviesList", copy.getCurrentMovies()).
                            putPOJO("currentUser", copy.getCurrentUser());
                }
            }

            if (action.getType().equals("on page")) {
               if (appLogic.getCurrentPage().executeOnPage(action, appLogic, dataBase)) {
                   if (!action.getFeature().equals("buy tokens") &&
                           !action.getFeature().equals("buy premium account")) {
                       AppLogic copy = new AppLogic(appLogic);

                       output.addObject().putPOJO("error", null).
                               putPOJO("currentMoviesList", copy.getCurrentMovies()).
                               putPOJO("currentUser", copy.getCurrentUser());
                   }
               } else {
                   output.addObject().put("error", "Error").
                           putPOJO("currentMoviesList", errorMovieList).
                           putPOJO("currentUser", null);
               }
            }
        }

        ObjectWriter objectWriter = objectMapper.writerWithDefaultPrettyPrinter();
        objectWriter.writeValue(new File(args[1]), output);
    }
}


