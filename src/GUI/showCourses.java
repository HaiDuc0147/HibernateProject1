package GUI;

import dao.CourseDao;
import dao.CourseOpenDao;
import dao.SubjectDao;
import hibernate.Course;
import hibernate.CourseOpen;
import hibernate.Subject;
import utils.Utils;

import javax.swing.*;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Date;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;

public class showCourses extends JDialog {
    private JPanel contentPane;
    private JButton addCourseSessionButton;
    private JButton addCourseButton;
    private JTable courseTable;
    private JLabel semesterInformation;
    private JButton deleteButton;
    private JList sessionList;
    private JTextField searchField;
    private JButton findButton;
    private JTable courseSessionTable;
    private JButton buttonOK;

    public showCourses() {

        this.setTitle("Danh sách học phần");
        this.setSize(1500, 700);
        semesterInformation.setText(ShowSemesters.chosenSemesterGlobal.getSemesterName() +
                " - Năm học " + ShowSemesters.chosenSemesterGlobal.getYear());
        setContentPane(contentPane);
        setModal(true);

        ImageIcon searchIcon = new ImageIcon ("img/search.png");
        searchIcon = Utils.transformImg(searchIcon, 15, 15);
        findButton.setIcon(searchIcon);
        ImageIcon addIcon = new ImageIcon ("img/add.png");
        addIcon = Utils.transformImg(addIcon, 15, 15);
        addCourseButton.setIcon(addIcon);
        ImageIcon removeIcon = new ImageIcon ("img/remove.png");
        removeIcon = Utils.transformImg(removeIcon, 15, 15);
        deleteButton.setIcon(removeIcon);
        ImageIcon addIcon1 = new ImageIcon ("img/add1.png");
        addIcon1 = Utils.transformImg(addIcon1, 15, 15);
        addCourseSessionButton.setIcon(addIcon1);

        List<CourseOpen> courseOpen = CourseOpenDao.getAllCourseOpen();
        List<CourseOpen> courseOpens = new ArrayList<>();
        HashSet<String> courseSessions = new HashSet<>();
        DefaultListModel modelSession = new DefaultListModel();
        sessionList.setModel(modelSession);
        for (CourseOpen c : courseOpen) {
            if (c.getSemesterId().getId() == ShowSemesters.chosenSemesterGlobal.getId()) {
                courseSessions.add(String.valueOf(c.getStartDay()) + " đến " + String.valueOf(c.getEndDay()));
                courseOpens.add(c);
            }
        }
        for (String courseSession : courseSessions) {
            System.out.println(courseSession);
            modelSession.addElement(courseSession);
        }
        int size = courseOpens.size();
        System.out.println(size);
        String[] columnNames = {"Mã Học Phần", "Tên Học Phần", "Ngày học", "Ca học", "Phòng học", "Slot tối đa", "Giáo viên giảng dạy",
                "Ngày bắt đầu đăng kí", "Ngày kết thúc đăng kí"};
        String[][] data = new String[size][9];
        DefaultTableModel model = (DefaultTableModel) courseTable.getModel();
        for (int i = 0; i < columnNames.length; i++)
            model.addColumn(columnNames[i]);
        if (size > 0) {
            for (int i = 0; i < size; i++) {
                data[i][0] = courseOpens.get(i).getCourseId().getCourseId().getSubjectId();
                data[i][1] = courseOpens.get(i).getCourseId().getCourseId().getSubjectName();
                data[i][2] = courseOpens.get(i).getCourseId().getStudyDay();
                data[i][3] = courseOpens.get(i).getCourseId().getStudyTime();
                data[i][4] = courseOpens.get(i).getCourseId().getClassroom();
                data[i][5] = String.valueOf(courseOpens.get(i).getCourseId().getSlot());
                data[i][6] = courseOpens.get(i).getCourseId().getTeacherName();
                data[i][7] = String.valueOf(courseOpens.get(i).getStartDay());
                data[i][8] = String.valueOf(courseOpens.get(i).getEndDay());
            }
            for (int i = 0; i < size; i++) {
                model.addRow(data[i]);
            }
            DefaultTableCellRenderer centerRenderer = new DefaultTableCellRenderer();
            centerRenderer.setHorizontalAlignment(JLabel.CENTER);
            for (int x = 0; x < columnNames.length; x++) {
                courseTable.getColumnModel().getColumn(x).setCellRenderer(centerRenderer);
            }
            courseTable.setModel(model);
            /**
             * additional code.
             **/
            model.fireTableDataChanged();
        }

        addCourseSessionButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                AddCourseSession addCourseSession = new AddCourseSession();
                addCourseSession.setVisible(true);
                //courseTable.repaint();

            }
        });
        addCourseButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                AddCourse addCourse = new AddCourse();
                addCourse.setVisible(true);
                JOptionPane.showMessageDialog(null, "Thêm học phần thành công!");
                //addCourse.removeAll();
                //addCourse.validate();
                //addCourse.repaint();
                while (model.getRowCount() > 0) {
                    model.removeRow(0);
                }
                List<CourseOpen> courseOpen1 = CourseOpenDao.getAllCourseOpen();
                List<CourseOpen> courseOpens1 = new ArrayList<>();
                sessionList.setModel(modelSession);

                for (CourseOpen c : courseOpen1) {
                    if (c.getSemesterId().getId() == ShowSemesters.chosenSemesterGlobal.getId()) {
                        courseSessions.add(String.valueOf(c.getStartDay()) + " đến " + String.valueOf(c.getEndDay()));
                        courseOpens1.add(c);
                    }
                }
                System.out.println(modelSession.size());
                for (String courseSession : courseSessions) {
                    //System.out.println(courseSession);
                    boolean isDuplicate = false;
                    for (int i = 0; i < modelSession.size(); i++)
                        if (String.valueOf(modelSession.getElementAt(i)).equals(courseSession)) {
                            isDuplicate = true;
                            break;
                        }
                    if (!isDuplicate)
                        modelSession.addElement(courseSession);
                }
                int size1 = courseOpens1.size();
                String[][] data1 = new String[size1][9];
                System.out.println(size1);
                if (size1 > 0) {
                    for (int i = 0; i < size1; i++) {
                        data1[i][0] = courseOpens1.get(i).getCourseId().getCourseId().getSubjectId();
                        data1[i][1] = courseOpens1.get(i).getCourseId().getCourseId().getSubjectName();
                        data1[i][2] = courseOpens1.get(i).getCourseId().getStudyDay();
                        data1[i][3] = courseOpens1.get(i).getCourseId().getStudyTime();
                        data1[i][4] = courseOpens1.get(i).getCourseId().getClassroom();
                        data1[i][5] = String.valueOf(courseOpens1.get(i).getCourseId().getSlot());
                        data1[i][6] = courseOpens1.get(i).getCourseId().getTeacherName();
                        data1[i][7] = String.valueOf(courseOpens1.get(i).getStartDay());
                        data1[i][8] = String.valueOf(courseOpens1.get(i).getEndDay());
                    }
                    for (int i = 0; i < size1; i++) {
                        model.addRow(data1[i]);
                    }
                }
            }
        });
        deleteButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int getSelectedRowForDeletion = courseTable.getSelectedRow();
                //Check if their is a row selected
                if (getSelectedRowForDeletion >= 0) {
                    DefaultTableModel model = (DefaultTableModel) courseTable.getModel();
                    int row = courseTable.getSelectedRow();
                    int dialogButton = JOptionPane.YES_NO_OPTION;
                    int dialogResult = JOptionPane.showConfirmDialog(null, "Khi bạn xóa lớp học này, các sinh viên đã đăng kí " +
                            "học phần này sẽ bị hủy đăng kí\nBạn có muốn xóa lớp này không?", "Warning", dialogButton);
                    if (dialogResult == JOptionPane.YES_OPTION) {
                        //{"Mã Học Phần", "Tên Học Phần","Ngày học", "Ca học", "Phòng học", "Slot tối đa",  "Giáo viên giảng dạy",
                        //"Ngày bắt đầu đăng kí", "Ngày kết thúc đăng kí"};
                        Subject s = new Subject();
                        String subjectId = courseTable.getModel().getValueAt(row, 0).toString();
                        s = SubjectDao.getASubject(subjectId);
                        Course c = new Course();
                        c.setCourseId(s);
                        c.setStudyTime(courseTable.getModel().getValueAt(row, 3).toString());
                        c.setStudyDay(courseTable.getModel().getValueAt(row, 2).toString());
                        c.setTeacherName(courseTable.getModel().getValueAt(row, 6).toString());
                        c.setSlot(0);
                        CourseOpen co = new CourseOpen();
                        co.setCourseId(c);
                        co.setStartDay(Date.valueOf(courseTable.getModel().getValueAt(row, 7).toString()));
                        co.setEndDay(Date.valueOf(courseTable.getModel().getValueAt(row, 8).toString()));
                        co.setSemesterId(ShowSemesters.chosenSemesterGlobal);
                        List<CourseOpen> courseOpenList = CourseOpenDao.getAllCourseOpen();
                        for (CourseOpen courseOpen : courseOpenList)
                            if (co.toString().equals(courseOpen.toString()))
                                CourseOpenDao.deleteACourseOpen(courseOpen);
                        List<Course> courses = CourseDao.getAllCourses();
                        for (Course course : courses)
                            if (c.toString().equals(course.toString()))
                                CourseDao.deleteACourse(course);
                        model.removeRow(courseTable.getSelectedRow());
                        JOptionPane.showMessageDialog(null, "Xóa học phần thành công ");
                    }

                } else {
                    JOptionPane.showMessageDialog(null, "Bạn chưa chọn học phần cần xóa");
                }
                courseTable.removeAll();
                courseTable.validate();
                courseTable.repaint();
            }
        });
        findButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    List<CourseOpen> courseOpens1 = CourseOpenDao.search(searchField.getText());
                    while (model.getRowCount() > 0) {
                        model.removeRow(0);
                    }
                    int size1 = courseOpens1.size();
                    if (size1 > 0) {
                        List<CourseOpen> courseOpens2 = new ArrayList<>();
                        for (CourseOpen courseOpen1 : courseOpens1) {
                            if (courseOpen1.getSemesterId().getId() == ShowSemesters.chosenSemesterGlobal.getId())
                               courseOpens2.add(courseOpen1);
                        }
                        int size2 = courseOpens2.size();
                        String[][] data1 = new String[size2][9];


                        for (int i = 0; i < size2; i++) {
                            data1[i][0] = courseOpens2.get(i).getCourseId().getCourseId().getSubjectId();
                            data1[i][1] = courseOpens2.get(i).getCourseId().getCourseId().getSubjectName();
                            data1[i][2] = courseOpens2.get(i).getCourseId().getStudyDay();
                            data1[i][3] = courseOpens2.get(i).getCourseId().getStudyTime();
                            data1[i][4] = courseOpens2.get(i).getCourseId().getClassroom();
                            data1[i][5] = String.valueOf(courseOpens2.get(i).getCourseId().getSlot());
                            data1[i][6] = courseOpens2.get(i).getCourseId().getTeacherName();
                            data1[i][7] = String.valueOf(courseOpens2.get(i).getStartDay());
                            data1[i][8] = String.valueOf(courseOpens2.get(i).getEndDay());
                        }
                        for (int i = 0; i < size2; i++) {
                            model.addRow(data1[i]);
                        }
                    }
                } catch (SQLException throwables) {
                    throwables.printStackTrace();
                }
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
