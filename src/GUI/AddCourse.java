package GUI;

import dao.CourseDao;
import dao.CourseOpenDao;
import dao.SubjectDao;
import hibernate.Course;
import hibernate.CourseOpen;
import hibernate.Subject;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

public class AddCourse extends JDialog {
    private JPanel contentPane;
    private JComboBox courseIdComboBox;
    private JComboBox subjectNameCombobox;
    private JComboBox dateCombobox;
    private JComboBox timeComboBox;
    private JTextField roomField;
    private JTextField teacherField;
    private JButton addButton;
    private JTextField slotField;
    private JButton buttonOK;

    public AddCourse() {
        this.setTitle("Thêm học phần");
        this.setSize(700, 400);
        setContentPane(contentPane);
        setModal(true);
        getRootPane().setDefaultButton(buttonOK);
        List<Subject> subjects=SubjectDao.getAllSubjects();
        for(Subject s: subjects) {
            courseIdComboBox.addItem(s.getSubjectId());
            subjectNameCombobox.addItem(s.getSubjectName());
        }
        String[] times = {"7:30–9:30", "9:30–11:30", "13:30–15:30", "15:30–17:30"};
        String[] days = {"Thứ 2", "Thứ 3", "Thứ 4", "Thứ 5", "Thứ 6", "Thứ 7", "Chúa nhật"};
        for(String t: times){
            timeComboBox.addItem(t);
        }
        for(String d: days){
            dateCombobox.addItem(d);
        }
        courseIdComboBox.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                for(Subject s: subjects) {
                    if (s.getSubjectId().equals(courseIdComboBox.getSelectedItem())) {
                        subjectNameCombobox.setSelectedItem(s.getSubjectName());
                    }
                }
            }
        });
        subjectNameCombobox.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                for(Subject s: subjects) {
                    if (s.getSubjectName().equals(subjectNameCombobox.getSelectedItem())) {
                        courseIdComboBox.setSelectedItem(s.getSubjectId());
                    }
                }
            }
        });
        addButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Course c = new Course();
                c.setCourseId(SubjectDao.getASubject((String)courseIdComboBox.getSelectedItem()));
                c.setStudyDay((String)dateCombobox.getSelectedItem());
                c.setClassroom(roomField.getText());
                c.setStudyTime((String)timeComboBox.getSelectedItem());
                c.setTeacherName(teacherField.getText());
                int slot = Integer.parseInt(slotField.getText());
                c.setSlot(slot);
                CourseDao.insertACourse(c);


                if(AddCourseSession.startDaySessionGlobal == null || AddCourseSession.endDaySessionGlobal == null)
                    JOptionPane.showMessageDialog(null, "Bạn chưa mở kì đăng ký học phần");
                else {
                    CourseOpen co = new CourseOpen();
                    co.setCourseId(c);
                    co.setStartDay(AddCourseSession.startDaySessionGlobal);
                    co.setEndDay(AddCourseSession.endDaySessionGlobal);
                    co.setSemesterId(ShowSemesters.chosenSemesterGlobal);
                    CourseOpenDao.insertACourseOpen(co);
                    JOptionPane.showMessageDialog(null, "Thêm học phần thành công!");
                    dispose();
                }
            }
        });
    }

    public static void main(String[] args) {
        AddCourse dialog = new AddCourse();
        dialog.pack();
        dialog.setVisible(true);
        System.exit(0);
    }

    private void createUIComponents() {
        // TODO: place custom component creation code here
    }
}


