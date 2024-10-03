package app.controller.mappers.dto;


/**
 *
 * @author Diogo Ribeiro <1190508@isep.ipp.pt>
 */
public class EmployeeDTO {

    private static final String NAME_BY_OMISSION = "[NO NAME]";
    private static final String ADDRESS_BY_OMISSION = "[NO ADDRESS]";
    private static final String PHONENUMBER_BY_OMISSION = "[NO PHONE NUMBER]";

    private String Id;
    private String name;
    private String address;
    private String phoneNumber;
    private String citizenCardNumber;
    private String role;
    private String password;

    public EmployeeDTO(String name, String Id, String role){

        this.name = name;
        this.Id = Id;
        this.role = role;

        this.address = ADDRESS_BY_OMISSION;
        this.phoneNumber = PHONENUMBER_BY_OMISSION;

    }

    public EmployeeDTO(String Id, String name, String role, String address, String phoneNumber) {

        this.Id = Id;
        this.name = name;
        this.role = role;
        this.address = address;
        this.phoneNumber = phoneNumber;

    }

    public EmployeeDTO(String name, String address, String id, String phoneNumber, String citizenCardNumber, String role,String password){
        this.name = name;
        this.address = address;
        this.Id = id;
        this.phoneNumber = phoneNumber;
        this.citizenCardNumber = citizenCardNumber;
        this.role = role;
        this.password = password;
    }


    public String getId() {
        return Id;
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

    public String getRole() {
        return role;
    }

    public String getCitizenCardNumber() { return citizenCardNumber;}

    @Override
    public String toString() {
        return "EmployeeDTO{" +
                "id=" + Id +
                ", name='" + name + '\'' +
                ", address='" + address + '\'' +
                ", phoneNumber='" + phoneNumber + '\'' +
                ", UserRole='" + role + '\'' +
                '}';
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
