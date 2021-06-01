package GUI;

import javax.swing.*;

public class FindStudent extends JDialog {
    private JPanel contentPane;

    public FindStudent() {
        setContentPane(contentPane);
        setModal(true);

    }

    public static void main(String[] args) {
        FindStudent dialog = new FindStudent();
        dialog.pack();
        dialog.setVisible(true);
        System.exit(0);
    }
}
