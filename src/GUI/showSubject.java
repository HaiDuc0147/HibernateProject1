package GUI;

import dao.SubjectDao;
import hibernate.Subject;
import utils.Utils;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;
import java.util.List;

public class showSubject extends JDialog {
    private JPanel contentPane;
    private JTable Subjects;
    private JButton deleteButton;
    private JButton addButton;
    private JButton updateButton;
    private JTextField searchField;
    private JButton searchButton;
    public static String subjectIdGlobal;
    public static String subjectNameGlobal;
    public showSubject() {
        this.setTitle("Danh sách môn học");
        this.setSize(500, 700);
        setContentPane(contentPane);
        setModal(true);
        ImageIcon searchIcon = new ImageIcon ("img/search.png");
        searchIcon = Utils.transformImg(searchIcon, 15, 15);
        searchButton.setIcon(searchIcon);
        ImageIcon addIcon = new ImageIcon ("img/add.png");
        addIcon = Utils.transformImg(addIcon, 15, 15);
        addButton.setIcon(addIcon);
        ImageIcon removeIcon = new ImageIcon ("img/remove.png");
        removeIcon = Utils.transformImg(removeIcon, 15, 15);
        deleteButton.setIcon(removeIcon);
        ImageIcon updateIcon = new ImageIcon ("img/update.png");
        updateIcon = Utils.transformImg(updateIcon, 15, 15);
        updateButton.setIcon(updateIcon);

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

                int row = Subjects.getSelectedRow();
                subjectIdGlobal = Subjects.getModel().getValueAt(row, 0).toString();
                subjectNameGlobal = Subjects.getModel().getValueAt(row, 1).toString();
                System.out.println(subjectNameGlobal);
                UpdateSubject updateSubject = new UpdateSubject();
                updateSubject.setLocationRelativeTo(null);
                updateSubject.setVisible(true);
                DefaultTableModel model = (DefaultTableModel) Subjects.getModel();
                model.setValueAt((String)showSubject.subjectNameGlobal, Subjects.getSelectedRow(), 1);
                model.setValueAt((int)UpdateSubject.creditsGlobal, Subjects.getSelectedRow(), 2);
            }
        });
        searchButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    List<Subject> subjects1 = SubjectDao.search(searchField.getText());
                    while (model.getRowCount() > 0) {
                        model.removeRow(0);
                    }
                    int size1 = subjects1.size();
                    String[][] data1 = new String[size1][3];
                    System.out.println(size1);
                    if (size1 > 0) {
                        for (int i = 0; i < size1; i++) {
                            data1[i][0] = subjects1.get(i).getSubjectId();
                            data1[i][1] = subjects1.get(i).getSubjectName();
                            data1[i][2] = String.valueOf(subjects1.get(i).getCredit());

                        }
                        for (int i = 0; i < size1; i++) {
                            model.addRow(data1[i]);
                        }
                    }
                } catch (SQLException sqlException) {
                    sqlException.printStackTrace();
                }
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

