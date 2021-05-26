package hibernate;

import java.sql.Date;
import java.util.Objects;

public class CourseOpen {
    private int id;
    private Date startDay;
    private Date endDay;

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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        CourseOpen that = (CourseOpen) o;
        return id == that.id && Objects.equals(startDay, that.startDay) && Objects.equals(endDay, that.endDay);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, startDay, endDay);
    }
}
