
import GUI.AddStudent;
import GUI.LoginForm;
import dao.LoginDao;
import dao.TeacherDao;
import hibernate.Login;
import hibernate.Teacher;

import javax.swing.*;
import java.awt.*;
import java.util.List;
import java.util.Scanner;

public class Main {

    public static void main(final String[] args)  {
        /*List<Teacher> teachers = TeacherDao.getAllTeacher();
        for(Teacher tc: teachers){
            System.out.println(tc.toString());
        }
        Login l = new Login();
        System.out.print("Username: ");
        Scanner sc = new Scanner(System.in);
        String usenname = sc.nextLine();
        System.out.print("Password: ");
        String password = sc.nextLine();
        l.setUsername(usenname);
        l.setPassword(password);
        l.setRole(true);
        LoginDao.insertALogin(l);
        /*Teacher t = new Teacher();
        t.setTeacherId("12");
        t.setTeacherName("HaiDuc");
        boolean check = TeacherDao.insertATeacher(t);

        //boolean check = LoginDao.insertALogin(l);
        sc.close();*/
        /*Runnable r = new Runnable() {
            public void run() {
                try {
                    AddStudent dialog = new AddStudent();
                    dialog.setTitle("Thêm Sinh Viên");
                    dialog.pack();
                    dialog.setVisible(true);
                    System.exit(0);
                } catch(Exception e) {
                    e.printStackTrace();
                }
            }
        };
        SwingUtilities.invokeLater(r);*/
        /*LoginForm dialog = new LoginForm();
        dialog.pack();
        dialog.setVisible(true);
        System.exit(0);*/
        List<Login> logins = LoginDao.getAllAccounts();
        Login login = new Login();
        login.setUsername("19120483");
        login.setPassword("19120483");
        System.out.println(LoginDao.checkAccountExisted(login));
        /*for(Login l: logins) {
            System.out.print(l.getUsername() + "\t\t");
            System.out.println(l.getPassword());
        }*/
    }

}