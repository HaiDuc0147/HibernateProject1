package hibernate;

import java.sql.Date;
import java.util.Objects;

public class CourseOpen {
    private int ids;
    private Date startDay;
    private Date endDay;
    private Subject courseId;
    private Semester id;

    public int getIds() {
        return ids;
    }

    public void setIds(int ids) {
        this.ids = ids;
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

    public Subject getCourseId() {
        return courseId;
    }

    public void setCourseId(Subject courseId) {
        this.courseId = courseId;
    }

    public Semester getId() {
        return id;
    }

    public void setId(Semester id) {
        this.id = id;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        CourseOpen that = (CourseOpen) o;
        return ids == that.ids && id == that.id && Objects.equals(startDay, that.startDay) && Objects.equals(endDay, that.endDay) && Objects.equals(courseId, that.courseId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(ids, startDay, endDay, courseId, id);
    }
}
