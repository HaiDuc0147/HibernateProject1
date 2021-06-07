package GUI;

import dao.ClassDao;
import hibernate.Clazz;
import utils.Utils;

import javax.swing.*;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

public class showClasses extends JDialog {
    private JPanel contentPane;
    private JTable showClassesTable;
    private JButton addClassButton;
    private JButton deleteButton;

    public showClasses() {
        this.setTitle("Danh sách lớp học");
        this.setSize(500, 700);
        setContentPane(contentPane);
        setModal(true);

        ImageIcon addIcon = new ImageIcon ("img/add.png");
        addIcon = Utils.transformImg(addIcon, 15, 15);
        addClassButton.setIcon(addIcon);
        ImageIcon removeIcon = new ImageIcon ("img/remove.png");
        removeIcon = Utils.transformImg(removeIcon, 15, 15);
        deleteButton.setIcon(removeIcon);

        List<Clazz> classes = ClassDao.getAllClasses();
        int size = classes.size();
        String[] columnNames = {"Mã lớp học ", "Tổng sinh viên", "Sinh viên nam", "Sinh viên nữ"};
        String[][] data = new String[size][4];
        for (int i = 0; i < size; i++) {
            data[i][0] = classes.get(i).getClassId();
            data[i][1] = String.valueOf(classes.get(i).getNumberOfStudent());
            data[i][2] = String.valueOf(classes.get(i).getNumberOfMale());
            data[i][3] = String.valueOf(classes.get(i).getNumberOfFemale());
        }
        DefaultTableModel model = (DefaultTableModel) showClassesTable.getModel();
        for (String name : columnNames)
            model.addColumn(name);
        for (int i = 0; i < size; i++) {
            model.addRow(data[i]);
        }
        DefaultTableCellRenderer centerRenderer = new DefaultTableCellRenderer();
        centerRenderer.setHorizontalAlignment( JLabel.CENTER );
        for(int x=0;x<columnNames.length;x++){
            showClassesTable.getColumnModel().getColumn(x).setCellRenderer( centerRenderer );
        }
        addClassButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                addClass addClassForm = new addClass();
                addClassForm.setSize(250, 120);
                addClassForm.setLocationRelativeTo(null);
                addClassForm.setVisible(true);
                DefaultTableModel model = (DefaultTableModel) showClassesTable.getModel();
                List<Clazz> classes = ClassDao.getAllClasses();
                int size = classes.size();
                String classId = classes.get(size - 1).getClassId();

                System.out.println(classId);
                if (classId != "") {
                    String data[] = {classId, "0", "0", "0"};
                    model.addRow(data);
                }
                JOptionPane.showMessageDialog(null, "Thêm lớp  " + classId + " thành công!");
            }
        });
        deleteButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int getSelectedRowForDeletion = showClassesTable.getSelectedRow();
                //Check if their is a row selected
                if (getSelectedRowForDeletion >= 0) {
                    DefaultTableModel model = (DefaultTableModel) showClassesTable.getModel();
                    int column = 0;
                    int row = showClassesTable.getSelectedRow();
                    int dialogButton = JOptionPane.YES_NO_OPTION;
                    int dialogResult = JOptionPane.showConfirmDialog(null, "Khi bạn xóa lớp học này, các sinh viên trong lớp cũng bị xóa theo\n" +
                            "Bạn có muốn xóa lớp này không?", "Warning", dialogButton);
                    if (dialogResult == JOptionPane.YES_OPTION) {
                        String classId = showClassesTable.getModel().getValueAt(row, column).toString();
                        ClassDao.deleteAClass(new Clazz(classId, 0, 0, 0));
                        model.removeRow(showClassesTable.getSelectedRow());
                        JOptionPane.showMessageDialog(null, "Đã xóa lớp " + classId);
                    }

                } else {
                    JOptionPane.showMessageDialog(null, "Unable To Delete");
                }
            }
        });
    }

    public static void main(String[] args) {
        showClasses dialog = new showClasses();
        dialog.pack();
        dialog.setVisible(true);
        System.exit(0);
    }
}
