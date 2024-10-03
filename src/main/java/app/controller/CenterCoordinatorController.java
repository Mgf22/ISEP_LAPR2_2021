package app.controller;

import app.controller.stores.CenterStore;
import app.domain.model.Company;

public class CenterCoordinatorController {
    private App app;
    private Company company;
    private CenterStore centerStore;

    public CenterCoordinatorController() {
        this.app = App.getInstance();
        this.company = this.app.getCompany();
        this.centerStore = company.getCenterStore();
    }

    public CenterStore getCenterStore() {
        return centerStore;
    }
}
