package app.ui.console;

import app.controller.mappers.dto.EmployeeDTO;
import app.ui.console.utils.Utils;
import pt.isep.lei.esoft.auth.domain.model.UserRole;
import app.controller.RegisterEmployeeController;

import java.util.List;

/**
 * @author Mateus Fernandes <1210821@isep.ipp.pt>
 */

public class RegisterEmployeeUI implements Runnable {


    private final RegisterEmployeeController controller;

    public RegisterEmployeeUI() {
        controller = new RegisterEmployeeController();
    }

    public void run() {
        try {
            String name;
            String address;
            String phoneNumber;
            String id;
            String citizenCardNumber;
            int option;
            UserRole role;
            String password;

            do {

                List<UserRole> userRoles = controller.getRoles();

                option = Utils.showAndSelectIndex(userRoles, "Role of the new Employee: ");
                role = userRoles.get(option);


                name = Utils.readLineFromConsole("Name: ");
                address = Utils.readLineFromConsole("Address: ");
                phoneNumber = Utils.readLineFromConsole("Phone Number: ");
                id = Utils.readLineFromConsole("ID / Email: ");
                citizenCardNumber = Utils.readLineFromConsole("Citizen Card Number: ");

                password = controller.generatePassword();
                displayInformation(name, address, id, phoneNumber, citizenCardNumber, role.getId(), password);
            } while (!Utils.confirm("Confirm the information (s/n)"));
            EmployeeDTO empDTO = new EmployeeDTO(name, address, id, phoneNumber, citizenCardNumber, role.getId(),password);

            boolean success = controller.saveEmployee(empDTO);

            if (success)
                Utils.printToConsole("Employee registered successfully.");
        } catch (Exception e) {
            System.out.println("\n[ERROR]: " + e + "\n");
        }
    }


    private void displayInformation(String name, String address, String id, String phoneNumber, String citizenCardNumber, String role, String password) {
        String info = String.format("Name: %s%nAddress: %s%nID: %s%nPhone Number: %s%nCitizen Card Number: %s%nRole: %s%nPassword: %s",
                name, address, id, phoneNumber, citizenCardNumber, role, password);

        Utils.printToConsole(info);
    }
}