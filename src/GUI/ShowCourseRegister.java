package GUI;

import dao.CourseRegisterDao;
import dao.SemesterDao;
import hibernate.CourseRegister;
import hibernate.Semester;
import utils.Utils;

import javax.swing.*;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableCellRenderer;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;

public class ShowCourseRegister extends JDialog {
    private JPanel contentPane;
    private JTable courseTable;
    private JComboBox semesterComboBox;
    private JButton removeButton;

    public ShowCourseRegister() {
        this.setTitle("Danh sách học phần");
        this.setSize(1500, 700);
        setContentPane(contentPane);
        setModal(true);
        List<Semester> semesters = SemesterDao.getAllSemester();
        for(Semester semester: semesters)
            semesterComboBox.addItem((String)semester.toString());
        String[] columnNames = {"Mã Học Phần", "Tên Học Phần", "Ngày học", "Ca học", "Phòng học", "Slot tối đa", "Giáo viên giảng dạy",
                "Ngày bắt đầu đăng kí", "Ngày kết thúc đăng kí", "Ngày đăng kí"};
        DefaultTableModel model = (DefaultTableModel) courseTable.getModel();
        for (int i = 0; i < columnNames.length; i++)
            model.addColumn(columnNames[i]);

        semesterComboBox.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String semesterName = (String)semesterComboBox.getSelectedItem();
                Semester atPresent = null;
                for(Semester s: semesters){
                    if(s.toString().equals(semesterName)) {
                        atPresent = s;
                        break;
                    }
                }
                List<CourseRegister> courseRegisters1 = CourseRegisterDao.getAllCourseRegister();
                List<CourseRegister> courseRegisters = new ArrayList<>();
                for (CourseRegister c : courseRegisters1) {
                    if (c.getCourseId().getSemesterId().getId() == atPresent.getId() && c.getStudentId().getStudentId().equals(LoginFormStudent.usernameGlobal)) {
                        courseRegisters.add(c);
                    }
                }
                int size = courseRegisters.size();
                while (model.getRowCount() > 0) {
                    model.removeRow(0);
                }
                String[][] data = new String[size][10];
                if (size > 0) {
                    for (int i = 0; i < size; i++) {
                        data[i][0] = courseRegisters.get(i).getCourseId().getCourseId().getCourseId().getSubjectId();
                        data[i][1] = courseRegisters.get(i).getCourseId().getCourseId().getCourseId().getSubjectName();
                        data[i][2] = courseRegisters.get(i).getCourseId().getCourseId().getStudyDay();
                        data[i][3] = courseRegisters.get(i).getCourseId().getCourseId().getStudyTime();
                        data[i][4] = courseRegisters.get(i).getCourseId().getCourseId().getClassroom();
                        data[i][5] = String.valueOf(courseRegisters.get(i).getCourseId().getCourseId().getSlot());
                        data[i][6] = courseRegisters.get(i).getCourseId().getCourseId().getTeacherName();
                        data[i][7] = String.valueOf(courseRegisters.get(i).getCourseId().getStartDay());
                        data[i][8] = String.valueOf(courseRegisters.get(i).getCourseId().getEndDay());
                        data[i][9] = String.valueOf(courseRegisters.get(i).getRegisterDay());
                    }
                    for (int i = 0; i < size; i++) {
                        model.addRow(data[i]);
                    }
                    DefaultTableCellRenderer centerRenderer = new DefaultTableCellRenderer();
                    centerRenderer.setHorizontalAlignment(JLabel.CENTER);
                    for (int x = 0; x < columnNames.length; x++) {
                        courseTable.getColumnModel().getColumn(x).setCellRenderer(centerRenderer);
                    }
                    courseTable.setModel(model);
                    TableCellRenderer rendererFromHeader = courseTable.getTableHeader().getDefaultRenderer();
                    JLabel headerLabel = (JLabel) rendererFromHeader;
                    headerLabel.setHorizontalAlignment(JLabel.CENTER);
                }
            }
        });
        removeButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                CourseRegister courseRegister = Utils.getCourseRegisterFromTable(courseTable, semesterComboBox);
                List<CourseRegister> courseRegisters = CourseRegisterDao.getAllCourseRegister();
                for(CourseRegister c: courseRegisters){
                    if(courseRegister.toString().equals(c.toString())){
                        CourseRegisterDao.deleteACourseRegister(c);
                        break;
                    }
                }

            }
        });
    }

    public static void main(String[] args) {
        ShowCourseRegister dialog = new ShowCourseRegister();
        dialog.pack();
        dialog.setVisible(true);
        System.exit(0);
    }
}
