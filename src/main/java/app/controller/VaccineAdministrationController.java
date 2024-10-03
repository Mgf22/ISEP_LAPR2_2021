package app.controller;

import app.controller.lists.SNSUserList;
import app.controller.lists.VaccineList;
import app.controller.mappers.VaccineMapper;
import app.controller.mappers.dto.VaccineListDTO;
import app.controller.stores.*;
import app.domain.model.*;
import app.tools.SendSMS;
import app.tools.Serialize;
import java.time.LocalDate;
import java.util.Timer;

/**
 * @author Tomás Pereira <1210830@isep.ipp.pt>
 */
public class VaccineAdministrationController {
    private App app;
    private Company company;
    private SNSUser snsUser;
    private CenterStore centerStore;
    private Center center;
    public SNSUserList waitingList;
    private VaccinationScheduleStore vacScheduleStore;
    private VaccineAdministrationStore vaStore;
    private VaccineType vaccineType;
    private VaccineStore vStore;
    private int centerIndex;
    private VaccineAdministration va;

    private static final String CENTER_SAVE_FILE = "center";

    public VaccineAdministrationController()
    {
        this.app = App.getInstance();
        this.company = this.app.getCompany();
        this.centerIndex = app.getCurrentCenterIndex();
    }

    public boolean checkUserInWaitingList(String snsNumber) {
        this.centerStore = company.getCenterStore();
        this.center = centerStore.getCenterByIndex(centerIndex);
        this.waitingList = center.getWaitingList();
        for (int i = 0; i < waitingList.size(); i++) {
            this.snsUser = waitingList.get(i);
            if (snsUser.getSnsUserNumber().equals(snsNumber)) {
                Serialize.serialize(centerStore.getCenterStore(), CENTER_SAVE_FILE);
                return true;
            }
        }
        return false;
    }

    public VaccineListDTO showVaccineList() {
        this.vStore = company.getVaccineStore();
        String typeName = vaccineType.getName();
        VaccineList vaccineList = vStore.getVaccineListByTypeName(typeName);
        return VaccineMapper.toDTO(vaccineList);
    }

    public void addAdministration(int vaccineID) {
        this.vStore = company.getVaccineStore();
        Vaccine vaccine = this.vStore.getVaccineByID(vaccineID);
        if (va == null) {
            vaStore.add(snsUser, center, vaccine, 1, LocalDate.now());
        } else {
            vaStore.add(snsUser, center, vaccine, va.getnOfDose()+1, LocalDate.now());
        }
        waitingList.remove(snsUser);
        center.getRecoveryRoom().add(snsUser);
        sendSMS();
    }

    public void sendSMS() {
        Timer timer = new Timer();
        timer.schedule(new SendSMS(snsUser), Long.parseLong(company.getMessageTime()));
        center.getRecoveryRoom().remove(snsUser);
    }

    public VaccineAdministration checkVaccineRecord(String snsNumber) {
        this.vacScheduleStore = company.getVaccinationScheduleStore();
        this.vaStore = company.getVaccineAdministrationStore();
        this.vaccineType = vacScheduleStore.getVaccinationScheduleBySNSNumber(snsNumber).getTypeOfVaccine();
        for (VaccineAdministration va : vaStore.getVaccineAdministrationStore()) {
            if (va.getSnsUser().getSnsUserNumber().equals(snsUser.getSnsUserNumber()) && va.getVaccine().getTypeID() == vaccineType.getId()) {
                this.va = va;
                return va;
            }
        }
        this.va = null;
        return null;
    }

    public VaccineListDTO createDTO(Vaccine vaccine) {
        VaccineList vList = new VaccineList();
        vList.add(vaccine);
        return VaccineMapper.toDTO(vList);
    }

    public boolean isVaNull() {
        return va == null;
    }

    public int getNOfDose() {
        return va.getnOfDose()+1;
    }
}
