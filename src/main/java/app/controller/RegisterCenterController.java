package app.controller;

import app.controller.stores.CenterStore;
import app.domain.model.Center;
import app.domain.model.Company;

/**
 *
 * @author Fábio Archer <1210806@isep.ipp.pt>
 */

public class RegisterCenterController {

    private App app;
    private Company company;

    private CenterStore cStore;

    public RegisterCenterController() {
        this.app = App.getInstance();
        this.company = this.app.getCompany();

        this.cStore = this.company.getCenterStore();
    }

    public Center createCenter(String type, String name, String address, String phoneNumber, String emailAddress, String faxNumber, String websiteAddress, String openingHours, String closingHours, double slotDuration, int maxNumberOfVaccinesPerSlot, String ars, String ages) {
        return cStore.createCenter(type, name, address, phoneNumber, emailAddress, faxNumber, websiteAddress, openingHours, closingHours, slotDuration, maxNumberOfVaccinesPerSlot, ars, ages);
    }

    public boolean saveCenter(Center cent) {
        return cStore.saveCenter(cent);
    }

    public boolean isDuplicate(Center cent) {
        return cStore.isDuplicate(cent);
    }

}
