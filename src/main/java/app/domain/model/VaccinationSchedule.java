package app.domain.model;



import java.io.Serializable;
import java.util.UUID;

/**
 *
 * @author Diogo Ribeiro <1190508@isep.ipp.pt>
 */
public class VaccinationSchedule implements Serializable {

    private static final long serialVersionUID = -4071926591187690839L;
    private String scheduleID;
    private String snsUserNumber;
    private String date;
    private CommunityMassVaccinationCenter vaccinationCenter;
    private VaccineType typeOfVaccine;

    public VaccinationSchedule(String snsUserNumber, String date, CommunityMassVaccinationCenter vaccinationCenter, VaccineType typeOfVaccine){
        this.scheduleID = UUID.randomUUID().toString();
        this.snsUserNumber = snsUserNumber;
        this.date = date;
        this.vaccinationCenter = vaccinationCenter;
        this.typeOfVaccine = typeOfVaccine;
    }

    public String getDate() {
        return date;
    }

    public String getScheduleID() {
        return scheduleID;
    }

    public String getSnsUserNumber() {
        return snsUserNumber;
    }

    public CommunityMassVaccinationCenter getVaccinationCenter() {
        return vaccinationCenter;
    }

    public VaccineType getTypeOfVaccine() {
        return typeOfVaccine;
    }
}
