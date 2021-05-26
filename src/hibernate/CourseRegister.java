package hibernate;

import java.sql.Date;
import java.util.Objects;

public class CourseRegister {
    private int id;
    private Date registerDay;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Date getRegisterDay() {
        return registerDay;
    }

    public void setRegisterDay(Date registerDay) {
        this.registerDay = registerDay;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        CourseRegister that = (CourseRegister) o;
        return id == that.id && Objects.equals(registerDay, that.registerDay);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, registerDay);
    }
}
