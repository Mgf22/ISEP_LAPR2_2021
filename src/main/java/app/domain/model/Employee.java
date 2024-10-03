package app.domain.model;

import java.io.Serializable;
import java.util.UUID;
import java.util.regex.Pattern;

/**
 * @author Mateus Fernandes and Diogo Ribeiro <1190508@isep.ipp.pt> <1210821@isep.ipp.pt>
 */

public class Employee implements Serializable {

    private static final String NAME_BY_OMISSION = "[NO NAME]";
    private static final String ADDRESS_BY_OMISSION = "[NO ADDRESS]";
    private static final String PHONENUMBER_BY_OMISSION = "[NO PHONE NUMBER]";
    private static final long serialVersionUID = 7048364609667831539L;

    private String Id;
    private String name;
    private String address;
    private String phoneNumber;
    private String role;
    private String citizenCardNumber;
    private Center center;
    private String password;

    /*public Employee(String Id, String name, String role, String address, String phoneNumber, String citizenCardNumber) {

        this.Id = Id;
        this.name = name;
        this.role = role;
        this.address = address;
        this.phoneNumber = phoneNumber;
        this.citizenCardNumber = citizenCardNumber;

    }*/

    public Employee(String role, String name, String address, String phoneNumber, String id, String citizenCardNumber, String password) {

        checkPhoneNumber(phoneNumber);
        checkAddress(address);
        checkName(name);
        checkCitizenCardNumber(citizenCardNumber);
        this.Id = id;
        this.role = role;
        this.name = name;
        this.address = address;
        this.phoneNumber = phoneNumber;
        this.citizenCardNumber = citizenCardNumber;
        this.password = password;
    }

    public Employee(String name, String id,String password, String role) {
        this.name = name;
        this.Id = id;
        this.password = password;
        this.role = role;

        this.address = ADDRESS_BY_OMISSION;
        this.phoneNumber = PHONENUMBER_BY_OMISSION;
    }

    public Employee(String id, String name, String id1, String address, String phoneNumber, String citizenCardNumber) {
    }

    public String getId() {
        return Id;
    }

    public void setId(String id) {
        Id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
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

    public String getCitizenCardNumber() {return citizenCardNumber;}

    public void setCitizenCardNumber(String citizenCardNumber) {this.citizenCardNumber = citizenCardNumber;}

    private void checkPhoneNumber(String phoneNumber) {
        if (phoneNumber.isEmpty())
            throw new IllegalArgumentException("Employee's Phone number can't be empty");
        if (phoneNumber.length() != 9)
            throw new IllegalArgumentException("Employee's Phone number must have 9 digits");
    }

    private void checkAddress(String address) {
        if (address.isEmpty())
            throw new IllegalArgumentException("Employee's address can't be empty");
        if (address.length() > 30)
            throw new IllegalArgumentException("Employee's address can't have more than 30 characters");
    }

    private void checkName(String name) {
        if (name.isEmpty())
            throw new IllegalArgumentException("Employees name can't be empty");
        if (name.length() > 35)
            throw new IllegalArgumentException("Employees name can't have more than 35 characters");
    }

    private void checkCitizenCardNumber(String citizenCardNumber) {
        if (citizenCardNumber.isEmpty())
            throw new IllegalArgumentException("Employee's Citizen card number can't be empty");
        if (citizenCardNumber.length() != 8)
            throw new IllegalArgumentException("Employee's Citizen card number must have 8 digits");
    }

    public Center getCenter() { return center; }

    public void setCenter(Center center) {
        this.center = center;
    }

    public String getPassword(){
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
