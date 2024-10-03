package app.controller.stores;


import app.controller.mappers.dto.CenterDTO;
import app.controller.mappers.dto.VaccineTypeDTO;
import app.domain.model.*;
import app.domain.shared.Constants;
import app.tools.Serialize;
import pt.isep.lei.esoft.auth.AuthFacade;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;

/**
 *
 * @author Diogo Ribeiro <1190508@isep.ipp.pt>
 */
public class VaccinationScheduleStore {

    private static ArrayList<VaccinationSchedule> store = new ArrayList<VaccinationSchedule>();
    private static final String VACCINATION_SCHEDULE_SAVE_FILE = "vaccinationSchedule";

    public VaccinationSchedule createVaccinationSchedule(String snsUserNumber, String date, VaccineTypeDTO vaccineTypeDTO, CenterDTO centerDTO){
        return new VaccinationSchedule(snsUserNumber, date, new CommunityMassVaccinationCenter(centerDTO.getName(), centerDTO.getAddress(), centerDTO.getPhoneNumber(), centerDTO.getEmailAddress(), centerDTO.getFaxNumber(), centerDTO.getWebsiteAddress(),
                                    centerDTO.getOpeningHours(), centerDTO.getClosingHours(), centerDTO.getSlotDuration(), centerDTO.getMaxNumberOfVaccinesPerSlot()), new VaccineType(vaccineTypeDTO.getName(), vaccineTypeDTO.getId()));
    }

    public boolean validateVaccinationSchedule(VaccinationSchedule vacSchedule){

        for(VaccinationSchedule vac : store){
            if(vacSchedule.getSnsUserNumber().equals(vac.getSnsUserNumber()) && vacSchedule.getTypeOfVaccine().getName().equals(vac.getTypeOfVaccine().getName()))
                return false;
        }

        return vacSchedule != null;
    }

    public void addVaccinationSchedule(VaccinationSchedule vacSchedule){
        store.add(vacSchedule);
        Serialize.serialize(store, VACCINATION_SCHEDULE_SAVE_FILE);
    }

    public boolean checkSchedule(String snsUserNumber, LocalDate today) {
        DateTimeFormatter dateFormatter = DateTimeFormatter.ofPattern("dd-MM-yyyy");
        for (VaccinationSchedule vSched : store) {
            if (vSched.getSnsUserNumber().equals(snsUserNumber) && vSched.getDate().equals(dateFormatter.format(today))) {
                return true;
            }
        }
        return false;
    }

    public VaccinationSchedule getVaccinationScheduleBySNSNumber(String snsNumber) {
        for (VaccinationSchedule vSched : store) {
            if (vSched.getSnsUserNumber().equals(snsNumber)) {
                return vSched;
            }
        }
        return null;
    }

    // Only use for deserialization in Sprint D
    public void deserializeSchedule (){
        store = Serialize.deserialize(VACCINATION_SCHEDULE_SAVE_FILE);
    }

}
