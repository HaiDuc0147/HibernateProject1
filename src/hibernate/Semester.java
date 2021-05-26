package hibernate;

import java.sql.Date;
import java.util.Objects;

public class Semester {
    private int id;
    private String semesterName;
    private int year;
    private Date startDay;
    private Date endDay;

    public int getId() {
        return id;
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

    public int getYear() {
        return year;
    }

    public void setYear(int year) {
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
    public int hashCode() {
        return Objects.hash(id, semesterName, year, startDay, endDay);
    }
}
