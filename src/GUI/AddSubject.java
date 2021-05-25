package GUI;

import dao.SubjectDao;
import hibernate.Subject;

import javax.swing.*;
import java.awt.event.*;

public class AddSubject extends JDialog {
    private JPanel contentPane;
    private JSpinner creditField;
    private JTextField subjectIdField;
    private JTextField subjectNameField;
    private JButton addButton;
    private JButton buttonOK;
    private JButton buttonCancel;

    public AddSubject() {
        setContentPane(contentPane);
        setModal(true);

        addButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Subject s = new Subject();
                s.setSubjectId(subjectIdField.getText());
                s.setSubjectName(subjectNameField.getText());
                s.setCredit((int)creditField.getValue());
                SubjectDao.addSubject(s);
                JOptionPane.showMessageDialog(null, "Thêm môn học thành công!");
            }
        });
    }


    public static void main(String[] args) {
        AddSubject dialog = new AddSubject();
        dialog.pack();
        dialog.setVisible(true);
        System.exit(0);
    }
}
