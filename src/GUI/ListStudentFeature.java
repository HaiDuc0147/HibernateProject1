package GUI;

import dao.StudentDao;
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

public class ListStudentFeature extends JDialog {
    private JPanel contentPane;
    private JButton informationButton;
    private JButton changePasswordButton;
    private JButton registerCourseButton;
    private JButton resultRegisterButton;
    private JButton logOutButton;
    private JLabel lineLabel;
    private JLabel lineLabel1;
    private JLabel KHTNLabel;
    private JLabel informationLabel;
    private JLabel showInformationLabel;
    private JLabel helloLabel;
    private JButton buttonOK;

    public ListStudentFeature() throws IOException, FontFormatException {
        setContentPane(contentPane);
        setModal(true);
        int width = 200;
        int height = 200;
        this.setTitle("Thông tin sinh viên");
        Font font = Font.createFont(Font.TRUETYPE_FONT, new File("fonts/SVN-Journey-Bold.otf")).deriveFont(100f);
        GraphicsEnvironment ge = GraphicsEnvironment.getLocalGraphicsEnvironment();
        //register the font
        ge.registerFont(font);
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

        ImageIcon resultRegisterIcon = new ImageIcon ("img/resultRegister1.png");
        resultRegisterIcon = Utils.transformImg(resultRegisterIcon, width, height);
        resultRegisterButton.setIcon(resultRegisterIcon);

        resultRegisterButton.setOpaque(false);
        resultRegisterButton.setContentAreaFilled(false);
        resultRegisterButton.setBorderPainted(false);

        ImageIcon lineIcon = new ImageIcon ("img/line1.png");
        lineIcon = Utils.transformImg(lineIcon, 1200, 20);
        lineLabel.setIcon(lineIcon);
        lineLabel1.setIcon(lineIcon);

        ImageIcon KHTNIcon = new ImageIcon ("img/KHTN2.png");
        KHTNIcon = Utils.transformImg(KHTNIcon, 200, 200);
        KHTNLabel.setIcon(KHTNIcon);
        informationLabel.setFont(font);

        ImageIcon showInformationIcon = new ImageIcon ("img/information.png");
        showInformationIcon = Utils.transformImg(showInformationIcon, width, height);
        showInformationLabel.setIcon(showInformationIcon);

        helloLabel.setFont(font);
        helloLabel.setText("Xin chào " + StudentDao.getAStudent(LoginFormStudent.usernameGlobal).getStudentName() + "!");

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
                informationLabel.setText("Thay đổi mật khẩu");
            }

            @Override
            public void mouseExited(MouseEvent e) {
                changePasswordButton.setBorderPainted(false);
                informationLabel.setText("");
            }
        });
        registerCourseButton.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseEntered(MouseEvent e) {
                Border border = BorderFactory.createLineBorder(Color.BLACK);
                registerCourseButton.setBorder(border);
                registerCourseButton.setBorderPainted(true);
                informationLabel.setText("Đăng kí học phần");
            }

            @Override
            public void mouseExited(MouseEvent e) {
                registerCourseButton.setBorderPainted(false);
                informationLabel.setText("");
            }
        });
        logOutButton.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseEntered(MouseEvent e) {
                Border border = BorderFactory.createLineBorder(Color.BLACK);
                logOutButton.setBorder(border);
                logOutButton.setBorderPainted(true);
                informationLabel.setText("Đăng xuất");
            }

            @Override
            public void mouseExited(MouseEvent e) {
                logOutButton.setBorderPainted(false);
                informationLabel.setText("");
            }
        });
        registerCourseButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                CourseRegisterForm courseRegister = new CourseRegisterForm();
                courseRegister.setVisible(true);
            }
        });
        resultRegisterButton.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseEntered(MouseEvent e) {
                Border border = BorderFactory.createLineBorder(Color.BLACK);
                resultRegisterButton.setBorder(border);
                resultRegisterButton.setBorderPainted(true);
                informationLabel.setText("Kết quả đăng kí học phần");
            }

            @Override
            public void mouseExited(MouseEvent e) {
                resultRegisterButton.setBorderPainted(false);
                informationLabel.setText("");
            }
        });
        resultRegisterButton.addActionListener(new ActionListener() {
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

        logOutButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dispose();
            }
        });
    }

    public static void main(String[] args) throws IOException, FontFormatException {
        ListStudentFeature dialog = new ListStudentFeature();
        dialog.pack();
        dialog.setVisible(true);
        System.exit(0);
    }
}
