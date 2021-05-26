package GUI;

import dao.ClassDao;
import hibernate.Clazz;

import javax.swing.*;
import java.awt.event.*;

public class addClass extends JDialog {
    private JPanel contentPane;
    private JTextField classField;
    private JButton addButton;

    public addClass() {
        this.setTitle("Thêm lớp học");
        setContentPane(contentPane);
        setModal(true);

        addButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String classId = classField.getText();
                Clazz c = new Clazz(classId, 0, 0, 0);
                ClassDao.insertAClass(c);
            }
        });
    }

    public static void main(String[] args) {
        addClass dialog = new addClass();
        dialog.pack();
        dialog.setVisible(true);
        System.exit(0);
    }
}
