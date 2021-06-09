package utils;

import GUI.LoginFormStudent;
import dao.*;
import hibernate.*;

import javax.swing.*;
import java.awt.*;
import java.sql.Date;
import java.util.ArrayList;
import java.util.List;

public class Utils {
    public static ImageIcon transformImg(ImageIcon imgIcon, int width, int heigth) {
        Image image = imgIcon.getImage(); // transform it
        Image newimg = image.getScaledInstance(width, heigth, java.awt.Image.SCALE_SMOOTH); // scale it the smooth way
        imgIcon = new ImageIcon(newimg);
        return imgIcon;
    }

    public static String formatNameToUsername(String name) {
        String[] nameTokens = name.split(" ");
        StringBuilder builder = new StringBuilder();
        for (int i = 0; i < nameTokens.length - 1; i++)
            builder.append(nameTokens[i].substring(0, 1).toLowerCase());
        builder.append(nameTokens[nameTokens.length - 1].toLowerCase());
        return VNCharacterUtils.removeAccent(builder.toString());
    }

    public static void changeDaysInMonth(JComboBox month, int days) {
        month.removeAllItems();
        for (int i = 1; i <= days; i++)
            month.addItem(i);
    }

    public static String getLookAndFeelClassName(String nameSnippet) {
        UIManager.LookAndFeelInfo[] plafs = UIManager.getInstalledLookAndFeels();
        for (UIManager.LookAndFeelInfo info : plafs) {
            if (info.getName().contains(nameSnippet)) {
                return info.getClassName();
            }
        }
        return null;
    }

    public static CourseRegister getCourseRegisterFromTable(JTable courseTable, JComboBox semesterComboBox) {
        List<Semester> semesters = SemesterDao.getAllSemester();
        List<Student> students = StudentDao.getAllStudent();
        CourseRegister courseRegister = new CourseRegister();
        for (Student student : students)
            if (student.getStudentId().equals(LoginFormStudent.usernameGlobal)) {
                courseRegister.setStudentId(student);
                break;
            }
        int row = courseTable.getSelectedRow();
        String courseId = (String) courseTable.getValueAt(row, 0);
        Subject s = SubjectDao.getASubject(courseId);
        Course c = new Course();
        c.setCourseId(s);
        String studyTime = (String) courseTable.getValueAt(row, 3);
        String studyDate = (String) courseTable.getValueAt(row, 2);
        String teacherName = (String) courseTable.getValueAt(row, 6);
        c.setStudyTime(studyTime);
        c.setStudyDay(studyDate);
        c.setTeacherName(teacherName);
        java.util.List<Course> courses = CourseDao.getAllCourses();
        for (Course course : courses) {
            if (course.toString().equals(c.toString())) {
                c = course;
                break;
            }
        }
        CourseOpen co = new CourseOpen();
        co.setCourseId(c);
        co.setStartDay(Date.valueOf((String) courseTable.getValueAt(row, 7)));
        co.setEndDay(Date.valueOf((String) courseTable.getValueAt(row, 8)));
        String semesterName = (String) semesterComboBox.getSelectedItem();
        Semester atPresent = null;
        for (Semester semester : semesters) {
            if (semester.toString().equals(semesterName)) {
                atPresent = semester;
                break;
            }
        }
        co.setSemesterId(atPresent);
        List<CourseOpen> courseOpens = CourseOpenDao.getAllCourseOpen();
        for (CourseOpen courseOpen : courseOpens) {
            if (courseOpen.toString().equals(co.toString())) {
                co = courseOpen;
                break;
            }
        }
        courseRegister.setCourseId(co);
        return courseRegister;
    }
    public static List<Student> getAllStudentInClass(String classId){
        List<Student> temp = StudentDao.getAllStudent();
        List<Student> result = new ArrayList<>();
        for(Student s: temp){
            if(s.getClassId().getClassId().equals(classId)){
                result.add(s);
            }
        }
        return result;
    }

    public static void main(String[] args) {
        List<Student> students = getAllStudentInClass("19CTT4");
        for(Student s: students)
            System.out.println(s.getStudentName());
    }
}
