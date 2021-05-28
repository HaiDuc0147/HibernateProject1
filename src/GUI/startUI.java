package GUI;

import utils.Utils;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.File;
import java.io.IOException;

public class startUI extends JDialog {
    private JPanel contentPane;
    private JButton studentChoiceButton;
    private JButton teacherChoiceButton;
    private JLabel studentLabel;
    private JLabel teacherLabel;
    private JLabel youAreLabel;
    private JButton buttonOK;

    public startUI() throws IOException, FontFormatException {
        Font font = Font.createFont(Font.TRUETYPE_FONT, new File("fonts/SVN-Journey-Bold.otf")).deriveFont(36f);
        GraphicsEnvironment ge = GraphicsEnvironment.getLocalGraphicsEnvironment();
        //register the font
        ge.registerFont(font);

        teacherLabel.setFont(font);
        studentLabel.setFont(font);

        this.setTitle("Hệ thống đăng kí học phần");
        teacherLabel.setPreferredSize(new Dimension(50, 40));
        studentLabel.setPreferredSize(new Dimension(50, 40));
        int width = 200;
        int height = 200;
        setContentPane(contentPane);
        setModal(true);

        ImageIcon studentChoiceIcon = new ImageIcon ("img/studentChoice1.png");
        studentChoiceIcon = Utils.transformImg(studentChoiceIcon, width, height);
        studentChoiceButton.setIcon(studentChoiceIcon);

        studentChoiceButton.setOpaque(false);
        studentChoiceButton.setContentAreaFilled(false);
        studentChoiceButton.setBorderPainted(false);

        ImageIcon teacherChoiceIcon = new ImageIcon ("img/teacherChoice.png");
        teacherChoiceIcon = Utils.transformImg(teacherChoiceIcon, width, height);
        teacherChoiceButton.setIcon(teacherChoiceIcon);

        teacherChoiceButton.setOpaque(false);
        teacherChoiceButton.setContentAreaFilled(false);
        teacherChoiceButton.setBorderPainted(false);

        studentChoiceButton.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseEntered(MouseEvent e) {
                studentChoiceButton.setBorderPainted(true);
                studentLabel.setText("Sinh Viên");
            }

            @Override
            public void mouseExited(MouseEvent e) {
                studentChoiceButton.setBorderPainted(false);
                studentLabel.setText("");
            }
        });
        teacherChoiceButton.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseExited(MouseEvent e) {
                teacherChoiceButton.setBorderPainted(false);
                teacherLabel.setText("");
            }

            @Override
            public void mouseEntered(MouseEvent e) {
                teacherChoiceButton.setBorderPainted(true);
                teacherLabel.setText("Giáo Vụ");

            }
        });
        teacherChoiceButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                LoginForm login = new LoginForm();
                login.setVisible(true);

            }
        });
    }

    public static void main(String[] args) throws IOException, FontFormatException {
        startUI dialog = new startUI();
        dialog.pack();
        dialog.setLocationRelativeTo(null);
        dialog.setVisible(true);
        dialog.dispose();
        System.exit(0);
    }
}


