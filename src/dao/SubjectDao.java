package dao;

import hibernate.Subject;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;
import utils.HibernateUtil;

import java.util.List;

public class SubjectDao {
    public static void addSubject(Subject s){
        Session session = HibernateUtil.getSessionFactory().openSession();
        Transaction t = session.beginTransaction();
        session.save(s);
        t.commit();
        session.close();
    }
    public static List<Subject> getAllSubjects(){
        Session session = HibernateUtil.getSessionFactory().openSession();
        List<Subject> subjects = null;
        try{
            final String hql = "select s from Subject s";
            Query query = session.createQuery(hql);
            subjects = query.list();
        }
        catch (HibernateException e){
            System.out.println(e.getMessage());
        } finally {
            session.close();
        }
        return subjects;
    }
}
