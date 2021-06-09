package GUI;

import dao.ClassDao;
import dao.StudentDao;
import hibernate.Clazz;
import hibernate.Student;
import utils.Utils;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

public class ShowStudentInformation extends JDialog {
    private JPanel contentPane;
    private JButton updateButton;
    private JTextField nameField;
    private JCheckBox maleCheckBox;
    private JCheckBox femaleCheckBox;
    private JLabel studentIdLabel;
    private JComboBox classIdComboBox;
    private JButton buttonOK;
    private JButton buttonCancel;

    public ShowStudentInformation() {
        this.setSize(500, 300);
        this.setTitle("Thông tin cá nhân");
        setContentPane(contentPane);
        setModal(true);
        ImageIcon updateIcon = new ImageIcon ("img/update.png");
        updateIcon = Utils.transformImg(updateIcon, 15, 15);
        updateButton.setIcon(updateIcon);
        Student t = StudentDao.getAStudent(LoginFormStudent.usernameGlobal);
        studentIdLabel.setText(t.getStudentId());
        nameField.setText(t.getStudentName());
        List<Clazz> clazzes = ClassDao.getAllClasses();
        for(Clazz cl: clazzes)
            classIdComboBox.addItem(cl.getClassId());
        classIdComboBox.setSelectedItem(t.getClassId().getClassId());
        if(t.getGender() == true)
            maleCheckBox.setSelected(true);
        else
            femaleCheckBox.setSelected(true);
        updateButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                t.setStudentName(nameField.getText());
                for(Clazz c: clazzes){
                    if(c.getClassId().equals((String)classIdComboBox.getSelectedItem())){
                        t.setClassId(c);
                        break;
                    }
                }
                t.setGender(maleCheckBox.isSelected());
                StudentDao.updateAStudent(t);
                JOptionPane.showMessageDialog(null, "Cập nhập thành công!");
            }
        });
        maleCheckBox.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                femaleCheckBox.setSelected(false);
            }
        });
        femaleCheckBox.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                maleCheckBox.setSelected(false);
            }
        });
    }

    public static void main(String[] args) {
        ShowStudentInformation dialog = new ShowStudentInformation();
        dialog.pack();
        dialog.setVisible(true);
        System.exit(0);
    }
}
