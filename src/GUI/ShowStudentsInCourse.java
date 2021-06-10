package GUI;

import hibernate.CourseRegister;

import javax.swing.*;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;

public class ShowStudentsInCourse extends JDialog {
    private JPanel contentPane;
    private JTable studentTable;
    private JTextField searchField;
    private JButton findButton;
    private JButton buttonOK;

    public ShowStudentsInCourse() {
        this.setSize(600, 700);
        this.setTitle("Danh sách sinh viên đăng kí học phần");
        setContentPane(contentPane);
        setModal(true);
        String[] columnNames = {"Mã sinh viên ", "Tên sinh viên", "Mã lớp", "Thời gian đăng kí học phần"};
        DefaultTableModel model = (DefaultTableModel) studentTable.getModel();
        for (String name : columnNames)
            model.addColumn(name);
        int size = showCourses.courseRegisters.size();
        String[][] data = new String[size][4];
        for (int i = 0; i < size; i++) {
            data[i][0] = showCourses.courseRegisters.get(i).getStudentId().getStudentId();
            data[i][1] = showCourses.courseRegisters.get(i).getStudentId().getStudentName();
            data[i][2] = showCourses.courseRegisters.get(i).getStudentId().getClassId().getClassId();
            data[i][3] = String.valueOf(showCourses.courseRegisters.get(i).getRegisterDay());
        }
        for (int i = 0; i < size; i++) {
            model.addRow(data[i]);
        }
        DefaultTableCellRenderer centerRenderer = new DefaultTableCellRenderer();
        centerRenderer.setHorizontalAlignment( JLabel.CENTER );
        for(int x=0;x<columnNames.length;x++){
            studentTable.getColumnModel().getColumn(x).setCellRenderer( centerRenderer );
        }

        findButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String keyword = searchField.getText();
                List<CourseRegister> courseRegistersByKeyword = new ArrayList<>();
                for(CourseRegister c: showCourses.courseRegisters){
                    if(c.getStudentId().getStudentId().contains(keyword) || c.getStudentId().getStudentName().contains(keyword))
                        courseRegistersByKeyword.add(c);
                }
                while (model.getRowCount() > 0) {
                    model.removeRow(0);
                }
                int size1 = courseRegistersByKeyword.size();
                String[][] data1 = new String[size1][4];
                for (int i = 0; i < size1; i++) {
                    data1[i][0] = courseRegistersByKeyword.get(i).getStudentId().getStudentId();
                    data1[i][1] = courseRegistersByKeyword.get(i).getStudentId().getStudentName();
                    data1[i][2] = courseRegistersByKeyword.get(i).getStudentId().getClassId().getClassId();
                    data1[i][3] = String.valueOf(courseRegistersByKeyword.get(i).getRegisterDay());
                }
                for (int i = 0; i < size1; i++) {
                    model.addRow(data1[i]);
                }
            }
        });
    }

    public static void main(String[] args) {
        ShowStudentsInCourse dialog = new ShowStudentsInCourse();
        dialog.pack();
        dialog.setVisible(true);
        System.exit(0);
    }
}
