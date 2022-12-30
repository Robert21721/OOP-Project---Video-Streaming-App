import input.files.ActionsInput;
import input.files.Input;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectWriter;
import com.fasterxml.jackson.databind.node.ArrayNode;
import solution.*;
import solution.premiumUserNotification.PremiumUserRecommendation;
import solution.observer.DataBase;

import java.io.File;
import java.io.IOException;

public class Main {
    /**
     * read input file, create database and app, execute the given actions
     * and write in the output file
     * @param args - input and output files
     * @throws IOException
     */
    public static void main(final String[] args) throws IOException {
        ObjectMapper objectMapper = new ObjectMapper();
        Input inputData = objectMapper.readValue(new File(args[0]), Input.class);

        ArrayNode output = objectMapper.createArrayNode();

        DataBase dataBase = new DataBase(inputData);
        AppLogic appLogic = new AppLogic();

        for (ActionsInput action : inputData.getActions()) {
            switch (action.getType()) {
                case "change page":
                    appLogic.getCurrentPage().executeChangePage(action, appLogic, dataBase, output);
                    break;

                case "on page":
                case "subscribe":
                    appLogic.getCurrentPage().executeOnPage(action, appLogic, dataBase, output);
                    break;

                case "back":
                    appLogic.getEditor().undo(action, appLogic, dataBase, output);
                    break;

                case "database":
                    Notification notification = null;

                    if (action.getFeature().equals("add")) {
                        notification = new Notification(action.getAddedMovie().getName(), "ADD");
                        dataBase.setNotification(notification, new Movie(action.getAddedMovie()), output);
                    } else {
                        notification = new Notification(action.getDeletedMovie(), "DELETE");
                        dataBase.setNotification(notification, null, output);
                    }
                    break;

                default:
            }
        }

        if (appLogic.getCurrentUser() != null) {
            if (appLogic.getCurrentUser().getCredentials().getAccountType().equals("premium")) {
                PremiumUserRecommendation recommendation = new PremiumUserRecommendation(dataBase);
                recommendation.giveRecommendation(appLogic, output);
            }
        }


        ObjectWriter objectWriter = objectMapper.writerWithDefaultPrettyPrinter();
        objectWriter.writeValue(new File(args[1]), output);
    }
}


