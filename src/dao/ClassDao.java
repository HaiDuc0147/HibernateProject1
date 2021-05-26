package dao;


import hibernate.Clazz;
import org.hibernate.Session;
import org.hibernate.Transaction;
import utils.HibernateUtil;

public class ClassDao {
    public static void insertAClass(Clazz c){
        //Login l = new Login();
        //l.setUsername(username);
        //l.setPassword(password);
        //l.setRole(role);
        Session session = HibernateUtil.getSessionFactory().openSession();
        Transaction t = session.beginTransaction();
        session.save(c);
        t.commit();
        session.close();
    }
}
