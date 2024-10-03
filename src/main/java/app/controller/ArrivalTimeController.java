package app.controller;

import app.controller.stores.CenterStore;
import app.controller.stores.SNSUserStore;
import app.controller.stores.VaccinationScheduleStore;
import app.domain.model.Center;
import app.domain.model.Company;
import app.domain.model.SNSUser;
import app.tools.Serialize;

import java.time.LocalDate;
import java.time.LocalTime;

/**
 * @author Tomás Pereira <1210830@isep.ipp.pt>
 */
public class ArrivalTimeController {
    private App app;
    private Company company;
    private SNSUserStore snsUserStore;
    private SNSUser snsUser;
    private CenterStore centerStore;
    private Center center;
    private VaccinationScheduleStore vacScheduleStore;
    private static final String CENTER_SAVE_FILE = "center";

    public ArrivalTimeController()
    {
        this.app = App.getInstance();
        this.company = this.app.getCompany();
        this.snsUserStore = company.getSNSUserStore();
        this.centerStore = company.getCenterStore();
        this.vacScheduleStore = company.getVaccinationScheduleStore();
    }

    public boolean getSNSUserByNum(String snsNum) {
        SNSUser user = snsUserStore.getSNSUserByNum(snsNum);
        if (user != null) {
            this.snsUser = user;
            return true;
        } else {
            return false;
        }

    }

    public void saveArrival(LocalDate now, LocalTime arrivalTime) {
        snsUser.setArrival(now, arrivalTime, center);
    }

    public void getCenterByIndex(int centerIndex) {
        this.center = centerStore.getCenterByIndex(centerIndex);
    }

    public boolean containsUser() {
        return this.center.getWaitingList().contains(snsUser);
    }

    public void addToWaitingList() {
        this.center.getWaitingList().add(snsUser);
        Serialize.serialize(centerStore.getCenterStore(), CENTER_SAVE_FILE);

    }

    public boolean checkSchedule(LocalDate today) {
        return this.vacScheduleStore.checkSchedule(snsUser.getSnsUserNumber(), today);
    }

    public boolean validateDate(LocalDate arrivalDate) {
        return this.snsUser.validateDate(arrivalDate);
    }
}
