package app.ui.console;


import app.controller.EmployeeListController;
import app.controller.ScheduleVaccineController;
import app.controller.mappers.dto.CenterDTO;
import app.controller.mappers.dto.EmployeeDTO;
import app.controller.mappers.dto.VaccineTypeDTO;
import app.ui.console.utils.Utils;
import jdk.jshell.execution.Util;
import pt.isep.lei.esoft.auth.mappers.dto.UserRoleDTO;

import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 *
 * @author Diogo Ribeiro <1190508@isep.ipp.pt>
 */
public class ScheduleVaccineUI implements Runnable{

    private ScheduleVaccineController controller;

    public ScheduleVaccineUI()
    {
        this.controller = new ScheduleVaccineController();
    }

    @Override
    public void run() {

        try {

            //VACCINE TYPES===========================================
            //Gets list of vaccine types
            List<VaccineTypeDTO> vacTypesDTOlist = this.controller.getVaccineTypes();

            //Through the dto list, it gets their name to display
            List<String> vaccinesName = new ArrayList<>();
            for (VaccineTypeDTO dto : vacTypesDTOlist) {
                vaccinesName.add(dto.getName());
            }

            //Presents the vaccine types and gets the type selected
            int index = Utils.showAndSelectIndex(vaccinesName, "Please choose a vaccine type to present: ");
            VaccineTypeDTO typeDTO = vacTypesDTOlist.get(index);


            //VACCINE CENTERS===========================================
            //Gets list of vaccine centers
            List<CenterDTO> vacCenterDTOlist = this.controller.getVaccinationCenters();

            //Through the dto list, it gets their name and address to display
            List<String> centerNameAddress = new ArrayList<>();
            for (CenterDTO dto : vacCenterDTOlist) {
                centerNameAddress.add("Name: "+dto.getName()+"  Adress: "+dto.getAddress());
            }

            //Presents the vaccine centers and gets the center selected
            index = Utils.showAndSelectIndex(centerNameAddress, "Please choose a vaccine center to present: ");
            CenterDTO centerDTO = vacCenterDTOlist.get(index);


            //Requests Data
            SimpleDateFormat format = new SimpleDateFormat("dd-MM-yyyy");
            Date date = Utils.readDateFromConsole("Input desired date for vaccination (dd-mm-yyyy): ");
            String snsUserNumber = String.valueOf(Utils.readIntegerFromConsole("Input your respective SNS User Number: "));

            //Checks if the SNS User has already been fully vaccinated for this vaccine type
            if (!controller.checkUserFullyVaccinated(snsUserNumber, typeDTO.getId())) {
                //Create
                controller.createVaccinationSchedule(snsUserNumber, format.format(date), typeDTO, centerDTO);

                //Shows the data and requests a confirmation
                boolean confirm = Utils.confirm("Is this the correct data (s/n): \nSNS User Number: " + snsUserNumber + "\nDate: " + format.format(date) + "\nVaccine Type: " + typeDTO.getName() + "\nCenter: " + centerDTO.getName());

                //Save vaccine schedule
                if (confirm) {
                    if (controller.saveVaccinationSchedule())
                        Utils.printToConsole("\nVaccine Schedule Created!\n");
                    else
                        Utils.printToConsole("\nVaccine Schedule already exists!\n");
                } else
                    Utils.printToConsole("\nVaccine Schedule Canceled!\n");
            } else {
                Utils.printToConsole("\nUser is already fully vaccinated for this vaccine type!\n");
            }

        } catch (Exception e) {
            System.out.println("\n[ERROR]: " + e + "\n");
        }

    }


}
