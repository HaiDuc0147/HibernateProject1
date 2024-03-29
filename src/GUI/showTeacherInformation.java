package GUI;

import dao.LoginDao;
import dao.TeacherDao;
import hibernate.Login;
import hibernate.Teacher;
import utils.Utils;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

public class showTeacherInformation extends JDialog {
    private JPanel contentPane;
    private JTextField teacherIdField;
    private JTextField nameField;
    private JTextField citizenIdField;
    private JTextField addressField;
    private JTextField phoneNumberField;
    private JLabel teacherIdLabel;
    private JButton updateButton;
    private JButton buttonOK;

    public showTeacherInformation() {
        this.setSize(500, 300);
        this.setTitle("Thông tin cá nhân");
        setContentPane(contentPane);
        setModal(true);
        ImageIcon updateIcon = new ImageIcon ("img/update.png");
        updateIcon = Utils.transformImg(updateIcon, 15, 15);
        updateButton.setIcon(updateIcon);
        Teacher tc = TeacherDao.getATeacher(LoginForm.usernameGlobal);
        teacherIdLabel.setText(tc.getTeacherId());
        nameField.setText(tc.getTeacherName());
        citizenIdField.setText(tc.getCitizenId());
        addressField.setText(tc.getAddress());
        phoneNumberField.setText(tc.getPhoneNumber());
        updateButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                tc.setTeacherName(nameField.getText());
                tc.setAddress(addressField.getText());
                tc.setPhoneNumber(phoneNumberField.getText());
                tc.setCitizenId(citizenIdField.getText());
                TeacherDao.updateATeacher(tc);
                String username = Utils.formatNameToUsername(nameField.getText());
                List<Login> logins = LoginDao.getAllAccounts();
                Login login = LoginDao.getAnAccount(LoginForm.usernameGlobal);
                int count = 0;
                for(Login lo: logins){
                    if(lo.getUsername().contains(username))
                        count++;
                }
                if(count > 0)
                    username += String.valueOf(count);
                login.setUsername(username);
                LoginDao.updateAnAccount(login);
                System.out.println(login.getId());
                JOptionPane.showMessageDialog(null, "Cập nhập thành công!\nTên đăng nhập được cập nhập thành " + username);
            }
        });
    }

    public static void main(String[] args) {
        showTeacherInformation dialog = new showTeacherInformation();
        dialog.pack();
        dialog.setVisible(true);
        System.exit(0);
    }
}
