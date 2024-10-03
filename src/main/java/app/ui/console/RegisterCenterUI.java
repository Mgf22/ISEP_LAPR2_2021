package app.ui.console;


import app.controller.RegisterCenterController;
import app.domain.model.Center;
import app.ui.console.utils.Utils;

import java.util.Arrays;

/**
 *
 * @author Fábio Archer <1210806@isep.ipp.pt>
 */
public class RegisterCenterUI implements Runnable {

    private RegisterCenterController controller;

    public RegisterCenterUI() {
        this.controller = new RegisterCenterController();
    }


    public void run() {

            int index = Utils.showAndSelectIndex(Arrays.asList("Community Mass Vaccination Center", "Healthcare Center"), "What kind of center do you want to register?");
            index++;
            if(index != 0) {
                String name = Utils.readLineFromConsole("Name: ");
                String address = Utils.readLineFromConsole("Address: ");
                String phoneNumber = Utils.readLineFromConsole("Phone Number: ");
                String emailAddress = Utils.readLineFromConsole("Email Address: ");
                String faxNumber = Utils.readLineFromConsole("Fax Number: ");
                String websiteAddress = Utils.readLineFromConsole("Website Address: ");
                String openingHours = Utils.readLineFromConsole("Opening Hours (HH:MM): ");
                String closingHours = Utils.readLineFromConsole("Closing Hours (HH:MM): ");
                double slotDuration = Utils.readDoubleFromConsole("Slot Duration (in minutes): ");
                int maxNumberOfVaccinesPerSlot = Utils.readIntegerFromConsole("Max Number Of Vaccines Per Slot: ");

                //Community Mass Vaccination Center
                Center cent;
                if(index == 1){
                    cent = controller.createCenter("mass", name, address, phoneNumber, emailAddress, faxNumber, websiteAddress, openingHours, closingHours, slotDuration, maxNumberOfVaccinesPerSlot, null, null);
                } else { //Healthcare Center
                    String ars = Utils.readLineFromConsole("Ars: ");
                    String ages = Utils.readLineFromConsole("Ages: ");
                    cent = controller.createCenter("healthcare", name, address, phoneNumber, emailAddress, faxNumber, websiteAddress, openingHours, closingHours, slotDuration, maxNumberOfVaccinesPerSlot, ars, ages);
                }

                //Confirms the data
                System.out.println(cent.toString());
                String res = "";
                while(!res.equalsIgnoreCase("y") && !res.equalsIgnoreCase("n")) {
                    res = Utils.readLineFromConsole("Do you want to save this new center? y/n");
                }
                if(res.equalsIgnoreCase("y")) {
                    if(controller.isDuplicate(cent)) {
                        System.out.println("There was an error: There is already a center with that name!");
                    } else {
                        boolean success = controller.saveCenter(cent);
                        if(!success) {
                            System.out.println("There was an error: Please make sure all fields are filled in correctly!");
                        } else {
                            System.out.println("The center was added successfully!");
                        }
                    }
                }

            }


    }
}
