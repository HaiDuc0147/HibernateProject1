package GUI;

import dao.LoginDao;
import hibernate.Login;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.io.IOException;

public class LoginForm extends JDialog {
    private JPanel contentPane;
    private JLabel loginLabel;
    private JLabel usernameLabel;
    private JLabel passwordLabel;
    private JTextField usernameField;
    private JPasswordField passwordField;
    private JButton loginButton;
    public static String usernameGlobal;
    public static String passwordGlobal;

    public LoginForm() {
        this.setSize(300, 200);
        this.setTitle("Đăng nhập");
        setContentPane(contentPane);
        setModal(true);
        //getRootPane().setDefaultButton(buttonOK);
        this.setLocationRelativeTo(null);
        this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        loginButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String username = usernameField.getText();
                String password = passwordField.getText();
                usernameGlobal = username;
                passwordGlobal = password;
                Login l = new Login();
                l.setUsername(username);
                l.setPassword(password);
                l.setRole(true);
                //System.out.println(isTeacher.getText());
                //LoginDao.insertALogin(l);
                String message = "";
                Boolean isSuccessLogin = false;
                if(LoginDao.checkAccountExisted(l)) {
                    message = "Đăng nhập thành công!";
                    isSuccessLogin = true;
                }
                else
                    message = "Đăng nhập thất bại, vui lòng kiểm tra lại username và password!";
                JOptionPane.showMessageDialog(null, message);
                if(isSuccessLogin){
                    try {
                        listFeature features = new listFeature();
                        features.setSize(1000, 900);
                        features.setLocationRelativeTo(null);
                        features.setVisible(true);
                        dispose();
                    } catch (IOException | FontFormatException ioException) {
                        ioException.printStackTrace();
                    }
                }
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
