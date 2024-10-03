package app.controller.mappers.dto;

import app.domain.model.Center;

import java.time.LocalTime;

public class SNSUserDTO {

    private String name;
    private String sex;
    private String birthDate;
    private String address;
    private String phoneNumber;
    private String email;
    private String snsUserNumber;
    private String citizenCardNumber;
    private Center arrivalCenter;
    private LocalTime arrivalTime;

    public SNSUserDTO(String name, String sex, String phoneNumber, String birthDate, String snsUserNumber, LocalTime arrivalTime) {
        this.name = name;
        this.sex = sex;
        this.phoneNumber = phoneNumber;
        this.birthDate = birthDate;
        this.snsUserNumber = snsUserNumber;
        this.arrivalTime = arrivalTime;
    }

    public SNSUserDTO(String name, String sex, String birthDate, String address, String phoneNumber, String email, String snsUserNumber, String citizenCardNumber) {
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

    public String getSex() {return sex;}

    public String getBirthDate() {return birthDate;}

    public String getAddress() {return address;}

    public String getPhoneNumber() {return phoneNumber;}

    public String getEmail() {return email;}

    public String getSnsUserNumber() {return snsUserNumber;}

    public String getCitizenCardNumber() {return citizenCardNumber;}

    public LocalTime getArrivalTime() { return arrivalTime;}

    @Override
    public String toString() {
        return "Name='" + name + '\'' +
                ", Sex='" + sex + '\'' +
                ", Birth Date='" + birthDate + '\'' +
                ", Address='" + address + '\'' +
                ", Phone Number='" + phoneNumber + '\'' +
                ", Email='" + email + '\'' +
                ", SNSUserNumber='" + snsUserNumber + '\'' +
                ", CitizenCardNumber='" + citizenCardNumber + '\'';
    }
}
