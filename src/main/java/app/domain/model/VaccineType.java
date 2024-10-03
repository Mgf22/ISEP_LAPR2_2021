package app.domain.model;

import java.io.Serializable;

/**
 * @author Tomás Pereira <1210830@isep.ipp.pt>
 */
public class VaccineType implements Serializable {

    private static final long serialVersionUID = -5656044327767774859L;
    private String name;
    private int id;

    public VaccineType(String name, int id) {
        this.name = name;
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public int getId() {
        return id;
    }

}
