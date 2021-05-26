package hibernate;

import java.util.Objects;

public class Clazz {
    private String classId;
    private Integer numberOfStudent;
    private Integer numberOfMale;
    private Integer numberOfFemale;

    public String getClassId() {
        return classId;
    }

    public void setClassId(String classId) {
        this.classId = classId;
    }

    public Integer getNumberOfStudent() {
        return numberOfStudent;
    }

    public void setNumberOfStudent(Integer numberOfStudent) {
        this.numberOfStudent = numberOfStudent;
    }

    public Integer getNumberOfMale() {
        return numberOfMale;
    }

    public void setNumberOfMale(Integer numberOfMale) {
        this.numberOfMale = numberOfMale;
    }

    public Integer getNumberOfFemale() {
        return numberOfFemale;
    }

    public void setNumberOfFemale(Integer numberOfFemale) {
        this.numberOfFemale = numberOfFemale;
    }

    public Clazz(String classId, Integer numberOfStudent, Integer numberOfMale, Integer numberOfFemale) {
        this.classId = classId;
        this.numberOfStudent = numberOfStudent;
        this.numberOfMale = numberOfMale;
        this.numberOfFemale = numberOfFemale;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Clazz clazz = (Clazz) o;
        return Objects.equals(classId, clazz.classId) && Objects.equals(numberOfStudent, clazz.numberOfStudent) && Objects.equals(numberOfMale, clazz.numberOfMale) && Objects.equals(numberOfFemale, clazz.numberOfFemale);
    }

    @Override
    public int hashCode() {
        return Objects.hash(classId, numberOfStudent, numberOfMale, numberOfFemale);
    }
}
