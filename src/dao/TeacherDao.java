package dao;

import hibernate.Teacher;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;
import utils.HibernateUtil;

import java.util.List;

public class TeacherDao {
    public static List<Teacher> getAllTeacher(){
        Session session = HibernateUtil.getSessionFactory().openSession();
        List<Teacher> teachers = null;
        try{
            final String hql = "select tc from Teacher tc";
            Query query = session.createQuery(hql);
            teachers = query.list();
        }
        catch (HibernateException e){
            System.out.println(e.getMessage());
        } finally {
            session.close();
        }
        return teachers;
    }
    public static boolean insertATeacher(Teacher tc){

        Session session = HibernateUtil.getSessionFactory().openSession();
        Transaction t = session.beginTransaction();
        session.save(tc);
        /*Query query = session.createQuery("insert into Teacher(teacherId, teacherName) select " +
                "teacherId, teacherName from Teacher");
        int update = query.executeUpdate();
        if(update == 0 || update == 1)
        {
            System.out.println(update + " row affected");
        }
        else
            System.out.println(update + " rows affected");

        System.out.println("Inserted Records Successfully");
        System.out.println("Successfully updated");*/
        t.commit();
        session.close();
        return true;
    }
}
