package app.controller.stores;


import app.domain.model.Center;
import app.domain.model.Employee;
import app.domain.model.SNSUser;
import app.tools.Serialize;
import app.domain.shared.Constants;
import pt.isep.lei.esoft.auth.AuthFacade;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;

/**
 *
 * @author Diogo Ribeiro <1190508@isep.ipp.pt>
 */
public class EmployeeStore {

    private static final String EMPLOYEE_SAVE_FILE = "employee";
    private static ArrayList<Employee> store = new ArrayList<>();

    public void addEmployee(Employee emp){
        boolean found = false;
        for (Employee employee: store){
            if (employee.getId().equals(emp.getId())){
                found = true;
            }
        }
        if (!found){
            store.add(emp);
            Serialize.serialize(store, EMPLOYEE_SAVE_FILE);
        }
    }

    public ArrayList<Employee> getAllEmployees() {
        return store;
    }

    public Set<Employee> getEmployeesByRole(String roleID) {

        Set<Employee> list = new HashSet<>();

        for(Employee e : store){
            if(e.getRole().equals(roleID)){
                list.add(e);
            }
        }

        return list;
    }


    public Employee getEmployeeById(String empID) {
        for(Employee e : store){
            if(e.getId().equals(empID)){
                return e;
            }
        }
        return null;
    }

    public void deserializeEmp (AuthFacade auth){
        store = Serialize.deserialize(EMPLOYEE_SAVE_FILE);
        for (Employee emp: store){
            auth.addUserWithRole(emp.getName(), emp.getId(), emp.getPassword(),emp.getRole());
        }
    }

}
