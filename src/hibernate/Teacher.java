package hibernate;

import java.util.Objects;

public class Teacher {
    private String teacherId;
    private String teacherName;
    private String phoneNumber;
    private String address;
    private String citizenId;

    public String getTeacherId() {
        return teacherId;
    }

    public void setTeacherId(String teacherId) {
        this.teacherId = teacherId;
    }

    public String getTeacherName() {
        return teacherName;
    }

    public void setTeacherName(String teacherName) {
        this.teacherName = teacherName;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getCitizenId() {
        return citizenId;
    }

    public void setCitizenId(String citizenId) {
        this.citizenId = citizenId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Teacher teacher = (Teacher) o;
        return Objects.equals(teacherId, teacher.teacherId) && Objects.equals(teacherName, teacher.teacherName) && Objects.equals(phoneNumber, teacher.phoneNumber) && Objects.equals(address, teacher.address) && Objects.equals(citizenId, teacher.citizenId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(teacherId, teacherName, phoneNumber, address, citizenId);
    }
}
