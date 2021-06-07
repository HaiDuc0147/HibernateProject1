package GUI;

import dao.TeacherDao;
import hibernate.Teacher;
import utils.Utils;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

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
                JOptionPane.showMessageDialog(null, "Cập nhập thành công!");
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
