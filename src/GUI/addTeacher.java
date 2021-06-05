package GUI;

import dao.LoginDao;
import dao.TeacherDao;
import hibernate.Login;
import hibernate.Teacher;
import utils.Utils;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class addTeacher extends JDialog {
    private JPanel contentPane;
    private JTextField idField;
    private JButton addButton;
    private JTextField nameField;
    private JTextField citizenIdField;
    private JTextField addressField;
    private JTextField phoneNumberField;

    public addTeacher() {
        setContentPane(contentPane);
        setModal(true);
        this.setTitle("Thông tin giáo vụ");
        this.setSize(500, 400);
        addButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Teacher teacher = new Teacher();
                teacher.setTeacherId(idField.getText());
                teacher.setTeacherName(nameField.getText());
                teacher.setAddress(addressField.getText());
                teacher.setCitizenId(citizenIdField.getText());
                teacher.setPhoneNumber(phoneNumberField.getText());
                TeacherDao.insertATeacher(teacher);
                Login login = new Login();
                String username = Utils.formatNameToUsername(nameField.getText());
                login.setUsername(username);
                login.setPassword(username);
                login.setRole(true);
                LoginDao.insertALogin(login);
                dispose();
            }
        });
    }

    public static void main(String[] args) {
        addTeacher dialog = new addTeacher();
        dialog.pack();
        dialog.setVisible(true);
        System.exit(0);
    }
}
