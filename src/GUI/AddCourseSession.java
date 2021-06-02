package GUI;

import dao.SemesterDao;
import hibernate.Semester;
import utils.Utils;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Date;

public class AddCourseSession extends JDialog {
    private JPanel contentPane;
    private JComboBox endDay;
    private JComboBox endMonth;
    private JComboBox endYear;
    private JComboBox startDay;
    private JComboBox startMonth;
    private JComboBox startYear;
    private JButton addButton;
    private JButton buttonOK;
    public static Date startDaySessionGlobal;
    public static Date endDaySessionGlobal;
    public AddCourseSession() {
        setContentPane(contentPane);
        setModal(true);
        this.setSize(300, 200);
        getRootPane().setDefaultButton(buttonOK);
        int[] months = {1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12};
        int[] days ={};
        for(int i = 1; i <= 30; i++){
            startDay.addItem(i);
            endDay.addItem(i);
        }
        for(int i = 0; i < months.length; i++) {
            startMonth.addItem(months[i]);
            endMonth.addItem(months[i]);
        }
        for(int i = 2019; i <= 2050; i++){
            startYear.addItem(i);
            endYear.addItem(i);
        }
        startMonth.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int[] month31days = {1, 3, 5, 7, 8, 10, 12};

                if((int)startMonth.getSelectedItem() == 2){
                    Utils.changeDaysInMonth(startDay, 29);
                }
                else{
                    boolean isMonth31Days = false;
                    for (int i = 0; i < month31days.length; i++) {
                        if ((int) startMonth.getSelectedItem() == month31days[i]) {
                            isMonth31Days = true;
                            Utils.changeDaysInMonth(startDay, 31);
                        }
                    }
                    if (!isMonth31Days)
                        Utils.changeDaysInMonth(startDay, 30);
                }
            }
        });
        addButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Semester s = new Semester();
                startDaySessionGlobal = new Date((int)startYear.getSelectedItem() - 1900, (int)startMonth.getSelectedItem() - 1, (int)startDay.getSelectedItem());
                endDaySessionGlobal = new Date((int)endYear.getSelectedItem() - 1900, (int)endMonth.getSelectedItem() - 1, (int)endDay.getSelectedItem());
                if(endDaySessionGlobal.after(startDaySessionGlobal)) {
                    JOptionPane.showMessageDialog(null, "Thêm kì đăng kí học phần thành công!");
                    dispose();
                }
                else
                    JOptionPane.showMessageDialog(null, "Ngày kết thúc phải sau ngày bắt đầu");
                //System.out.println((String)semesterName.getSelectedItem());
            }
        });
    }

    public static void main(String[] args) {
        AddCourseSession dialog = new AddCourseSession();
        dialog.pack();
        dialog.setVisible(true);
        System.exit(0);
    }
}
