package app.ui.console;

import app.controller.EmployeeListController;
import app.controller.mappers.dto.EmployeeDTO;
import app.ui.console.utils.Utils;
import pt.isep.lei.esoft.auth.mappers.dto.UserRoleDTO;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;


/**
 *
 * @author Diogo Ribeiro <1190508@isep.ipp.pt>
 */
public class EmployeeListUI implements Runnable{

    private EmployeeListController controller;

    public EmployeeListUI()
    {
        this.controller = new EmployeeListController();
    }

    public void run()
    {

        try {
            //Gets list of user roles
            List<UserRoleDTO> empRoleDTO = this.controller.getEmployeeRoles();

            //Through the dto list, it gets their id to display
            List<String> rolesDescription = new ArrayList<>();
            for (UserRoleDTO dto : empRoleDTO) {
                rolesDescription.add(dto.getId());
            }

            //Presents the role dto ID's and gets the index selected
            int index = Utils.showAndSelectIndex(rolesDescription, "Please choose a role to present: ");

            //Gets an Employee list corresponding to the role selected
            List<EmployeeDTO> empDTO = this.controller.getEmployees(empRoleDTO.get(index));

            //Displays list
            Utils.showList(empDTO, "List of Employees: ");

        } catch (Exception e) {
            System.out.println("\n[ERROR]: " + e + "\n");
        }

    }

}
