package GUI;

import dao.CourseDao;
import dao.CourseOpenDao;
import dao.SemesterDao;
import hibernate.Course;
import hibernate.CourseOpen;
import hibernate.Semester;

import javax.swing.*;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;

public class showCourses extends JDialog {
    private JPanel contentPane;
    private JButton addCourseSessionButton;
    private JButton addCourseButton;
    private JTable courseTable;
    private JLabel semesterInformation;
    private JButton buttonOK;

    public showCourses() {
        this.setTitle("Danh sách học phần");
        this.setSize(500, 700);
        semesterInformation.setText(ShowSemesters.chosenSemesterGlobal.getSemesterName());
        setContentPane(contentPane);
        setModal(true);
        List<CourseOpen> courseOpen = CourseOpenDao.getAllCourseOpen();
        List<CourseOpen> courseOpens = new ArrayList<>();
        for (CourseOpen c : courseOpen) {
            if (c.getId().getId() == ShowSemesters.chosenSemesterGlobal.getId())
                courseOpens.add(c);
        }
        int size = courseOpens.size();
        System.out.println(size);
        String[] columnNames = {"Mã Học Phần", "Tên Học Phần", "Ngày bắt đầu", "Ngày kết thúc"};
        String[][] data = new String[size][4];
        DefaultTableModel model = (DefaultTableModel) courseTable.getModel();
        for (int i = 0; i < columnNames.length; i++)
            model.addColumn(columnNames[i]);
        if (size > 0) {

            for (int i = 0; i < size; i++) {
                data[i][0] = courseOpens.get(i).getCourseId().getSubjectId();
                data[i][1] = courseOpens.get(i).getCourseId().getSubjectName();
                data[i][2] = String.valueOf(courseOpens.get(i).getStartDay());
                data[i][3] = String.valueOf(courseOpens.get(i).getEndDay());
            }
            for (int i = 0; i < size; i++) {
                model.addRow(data[i]);
            }
            DefaultTableCellRenderer centerRenderer = new DefaultTableCellRenderer();
            centerRenderer.setHorizontalAlignment(JLabel.CENTER);
            for (int x = 0; x < columnNames.length; x++) {
                courseTable.getColumnModel().getColumn(x).setCellRenderer(centerRenderer);
            }
        }

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
