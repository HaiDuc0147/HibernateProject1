package hibernate;

import java.sql.Date;
import java.util.Objects;

public class Course {
    private int id;
    private String courseId;

    private String studyDay;
    private String studyTime;
    private String teacherName;
    private String classroom;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getCourseId() {
        return courseId;
    }

    public void setCourseId(String courseId) {
        this.courseId = courseId;
    }
    public String getStudyDay() {
        return studyDay;
    }

    public void setStudyDay(String studyDay) {
        this.studyDay = studyDay;
    }

    public String getStudyTime() {
        return studyTime;
    }

    public void setStudyTime(String studyTime) {
        this.studyTime = studyTime;
    }

    public String getTeacherName() {
        return teacherName;
    }

    public void setTeacherName(String teacherName) {
        this.teacherName = teacherName;
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
        return id == course.id && Objects.equals(studyDay, course.studyDay) && Objects.equals(studyTime, course.studyTime) && Objects.equals(teacherName, course.teacherName) && Objects.equals(classroom, course.classroom);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, studyDay, studyTime, teacherName, classroom);
    }
}
