package app.controller;

import app.controller.mappers.dto.EmployeeDTO;
import app.domain.model.Company;

import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

import app.domain.model.Employee;
import app.tools.PasswordGenerator;
import pt.isep.lei.esoft.auth.domain.model.UserRole;

/**
 * @author Mateus Fernandes <1210821@isep.ipp.pt>
 */

public class RegisterEmployeeController {

    private final Company company;

    public RegisterEmployeeController() {
        this(App.getInstance().getCompany());
    }

    public RegisterEmployeeController(Company company) {
        this.company = company;
    }

    public List<UserRole> getRoles() {
        return company.getEmployeeRoles();
    }

    public boolean saveEmployee(EmployeeDTO empDTO) {
        Employee emp;
        emp = this.company.createEmployee(empDTO);
        this.company.saveEmployee(emp);
        return true;
    }

    public String generatePassword() {
        return PasswordGenerator.generatePassword(12);
    }
}
