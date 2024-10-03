package app.domain.model;

import java.io.Serializable;

/**
 *
 * @author Fábio Archer <1210806@isep.ipp.pt>
 */

public class CommunityMassVaccinationCenter extends Center implements Serializable {

    private static final long serialVersionUID = 5135719245946571827L;
    private VaccineType vaccineType;

    public CommunityMassVaccinationCenter(String name, String address, String phoneNumber, String emailAddress, String faxNumber, String websiteAddress, String openingHours, String closingHours, double slotDuration, int maxNumberOfVaccinesPerSlot) {
        super(name, address, phoneNumber, emailAddress, faxNumber, websiteAddress, openingHours, closingHours, slotDuration, maxNumberOfVaccinesPerSlot);
    }

    public static CommunityMassVaccinationCenter create(String name, String address, String phoneNumber, String emailAddress, String faxNumber, String websiteAddress, String openingHours, String closingHours, double slotDuration, int maxNumberOfVaccinesPerSlot) {
        return(new CommunityMassVaccinationCenter(name, address, phoneNumber, emailAddress, faxNumber, websiteAddress, openingHours, closingHours, slotDuration, maxNumberOfVaccinesPerSlot));
    }

    public VaccineType getVaccineType() {
        return vaccineType;
    }

    public void setVaccineType(VaccineType vaccineType) {
        this.vaccineType = vaccineType;
    }

    public boolean validateStrings() {
        if(this.getName().isBlank() || this.getAddress().isBlank() || this.getPhoneNumber().isBlank() || this.getEmailAddress().isBlank() || this.getFaxNumber().isBlank() || this.getWebsiteAddress().isBlank() || this.getOpeningHours().isBlank() || this.getClosingHours().isBlank()) {
            return false;
        }
        return true;
    }

    public String toString() {
        return "CommunityMassVaccinationCenter{" +
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
                "}";
    }
}
