package GUI;

import dao.CourseRegisterDao;
import dao.StudentDao;
import hibernate.CourseRegister;
import hibernate.Student;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.util.ArrayList;
import java.util.List;

public class ShowStudentCourse extends JDialog {
    private JPanel contentPane;
    private JTable courseTable;

    public ShowStudentCourse() {
        this.setSize(800, 500);
        this.setTitle("Danh sách các môn đã đăng kí");
        setContentPane(contentPane);
        setModal(true);
        Student s = StudentDao.getAStudent(ShowStudentsInClass.studentIdGlobal);
        //Student s = StudentDao.getAStudent("19120483");
        List<CourseRegister> courseRegistersTemp = CourseRegisterDao.getAllCourseRegister();
        List<CourseRegister> courseRegisters = new ArrayList<>();
        for(CourseRegister c: courseRegistersTemp){
            if(c.getStudentId().getStudentId().equals(s.getStudentId())){
                courseRegisters.add(c);
            }
        }
        String[] columnNames = {"Mã Môn Học", "Tên Môn Học", "Số Tín Chỉ", "Ngày Học", "Ca Học"};
        DefaultTableModel model = (DefaultTableModel)courseTable.getModel();
        for(int i = 0; i < columnNames.length; i++){
            model.addColumn(columnNames[i]);
        }
        int size = courseRegisters.size();
        String[][] data = new String[size][5];
        for(int i = 0; i < size; i++){
            data[i][0] = courseRegisters.get(i).getCourseId().getCourseId().getCourseId().getSubjectId();
            data[i][1] = courseRegisters.get(i).getCourseId().getCourseId().getCourseId().getSubjectName();
            data[i][2] = String.valueOf(courseRegisters.get(i).getCourseId().getCourseId().getCourseId().getCredit());
            data[i][3] = courseRegisters.get(i).getCourseId().getCourseId().getStudyDay();
            data[i][4] = courseRegisters.get(i).getCourseId().getCourseId().getStudyTime();
        }
        for(int i = 0; i < size; i++)
            model.addRow(data[i]);
    }

    public static void main(String[] args) {
        ShowStudentCourse dialog = new ShowStudentCourse();
        dialog.pack();
        dialog.setVisible(true);
        System.exit(0);
    }
}
