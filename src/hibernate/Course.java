package hibernate;

import java.sql.Date;
import java.util.Objects;

public class Course {
    private int id;
    private Subject courseId;
    private String studyDay;
    private String studyTime;
    private String teacherName;
    private String classroom;
    private int slot;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Subject getCourseId() {
        return courseId;
    }

    public void setCourseId(Subject courseId) {
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

    public int getSlot() {
        return slot;
    }

    public void setSlot(int slot) {
        this.slot = slot;
    }

    @Override
    public String toString() {
        return "Course{" +
                "courseId=" + courseId +
                ", studyDay='" + studyDay + '\'' +
                ", studyTime='" + studyTime + '\'' +
                ", teacherName='" + teacherName + '\'' +
                '}';
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
