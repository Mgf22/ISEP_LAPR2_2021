package app.controller.mappers;

import app.controller.lists.VaccineList;
import app.controller.mappers.dto.VaccineDTO;
import app.controller.mappers.dto.VaccineListDTO;

public class VaccineMapper {

    public static VaccineListDTO toDTO(VaccineList vList) {
        VaccineListDTO list = new VaccineListDTO();

        for (int i = 0; i < vList.size(); i++) {
            list.add(new VaccineDTO(vList.get(i).getTypeName(), vList.get(i).getId(), vList.get(i).getName(), vList.get(i).getId(), vList.get(i).getDosage(), vList.get(i).getAgeGroupMax(), vList.get(i).getAgeGroupMin()));
        }
        return list;
    }
}
