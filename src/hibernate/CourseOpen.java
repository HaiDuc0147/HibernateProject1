package hibernate;

import java.sql.Date;
import java.util.Objects;

public class CourseOpen {
    private int id;
    private Date startDay;
    private Date endDay;
    private Course courseId;
    private Semester semesterId;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
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

    public Course getCourseId() {
        return courseId;
    }

    public void setCourseId(Course courseId) {
        this.courseId = courseId;
    }

    public Semester getSemesterId() {
        return semesterId;
    }

    public void setSemesterId(Semester semesterId) {
        this.semesterId = semesterId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        CourseOpen that = (CourseOpen) o;
        return id == that.id && courseId == that.courseId && semesterId == that.semesterId && Objects.equals(startDay, that.startDay) && Objects.equals(endDay, that.endDay);
    }

    @Override
    public String toString() {
        return "CourseOpen{" +
                "startDay=" + startDay +
                ", endDay=" + endDay +
                ", courseId=" + courseId +
                ", semesterId=" + semesterId +
                '}';
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, startDay, endDay, courseId, semesterId);
    }
}
