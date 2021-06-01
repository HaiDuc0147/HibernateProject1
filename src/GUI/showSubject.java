package GUI;

import dao.ClassDao;
import dao.SubjectDao;
import hibernate.Clazz;
import hibernate.Subject;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

public class showSubject extends JDialog {
    private JPanel contentPane;
    private JTable Subjects;
    private JButton deleteButton;
    private JButton addButton;
    private JButton updateButton;
    public static String subjectIdGlobal;

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
        deleteButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int getSelectedRowForDeletion = Subjects.getSelectedRow();
                //Check if their is a row selected
                if (getSelectedRowForDeletion >= 0) {
                    DefaultTableModel model = (DefaultTableModel) Subjects.getModel();
                    int column = 0;
                    int row = Subjects.getSelectedRow();
                    int dialogButton = JOptionPane.YES_NO_OPTION;
                    int dialogResult = JOptionPane.showConfirmDialog(null, "Khi bạn xóa môn học này, các học phần của môn học này cũng bị xóa theo\n" +
                            "Bạn có muốn xóa môn học này không?", "Warning", dialogButton);
                    if (dialogResult == JOptionPane.YES_OPTION) {
                        String subjectId = Subjects.getModel().getValueAt(row, column).toString();
                        String subjectName = Subjects.getModel().getValueAt(row, 1).toString();
                        SubjectDao.deleteASubject(new Subject(subjectId, "", 0));
                        model.removeRow(Subjects.getSelectedRow());
                        JOptionPane.showMessageDialog(null, "Đã xóa môn " + subjectName);
                    }

                } else {
                    JOptionPane.showMessageDialog(null, "Unable To Delete");
                }
            }
        });
        addButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                AddSubject addSubject = new AddSubject();
                addSubject.setLocationRelativeTo(null);
                addSubject.setVisible(true);
                DefaultTableModel model = (DefaultTableModel) Subjects.getModel();
                List<Subject> subjectsList = SubjectDao.getAllSubjects();
                int size = subjectsList.size();
                String subjectId = subjectsList.get(size - 1).getSubjectId();
                String subjectName = subjectsList.get(size - 1).getSubjectName();
                String subjectCredit = String.valueOf(subjectsList.get(size - 1).getCredit());

                if (subjectId != "") {
                    String data[] = {subjectId, subjectName, subjectCredit};
                    model.addRow(data);
                }
                JOptionPane.showMessageDialog(null, "Thêm môn " + subjectName + " thành công!");
            }
        });
        updateButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int column = 0;
                int row = Subjects.getSelectedRow();
                subjectIdGlobal = Subjects.getModel().getValueAt(row, column).toString();
                UpdateSubject updateSubject = new UpdateSubject();
                updateSubject.setLocationRelativeTo(null);
                updateSubject.setVisible(true);
                DefaultTableModel model = (DefaultTableModel) Subjects.getModel();
                model.setValueAt((String)UpdateSubject.subjectNameGlobal, Subjects.getSelectedRow(), 1);
                model.setValueAt((int)UpdateSubject.creditsGlobal, Subjects.getSelectedRow(), 2);
            }
        });
    }


    public static void main(String[] args) {
        showSubject dialog = new showSubject();
        dialog.pack();
        dialog.setVisible(true);
        System.exit(0);
    }
}

