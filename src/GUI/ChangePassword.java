package GUI;

import dao.LoginDao;
import hibernate.Login;

import javax.swing.*;
import javax.swing.text.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ChangePassword extends JDialog {
    private JPanel contentPane;
    private JPasswordField oldPasswordField;
    private JPasswordField newPasswordField;
    private JPasswordField againPasswordField;
    private JButton changeButton;
    private JButton buttonOK;

    public ChangePassword() {
        this.setSize(400, 200);
        this.setTitle("Thay đổi mật khẩu");
        String username = LoginForm.usernameGlobal;
        String password = LoginForm.passwordGlobal;
        setContentPane(contentPane);
        setModal(true);
        this.setLocationRelativeTo(null);
        changeButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Login l = new Login();
                String newPassword = newPasswordField.getText();
                String againPassword = againPasswordField.getText();
                if(!oldPasswordField.getText().equals(password))
                    JOptionPane.showMessageDialog(null, "Sai mật khẩu cũ");
                else if(!newPassword.equals(againPassword))
                    JOptionPane.showMessageDialog(null, "Mật khẩu nhập lại không khớp với mật khẩu mới");
                else {
                    l.setPassword(newPassword);
                    l.setUsername(username);
                    l.setRole(true);
                    LoginDao.updateAccount(l);
                    JOptionPane.showMessageDialog(null, "Đổi mật khẩu thành công");
                    LoginForm.passwordGlobal = newPassword;
                    dispose();
                }


            }
        });
    }

    public static void main(String[] args) {
        ChangePassword dialog = new ChangePassword();
        dialog.pack();
        dialog.setVisible(true);
        System.exit(0);
    }
}
