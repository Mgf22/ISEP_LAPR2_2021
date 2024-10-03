package app.controller;

import app.controller.mappers.VaccineTypeMapper;
import app.controller.mappers.dto.VaccineTypeDTO;
import app.controller.stores.VaccineStore;
import app.controller.stores.VaccineTypeStore;
import app.domain.model.Company;
import app.domain.model.Vaccine;
import app.domain.model.VaccineType;

import java.util.ArrayList;

/**
 * @author Tomás Pereira <1210830@isep.ipp.pt>
 */
public class VaccineController {
    private App app;
    private Company company;
    private VaccineTypeMapper vacTypeMapper;

    public VaccineController()
    {
        this.app = App.getInstance();
        this.company = this.app.getCompany();
        this.vacTypeMapper = new VaccineTypeMapper();
    }

    public ArrayList<VaccineTypeDTO> getVaccineTypes() {
        ArrayList<VaccineType> vaccineTypes = company.getVaccineTypeStore().getVaccineTypes();

        return vacTypeMapper.toDTOVacTypes(vaccineTypes);
    }

    public Vaccine createVaccine(String typeName, int typeID, String name, int lot, int id, double dosage, int timeInterval, int ageGroupMax, int ageGroupMin, int numberOfDoses) {
        return this.company.createVaccine(typeName, typeID, name, lot, id, dosage, timeInterval, ageGroupMax, ageGroupMin, numberOfDoses);
    }

    public boolean saveVaccine(Vaccine vaccine) {
        if (company.getVaccineStore().validateVaccine(vaccine)) {
            company.getVaccineStore().addVaccine(vaccine);
            return true;
        }
        return false;
    }
}
