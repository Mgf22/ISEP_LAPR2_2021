package app.controller.mappers.dto;

/**
 * @author Tomás Pereira <1210830@isep.ipp.pt>
 */
public class VaccineTypeDTO {

    private String name;
    private int id;

    public VaccineTypeDTO(String name, int id) {
        this.name = name;
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public int getId() {
        return id;
    }

    public VaccineTypeDTO createVaccineTypeDTO(String name, int id) {
        return new VaccineTypeDTO(name, id);
    }

}
