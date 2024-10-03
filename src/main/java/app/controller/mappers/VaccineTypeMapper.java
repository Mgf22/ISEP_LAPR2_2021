package app.controller.mappers;

import app.controller.mappers.dto.EmployeeDTO;
import app.controller.mappers.dto.VaccineTypeDTO;
import app.domain.model.Employee;
import app.domain.model.VaccineType;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

/**
 * @author Tomás Pereira <1210830@isep.ipp.pt>
 */
public class VaccineTypeMapper {

    public ArrayList<VaccineTypeDTO> toDTOVacTypes(ArrayList<VaccineType> listVaccineType){
        ArrayList<VaccineTypeDTO> list = new ArrayList<>();

        for(VaccineType vacType : listVaccineType) {
            list.add(new VaccineTypeDTO(vacType.getName(), vacType.getId()));
        }

        return list;
    }
}
