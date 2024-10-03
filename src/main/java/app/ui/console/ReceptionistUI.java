package app.ui.console;

import app.controller.App;
import app.controller.ReceptionistController;
import app.domain.model.Center;
import app.ui.console.utils.Utils;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Tomás Pereira <1210830@isep.ipp.pt>
 */
public class ReceptionistUI implements Runnable{
    private ReceptionistController controller;

    public ReceptionistUI() {
        this.controller = new ReceptionistController();
    }

    public void run() {
        if (!controller.getCenterStore().getAllCenters().isEmpty()) {
            Utils.printToConsole("Center list:\n" + controller.getCenterStore().getAllCenters());
            int centerIndex = Utils.readIntegerFromConsole("Please choose a center:");
            App.getInstance().setCurrentCenterIndex(centerIndex);

            List<MenuItem> options = new ArrayList<>();
            options.add(new MenuItem("Set arrival time for an SNSUser ", new ArrivalTimeUI(centerIndex)));

            int option;
            do {
                option = Utils.showAndSelectIndex(options, "\n\nReceptionist Menu:");

                if ((option >= 0) && (option < options.size())) {
                    options.get(option).run();
                }
            }
            while (option != -1);
        } else {
            Utils.printToConsole("There are currently no centers available for selection.");
        }
    }
}
