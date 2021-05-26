package GUI;

import dao.SubjectDao;
import hibernate.Subject;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.util.List;

public class showSubject extends JDialog {
    private JPanel contentPane;
    private JTable Subjects;

    public showSubject() {
        this.setTitle("Danh sách môn học");
        this.setSize(500, 700);
        setContentPane(contentPane);
        setModal(true);
        List<Subject> subjects = SubjectDao.getAllSubjects();
        int size = subjects.size();
        String[] columnNames = {"Mã Môn Học", "Tên Môn Học", "Số Tín Chỉ"};
        String[][] data = new String[size][3];
        for(int i = 0; i < size; i++){
            data[i][0] = subjects.get(i).getSubjectId();
            data[i][1] = subjects.get(i).getSubjectName();
            data[i][2] = String.valueOf(subjects.get(i).getCredit());
        }

        DefaultTableModel model = (DefaultTableModel)Subjects.getModel();
        model.addColumn("Mã Môn Học");
        model.addColumn("Tên Môn Học");
        model.addColumn("Số Tín Chỉ");
        for(int i = 0; i < size; i++){
            model.addRow(data[i]);
        }

        //JTable temp = new JTable(data, columnNames);
        //Subjects = temp;

        //contentPane.add(Subjects, 0);
        /*TableColumn idSubject = new TableColumn();
        idSubject.setHeaderValue("Mã Môn Học");
        TableColumn subjectName = new TableColumn();
        subjectName.setHeaderValue("Tên Môn Học");
        TableColumn credits = new TableColumn();
        credits.setHeaderValue("Số Tín Chỉ");

        Subjects.addColumn(idSubject);
        Subjects.addColumn(subjectName);
        Subjects.addColumn(credits);*/
    }

    public static void main(String[] args) {
        showSubject dialog = new showSubject();
        dialog.pack();
        dialog.setVisible(true);
        System.exit(0);
    }
}
