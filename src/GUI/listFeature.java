package GUI;

import utils.Utils;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;

public class listFeature extends JDialog {
    private JPanel contentPane;
    private JButton addStudentButton;
    private JButton addSubjectButton;
    private JButton showSubjectButton;
    private JLabel informationLabel;
    private JButton changeInformationButton;
    private JButton classInformationButton;
    private JButton logOutButton;
    private JButton buttonOK;

    public listFeature() throws IOException, FontFormatException {
        Font font = Font.createFont(Font.TRUETYPE_FONT, new File("fonts/SVN-Journey-Bold.otf")).deriveFont(36f);
        GraphicsEnvironment ge = GraphicsEnvironment.getLocalGraphicsEnvironment();
        //register the font
        ge.registerFont(font);

        this.setTitle("Quản lí sinh viên");
        this.setSize(800, 900);
        this.setLocationRelativeTo(null);
        informationLabel.setFont(font);
        int width = 200;
        int height = 200;

        setContentPane(contentPane);
        setModal(true);

        //Image img = ImageIO.read(new FileInputStream("img/addStudent1.ico"));

        ImageIcon addStudentIcon = new ImageIcon ("img/addStudent2.png");
        addStudentIcon = Utils.transformImg(addStudentIcon, width, height);
        addStudentButton.setIcon(addStudentIcon);

        addStudentButton.setOpaque(false);
        addStudentButton.setContentAreaFilled(false);
        addStudentButton.setBorderPainted(false);

        ImageIcon addSubjectIcon = new ImageIcon ("img/addSubject.png");
        addSubjectIcon = Utils.transformImg(addSubjectIcon, width, height);
        addSubjectButton.setIcon(addSubjectIcon);

        addSubjectButton.setOpaque(false);
        addSubjectButton.setContentAreaFilled(false);
        addSubjectButton.setBorderPainted(false);

        ImageIcon showSubjectIcon = new ImageIcon ("img/Subjects.png");
        showSubjectIcon = Utils.transformImg(showSubjectIcon, width, height);
        showSubjectButton.setIcon(showSubjectIcon);

        showSubjectButton.setOpaque(false);
        showSubjectButton.setContentAreaFilled(false);
        showSubjectButton.setBorderPainted(false);

        ImageIcon changeInformationIcon = new ImageIcon ("img/changeInformation.png");
        changeInformationIcon = Utils.transformImg(changeInformationIcon, width, height);
        changeInformationButton.setIcon(changeInformationIcon);

        changeInformationButton.setOpaque(false);
        changeInformationButton.setContentAreaFilled(false);
        changeInformationButton.setBorderPainted(false);

        ImageIcon classInformationIcon = new ImageIcon ("img/classInformation5.png");
        classInformationIcon = Utils.transformImg(classInformationIcon, width, height);
        classInformationButton.setIcon(classInformationIcon);

        classInformationButton.setOpaque(false);
        classInformationButton.setContentAreaFilled(false);
        classInformationButton.setBorderPainted(false);

        ImageIcon logOutIcon = new ImageIcon ("img/logout.png");
        logOutIcon = Utils.transformImg(logOutIcon, width, height);
        logOutButton.setIcon(logOutIcon);

        logOutButton.setOpaque(false);
        logOutButton.setContentAreaFilled(false);
        logOutButton.setBorderPainted(false);



        addStudentButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                AddStudent addStudent = new AddStudent();
                addStudent.setVisible(true);
            }
        });
        addSubjectButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                AddSubject addSubject = new AddSubject();
                addSubject.setVisible(true);
            }
        });
        showSubjectButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                showSubject subjectList = new showSubject();
                subjectList.setVisible(true);
            }
        });

        addStudentButton.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseEntered(MouseEvent e) {
                addStudentButton.setBorderPainted(true);
                informationLabel.setText("Thêm sinh viên");
            }
        });
        addStudentButton.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseExited(MouseEvent e) {
                addStudentButton.setBorderPainted(false);
                informationLabel.setText("");
            }
        });
        addStudentButton.addMouseListener(new MouseAdapter() {
        });
        showSubjectButton.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseEntered(MouseEvent e) {
                showSubjectButton.setBorderPainted(true);
                informationLabel.setText("Danh sách môn học");
            }

            @Override
            public void mouseExited(MouseEvent e) {
                showSubjectButton.setBorderPainted(false);
                informationLabel.setText("");
            }
        });
        addSubjectButton.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseEntered(MouseEvent e) {
                //addSubjectButton.setBounds(10,40,50,20);

                addSubjectButton.setBorderPainted(true);

                informationLabel.setText("Thêm môn học");
            }

            @Override
            public void mouseExited(MouseEvent e) {
                addSubjectButton.setBorderPainted(true);
                informationLabel.setText("");
            }
        });
        changeInformationButton.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseEntered(MouseEvent e) {
                changeInformationButton.setBorderPainted(true);
                informationLabel.setText("Thay đổi mật khẩu");
            }

            @Override
            public void mouseExited(MouseEvent e) {
                changeInformationButton.setBorderPainted(false);
                informationLabel.setText("");
            }
        });
        classInformationButton.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseEntered(MouseEvent e) {
                classInformationButton.setBorderPainted(true);
                informationLabel.setText("Thông tin lớp học");
            }

            @Override
            public void mouseExited(MouseEvent e) {
                classInformationButton.setBorderPainted(false);
                informationLabel.setText("");
            }
        });
        changeInformationButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                ChangePassword changePassword = new ChangePassword();
                changePassword.setVisible(true);
            }
        });
        logOutButton.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseEntered(MouseEvent e) {
                logOutButton.setBorderPainted(true);
                informationLabel.setText("Đăng xuất");
            }

            @Override
            public void mouseExited(MouseEvent e) {
                logOutButton.setBorderPainted(false);
                informationLabel.setText("");
            }
        });
        logOutButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dispose();
            }
        });
    }

    public static void main(String[] args) throws IOException, UnsupportedLookAndFeelException, ClassNotFoundException, InstantiationException, IllegalAccessException, FontFormatException {
        listFeature dialog = new listFeature();

        dialog.pack();
        dialog.setLocationRelativeTo(null);
        dialog.setVisible(true);
        System.exit(0);

    }
}
