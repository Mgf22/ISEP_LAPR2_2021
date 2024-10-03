package app.controller;

import app.controller.mappers.EmployeeMapper;
import app.controller.mappers.dto.EmployeeDTO;
import app.controller.stores.EmployeeStore;
import app.domain.model.Company;
import app.domain.model.Employee;
import pt.isep.lei.esoft.auth.AuthFacade;
import pt.isep.lei.esoft.auth.mappers.dto.UserRoleDTO;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

/**
 *
 * @author Diogo Ribeiro <1190508@isep.ipp.pt>
 */
public class EmployeeListController {

    private App app;
    private Company company;
    private AuthFacade authFacade;

    private EmployeeStore eStore;
    private EmployeeMapper eMapper;

    public EmployeeListController()
    {
        this.app = App.getInstance();
        this.company = this.app.getCompany();
        this.authFacade = this.company.getAuthFacade();

        this.eStore = this.company.getEmployeeStore();
        this.eMapper = new EmployeeMapper();
    }


    /**
     * Gets list of employee roles
     *
     * @return List of roles DTO
     */
    public List<UserRoleDTO> getEmployeeRoles() throws Exception {

        //Gets list of roles
        List<UserRoleDTO> list = this.authFacade.getUserRoles();

        //If list size is bigger than 0, it returns, else it throws an exception
        if(list.size() > 0){
            return list;
        }else{
            throw new Exception("No roles found!");
        }

    }


    /**
     * Gets list of employees based on the role
     *
     * @return List of employees DTO
     */
    public List<EmployeeDTO> getEmployees(UserRoleDTO roleDTO) throws Exception {

        Set<Employee> eEmployees = eStore.getEmployeesByRole(roleDTO.getId());

        return eMapper.toDTO(eEmployees);

    }

}
