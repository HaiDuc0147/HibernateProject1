package GUI;

import dao.LoginDao;
import hibernate.Login;
import utils.Utils;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;
import java.util.List;

public class showListTeacher extends JDialog {
    private JPanel contentPane;
    private JTable listTeacherTable;
    private JButton addButton;
    private JButton deleteButton;
    private JButton resetPasswordButton;
    private JButton showPasswordButton;
    private JTextField searchField;
    private JButton searchButton;
    private JButton buttonOK;

    public showListTeacher() {
        setContentPane(contentPane);
        setModal(true);
        this.setTitle("Danh sách tài khoản giáo vụ");
        this.setSize(550, 700);
        ImageIcon searchIcon = new ImageIcon ("img/search.png");
        searchIcon = Utils.transformImg(searchIcon, 15, 15);
        searchButton.setIcon(searchIcon);
        ImageIcon addIcon = new ImageIcon ("img/add.png");
        addIcon = Utils.transformImg(addIcon, 15, 15);
        addButton.setIcon(addIcon);
        ImageIcon removeIcon = new ImageIcon ("img/remove.png");
        removeIcon = Utils.transformImg(removeIcon, 15, 15);
        deleteButton.setIcon(removeIcon);
        ImageIcon resetIcon = new ImageIcon ("img/reset.png");
        resetIcon = Utils.transformImg(resetIcon, 15, 15);
        resetPasswordButton.setIcon(resetIcon);
        ImageIcon showIcon = new ImageIcon ("img/show1.png");
        showIcon = Utils.transformImg(showIcon, 15, 15);
        showPasswordButton.setIcon(showIcon);
        final Boolean[] isAppeared = {false};
        List<Login> logins = LoginDao.getAllAccounts();
        int size = logins.size();
        String[] columnNames = {"Tên đăng nhập", "Mật khẩu"};
        String[][] data = new String[size][2];
        int j = 0;
        for (int i = 0; i < size; i++) {
            if (logins.get(i).getRole() == true) {
                data[j][0] = logins.get(i).getUsername();
                data[j][1] = "*************";
                j++;
            }
        }

        DefaultTableModel model = (DefaultTableModel) listTeacherTable.getModel();
        for (int i = 0; i < columnNames.length; i++)
            model.addColumn(columnNames[i]);
        for (int i = 0; i < j; i++)
            model.addRow(data[i]);
        showPasswordButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if(!isAppeared[0]) {
                    int row = listTeacherTable.getSelectedRow();
                    Login l = LoginDao.getAnAccount((String) listTeacherTable.getValueAt(row, 0));
                    String password = l.getPassword();
                    listTeacherTable.setValueAt(password, row, 1);
                    isAppeared[0] = true;
                }
                else{
                    int row = listTeacherTable.getSelectedRow();
                    String password = "*************";
                    listTeacherTable.setValueAt(password, row, 1);
                    isAppeared[0] = false;
                }
            }
        });
        addButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                addTeacher addTeacherForm = new addTeacher();
                addTeacherForm.setLocationRelativeTo(null);
                addTeacherForm.setVisible(true);
                while (model.getRowCount() > 0) {
                    model.removeRow(0);
                }
                List<Login> logins1 = LoginDao.getAllAccounts();
                int size1 = logins1.size();
                String[][] data1 = new String[size1][2];

                int k = 0;
                for (int i = 0; i < size1; i++) {
                    if (logins1.get(i).getRole() == true) {
                        data1[k][0] = logins1.get(i).getUsername();
                        data1[k][1] = "*************";
                        k++;
                    }
                }
                for (int i = 0; i <= k; i++)
                    model.addRow(data[i]);
            }
        });
        deleteButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int getSelectedRowForDeletion = listTeacherTable.getSelectedRow();
                int row = listTeacherTable.getSelectedRow();
                if (getSelectedRowForDeletion >= 0) {
                    Login l = LoginDao.getAnAccount((String) listTeacherTable.getValueAt(row, 0));
                    List<Login> logins1 = LoginDao.getAllAccounts();
                    for(Login lo: logins1){
                        //System.out.println(lo.getUsername());
                        if(l.toString().equals(lo.toString())) {
                            System.out.println(lo);
                            l.setId(lo.getId());
                        }
                    }
                    DefaultTableModel model = (DefaultTableModel) listTeacherTable.getModel();
                    LoginDao.deleteAnAccount(l);
                    model.removeRow(listTeacherTable.getSelectedRow());
                    JOptionPane.showMessageDialog(null, "Đã xóa tài khoản " + l.getUsername());

                } else {
                    JOptionPane.showMessageDialog(null, "Bạn chưa chọn tài khoản cần xóa!");
                }
            }
        });
        resetPasswordButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int row = listTeacherTable.getSelectedRow();
                Login l = LoginDao.getAnAccount((String) listTeacherTable.getValueAt(row, 0));
                String password = l.getPassword();
                l.setPassword("admin");
                List<Login> logins2 = LoginDao.getAllAccounts();
                for(Login lo: logins2){
                    if(l.toString().equals(lo.toString())) {
                        l.setId(lo.getId());
                    }
                }
                LoginDao.updateAccount(l);
                JOptionPane.showMessageDialog(null, "Mật khẩu được set mặc định là admin");
                listTeacherTable.setValueAt("admin", row, 1);
            }
        });
        searchButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    List<Login> logins1 = LoginDao.search(searchField.getText());
                    while (model.getRowCount() > 0) {
                        model.removeRow(0);
                    }
                    int size1 = logins1.size();
                    String[][] data1 = new String[size1][2];
                    System.out.println(size1);
                    int j = 0;
                    if (size1 > 0) {
                        for (int i = 0; i < size1; i++) {
                            if(logins1.get(i).getRole()) {
                                data1[j][0] = logins1.get(i).getUsername();
                                data1[j][1] = "*************";
                                j++;
                            }
                        }
                        for (int i = 0; i < j; i++) {
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
        showListTeacher dialog = new showListTeacher();
        dialog.pack();
        dialog.setVisible(true);
        System.exit(0);
    }
}
