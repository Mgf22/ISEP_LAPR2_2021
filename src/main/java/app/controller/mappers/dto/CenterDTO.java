package app.controller.mappers.dto;


import app.controller.lists.SNSUserList;
import app.domain.model.CenterCoordinator;

/**
 *
 * @author Diogo Ribeiro <1190508@isep.ipp.pt>
 */
public class CenterDTO {

    private String name;
    private String address;
    private String phoneNumber;
    private String emailAddress;
    private String faxNumber;
    private String websiteAddress;
    private String openingHours;
    private String closingHours;
    private double slotDuration;
    private int maxNumberOfVaccinesPerSlot;
    private CenterCoordinator coordinator;

    private SNSUserList waitingList;

    public CenterDTO(String name, String address, String phoneNumber, String emailAddress, String faxNumber, String websiteAddress, String openingHours, String closingHours, double slotDuration, int maxNumberOfVaccinesPerSlot) {
        this.name = name;
        this.address = address;
        this.phoneNumber = phoneNumber;
        this.emailAddress = emailAddress;
        this.faxNumber = faxNumber;
        this.websiteAddress = websiteAddress;
        this.openingHours = openingHours;
        this.closingHours = closingHours;
        this.slotDuration = slotDuration;
        this.maxNumberOfVaccinesPerSlot = maxNumberOfVaccinesPerSlot;
        this.waitingList = new SNSUserList();
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getEmailAddress() {
        return emailAddress;
    }

    public void setEmailAddress(String emailAddress) {
        this.emailAddress = emailAddress;
    }

    public String getFaxNumber() {
        return faxNumber;
    }

    public void setFaxNumber(String faxNumber) {
        this.faxNumber = faxNumber;
    }

    public String getWebsiteAddress() {
        return websiteAddress;
    }

    public void setWebsiteAddress(String websiteAddress) {
        this.websiteAddress = websiteAddress;
    }

    public String getOpeningHours() {
        return openingHours;
    }

    public void setOpeningHours(String openingHours) {
        this.openingHours = openingHours;
    }

    public String getClosingHours() {
        return closingHours;
    }

    public void setClosingHours(String closingHours) {
        this.closingHours = closingHours;
    }

    public double getSlotDuration() {
        return slotDuration;
    }

    public void setSlotDuration(double slotDuration) {
        this.slotDuration = slotDuration;
    }

    public int getMaxNumberOfVaccinesPerSlot() {
        return maxNumberOfVaccinesPerSlot;
    }

    public void setMaxNumberOfVaccinesPerSlot(int maxNumberOfVaccinesPerSlot) {
        this.maxNumberOfVaccinesPerSlot = maxNumberOfVaccinesPerSlot;
    }

    public CenterCoordinator getCoordinator() {
        return coordinator;
    }

    public void setCoordinator(CenterCoordinator coordinator) {
        this.coordinator = coordinator;
    }

    public SNSUserList getWaitingList() {
        return waitingList;
    }

    public void setWaitingList(SNSUserList waitingList) {
        this.waitingList = waitingList;
    }
}
