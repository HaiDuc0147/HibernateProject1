package GUI;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class showCourses extends JDialog {
    private JPanel contentPane;
    private JButton addCourseSessionButton;
    private JButton addCourseButton;
    private JTable courseTable;
    private JButton buttonOK;

    public showCourses() {
        setContentPane(contentPane);
        setModal(true);
        getRootPane().setDefaultButton(buttonOK);
        addCourseSessionButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                AddCourseSession addCourseSession = new AddCourseSession();
                addCourseSession.setVisible(true);
            }
        });
        addCourseButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                AddCourse addCourse = new AddCourse();
                addCourse.setVisible(true);
            }
        });
    }

    public static void main(String[] args) {
        showCourses dialog = new showCourses();
        dialog.pack();
        dialog.setVisible(true);
        System.exit(0);
    }
}
