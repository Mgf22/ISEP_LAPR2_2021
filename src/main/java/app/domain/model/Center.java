package app.domain.model;

import app.controller.lists.SNSUserList;
import app.tools.Validations;

import java.io.Serializable;

/**
 *
 * @author Fábio Archer <1210806@isep.ipp.pt>
 */

public abstract class Center implements Serializable  {


    private static final long serialVersionUID = 5088952129267345076L;
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
    private SNSUserList recoveryRoom;

    public Center(String name, String address, String phoneNumber, String emailAddress, String faxNumber, String websiteAddress, String openingHours, String closingHours, double slotDuration, int maxNumberOfVaccinesPerSlot) {
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
        this.recoveryRoom = new SNSUserList();
    }

    public String getName() {
        return name;
    }

    public String getAddress() {
        return address;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public String getEmailAddress() {
        return emailAddress;
    }

    public String getFaxNumber() {
        return faxNumber;
    }

    public String getWebsiteAddress() {
        return websiteAddress;
    }

    public String getOpeningHours() {
        return openingHours;
    }

    public String getClosingHours() {
        return closingHours;
    }

    public double getSlotDuration() {
        return slotDuration;
    }

    public SNSUserList getRecoveryRoom() {
        return recoveryRoom;
    }

    public int getMaxNumberOfVaccinesPerSlot() {
        return maxNumberOfVaccinesPerSlot;
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

    public abstract boolean validateStrings();

    public boolean validateHours() {
        return(Validations.validateHourFormat(this.getOpeningHours()) && Validations.validateHourFormat(this.getClosingHours()));
    }

}
