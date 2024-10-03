package app.controller.mappers.dto;

import app.domain.model.Vaccine;

import java.util.ArrayList;
import java.util.List;

public class VaccineListDTO {
    private List<VaccineDTO> list = new ArrayList<>();

    public VaccineListDTO() {

    }

    public boolean add(VaccineDTO vaccineDTO) {
        list.add(vaccineDTO);
        return true;
    }

    public VaccineDTO get(int index) {
        return list.get(index);
    }

    public boolean remove(VaccineDTO vaccineDTO) {
        list.remove(vaccineDTO);
        return true;
    }

    public int size() {
        return list.size();
    }

    public boolean contains(VaccineDTO vaccineDTO) {
        return list.contains(vaccineDTO);
    }
}
