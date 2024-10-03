package app.domain.model;

public class CenterCoordinator {

    private Employee employee;

    public CenterCoordinator(Employee employee){

        this.employee = employee;

    }

    public Employee getEmployee() {
        return employee;
    }

    public void setEmployee(Employee employee) {
        this.employee = employee;
    }

}
