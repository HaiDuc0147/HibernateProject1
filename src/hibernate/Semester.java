package hibernate;

import java.sql.Date;
import java.util.Objects;

public class Semester {
    private int id;
    private String semesterName;
    private String year;
    private Date startDay;
    private Date endDay;

    public Semester() {}


    public int getId() {
        return id;
    }

    public Semester(int id, String semesterName, String year, Date startDay, Date endDay) {
        this.id = id;
        this.semesterName = semesterName;
        this.year = year;
        this.startDay = startDay;
        this.endDay = endDay;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getSemesterName() {
        return semesterName;
    }

    public void setSemesterName(String semesterName) {
        this.semesterName = semesterName;
    }

    public String getYear() {
        return year;
    }

    public void setYear(String year) {
        this.year = year;
    }

    public Date getStartDay() {
        return startDay;
    }

    public void setStartDay(Date startDay) {
        this.startDay = startDay;
    }

    public Date getEndDay() {
        return endDay;
    }

    public void setEndDay(Date endDay) {
        this.endDay = endDay;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Semester semester = (Semester) o;
        return id == semester.id && year == semester.year && Objects.equals(semesterName, semester.semesterName) && Objects.equals(startDay, semester.startDay) && Objects.equals(endDay, semester.endDay);
    }

    @Override
    public String toString() {
        return semesterName  + " - " + year;
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, semesterName, year, startDay, endDay);
    }
}
