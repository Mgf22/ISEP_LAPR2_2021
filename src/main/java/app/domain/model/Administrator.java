package app.domain.model;

public class Administrator {

    private Employee employee;

    public Administrator(Employee employee){

        this.employee = employee;

    }

    public Employee getEmployee() {
        return employee;
    }

    public void setEmployee(Employee employee) {
        this.employee = employee;
    }
}
