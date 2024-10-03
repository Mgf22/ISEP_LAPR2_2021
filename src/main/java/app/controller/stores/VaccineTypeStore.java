package app.controller.stores;

import app.domain.model.VaccineType;
import app.tools.Serialize;

import java.util.ArrayList;

/**
 * @author Tomás Pereira <1210830@isep.ipp.pt>
 */
public class VaccineTypeStore {

    private static final String VACCINETYPE_SAVE_FILE = "vaccineType";
    private static ArrayList<VaccineType> store = new ArrayList<>();

    public VaccineTypeStore() {
        store = Serialize.deserialize(VACCINETYPE_SAVE_FILE);
        store.add(new VaccineType("Covid-19", 01));
        store.add(new VaccineType("Tuberculosis", 02));
        store.add(new VaccineType("Tetanus", 03));
        store.add(new VaccineType("Flu", 04));
        store.add(new VaccineType("Measles", 05));
    }

    public ArrayList<VaccineType> getVaccineTypes() {
        return store;
    }


}
