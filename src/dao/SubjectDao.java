package dao;

import hibernate.Subject;
import org.hibernate.Session;
import org.hibernate.Transaction;
import utils.HibernateUtil;

public class SubjectDao {
    public static void addSubject(Subject s){
        Session session = HibernateUtil.getSessionFactory().openSession();
        Transaction t = session.beginTransaction();
        session.save(s);
        t.commit();
        session.close();
    }
}
