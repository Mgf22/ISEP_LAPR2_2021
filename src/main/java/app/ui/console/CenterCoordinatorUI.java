package app.ui.console;

import app.controller.App;
import app.controller.CenterCoordinatorController;
import app.ui.console.utils.Utils;
import app.ui.gui.VaccinationStatisticsUI;

import java.util.ArrayList;
import java.util.List;

public class CenterCoordinatorUI implements Runnable {

    private CenterCoordinatorController controller;

    public CenterCoordinatorUI() {
        this.controller = new CenterCoordinatorController();
    }

    public void run() {

        if(controller.getCenterStore().getAllCenters().isEmpty()) {
            Utils.printToConsole("There are currently no centers available for selection.");
        } else {
            Utils.printToConsole("Center list:\n" + controller.getCenterStore().getAllCenters());
            int centerIndex = Utils.readIntegerFromConsole("Please select the center you are working at:");
            App.getInstance().setCurrentCenterIndex(centerIndex);

            List<MenuItem> options = new ArrayList<MenuItem>();
            options.add(new MenuItem("Check/Export vaccination statistics", new VaccinationStatisticsUI()));

            int option = 0;
            do
            {
                option = Utils.showAndSelectIndex(options, "\n\nCenter Coordinator Menu:");

                if ( (option >= 0) && (option < options.size()))
                {
                    options.get(option).run();
                }
            }
            while (option != -1 );
        }
    }
}
