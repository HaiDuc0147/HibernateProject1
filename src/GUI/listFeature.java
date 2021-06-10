package GUI;

import dao.TeacherDao;
import hibernate.Teacher;
import utils.Utils;

import javax.swing.*;
import javax.swing.border.Border;
import java.awt.*;
import java.awt.event.*;
import java.io.File;
import java.io.IOException;
import java.util.List;

public class listFeature extends JDialog {
    private JPanel contentPane;
    private JButton addStudentButton;
    private JButton addSubjectButton;
    private JButton showSubjectButton;
    private JLabel informationLabel;
    private JButton changeInformationButton;
    private JButton classInformationButton;
    private JButton logOutButton;
    private JButton addTeacherButton;
    private JButton addCourseButton;
    private JButton seeInformationButton;
    private JButton showSemesterButton;
    private JLabel greetingLabel;
    private JLabel lineLabel;
    private JLabel helloLabel;
    private JPanel headerPanel;
    private JButton buttonOK;

    public listFeature() throws IOException, FontFormatException {
        Font font = Font.createFont(Font.TRUETYPE_FONT, new File("fonts/SVN-Journey-Bold.otf")).deriveFont(72f);
        GraphicsEnvironment ge = GraphicsEnvironment.getLocalGraphicsEnvironment();
        //register the font
        ge.registerFont(font);



        setContentPane(contentPane);
        setModal(true);
        this.setTitle("Quản lí sinh viên");
        this.setSize(800, 900);
        //helloField.setFont(font);
        //helloField.setText("Xin chào " + LoginForm.usernameGlobal);
        this.setLocationRelativeTo(null);
        informationLabel.setFont(font);
        int width = 200;
        int height = 200;

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

        ImageIcon addTeacherIcon = new ImageIcon ("img/addTeacher2.png");
        addTeacherIcon = Utils.transformImg(addTeacherIcon, width, height);
        addTeacherButton.setIcon(addTeacherIcon);

        addTeacherButton.setOpaque(false);
        addTeacherButton.setContentAreaFilled(false);
        addTeacherButton.setBorderPainted(false);

        ImageIcon addCourseIcon = new ImageIcon ("img/courseList3.png");
        addCourseIcon = Utils.transformImg(addCourseIcon, width, height);
        addCourseButton.setIcon(addCourseIcon);

        addCourseButton.setOpaque(false);
        addCourseButton.setContentAreaFilled(false);
        addCourseButton.setBorderPainted(false);

        ImageIcon seeInformationIcon = new ImageIcon ("img/seeInformation3.png");
        seeInformationIcon = Utils.transformImg(seeInformationIcon, width, height);
        seeInformationButton.setIcon(seeInformationIcon);

        seeInformationButton.setOpaque(false);
        seeInformationButton.setContentAreaFilled(false);
        seeInformationButton.setBorderPainted(false);

        ImageIcon showSemesterIcon = new ImageIcon ("img/listSemester.png");
        showSemesterIcon = Utils.transformImg(showSemesterIcon, width, height);
        showSemesterButton.setIcon(showSemesterIcon);

        showSemesterButton.setOpaque(false);
        showSemesterButton.setContentAreaFilled(false);
        showSemesterButton.setBorderPainted(false);

        ImageIcon KHTNIcon = new ImageIcon ("img/KHTN2.png");
        KHTNIcon = Utils.transformImg(KHTNIcon, 200, 200);
        greetingLabel.setIcon(KHTNIcon);
        helloLabel.setFont(font);
        List<Teacher> teachers = TeacherDao.getAllTeacher();
        String name = new String();
        for(Teacher tc: teachers){
            if(Utils.formatNameToUsername(tc.getTeacherName()).equals(LoginForm.usernameGlobal)){
                name = tc.getTeacherName();
                break;
            }
        }
        helloLabel.setText("Xin chào " + name + "!");
        ImageIcon lineIcon = new ImageIcon ("img/line1.png");
        lineIcon = Utils.transformImg(lineIcon, 900, 20);
        lineLabel.setIcon(lineIcon);

        addStudentButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                AddStudent addStudent = new AddStudent();
                addStudent.setLocationRelativeTo(null);
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
                Border border = BorderFactory.createLineBorder(Color.BLACK);
                addStudentButton.setBorder(border);
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
        showSubjectButton.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseEntered(MouseEvent e) {
                Border border = BorderFactory.createLineBorder(Color.BLACK);
                showSubjectButton.setBorder(border);
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
                Border border = BorderFactory.createLineBorder(Color.BLACK);
                addSubjectButton.setBorder(border);
                addSubjectButton.setBorderPainted(true);

                informationLabel.setText("Thêm môn học");
            }

            @Override
            public void mouseExited(MouseEvent e) {
                addSubjectButton.setBorderPainted(false);
                informationLabel.setText("");
            }
        });
        changeInformationButton.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseEntered(MouseEvent e) {
                Border border = BorderFactory.createLineBorder(Color.BLACK);
                changeInformationButton.setBorder(border);
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
                Border border = BorderFactory.createLineBorder(Color.BLACK);
                classInformationButton.setBorder(border);
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
        logOutButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dispose();
            }
        });
        addTeacherButton.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseEntered(MouseEvent e) {
                Border border = BorderFactory.createLineBorder(Color.BLACK);
                addTeacherButton.setBorder(border);
                addTeacherButton.setBorderPainted(true);
                informationLabel.setText("Danh sách giáo vụ");
            }

            @Override
            public void mouseExited(MouseEvent e) {
                addTeacherButton.setBorderPainted(false);
                informationLabel.setText("");
            }
        });
        addCourseButton.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseEntered(MouseEvent e) {
                Border border = BorderFactory.createLineBorder(Color.BLACK);
                addCourseButton.setBorder(border);
                addCourseButton.setBorderPainted(true);
                informationLabel.setText("Danh sách học phần");
            }

            @Override
            public void mouseExited(MouseEvent e) {
                addCourseButton.setBorderPainted(false);
                informationLabel.setText("");
            }
        });
        addTeacherButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                showListTeacher showListTeacherForm = new showListTeacher();
                showListTeacherForm.setVisible(true);
            }
        });
        seeInformationButton.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseEntered(MouseEvent e) {
                Border border = BorderFactory.createLineBorder(Color.BLACK);
                seeInformationButton.setBorder(border);
                seeInformationButton.setBorderPainted(true);
                informationLabel.setText("Thông tin cá nhân");
            }

            @Override
            public void mouseExited(MouseEvent e) {
                seeInformationButton.setBorderPainted(false);
                informationLabel.setText("");
            }
        });
        showSemesterButton.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseEntered(MouseEvent e) {
                Border border = BorderFactory.createLineBorder(Color.BLACK);
                showSemesterButton.setBorder(border);
                showSemesterButton.setBorderPainted(true);
                informationLabel.setText("Danh sách học kỳ");
            }

            @Override
            public void mouseExited(MouseEvent e) {
                showSemesterButton.setBorderPainted(false);
                informationLabel.setText("");
            }
        });
        classInformationButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                showClasses showClassesForm = new showClasses();
                showClassesForm.setVisible(true);
            }
        });
        showSemesterButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                ShowSemesters showSemesters = new ShowSemesters();
                showSemesters.setLocationRelativeTo(null);
                showSemesters.setVisible(true);
            }
        });
        addCourseButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if(ShowSemesters.chosenSemesterGlobal == null){
                    JOptionPane.showMessageDialog(null, "Bạn chưa chọn kì hiện tại!");
                }
                else {
                    showCourses showCoursesForm = new showCourses();
                    //showCoursesForm.setSize(300, 300);
                    showCoursesForm.setVisible(true);
                }
            }
        });

        seeInformationButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                showTeacherInformation showTeacherInformationForm = new showTeacherInformation();
                showTeacherInformationForm.setLocationRelativeTo(null);
                showTeacherInformationForm.setVisible(true);
            }
        });
    }

    public static void main(String[] args) throws IOException, UnsupportedLookAndFeelException, ClassNotFoundException, InstantiationException, IllegalAccessException, FontFormatException {
        try {
            UIManager.setLookAndFeel("com.sun.java.swing.plaf.nimbus.CrossPlatformLookAndFeel");
        } catch(Exception ignored){}
        listFeature dialog = new listFeature();

        dialog.pack();
        dialog.setLocationRelativeTo(null);
        dialog.setVisible(true);
        System.exit(0);

    }
}
