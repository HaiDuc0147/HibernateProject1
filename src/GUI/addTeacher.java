package GUI;

import dao.LoginDao;
import dao.TeacherDao;
import hibernate.Login;
import hibernate.Teacher;
import utils.Utils;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class addTeacher extends JDialog {
    private JPanel contentPane;
    private JTextField idField;
    private JButton addButton;
    private JTextField nameField;
    private JTextField citizenIdField;
    private JTextField addressField;
    private JTextField phoneNumberField;
    private JLabel idLabel;

    public addTeacher() {
        setContentPane(contentPane);
        setModal(true);
        this.setTitle("Thông tin giáo vụ");
        this.setSize(500, 400);
        List<Teacher> teachers = TeacherDao.getAllTeacher();
        List<Integer> listId = new ArrayList<>();
        int size = teachers.size();
        for(int i = 0; i < size; i++){
            listId.add(Integer.parseInt(teachers.get(i).getTeacherId()));
        }
        int max = Collections.max(listId) + 1;
        int moreZeros = 4 - String.valueOf(max).length();
        String id = "";
        for(int i = 0; i < moreZeros; i++)
            id += "0";
        id += String.valueOf(max);
        idLabel.setText(id);
        addButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Teacher teacher = new Teacher();
                teacher.setTeacherId(idLabel.getText());
                teacher.setTeacherName(nameField.getText());
                teacher.setAddress(addressField.getText());
                teacher.setCitizenId(citizenIdField.getText());
                teacher.setPhoneNumber(phoneNumberField.getText());
                TeacherDao.insertATeacher(teacher);
                String username = Utils.formatNameToUsername(nameField.getText());
                List<Login> logins = LoginDao.getAllAccounts();
                int count = 0;
                for(Login lo: logins){
                    if(lo.getUsername().contains(username))
                        count++;
                }
                Login login = new Login();
                if(count > 0)
                    username += String.valueOf(count);
                login.setUsername(username);
                login.setPassword(username);
                login.setRole(true);
                LoginDao.insertALogin(login);
                JOptionPane.showMessageDialog(null, "Tạo giáo vụ thành công\nTên đăng nhập của giáo vụ là " + username);
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
