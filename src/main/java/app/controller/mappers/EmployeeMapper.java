package app.controller.mappers;


import app.controller.mappers.dto.EmployeeDTO;
import app.domain.model.Employee;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;

/**
 *
 * @author Diogo Ribeiro <1190508@isep.ipp.pt>
 */
public class EmployeeMapper {

    public EmployeeDTO toDTO(Employee employee){
        return new EmployeeDTO(employee.getId(), employee.getName(), employee.getRole(), employee.getAddress(), employee.getPhoneNumber());
    }

    public ArrayList<EmployeeDTO> toDTO(Set<Employee> listEmployee){

        ArrayList<EmployeeDTO> list = new ArrayList<>();

        for(Employee employee : listEmployee) {
            list.add( new EmployeeDTO(employee.getId(), employee.getName(), employee.getRole(), employee.getAddress(), employee.getPhoneNumber()) );
        }

        return list;
    }

}
