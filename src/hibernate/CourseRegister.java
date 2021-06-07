package hibernate;

import java.sql.Date;
import java.util.Objects;

public class CourseRegister {
    private int id;
    private CourseOpen courseId;
    private Date registerDay;
    private Student studentId;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public CourseOpen getCourseId() {
        return courseId;
    }

    public void setCourseId(CourseOpen courseId) {
        this.courseId = courseId;
    }

    public Date getRegisterDay() {
        return registerDay;
    }

    public void setRegisterDay(Date registerDay) {
        this.registerDay = registerDay;
    }

    public Student getStudentId() {
        return studentId;
    }

    public void setStudentId(Student studentId) {
        this.studentId = studentId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        CourseRegister that = (CourseRegister) o;
        return id == that.id && courseId == that.courseId && Objects.equals(registerDay, that.registerDay) && Objects.equals(studentId, that.studentId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, courseId, registerDay, studentId);
    }
}
