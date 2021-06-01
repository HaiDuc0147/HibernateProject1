package GUI;

import dao.SubjectDao;
import hibernate.Subject;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class UpdateSubject extends JDialog {
    private JPanel contentPane;
    private JTextField subjectNameField;
    private JSpinner subjectCreditsField;
    private JLabel subjectIdField;
    private JButton changeButton;
    public static String subjectNameGlobal;
    public static int creditsGlobal;

    public UpdateSubject(){
        this.setTitle("Cập nhập thông tin môn học");
        this.setSize(270, 150);
        setContentPane(contentPane);
        setModal(true);
        subjectIdField.setText("Mã môn: " + showSubject.subjectIdGlobal);

        changeButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                subjectNameGlobal = subjectNameField.getText();
                creditsGlobal = (int)subjectCreditsField.getValue();
                SubjectDao.updateASubject(new Subject(showSubject.subjectIdGlobal, subjectNameGlobal, creditsGlobal));
                JOptionPane.showMessageDialog(null, "Cập nhập thành công!");
                dispose();
            }
        });
    }

    public static void main(String[] args)  {
        UpdateSubject dialog = new UpdateSubject();
        dialog.pack();
        dialog.setVisible(true);
        System.exit(0);
    }
}
