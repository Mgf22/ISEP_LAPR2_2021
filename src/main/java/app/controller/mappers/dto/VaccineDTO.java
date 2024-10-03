package app.controller.mappers.dto;

public class VaccineDTO {

    String typeName;
    int typeID;
    String name;
    int id;
    double dosage;
    int ageGroupMax;
    int ageGroupMin;

    public VaccineDTO(String typeName, int typeID, String name, int id, double dosage, int ageGroupMax, int ageGroupMin) {
        this.typeName = typeName;
        this.typeID = typeID;
        this.name = name;
        this.id = id;
        this.dosage = dosage;
        this.ageGroupMax = ageGroupMax;
        this.ageGroupMin = ageGroupMin;
    }

    public String getTypeName() {
        return typeName;
    }

    public int getTypeID() {
        return typeID;
    }

    public String getName() {
        return name;
    }

    public int getId() {
        return id;
    }

    public double getDosage() {
        return dosage;
    }

    public int getAgeGroupMax() {
        return ageGroupMax;
    }

    public int getAgeGroupMin() {
        return ageGroupMin;
    }

    public static VaccineDTO create(String typeName, int typeID, String name, int id, double dosage, int ageGroupMax, int ageGroupMin) {
        return new VaccineDTO(typeName, typeID, name, id, dosage, ageGroupMax, ageGroupMin);
    }

    @Override
    public String toString() {
        return id + " - " + name + "(" + dosage +"ml / " + ageGroupMin + "-" + ageGroupMax + ")";
    }
}
