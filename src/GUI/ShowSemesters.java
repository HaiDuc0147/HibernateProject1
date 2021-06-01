package GUI;

import dao.SemesterDao;
import dao.SubjectDao;
import hibernate.Semester;
import hibernate.Subject;

import javax.swing.*;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Date;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.List;

public class ShowSemesters extends JDialog {
    private JPanel contentPane;
    private JTable semesterTable;
    private JButton addButton;
    private JButton deleteButton;
    private JButton choosenButton;
    public static Semester chosenSemesterGlobal;
    public ShowSemesters() {
        this.setSize(500, 700);
        setContentPane(contentPane);
        setModal(true);

        this.setTitle("Danh sách môn học");
        this.setSize(500, 700);
        setContentPane(contentPane);
        setModal(true);
        List<Semester> semesters = SemesterDao.getAllSemester();
        int size = semesters.size();
        String[] columnNames = {"Học kỳ", "Năm học", "Ngày bắt đầu", "Ngày kết thúc"};
        String[][] data = new String[size][4];
        for(int i = 0; i < size; i++){
            data[i][0] = semesters.get(i).getSemesterName();
            data[i][1] = semesters.get(i).getYear();
            data[i][2] = String.valueOf(semesters.get(i).getStartDay());
            data[i][3] = String.valueOf(semesters.get(i).getEndDay());
        }

        DefaultTableModel model = (DefaultTableModel)semesterTable.getModel();
        for(int i = 0; i < columnNames.length; i++)
            model.addColumn(columnNames[i]);
        DefaultTableCellRenderer centerRenderer = new DefaultTableCellRenderer();
        centerRenderer.setHorizontalAlignment( JLabel.CENTER );
        for(int x=0;x<columnNames.length;x++){
            semesterTable.getColumnModel().getColumn(x).setCellRenderer( centerRenderer );
        }
        for(int i = 0; i < size; i++){
            model.addRow(data[i]);
        }
        addButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                AddSemester addSemester = new AddSemester();
                addSemester.setLocationRelativeTo(null);
                addSemester.setVisible(true);

                DefaultTableModel model = (DefaultTableModel) semesterTable.getModel();
                List<Semester> semesters = SemesterDao.getAllSemester();
                int size = semesters.size();
                String semesterName = semesters.get(size - 1).getSemesterName();
                String semesterYear = semesters.get(size - 1).getYear();
                //Date startDay = semesters.get(size - 1).getStartDay();
                String startDay = String.valueOf(semesters.get(size - 1).getStartDay());
                String endDay = String.valueOf(semesters.get(size - 1).getEndDay());

                if (semesterName != "") {
                    String data[] = {semesterName, semesterYear, startDay, endDay};
                    model.addRow(data);
                }
                JOptionPane.showMessageDialog(null, "Thêm học kỳ thành công!");
            }


        });
        deleteButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int getSelectedRowForDeletion = semesterTable.getSelectedRow();
                //Check if their is a row selected
                if (getSelectedRowForDeletion >= 0) {
                    DefaultTableModel model = (DefaultTableModel) semesterTable.getModel();

                    int row = semesterTable.getSelectedRow();
                    int dialogButton = JOptionPane.YES_NO_OPTION;
                    int dialogResult = JOptionPane.showConfirmDialog(null, "Khi bạn xóa học kỳ này, các học phần trong học kỳ này cũng bị xóa theo\n" +
                            "Bạn có muốn xóa học kỳ này không?", "Warning", dialogButton);
                    if (dialogResult == JOptionPane.YES_OPTION) {
                        String semesterName = semesterTable.getModel().getValueAt(row, 0).toString();
                        String semesterYear = semesterTable.getModel().getValueAt(row, 1).toString();
                        String startDayString = semesterTable.getModel().getValueAt(row, 2).toString();
                        String endDayString = semesterTable.getModel().getValueAt(row, 3).toString();
                        Date startDay = Date.valueOf(startDayString);
                        Date endDay = Date.valueOf(endDayString);

                        Semester s = new Semester();
                        s.setSemesterName(semesterName);
                        s.setYear(semesterYear);
                        s.setStartDay(startDay);
                        s.setEndDay(endDay);
                        List<Semester> semesters1 = SemesterDao.getAllSemester();
                        for(Semester se: semesters1){
                            if(se.toString().equals(s.toString()))
                                s.setId(se.getId());
                        }
                        SemesterDao.deleteASemester(s);
                        //SemesterDao.deleteASemester(new Semester(semesterName, semesterYear, startDay, endDay));
                        model.removeRow(semesterTable.getSelectedRow());
                        JOptionPane.showMessageDialog(null, "Xóa học kỳ thành công");
                    }

                } else {
                    JOptionPane.showMessageDialog(null, "Bạn chưa chọn học kỳ để xóa!");
                }
            }
        });
        choosenButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int selectedRow = semesterTable.getSelectedRow();
                if(selectedRow >= 0) {
                    int row = semesterTable.getSelectedRow();
                    String semesterName = semesterTable.getModel().getValueAt(row, 0).toString();
                    String semesterYear = semesterTable.getModel().getValueAt(row, 1).toString();
                    String startDayString = semesterTable.getModel().getValueAt(row, 2).toString();
                    String endDayString = semesterTable.getModel().getValueAt(row, 3).toString();
                    Date startDay = Date.valueOf(startDayString);
                    Date endDay = Date.valueOf(endDayString);
                    Semester s = new Semester();
                    s.setSemesterName(semesterName);
                    s.setYear(semesterYear);
                    s.setStartDay(startDay);
                    s.setEndDay(endDay);
                    List<Semester> semesters1 = SemesterDao.getAllSemester();
                    for (Semester se : semesters1) {
                        if (se.toString().equals(s.toString()))
                            s.setId(se.getId());
                    }
                    chosenSemesterGlobal = s;
                    JOptionPane.showMessageDialog(null, "\t\tCập nhập thành công \nHiện tại là " + chosenSemesterGlobal.getSemesterName()
                    +" Năm học: " + chosenSemesterGlobal.getYear());
                }
                else
                    JOptionPane.showMessageDialog(null, "Bạn chưa chọn học kỳ!");

            }
        });
    }

    public static void main(String[] args) {
        ShowSemesters dialog = new ShowSemesters();
        dialog.pack();
        dialog.setVisible(true);
        System.exit(0);
    }
}
