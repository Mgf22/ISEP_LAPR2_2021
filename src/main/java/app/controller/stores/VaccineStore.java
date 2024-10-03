package app.controller.stores;

import app.controller.lists.VaccineList;
import app.domain.model.Vaccine;
import app.tools.Serialize;

import java.util.ArrayList;

/**
 * @author Tomás Pereira <1210830@isep.ipp.pt>
 */
public class VaccineStore {
    private static final String VACCINE_SAVE_FILE = "vaccine";
    private static ArrayList<Vaccine> store = new ArrayList<>();

    public VaccineStore() {
        store = Serialize.deserialize(VACCINE_SAVE_FILE);
        store.add(new Vaccine("Covid-19", 01, "Pfizer", 001, 1, 0.4, 10, 20, 10, 2));
        store.add(new Vaccine("Covid-19", 01, "Moderna", 002, 2, 0.4, 10, 20, 10, 1));
        store.add(new Vaccine("Tuberculosis", 02, "Tuber1", 003, 3, 0.4, 10, 20, 10, 2));
        store.add(new Vaccine("Tuberculosis", 02, "Tuber2", 004, 4, 0.4, 10, 20, 10, 1));
    }

    public boolean validateVaccine(Vaccine vaccine) {
        return (vaccine.getAgeGroupMax() >= 0 && vaccine.getAgeGroupMax() <= 150) && (vaccine.getAgeGroupMin() >= 0 && vaccine.getAgeGroupMin() <= 150) && vaccine.getAgeGroupMin() < vaccine.getAgeGroupMax() && !vaccine.getName().equals("") && vaccine.getDosage() > 0 && vaccine.getId() >= 0 && vaccine.getLot() >= 0 && vaccine.getTimeInterval() > 0 && vaccine.getNumberOfDoses() > 0;
    }

    public void addVaccine(Vaccine vaccine) {
        store.add(vaccine);
        Serialize.serialize(store, VACCINE_SAVE_FILE);
    }

    public VaccineList getVaccineListByTypeName(String typeName) {
        VaccineList vList = new VaccineList();

        for (Vaccine v : store) {
            if (v.getTypeName().equals(typeName)) {
                vList.add(v);
            }
        }
        return vList;
    }

    public Vaccine getVaccineByID(int vaccineID) {
        for (Vaccine v : store) {
            if (v.getId() == vaccineID) {
                return v;
            }
        }
        return null;
    }
}
