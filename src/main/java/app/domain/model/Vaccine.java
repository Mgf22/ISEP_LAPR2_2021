package app.domain.model;

import java.io.Serializable;

/**
 * @author Tomás Pereira <1210830@isep.ipp.pt>
 */
public class Vaccine extends VaccineType implements Serializable {
    private static final long serialVersionUID = 1517473233298030706L;
    String name;
    int lot;
    int id;
    double dosage;
    int timeInterval;
    int ageGroupMax;
    int ageGroupMin;
    int numberOfDoses;

    public Vaccine(String typeName, int typeID, String name, int lot, int id, double dosage, int timeInterval, int ageGroupMax, int ageGroupMin, int numberOfDoses) {
        super(typeName, typeID);
        this.name = name;
        this.lot = lot;
        this.id = id;
        this.dosage = dosage;
        this.timeInterval = timeInterval;
        this.ageGroupMax = ageGroupMax;
        this.ageGroupMin = ageGroupMin;
        this.numberOfDoses = numberOfDoses;
    }

    public String getTypeName() {
        return super.getName();
    }

    public int getTypeID() {
        return super.getId();
    }

    public String getName() {
        return name;
    }

    public int getLot() {
        return lot;
    }

    public int getId() {
        return id;
    }

    public double getDosage() {
        return dosage;
    }

    public int getTimeInterval() {
        return timeInterval;
    }

    public int getAgeGroupMax() {
        return ageGroupMax;
    }

    public int getAgeGroupMin() {
        return ageGroupMin;
    }

    public int getNumberOfDoses() {
        return numberOfDoses;
    }

    public static Vaccine create(String typeName, int typeID, String name, int lot, int id, double dosage, int timeInterval, int ageGroupMax, int ageGroupMin, int numberOfDoses) {
        return new Vaccine(typeName, typeID, name, lot, id, dosage, timeInterval, ageGroupMax, ageGroupMin, numberOfDoses);
    }

    @Override
    public String toString() {
        return "Vaccine{\n" +
                "Type Name: " + this.getTypeName() + '\n' +
                "Type ID: " + this.getTypeID() + '\n' +
                "Name/Brand: " + name + '\n' +
                "Lot: " + lot + '\n' +
                "ID: " + id + '\n' +
                "Dosage: " + dosage + " ml\n" +
                "Time Interval: " + timeInterval + " days\n" +
                "Minimum age: " + ageGroupMin + '\n' +
                "Maximum age: " + ageGroupMax + '\n' +
                "Number of doses: " + numberOfDoses + '\n' +
                '}';
    }
}
