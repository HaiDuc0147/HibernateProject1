package GUI;

import dao.CourseOpenDao;
import dao.SemesterDao;
import dao.StudentDao;
import hibernate.CourseOpen;
import hibernate.CourseRegister;
import hibernate.Semester;
import hibernate.Student;
import utils.Utils;

import javax.swing.*;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableCellRenderer;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Date;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

public class CourseRegisterForm extends JDialog {
    private JPanel contentPane;
    private JTable courseTable;
    private JButton registerButton;
    private JLabel semesterInformation;
    private JComboBox semesterComboBox;

    public CourseRegisterForm() {
        this.setTitle("Danh sách học phần");
        this.setSize(1500, 700);
        setContentPane(contentPane);
        setModal(true);
        List<Semester> semesters = SemesterDao.getAllSemester();
        for(Semester semester: semesters)
            semesterComboBox.addItem((String)semester.toString());
        String[] columnNames = {"Mã Học Phần", "Tên Học Phần", "Ngày học", "Ca học", "Phòng học", "Slot tối đa", "Giáo viên giảng dạy",
                "Ngày bắt đầu đăng kí", "Ngày kết thúc đăng kí"};
        DefaultTableModel model = (DefaultTableModel) courseTable.getModel();
        for (int i = 0; i < columnNames.length; i++)
            model.addColumn(columnNames[i]);

      semesterComboBox.addActionListener(new ActionListener() {
          @Override
          public void actionPerformed(ActionEvent e) {
              String semesterName = (String)semesterComboBox.getSelectedItem();
              Semester atPresent = null;
              for(Semester s: semesters){
                  if(s.toString().equals(semesterName)) {
                      atPresent = s;
                      break;
                  }
              }
              List<CourseOpen> courseOpen = CourseOpenDao.getAllCourseOpen();
              List<CourseOpen> courseOpens = new ArrayList<>();
              for (CourseOpen c : courseOpen) {
                  if (c.getSemesterId().getId() == atPresent.getId()) {
                      courseOpens.add(c);
                  }
              }
              int size = courseOpens.size();
              while (model.getRowCount() > 0) {
                  model.removeRow(0);
              }
              String[][] data = new String[size][9];
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
                  TableCellRenderer rendererFromHeader = courseTable.getTableHeader().getDefaultRenderer();
                  JLabel headerLabel = (JLabel) rendererFromHeader;
                  headerLabel.setHorizontalAlignment(JLabel.CENTER);
              }
          }
      });
        registerButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                List<Student> students = StudentDao.getAllStudent();
                CourseRegister courseRegister = new CourseRegister();
                for(Student student: students)
                    if(student.getStudentId().equals(LoginFormStudent.usernameGlobal)){
                        courseRegister.setStudentId(student);
                        break;
                    }
                DateTimeFormatter dtf = DateTimeFormatter.ofPattern("uuuu/MM/dd");
                LocalDate localDate = LocalDate.now();
                Date date = java.sql.Date.valueOf(localDate);
                courseRegister.setRegisterDay(date);
                //courseRegister.setCourseId();

            }
        });
    }

    public static void main(String[] args) throws UnsupportedLookAndFeelException, ClassNotFoundException, InstantiationException, IllegalAccessException {
        CourseRegisterForm dialog = new CourseRegisterForm();
        dialog.setSize(2000, 700);
        String className = Utils.getLookAndFeelClassName("Nimbus");
        UIManager.setLookAndFeel(className);
        for(Window window : JFrame.getWindows()) {
            SwingUtilities.updateComponentTreeUI(window);
        }
        dialog.pack();
        dialog.setVisible(true);
        System.exit(0);
      //DateTimeFormatter dtf = DateTimeFormatter.ofPattern("uuuu/MM/dd");
      //LocalDate localDate = LocalDate.now();
      //Date date = java.sql.Date.valueOf(localDate);
      //System.out.println(date.toString());
    }
}
