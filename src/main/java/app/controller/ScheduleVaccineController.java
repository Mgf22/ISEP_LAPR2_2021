package app.controller;

import app.controller.mappers.CenterMapper;
import app.controller.mappers.EmployeeMapper;
import app.controller.mappers.VaccineTypeMapper;
import app.controller.mappers.dto.CenterDTO;
import app.controller.mappers.dto.VaccineTypeDTO;
import app.controller.stores.*;
import app.domain.model.*;
import pt.isep.lei.esoft.auth.AuthFacade;

import java.sql.Array;
import java.util.ArrayList;
import java.util.List;


/**
 *
 * @author Diogo Ribeiro <1190508@isep.ipp.pt>
 */
public class ScheduleVaccineController {

    private App app;
    private Company company;
    private AuthFacade authFacade;

    private VaccineTypeStore vacTypesStore;
    private VaccineTypeMapper vacTypeMapper;

    private CenterStore vacCenterStore;
    private CenterMapper vacCenterMapper;

    private VaccinationScheduleStore vacScheduleStore;

    private VaccinationSchedule vacSchedule;

    private SNSUserStore snsUserStore;
    private VaccineAdministrationStore vaStore;

    public ScheduleVaccineController()
    {
        this.app = App.getInstance();
        this.company = this.app.getCompany();
        this.authFacade = this.company.getAuthFacade();

        this.vacTypesStore = this.company.getVaccineTypeStore();
        this.vacTypeMapper = new VaccineTypeMapper();

        this.vacCenterStore = this.company.getCenterStore();
        this.vacCenterMapper = new CenterMapper();

        this.vacScheduleStore = this.company.getVaccinationScheduleStore();

        this.snsUserStore = this.company.getSNSUserStore();
        this.vaStore = this.company.getVaccineAdministrationStore();
    }

    public List<VaccineTypeDTO> getVaccineTypes(){
        List<VaccineTypeDTO> list;

        list = vacTypeMapper.toDTOVacTypes(vacTypesStore.getVaccineTypes());

        return list;
    }


    public List<CenterDTO> getVaccinationCenters(){
        List<CenterDTO> list = new ArrayList<>();

        list = vacCenterMapper.toDTO(vacCenterStore.getCenterStore());

        return list;
    }


    public void createVaccinationSchedule(String snsUserNumber, String date, VaccineTypeDTO vactypeDTO, CenterDTO centerDTO){

        vacSchedule = vacScheduleStore.createVaccinationSchedule(snsUserNumber, date, vactypeDTO, centerDTO);

    }

    public boolean saveVaccinationSchedule(){

        boolean valid = vacScheduleStore.validateVaccinationSchedule(vacSchedule);

        if(valid)
            vacScheduleStore.addVaccinationSchedule(vacSchedule);

        return valid;
    }

    public boolean checkUserFullyVaccinated(String snsNum, int typeID) {
        SNSUser snsUser = snsUserStore.getSNSUserByNum(snsNum);

        for (VaccineAdministration va : vaStore.getVaccineAdministrationStore()) {
            if (va.getSnsUser().getSnsUserNumber().equals(snsUser.getSnsUserNumber()) && va.getVaccine().getTypeID() == typeID) {
                if (va.getnOfDose()+1 > va.getVaccine().getNumberOfDoses()) {
                    return true;
                }
            }
        }
        return false;
    }

}
