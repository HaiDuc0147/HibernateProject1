package GUI;

import utils.Utils;

import javax.swing.*;
import javax.swing.border.Border;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.File;
import java.io.IOException;

public class ListFeatureStudent extends JDialog {
    private JPanel contentPane;
    private JButton informationButton;
    private JButton changePasswordButton;
    private JButton registerCourseButton;
    private JLabel informationLabel;
    private JLabel changePasswordLabel;
    private JLabel registerCourseLabel;
    private JButton logOutButton;
    private JLabel logOutLabel;
    private JButton button1;

    public ListFeatureStudent() throws IOException, FontFormatException {
        setContentPane(contentPane);
        setModal(true);
        Font font = Font.createFont(Font.TRUETYPE_FONT, new File("fonts/SVN-Journey-Bold.otf")).deriveFont(32f);
        GraphicsEnvironment ge = GraphicsEnvironment.getLocalGraphicsEnvironment();
        //register the font
        ge.registerFont(font);

        this.setTitle("Sinh viên");
        this.setSize(1200, 400);
        //helloField.setFont(font);
        //helloField.setText("Xin chào " + LoginForm.usernameGlobal);
        this.setLocationRelativeTo(null);
        informationLabel.setSize(30, 30);
        informationLabel.setFont(font);
        changePasswordLabel.setFont(font);
        registerCourseLabel.setFont(font);
        logOutLabel.setFont(font);
        int width = 200;
        int height = 200;
        ImageIcon seeInformationIcon = new ImageIcon ("img/seeInformation3.png");
        seeInformationIcon = Utils.transformImg(seeInformationIcon, width, height);
        informationButton.setIcon(seeInformationIcon);

        informationButton.setOpaque(false);
        informationButton.setContentAreaFilled(false);
        informationButton.setBorderPainted(false);

        ImageIcon changeInformationIcon = new ImageIcon ("img/changeInformation.png");
        changeInformationIcon = Utils.transformImg(changeInformationIcon, width, height);
        changePasswordButton.setIcon(changeInformationIcon);

        changePasswordButton.setOpaque(false);
        changePasswordButton.setContentAreaFilled(false);
        changePasswordButton.setBorderPainted(false);

        ImageIcon courseRegisterIcon = new ImageIcon ("img/courseRegister5.png");
        courseRegisterIcon = Utils.transformImg(courseRegisterIcon, width, height);
        registerCourseButton.setIcon(courseRegisterIcon);

        registerCourseButton.setBorderPainted(false);
        registerCourseButton.setContentAreaFilled(false);
        registerCourseButton.setOpaque(false);

        ImageIcon logOutIcon = new ImageIcon ("img/logout.png");
        logOutIcon = Utils.transformImg(logOutIcon, width, height);
        logOutButton.setIcon(logOutIcon);

        logOutButton.setOpaque(false);
        logOutButton.setContentAreaFilled(false);
        logOutButton.setBorderPainted(false);

        informationButton.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseEntered(MouseEvent e) {
                Border border = BorderFactory.createLineBorder(Color.BLACK);
                informationButton.setBorder(border);
                informationButton.setBorderPainted(true);
                informationLabel.setText("Thông tin cá nhân");
            }

            @Override
            public void mouseExited(MouseEvent e) {
                informationButton.setBorderPainted(false);
                informationLabel.setText("");
            }
        });
        changePasswordButton.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseEntered(MouseEvent e) {
                Border border = BorderFactory.createLineBorder(Color.BLACK);
                changePasswordButton.setBorder(border);
                changePasswordButton.setBorderPainted(true);
                changePasswordLabel.setText("Thay đổi mật khẩu");
            }

            @Override
            public void mouseExited(MouseEvent e) {
                changePasswordButton.setBorderPainted(false);
                changePasswordLabel.setText("");
            }
        });
        registerCourseButton.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseEntered(MouseEvent e) {
              //Border border = BorderFactory.createBevelBorder(1, Color.BLACK, Color.BLUE);
              //registerCourseButton.setBorder(border);
                registerCourseButton.setBorderPainted(true);
                registerCourseLabel.setText("");
            }

            @Override
            public void mouseExited(MouseEvent e) {
                registerCourseButton.setBorderPainted(false);
                registerCourseLabel.setText("");
            }
        });
        logOutButton.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseEntered(MouseEvent e) {
                Border border = BorderFactory.createLineBorder(Color.BLACK);
                logOutButton.setBorder(border);
                logOutButton.setBorderPainted(true);
                logOutLabel.setText("Đăng xuất");
            }

            @Override
            public void mouseExited(MouseEvent e) {
                logOutButton.setBorderPainted(false);
                logOutLabel.setText("");
            }
        });
        registerCourseButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                CourseRegisterForm courseRegister = new CourseRegisterForm();
                courseRegister.setVisible(true);
            }
        });
        button1.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                ShowCourseRegister showCourseRegister = new ShowCourseRegister();
                showCourseRegister.setVisible(true);
            }
        });
        informationButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                ShowStudentInformation showStudentInformation = new ShowStudentInformation();
                showStudentInformation.setLocationRelativeTo(null);
                showStudentInformation.setVisible(true);
            }
        });
        changePasswordButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                ChangePasswordStudent changePasswordStudent = new ChangePasswordStudent();
                changePasswordStudent.setLocationRelativeTo(null);
                changePasswordStudent.setVisible(true);
            }
        });
    }

    public static void main(String[] args) throws IOException, FontFormatException {
        ListFeatureStudent dialog = new ListFeatureStudent();

        //dialog.pack();
        dialog.setSize(1200, 400);
        dialog.setVisible(true);
        System.exit(0);
    }
}
