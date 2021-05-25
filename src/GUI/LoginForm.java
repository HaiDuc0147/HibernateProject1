package GUI;

import dao.LoginDao;
import hibernate.Login;

import javax.swing.*;
import java.awt.event.*;

public class LoginForm extends JDialog {
    private JPanel contentPane;
    private JLabel loginLabel;
    private JLabel usernameLabel;
    private JLabel passwordLabel;
    private JTextField usernameField;
    private JPasswordField passwordField;
    private JButton loginButton;
    private JCheckBox isTeacher;
    private JCheckBox isStudent;


    public LoginForm() {
        setContentPane(contentPane);
        setModal(true);
        //getRootPane().setDefaultButton(buttonOK);


        // call onCancel() when cross is clicked


        // call onCancel() on ESCAPE
        loginButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String username = usernameField.getText();
                String password = passwordField.getText();
                Login l = new Login();
                l.setUsername(username);
                l.setPassword(password);
                l.setRole(isTeacher.isSelected());
                //System.out.println(isTeacher.getText());
                //LoginDao.insertALogin(l);
                String message = "";
                if(LoginDao.checkAccountExisted(l))
                    message = "Đăng nhập thành công!";
                else
                    message = "Đăng nhập thất bại, vui lòng kiểm tra lại username và password!";
                JOptionPane.showMessageDialog(null, message);
            }
        });

        isTeacher.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                isStudent.setSelected(false);
            }
        });

        isStudent.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                isTeacher.setSelected(false);
            }
        });
    }

    public static void main(String[] args) {
        LoginForm dialog = new LoginForm();
        dialog.pack();
        dialog.setVisible(true);
        System.exit(0);
    }
}
