package app.domain.model;

import java.io.Serializable;
import java.time.LocalDate;

/**
 * @author Tomás Pereira <1210830@isep.ipp.pt>
 */
public class VaccineAdministration implements Serializable {
    private static final long serialVersionUID = -3730890168532665576L;
    private SNSUser snsUser;
    private Center center;
    private Vaccine vaccine;
    private int nOfDose;
    private LocalDate date;

    public VaccineAdministration(SNSUser snsUser, Center center, Vaccine vaccine, int nOfDose, LocalDate date) {
        this.snsUser = snsUser;
        this.center = center;
        this.vaccine = vaccine;
        this.nOfDose = nOfDose;
        this.date = date;
    }

    public SNSUser getSnsUser() {
        return snsUser;
    }

    public Center getCenter() {
        return center;
    }

    public Vaccine getVaccine() {
        return vaccine;
    }

    public int getnOfDose() {
        return nOfDose;
    }

    public LocalDate getDate() {
        return date;
    }

    public boolean isLastDose() {
        return vaccine.getNumberOfDoses() == nOfDose;
    }
}
