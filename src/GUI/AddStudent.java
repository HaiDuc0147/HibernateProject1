package GUI;

import dao.ClassDao;
import dao.LoginDao;
import dao.StudentDao;
import hibernate.Clazz;
import hibernate.Login;
import hibernate.Student;
import utils.Utils;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

public class AddStudent extends JDialog {
    private JPanel contentPane;
    private JTextField studentIdField;
    private JTextField nameStudentField;
    private JTextField classIdField;
    private JCheckBox isMaleCheckBox;
    private JCheckBox isFemaleCheckBox;
    private JButton addButton;
    private JComboBox classField;
    private JButton buttonOK;
    private JButton buttonCancel;

    public AddStudent() {
        setContentPane(contentPane);
        setModal(true);
        this.setTitle("Thêm sinh viên");
        this.setSize(300, 250);

        ImageIcon addIcon = new ImageIcon ("img/add.png");
        addIcon = Utils.transformImg(addIcon, 15, 15);
        addButton.setIcon(addIcon);

        List<Clazz> clazzes = ClassDao.getAllClasses();
        for(Clazz cl: clazzes)
            classField.addItem(cl.getClassId());
        // call onCancel() when cross is clicked

        // call onCancel() on ESCAPE

        isMaleCheckBox.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                isFemaleCheckBox.setSelected(false);
            }
        });
        isFemaleCheckBox.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                isMaleCheckBox.setSelected(false);
            }
        });
        addButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Student s = new Student();
                s.setStudentId(studentIdField.getText());
                s.setStudentName(nameStudentField.getText());
                Clazz c = ClassDao.getAClass((String)classField.getSelectedItem());
                s.setClassId(c);

                s.setGender(isMaleCheckBox.isSelected());
                c.setNumberOfStudent(c.getNumberOfStudent() + 1);
                if(isMaleCheckBox.isSelected())
                    c.setNumberOfMale(c.getNumberOfMale() + 1);
                else
                    c.setNumberOfFemale(c.getNumberOfFemale() + 1);
                ClassDao.updateAClass(c);
                s.setCredits(0);
                Login l = new Login();
                l.setUsername(studentIdField.getText());
                l.setPassword(studentIdField.getText());
                l.setRole(false);
                StudentDao.addStudent(s);
                LoginDao.insertALogin(l);
            }
        });
    }



    public static void main(String[] args) {
        AddStudent dialog = new AddStudent();
        dialog.pack();
        dialog.setVisible(true);
        System.exit(0);
    }
}
