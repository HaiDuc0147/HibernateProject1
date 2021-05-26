package GUI;

import utils.HibernateUtil;
import utils.Utils;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;

public class listFeature extends JDialog {
    private JPanel contentPane;
    private JButton addStudentButton;
    private JButton addSubjectButton;
    private JButton showSubjectButton;
    private JLabel informationLabel;
    private JButton buttonOK;

    public listFeature() throws IOException {
        this.setTitle("Quản lí sinh viên");

        informationLabel.setPreferredSize(new Dimension(100, 20));
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




        addStudentButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                AddStudent addStudent = new AddStudent();
                //addStudent.setSize(400, 400);
                addStudent.setVisible(true);
            }
        });
        addSubjectButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                AddSubject addSubject = new AddSubject();
                //addSubject.setSize(400, 400);
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
                addSubjectButton.setBorderPainted(true);
                informationLabel.setText("Thêm môn học");
            }

            @Override
            public void mouseExited(MouseEvent e) {
                addSubjectButton.setBorderPainted(true);
                informationLabel.setText("");
            }
        });
    }

    public static void main(String[] args) throws IOException, UnsupportedLookAndFeelException, ClassNotFoundException, InstantiationException, IllegalAccessException {
        listFeature dialog = new listFeature();

        dialog.pack();
        dialog.setLocationRelativeTo(null);
        dialog.setBackground(Color.blue);
        dialog.setVisible(true);
        System.exit(0);

    }
}
