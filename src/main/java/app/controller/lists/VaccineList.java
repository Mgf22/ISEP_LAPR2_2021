package app.controller.lists;
import app.domain.model.Vaccine;

import java.util.ArrayList;
import java.util.List;

public class VaccineList {
    private List<Vaccine> list = new ArrayList<>();

    public VaccineList() {

    }

    public boolean add(Vaccine vaccine) {
        list.add(vaccine);
        return true;
    }

    public Vaccine get(int index) {
        return list.get(index);
    }

    public boolean remove(Vaccine vaccine) {
        list.remove(vaccine);
        return true;
    }

    public int size() {
        return list.size();
    }

    public boolean contains(Vaccine vaccine) {
        return list.contains(vaccine);
    }
}
