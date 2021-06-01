package dao;

import hibernate.Clazz;
import hibernate.Course;
import hibernate.Student;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;
import utils.HibernateUtil;

import java.util.List;

public class CourseDao {
    public static void insertACourse(Course c) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        Transaction t = session.beginTransaction();
        session.save(c);
        t.commit();
        session.close();
    }

    public static void updateACourse(Course c) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        Transaction t = session.beginTransaction();
        session.update(c);
        t.commit();
        session.close();
    }

    public static List<Course> getAllClasses() {
        Session session = HibernateUtil.getSessionFactory().openSession();
        List<Course> courses = null;
        try {
            final String hql = "select c from Course c";
            Query query = session.createQuery(hql);
            courses = query.list();
        } catch (HibernateException e) {
            System.out.println(e.getMessage());
        } finally {
            session.close();
        }
        return courses;
    }

//public static Course getACourse(String classId) {
//    List<Clazz> classes = dao.ClassDao.getAllClasses();
//    int i = 0;
//    for (; i < classes.size(); i++)
//        if (classes.get(i).getClassId().equals(classId))
//            return classes.get(i);
//    return null;
//}

    public static void deleteACourse(Course c) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        Transaction transaction = session.beginTransaction();
        try {
            // your code

       // List<Student> students = StudentDao.getAllStudent();
       // for (Student st : students) {
       //     if (st.getClassId().getClassId().equals(c.getClassId())) {
       //         StudentDao.deleteAStudent(st);
       //     }
       // }
            session.delete(c);
            //session.flush();
            transaction.commit();
        } catch (Throwable t) {
            transaction.rollback();
            throw t;
        } finally {
            session.close();
        }
    }

    public static void main(String[] args) {
        Clazz c = new Clazz("19CTT6", 0, 1, 0);
        //insertAClass(c);
        //updateAClass(c);
        //List<Clazz> classes = getAllClasses();
        //for (Clazz cl : classes)
        //    System.out.println(cl);
    }


}
