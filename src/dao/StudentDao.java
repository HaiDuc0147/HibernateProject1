package dao;

import hibernate.Clazz;
import hibernate.Student;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;
import utils.HibernateUtil;

import java.util.List;

public class StudentDao {
    public static List<Student> getAllStudent(){
        Session session = HibernateUtil.getSessionFactory().openSession();
        List<Student> students = null;
        try{
            final String hql = "select st from Student st";
            Query query = session.createQuery(hql);
            students = query.list();
        }
        catch (HibernateException e){
            System.out.println(e.getMessage());
        } finally {
            session.close();
        }
        return students;
    }
    public static void addStudent(Student s){
        Session session = HibernateUtil.getSessionFactory().openSession();
        Transaction t = session.beginTransaction();
        session.save(s);
        t.commit();
        session.close();
    }
    public static void deleteAStudent(Student s){
        Session session = HibernateUtil.getSessionFactory().openSession();
        Transaction transaction = session.beginTransaction();
        try {
            // your code
            session.delete(s);
            //session.flush();
            transaction.commit();
        } catch (Throwable t) {
            transaction.rollback();
            throw t;
        } finally {
            session.close();
        }
    }
}
