package hibernate;

import java.sql.Date;
import java.util.Objects;

public class Course {
    private int id;
    private Date studyDay;
    private String studyTime;
    private String teacherId;
    private String classroom;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Date getStudyDay() {
        return studyDay;
    }

    public void setStudyDay(Date studyDay) {
        this.studyDay = studyDay;
    }

    public String getStudyTime() {
        return studyTime;
    }

    public void setStudyTime(String studyTime) {
        this.studyTime = studyTime;
    }

    public String getTeacherId() {
        return teacherId;
    }

    public void setTeacherId(String teacherId) {
        this.teacherId = teacherId;
    }

    public String getClassroom() {
        return classroom;
    }

    public void setClassroom(String classroom) {
        this.classroom = classroom;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Course course = (Course) o;
        return id == course.id && Objects.equals(studyDay, course.studyDay) && Objects.equals(studyTime, course.studyTime) && Objects.equals(teacherId, course.teacherId) && Objects.equals(classroom, course.classroom);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, studyDay, studyTime, teacherId, classroom);
    }
}
