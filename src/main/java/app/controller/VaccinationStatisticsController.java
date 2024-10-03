package app.controller;

import app.controller.stores.CenterStore;
import app.controller.stores.VaccineAdministrationStore;
import app.domain.model.Center;
import app.domain.model.Company;
import app.domain.model.VaccineAdministration;
import app.tools.VaccineAdministrationCounter;
import javafx.fxml.Initializable;

import java.io.PrintWriter;
import java.net.URL;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;
import java.util.stream.Collectors;

public class VaccinationStatisticsController implements Initializable {

    private App app;
    private Company company;
    public VaccinationStatisticsController() {
        this.app = App.getInstance();
        this.company = app.getCompany();
    }

    public List<VaccineAdministrationCounter> generateVaccineAdministrationCounterList(int centerIndex, LocalDate date1, LocalDate date2) {
        CenterStore centerStore = company.getCenterStore();
        VaccineAdministrationStore vaccineAdministrationStore = company.getVaccineAdministrationStore();
        Center workingCenter = centerStore.getCenterByIndex(centerIndex);

        List<VaccineAdministrationCounter> vaccineAdministrationCounterList = new ArrayList<>();
        List<VaccineAdministration> fullyVaccinatedList = vaccineAdministrationStore.getLastDoseCenterAdministrationsByDatePeriod(workingCenter, date1, date2);

        List<LocalDate> datesBetween = date1.datesUntil(date2.plusDays(1)).collect(Collectors.toList());
        for(LocalDate date : datesBetween) {
            int counter = 0;
            for(VaccineAdministration vacAdm : fullyVaccinatedList) {
                if(vacAdm.getDate().compareTo(date) == 0) { //if equal
                    counter++;
                }
            }
            VaccineAdministrationCounter vaCounter = new VaccineAdministrationCounter(date, counter);
            vaccineAdministrationCounterList.add(vaCounter);
        }
        return vaccineAdministrationCounterList;
    }

    public boolean exportVaccinationStatistics(int centerIndex, String filePath, LocalDate date1, LocalDate date2) {
        CenterStore centerStore = company.getCenterStore();
        VaccineAdministrationStore vaccineAdministrationStore = company.getVaccineAdministrationStore();
        Center workingCenter = centerStore.getCenterByIndex(centerIndex);

        List<VaccineAdministration> fullyVaccinatedList = vaccineAdministrationStore.getLastDoseCenterAdministrationsByDatePeriod(workingCenter, date1, date2);
        boolean success = exportFullyVaccinatedByDatePeriod(filePath, fullyVaccinatedList, date1, date2);
        return success;
    }

    public boolean exportFullyVaccinatedByDatePeriod(String filePath, List<VaccineAdministration> fullyVaccinatedList, LocalDate date1, LocalDate date2) {
        String csvData = "date;people_fully_vaccinated" + "\n";
        List<LocalDate> datesBetween = date1.datesUntil(date2.plusDays(1)).collect(Collectors.toList());
        for(LocalDate date : datesBetween) {
            int counter = 0;
            for(VaccineAdministration vaccineAdministration : fullyVaccinatedList) {
                if(vaccineAdministration.getDate().compareTo(date) == 0) { //if equal
                    counter++;
                }
            }
            csvData = csvData + date + ";" + counter + "\n";
        }
        try {
            PrintWriter write = new PrintWriter(filePath);
            write.append(csvData);
            write.flush();
            //System.out.println(csvData);
            return true;
        } catch(Exception e) {
            return false;
        }
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

    }

}
