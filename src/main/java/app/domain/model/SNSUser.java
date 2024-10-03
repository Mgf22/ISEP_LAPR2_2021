package app.domain.model;

import java.io.Serializable;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;

/**
 *
 * @author Mateus Fernandes <1210821@isep.ipp.pt>
 */

public class SNSUser implements Serializable {

    private static final long serialVersionUID = -3436152424770175638L;
    private String name;
    private String sex;
    private String birthDate;
    private String address;
    private String phoneNumber;
    private String email;
    private String snsUserNumber;
    private String citizenCardNumber;
    private String password;
    private Center arrivalCenter;
    private LocalDate arrivalDate;
    private LocalTime arrivalTime;

    public SNSUser(String name, String sex, String birthDate, String address, String phoneNumber, String email, String snsUserNumber, String citizenCardNumber, String password) {
        this.name = name;
        this.sex = sex;
        this.birthDate = birthDate;
        this.address = address;
        this.phoneNumber = phoneNumber;
        this.email = email;
        this.snsUserNumber = snsUserNumber;
        this.citizenCardNumber = citizenCardNumber;
        this.password = password;
    }

    public SNSUser(String name, String sex, String birthDate, String address, String phoneNumber, String email, String snsUserNumber, String citizenCardNumber) {
        this.name = name;
        this.sex = sex;
        this.birthDate = birthDate;
        this.address = address;
        this.phoneNumber = phoneNumber;
        this.email = email;
        this.snsUserNumber = snsUserNumber;
        this.citizenCardNumber = citizenCardNumber;
    }

    public String getName() {return name;}

    public String getAddress() {return address;}

    public String getSex() {return sex;}

    public String getPhoneNumber() {return phoneNumber;}

    public String getEmail() {return email;}

    public String getBirthDate() {return birthDate;}

    public String getSnsUserNumber() {return snsUserNumber;}

    public String getCitizenCardNumber() {return citizenCardNumber;}

    public String getPassword() {return password;}

    public Center getArrivalCenter() {return arrivalCenter;}

    public LocalTime getArrivalTime() {return arrivalTime;}

    public void setArrivalCenter(Center arrivalCenter) {
        this.arrivalCenter = arrivalCenter;
    }

    public void setArrivalDate(LocalDate arrivalDate) {
        this.arrivalDate = arrivalDate;
    }

    public void setArrivalTime(LocalTime arrivalTime) {
        this.arrivalTime = arrivalTime;
    }

    public void setArrival(LocalDate arrivalDate, LocalTime arrivalTime, Center arrivalCenter) {
        setArrivalDate(arrivalDate);
        setArrivalTime(arrivalTime);
        setArrivalCenter(arrivalCenter);
    }

    public boolean validateDate(LocalDate arrivalDate) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-YYYY");
        if (this.arrivalDate == null) {
            return false;
        }
        return formatter.format(arrivalDate).equals(formatter.format(this.arrivalDate));
    }

    @Override
    public String toString() {
        return "Name='" + name + '\'' +
                ", Sex='" + sex + '\'' +
                ", Birth Date='" + birthDate + '\'' +
                ", Address='" + address + '\'' +
                ", Phone Number='" + phoneNumber + '\'' +
                ", Email='" + email + '\'' +
                ", SNS User Number='" + snsUserNumber + '\'' +
                ", Citizen Card Number='" + citizenCardNumber + '\'';
    }
}
