package GUI;

import dao.LoginDao;
import dao.StudentDao;
import hibernate.Login;
import hibernate.Student;

import javax.swing.*;
import java.awt.event.*;

public class AddStudent extends JDialog {
    private JPanel contentPane;
    private JTextField studentIdField;
    private JTextField nameStudentField;
    private JTextField classIdField;
    private JCheckBox isMaleCheckBox;
    private JCheckBox isFemaleCheckBox;
    private JButton thêmButton;
    private JButton buttonOK;
    private JButton buttonCancel;

    public AddStudent() {
        setContentPane(contentPane);
        setModal(true);


        // call onCancel() when cross is clicked



        // call onCancel() on ESCAPE

        isMaleCheckBox.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                isFemaleCheckBox.setSelected(false);
            }
        });
        isFemaleCheckBox.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                isMaleCheckBox.setSelected(false);
            }
        });
        thêmButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Student s = new Student();
                s.setStudentId(studentIdField.getText());
                s.setStudentName(nameStudentField.getText());
                s.setClassId(classIdField.getText());
                s.setGender(isMaleCheckBox.isSelected());
                s.setCredits(0);
                Login l = new Login();
                l.setUsername(studentIdField.getText());
                l.setPassword(studentIdField.getText());
                l.setRole(false);
                StudentDao.addStudent(s);
                LoginDao.insertALogin(l);
            }
        });
    }



    public static void main(String[] args) {
        AddStudent dialog = new AddStudent();
        dialog.pack();
        dialog.setVisible(true);
        System.exit(0);
    }
}
