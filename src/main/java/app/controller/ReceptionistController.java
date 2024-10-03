package app.controller;

import app.controller.stores.CenterStore;
import app.controller.stores.EmployeeStore;
import app.domain.model.Center;
import app.domain.model.Company;
import app.domain.model.Employee;
import pt.isep.lei.esoft.auth.UserSession;

/**
 * @author Tomás Pereira <1210830@isep.ipp.pt>
 */
public class ReceptionistController {
    private App app;
    private Company company;
    private EmployeeStore empStore;
    private CenterStore centerStore;

    public ReceptionistController() {
        this.app = App.getInstance();
        this.company = this.app.getCompany();
        this.empStore = company.getEmployeeStore();
        this.centerStore = company.getCenterStore();
    }

    public CenterStore getCenterStore() {
        return centerStore;
    }

    public void setCenter(Center center) {
        UserSession currentSession = app.getCurrentUserSession();
        Employee receptionist = empStore.getEmployeeById(currentSession.getUserId().getEmail());

        receptionist.setCenter(center);
    }
}
