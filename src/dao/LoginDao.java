package dao;

import hibernate.Login;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;
import utils.HibernateUtil;

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
            if (lo.getUsername().equals(l.getUsername()) && lo.getPassword().equals(l.getPassword()))
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

    public static void main(String[] args) {
        Login l = new Login();
        //l.setId(1);
     //l.setUsername("thđức");
     //l.setPassword("thđức");
     //l.setRole(true);
        l.setId(1);

        //LoginDao.updateAccount(l);
        LoginDao.deleteAnAccount(l);
    }
}
