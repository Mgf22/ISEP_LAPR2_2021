package app.domain.model;

import java.io.Serializable;
import java.util.ArrayList;

/**
 *
 * @author Fábio Archer <1210806@isep.ipp.pt>
 */

public class HealthcareCenter extends Center implements Serializable {

    private static final long serialVersionUID = -5629443473505228400L;
    private String ars;
    private String ages;

    private ArrayList<VaccineType> vaccineTypes;

    public HealthcareCenter(String name, String address, String phoneNumber, String emailAddress, String faxNumber, String websiteAddress, String openingHours, String closingHours, double slotDuration, int maxNumberOfVaccinesPerSlot, String ars, String ages) {
        super(name, address, phoneNumber, emailAddress, faxNumber, websiteAddress, openingHours, closingHours, slotDuration, maxNumberOfVaccinesPerSlot);
        this.ars = ars;
        this.ages = ages;
    }

    public static HealthcareCenter create(String name, String address, String phoneNumber, String emailAddress, String faxNumber, String websiteAddress, String openingHours, String closingHours, double slotDuration, int maxNumberOfVaccinesPerSlot, String ars, String ages) {
        return(new HealthcareCenter(name, address, phoneNumber, emailAddress, faxNumber, websiteAddress, openingHours, closingHours, slotDuration, maxNumberOfVaccinesPerSlot, ars, ages));
    }

    public ArrayList<VaccineType> getVaccineTypes() {
        return vaccineTypes;
    }

    public void addVaccineType(VaccineType vaccineType) {
        this.vaccineTypes.add(vaccineType);
    }

    public String getArs() {
        return ars;
    }

    public void setArs(String ars) {
        this.ars = ars;
    }

    public String getAges() {
        return ages;
    }

    public void setAges(String ages) {
        this.ages = ages;
    }

    public boolean validateStrings() {
        if(this.getName().isBlank() || this.getAddress().isBlank() || this.getPhoneNumber().isBlank() || this.getEmailAddress().isBlank() || this.getFaxNumber().isBlank() || this.getWebsiteAddress().isBlank() || this.getOpeningHours().isBlank() || this.getClosingHours().isBlank() || ars.isBlank() || ages.isBlank()) {
            return false;
        }
        return true;
    }

    public String toString() {
        return "HealthcareCenter{" +
                "name=" + super.getName() +
                ", address='" + super.getAddress() + '\'' +
                ", phone number=" + super.getPhoneNumber() +
                ", email address='" + super.getEmailAddress() + '\'' +
                ", fax number='" + super.getFaxNumber() + '\'' +
                ", website address='" + super.getWebsiteAddress() + '\'' +
                ", opening hours='" + super.getOpeningHours() + '\'' +
                ", closing hours='" + super.getClosingHours() + '\'' +
                ", slot duration='" + super.getSlotDuration() + '\'' +
                ", max number of vaccines per slot='" + super.getMaxNumberOfVaccinesPerSlot() + '\'' +
                ", ars='" + this.getArs() + '\'' +
                ", ages='" + this.getAges() + '\'' +
                "}";
    }
}
