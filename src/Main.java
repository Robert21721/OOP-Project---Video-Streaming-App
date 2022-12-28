import input.files.ActionsInput;
import input.files.Input;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectWriter;
import com.fasterxml.jackson.databind.node.ArrayNode;
import solution.*;
import solution.Commands.Editor;
import solution.PremiumUserNotification.PremiumUserRecommendation;
import solution.data.DataBase;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

public class Main {
    public static void main(final String[] args) throws IOException {
        ObjectMapper objectMapper = new ObjectMapper();
        Input inputData = objectMapper.readValue(new File(args[0]), Input.class);

        ArrayNode output = objectMapper.createArrayNode();

        DataBase dataBase = new DataBase(inputData);
        AppLogic appLogic = new AppLogic();

        for (ActionsInput action : inputData.getActions()) {
            if (action.getType().equals("change page")) {
                appLogic.getCurrentPage().executeChangePage(action, appLogic, dataBase, output);
            } else if (action.getType().equals("on page") || action.getType().equals("subscribe")) {
                appLogic.getCurrentPage().executeOnPage(action, appLogic, dataBase, output);
            } else if (action.getType().equals("back")) {
                appLogic.getEditor().undo(action, appLogic, dataBase, output);
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


