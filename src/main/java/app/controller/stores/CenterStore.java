package app.controller.stores;

import app.domain.model.Center;
import app.domain.model.CommunityMassVaccinationCenter;
import app.domain.model.HealthcareCenter;
import app.tools.Serialize;
import pt.isep.lei.esoft.auth.AuthFacade;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;

/**
 *
 * @author Fábio Archer <1210806@isep.ipp.pt>
 */

public class CenterStore {

    private static final String CENTER_SAVE_FILE = "center";
    private static ArrayList<Center> store = new ArrayList<>();

    public Center createCenter(String type, String name, String address, String phoneNumber, String emailAddress, String faxNumber, String websiteAddress, String openingHours, String closingHours, double slotDuration, int maxNumberOfVaccinesPerSlot, String ars, String ages) {
        if(type.equals("mass")) {
            return(CommunityMassVaccinationCenter.create(name, address, phoneNumber, emailAddress, faxNumber, websiteAddress, openingHours, closingHours, slotDuration, maxNumberOfVaccinesPerSlot));
        } else {
            return(HealthcareCenter.create(name, address, phoneNumber, emailAddress, faxNumber, websiteAddress, openingHours, closingHours, slotDuration, maxNumberOfVaccinesPerSlot, ars, ages));
        }
    }

    public boolean saveCenter(Center cent) {
        if(validateCenter(cent)) {
            this.add(cent);
            return true;
        }
        return false;
    }

    public boolean validateCenter(Center cent) {
        if(!cent.validateStrings() || !cent.validateHours()) {
            return false;
        }
        return true;
    }

    public boolean isDuplicate(Center cent) {
        for (Center center: store) {
            if(center.getName().equalsIgnoreCase(cent.getName())) {
                return true;
            }
        }
        return false;
    }

    public void add(Center cent) {
        boolean found = false;
        for (Center cen: store){
            if (cen.getName().equals(cent.getName())){
                found = true;
            }
        }
        if (!found){
            store.add(cent);
            Serialize.serialize(store, CENTER_SAVE_FILE);
        }
    }

    public String getAllCenters() {
        StringBuilder allCenters = new StringBuilder();
        for (Center center : store) {
            allCenters.append(store.indexOf(center)+1).append(". ").append(center.getName() + "\n");
        }

        return allCenters.toString();
    }

    public Center getCenterByIndex(int index) {
        if (store.size() > index || index > 0) {
            return store.get(index-1);
        } else {
            return null;
        }
    }


    public ArrayList<Center> getCenterStore(){
        return store;
    }

    public void deserializeCenter() {
        store = Serialize.deserialize(CENTER_SAVE_FILE);
    }

}
