package app.domain.model;

import pt.isep.lei.esoft.auth.domain.model.UserRole;

public class Receptionist extends Employee{

    private Employee employee;

    public Receptionist(Employee employee, String Id, String name, UserRole role, String address, String phoneNumber, String citizenCardNumber){
        super(Id, name, role.getId(), address, phoneNumber, citizenCardNumber);
        this.employee = employee;
    }

    public Employee getEmployee() {
        return employee;
    }

    public void setEmployee(Employee employee) {
        this.employee = employee;
    }

}
