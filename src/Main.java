import input.files.ActionsInput;
import input.files.Input;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectWriter;
import com.fasterxml.jackson.databind.node.ArrayNode;
import solution.AppLogic;
import solution.DataBase;

import java.io.File;
import java.io.IOException;

public class Main {
    public static void main(String[] args) throws IOException {
        ObjectMapper objectMapper = new ObjectMapper();
        Input inputData = objectMapper.readValue(new File(args[0]), Input.class);

        ArrayNode output = objectMapper.createArrayNode();

        DataBase dataBase = new DataBase(inputData);
        AppLogic appLogic = new AppLogic();

        // System.out.printf("ceva\n");

        for (ActionsInput action : inputData.getActions()) {
            // System.out.println(appLogic.getCurrentPage());
            // System.out.println(appLogic.getCurrentUser());

            if (action.getType().equals("change page")) {
                if (!appLogic.getCurrentPage().executeChangePage(action, appLogic, dataBase)) {
                    output.addObject().put("error", "Error").
                            putPOJO("currentMoviesList", appLogic.getCurrentMovies()).
                            putPOJO("currentUser", null);
                } else if (action.getPage().equals("movies")) {
                    output.addObject().putPOJO("error", null).
                            putPOJO("currentMoviesList", appLogic.getCurrentMovies()).
                            putPOJO("currentUser", appLogic.getCurrentUser());
                }


            }

            if (action.getType().equals("on page")) {
               if (appLogic.getCurrentPage().executeOnPage(action, appLogic, dataBase)) {
                   output.addObject().putPOJO("error", null).
                           putPOJO("currentMoviesList", appLogic.getCurrentMovies()).
                           putPOJO("currentUser", appLogic.getCurrentUser());
               } else {
                   output.addObject().put("error", "Error").
                           putPOJO("currentMoviesList", appLogic.getCurrentMovies()).
                           putPOJO("currentUser", null);
               }
            }
        }

        ObjectWriter objectWriter = objectMapper.writerWithDefaultPrettyPrinter();
        objectWriter.writeValue(new File(args[1]), output);
    }
}
