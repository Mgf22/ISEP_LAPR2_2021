package app.controller.stores;

import app.domain.model.*;
import app.domain.shared.Constants;
import app.tools.Serialize;
import pt.isep.lei.esoft.auth.AuthFacade;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

/**
 * @author Tomás Pereira <1210830@isep.ipp.pt>
 */
public class VaccineAdministrationStore {
    private static final String VACCINEADMINISTRATION_SAVE_FILE = "vaccineAdministration";
    private static ArrayList<VaccineAdministration> store = new ArrayList<>();

    public VaccineAdministrationStore() {
    }

    public ArrayList<VaccineAdministration> getVaccineAdministrationStore() {
        return store;
    }

    public void add(SNSUser snsUser, Center center, Vaccine vaccine, int nOfDose, LocalDate date) {
        boolean found = false;
        for(VaccineAdministration va : store) {
            if(va.getSnsUser().getName().equals(snsUser.getName()) && va.getCenter().getName().equals(center.getName()) && va.getVaccine().getName().equals(vaccine.getName()) && va.getnOfDose() == nOfDose) {
                found = true;
            }
        }
        if(!found) {
            store.add(new VaccineAdministration(snsUser, center, vaccine, nOfDose, date));
            Serialize.serialize(store, VACCINEADMINISTRATION_SAVE_FILE);
        }
    }

    public List<VaccineAdministration> getLastDoseCenterAdministrationsByDatePeriod(Center workingCenter, LocalDate date1, LocalDate date2) {
        List<VaccineAdministration> fullyVaccinatedList = new ArrayList<VaccineAdministration>();
        for (VaccineAdministration vac : store)  {
            Center center = vac.getCenter();
            if(workingCenter.getName().equals(center.getName())) {
                LocalDate administrationDate = vac.getDate();
                boolean isLastDose = vac.isLastDose();
                if(isLastDose && !administrationDate.isBefore(date1) && !administrationDate.isAfter(date2)) {
                    fullyVaccinatedList.add(vac);
                }
            }
        }
        return fullyVaccinatedList;
    }

    public List<String> countAdministrations(CenterStore censtore) {
        int counter;
        ArrayList<Center> cen;
        List<String> administrationCountList = new ArrayList<>();
        cen = censtore.getCenterStore();
        LocalDate day = LocalDate.now();
        String adm ;
        for (int i = 0; i < cen.size(); i++) {
            counter = 0;
            List<String> snsUserList = new ArrayList<>();
            for (VaccineAdministration va : store) {
                if (!snsUserList.contains(va.getSnsUser().getSnsUserNumber()) && va.getDate().equals(day)) {
                    if (cen.get(i).getName().equals(va.getCenter().getName())) {
                        snsUserList.add(va.getSnsUser().getSnsUserNumber());
                        counter++;
                    }
                }
            }
            adm= LocalDate.now().toString()+";"+cen.get(i).getName()+";"+counter;
            administrationCountList.add(adm);
        }
            return administrationCountList;
    }

    public void deserializeVacAd (){
        store = Serialize.deserialize(VACCINEADMINISTRATION_SAVE_FILE);
    }
}
