package dao;

import hibernate.Login;
import hibernate.Student;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Transaction;

import org.hibernate.query.Query;
import utils.HibernateUtil;

import java.util.List;

public class LoginDao {
    public static List<Login> getAllAccounts(){
        Session session = HibernateUtil.getSessionFactory().openSession();
        List<Login> logins = null;
        try{
            final String hql = "select lo from Login lo";
            Query query = session.createQuery(hql);
            logins = query.list();
        }
        catch (HibernateException e){
            System.out.println(e.getMessage());
        } finally {
            session.close();
        }
        return logins;
    }

    public static void insertALogin(Login l){
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
    public static boolean checkAccountExisted(Login l){
        List<Login> logins = LoginDao.getAllAccounts();
        for(Login lo: logins){
            //System.out.println(lo);
            if(lo.getUsername().equals(l.getUsername()) && lo.getPassword().equals(l.getPassword()))
                return true;
        }
        return false;
    }

}
