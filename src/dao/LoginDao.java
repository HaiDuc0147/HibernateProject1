package dao;

import hibernate.Login;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;
import utils.HibernateUtil;
import utils.Utils;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class LoginDao {
    public static List<Login> getAllAccounts() {
        Session session = HibernateUtil.getSessionFactory().openSession();
        List<Login> logins = null;
        try {
            final String hql = "select lo from Login lo";
            Query query = session.createQuery(hql);
            logins = query.list();
        } catch (HibernateException e) {
            System.out.println(e.getMessage());
        } finally {
            session.close();
        }
        return logins;
    }

    public static Login getAnAccount(String username) {
        List<Login> logins = LoginDao.getAllAccounts();
        int i = 0;
        for (; i < logins.size(); i++)
            if (logins.get(i).getUsername().equals(username))
                return logins.get(i);
        return null;
    }


    public static void insertALogin(Login l) {
        //Login l = new Login();
        //l.setUsername(username);
        //l.setPassword(password);
        //l.setRole(role);
        Session session = HibernateUtil.getSessionFactory().openSession();
        Transaction t = session.beginTransaction();
        session.save(l);
        t.commit();
        session.close();
    }

    public static boolean checkAccountExisted(Login l) {
        List<Login> logins = LoginDao.getAllAccounts();
        for (Login lo : logins) {
            //System.out.println(lo);
            if (lo.getUsername().equals(l.getUsername()) && lo.getPassword().equals(l.getPassword()) && lo.getRole() == l.getRole())
                return true;
        }
        return false;
    }

    public static boolean updateAccount(Login l) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        if (LoginDao.getAnAccount(l.getUsername()) == null) {
            return false;
        }
        l.setId(LoginDao.getAnAccount(l.getUsername()).getId());
        Transaction t = null;
        try {
            t = session.beginTransaction();
            session.update(l);
            t.commit();
        } catch (HibernateException ex) {
            t.rollback();
            System.err.println(ex);
        } finally {
            session.close();
        }
        return true;
    }

    public static void deleteAnAccount(Login l) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        Transaction transaction = session.beginTransaction();
        //s.setId(SemesterDao.getASemester(l.getUsername()).getId());
        session.delete(l);
        transaction.commit();
        session.close();

    }
    public static List<Login> search(String keyword) throws SQLException {
        List<Login> logins = new ArrayList<>();
        String host="localhost";
        String port="5432";
        String dbname="StudentDB";
        String user="postgres";
        String pass="zxcvbnmA8";
        String dburl = "jdbc:postgresql://"+host+":"+port+"/"+dbname+"?loggerLevel=OFF";
        Connection con = DriverManager.getConnection(dburl, "postgres", "zxcvbnmA8");
        Statement stmt = con.createStatement();
        String query = "SELECT * FROM login WHERE username LIKE ? ";
        PreparedStatement ps = con.prepareStatement(query);
        keyword = Utils.formatNameToUsername(keyword);
        ps.setString(1, "%" + keyword + "%");
        ResultSet rs = ps.executeQuery();
        while (rs.next()){
            String usernmame = rs.getString("username");
            String password = rs.getString("password");
            Boolean role = rs.getBoolean("role");
            Login l = new Login(usernmame, password, role);
            logins.add(l);
        }
        return logins;
    }
    public static void main(String[] args) throws SQLException {
        List<Login> logins = LoginDao.search("Văn Khiết");
        for(Login l: logins){
            System.out.println(l.getUsername());
        }
    }
}
