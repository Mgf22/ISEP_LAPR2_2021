package app.ui.console;
import app.controller.VaccineController;
import app.controller.mappers.dto.VaccineTypeDTO;
import app.domain.model.Vaccine;
import app.ui.console.utils.Utils;

import java.util.ArrayList;

/**
 * @author Tomás Pereira <1210830@isep.ipp.pt>
 */
public class VaccineUI implements Runnable{
    private VaccineController controller;
    private ArrayList<VaccineTypeDTO> vacTypes;
    private ArrayList<String> vacTypeNames = new ArrayList<>();

    public VaccineUI() {
        this.controller = new VaccineController();
    }

    public void run() {

        String name;
        int lot;
        int id;
        double dosage;
        int timeInterval;
        int ageGroupMax;
        int ageGroupMin;
        int numberOfDoses;
        boolean userConfirm = false;

         do {
            vacTypes = controller.getVaccineTypes();
            int vacTypeIndex = showAndSelectVaccineTypes();

            if (vacTypeIndex != -1) {
                name = Utils.readLineFromConsole("\nName/Brand: ");
                lot = Utils.readIntegerFromConsole("Lot: ");
                id = Utils.readIntegerFromConsole("ID: ");
                dosage = Utils.readDoubleFromConsole("Dosage (ml): ");
                timeInterval = Utils.readIntegerFromConsole("Time Interval (days): ");
                ageGroupMin = Utils.readIntegerFromConsole("Minimum age (0-150): ");
                ageGroupMax = Utils.readIntegerFromConsole("Maximum age (0-150):");
                numberOfDoses = Utils.readIntegerFromConsole("Number of doses: ");

                VaccineTypeDTO chosenVacType = vacTypes.get(vacTypeIndex);
                Vaccine tempVac = controller.createVaccine(chosenVacType.getName(), chosenVacType.getId(), name, lot, id, dosage, timeInterval, ageGroupMax, ageGroupMin, numberOfDoses);
                Utils.printToConsole("\nConfirm the data:\n\n" + tempVac.toString());
                userConfirm = Utils.confirm("Is the data correct? (s/n)");
                if (userConfirm) {
                    boolean verifyVaccine = controller.saveVaccine(tempVac);
                    if (verifyVaccine) {
                        Utils.printToConsole("Vaccine added successfully.");
                    } else {
                        Utils.printToConsole("Error. Vaccine not added.\nPlease check if there are any negative numbers or if the age interval respects the rules.");
                        boolean userRetry = Utils.confirm("Do you wish to retry? (s/n)");
                        if (userRetry) {
                            userConfirm = false;
                        }
                    }
                }
            } else {
                userConfirm = true;
            }
        } while (!userConfirm);
    }

    /**
     * Displays and allows the user to choose a vaccine type.
     * @return the index of the vaccine type chosen by the user.
     */
    private int showAndSelectVaccineTypes() {
        Utils.printToConsole("\n");

        if (vacTypeNames.size() == 0) {
            for (VaccineTypeDTO vtDTO : vacTypes) {
                vacTypeNames.add(vtDTO.getName());
            }
        }
        return Utils.showAndSelectIndex(vacTypeNames, "Choose a vaccine type from the list:");
    }
}
