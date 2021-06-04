package GUI;

import dao.ClassDao;
import dao.CourseDao;
import dao.CourseOpenDao;
import dao.SubjectDao;
import hibernate.Clazz;
import hibernate.Course;
import hibernate.CourseOpen;
import hibernate.Subject;

import javax.swing.*;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Date;
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
    private JTable courseSessionTable;
    private JButton buttonOK;

    public showCourses() {

        this.setTitle("Danh sách học phần");
        this.setSize(1500, 700);
        semesterInformation.setText(ShowSemesters.chosenSemesterGlobal.getSemesterName() +
                " - Năm học " + ShowSemesters.chosenSemesterGlobal.getYear());
        setContentPane(contentPane);
        setModal(true);
        List<CourseOpen> courseOpen = CourseOpenDao.getAllCourseOpen();
        List<CourseOpen> courseOpens = new ArrayList<>();
        HashSet<String> courseSessions= new HashSet<>();
        DefaultListModel modelSession = new DefaultListModel();
        sessionList.setModel(modelSession);
        for (CourseOpen c : courseOpen) {
            if (c.getSemesterId().getId() == ShowSemesters.chosenSemesterGlobal.getId()){
                courseSessions.add(String.valueOf(c.getStartDay()) + " đến " + String.valueOf(c.getEndDay()));
                courseOpens.add(c);
            }
        }
        for (String courseSession: courseSessions){
            System.out.println(courseSession);
            modelSession.addElement(courseSession);
        }
        int size = courseOpens.size();
        System.out.println(size);
        String[] columnNames = {"Mã Học Phần", "Tên Học Phần","Ngày học", "Ca học", "Phòng học", "Slot tối đa",  "Giáo viên giảng dạy",
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
        }

        addCourseSessionButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                AddCourseSession addCourseSession = new AddCourseSession();
                addCourseSession.setVisible(true);
                courseTable.repaint();
            }
        });
        addCourseButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                AddCourse addCourse = new AddCourse();
                addCourse.setVisible(true);
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
                        for(CourseOpen courseOpen: courseOpenList)
                            if(co.toString().equals(courseOpen.toString()))
                                CourseOpenDao.deleteACourseOpen(courseOpen);
                        List<Course> courses = CourseDao.getAllCourses();
                        for(Course course: courses)
                            if(c.toString().equals(course.toString()))
                                CourseDao.deleteACourse(course);

                        //c.set
                        //c.setCourseId();

                        //co.setCourseId();
                        model.removeRow(courseTable.getSelectedRow());
                        JOptionPane.showMessageDialog(null, "Xóa học phần thành công ");
                    }

                } else {
                    JOptionPane.showMessageDialog(null, "Bạn chưa chọn học phần cần xóa");
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
