package GUI;

import dao.LoginDao;
import hibernate.Login;
import hibernate.Student;
import utils.Utils;

import javax.swing.*;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;

public class ShowStudentsInClass extends JDialog {
    private JPanel contentPane;
    private JTable studentTable;
    private JButton showCourseButton;
    private JButton personalInformationButton;
    private JButton resetPasswordButton;
    private JTextField searchField;
    private JButton findButton;
    public static String studentIdGlobal;
    public ShowStudentsInClass() {
        this.setTitle("Danh sách sinh viên");
        setContentPane(contentPane);

        ImageIcon showCourseRegisterIcon = new ImageIcon ("img/showCourseRegister.png");
        showCourseRegisterIcon = Utils.transformImg(showCourseRegisterIcon, 15, 15);
        showCourseButton.setIcon(showCourseRegisterIcon);

        ImageIcon studentLookUpIcon = new ImageIcon ("img/studentLookUp.png");
        studentLookUpIcon = Utils.transformImg(studentLookUpIcon, 15, 15);
        personalInformationButton.setIcon(studentLookUpIcon);

        ImageIcon changePassword = new ImageIcon ("img/changePassword.png");
        changePassword = Utils.transformImg(changePassword, 15, 15);
        resetPasswordButton.setIcon(changePassword);
        setModal(true);
        this.setSize(500, 600);
        List<Student> students = Utils.getAllStudentInClass(showClasses.classIdGlobal);
        int size = students.size();
        DefaultTableModel model = (DefaultTableModel)studentTable.getModel();
        String[] columnNames = {"Mã Sinh viên", "Tên sinh viên", "Giới tính"};
        for(int i = 0; i < columnNames.length; i++)
            model.addColumn(columnNames[i]);
        String[][] data = new String[size][3];
        for(int i = 0; i < size; i++){
            data[i][0] = students.get(i).getStudentId();
            data[i][1] = students.get(i).getStudentName();
            if(students.get(i).getGender()){
                data[i][2] = "Nam";
            }
            else
                data[i][2] = "Nữ";
        }
        for(int i = 0; i < size; i++){
            model.addRow(data[i]);
        }
        DefaultTableCellRenderer centerRenderer = new DefaultTableCellRenderer();
        centerRenderer.setHorizontalAlignment(JLabel.CENTER);
        for (int x = 0; x < columnNames.length; x++) {
            studentTable.getColumnModel().getColumn(x).setCellRenderer(centerRenderer);
        }
        studentTable.setModel(model);
        /**
         * additional code.
         **/
        model.fireTableDataChanged();
        showCourseButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int row = studentTable.getSelectedRow();
                studentIdGlobal = (String)studentTable.getValueAt(row, 0);
                ShowStudentCourse showStudentCourse = new ShowStudentCourse();
                showStudentCourse.setLocationRelativeTo(null);
                showStudentCourse.setVisible(true);
            }
        });
        personalInformationButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int row = studentTable.getSelectedRow();
                LoginFormStudent.usernameGlobal = (String)studentTable.getValueAt(row, 0);
                ShowStudentInformation showStudentInformation = new ShowStudentInformation();
                showStudentInformation.setLocationRelativeTo(null);
                showStudentInformation.setVisible(true);
            }
        });
        resetPasswordButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Login l = new Login();
                int row = studentTable.getSelectedRow();
                l.setPassword((String)studentTable.getValueAt(row, 0));
                l.setUsername((String)studentTable.getValueAt(row, 0));
                l.setRole(false);
                LoginDao.updateAccount(l);
                JOptionPane.showMessageDialog(null, "Password được reset mặc định là MSSV!");
                dispose();
            }
        });
        findButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String keyword = searchField.getText();
                List<Student> studentsByKeyword = new ArrayList<>();
                for(Student t: students){
                    if(t.getStudentId().contains(keyword) || t.getStudentName().contains(keyword))
                        studentsByKeyword.add(t);
                }
                while (model.getRowCount() > 0) {
                    model.removeRow(0);
                }
                int size1 = studentsByKeyword.size();
                String[][] data1 = new String[size1][3];
                for(int i = 0; i < size1; i++){
                    data1[i][0] = studentsByKeyword.get(i).getStudentId();
                    data1[i][1] = studentsByKeyword.get(i).getStudentName();
                    if(studentsByKeyword.get(i).getGender()){
                        data1[i][2] = "Nam";
                    }
                    else
                        data1[i][2] = "Nữ";
                }
                for(int i = 0; i < size1; i++){
                    model.addRow(data1[i]);
                }
            }
        });
    }

    public static void main(String[] args) {
        ShowStudentsInClass dialog = new ShowStudentsInClass();
        dialog.pack();
        dialog.setVisible(true);
        System.exit(0);
    }
}
