package app.ui.console;

import app.controller.App;
import app.controller.NurseController;
import app.ui.console.utils.Utils;
import app.ui.gui.VaccineAdministrationUI1;

import java.util.ArrayList;
import java.util.List;

public class NurseUI implements Runnable {

    private NurseController controller;

    public NurseUI() {
        this.controller = new NurseController();
    }

    public void run() {

        if(controller.getCenterStore().getAllCenters().isEmpty()) {
            Utils.printToConsole("There are currently no centers available for selection.");
        } else {
            Utils.printToConsole("Center list:\n" + controller.getCenterStore().getAllCenters());
            int centerIndex = Utils.readIntegerFromConsole("Please select the center you are working at:");
            App.getInstance().setCurrentCenterIndex(centerIndex);

            List<MenuItem> options = new ArrayList<MenuItem>();
            options.add(new MenuItem("Show users in the waiting room ", new ShowWaitingRoomListUI(centerIndex)));
            options.add(new MenuItem("Administrate a vaccine", new VaccineAdministrationUI1()));
            int option = 0;
            do
            {
                option = Utils.showAndSelectIndex(options, "\n\nNurse Menu:");

                if ( (option >= 0) && (option < options.size()))
                {
                    options.get(option).run();
                }
            }
            while (option != -1 );
        }
    }
}
