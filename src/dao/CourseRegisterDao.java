package dao;

import hibernate.CourseRegister;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;
import utils.HibernateUtil;

import java.sql.SQLException;
import java.util.List;

public class CourseRegisterDao {
    public static void insertACourseRegister(CourseRegister c){
        Session session = HibernateUtil.getSessionFactory().openSession();
        Transaction t = session.beginTransaction();
        session.save(c);
        t.commit();
        session.close();
    }
    public static void updateACourseRegister(CourseRegister c){
        Session session = HibernateUtil.getSessionFactory().openSession();
        Transaction t = session.beginTransaction();
        session.update(c);
        t.commit();
        session.close();
    }
    public static List<CourseRegister> getAllCourseRegister(){
        Session session = HibernateUtil.getSessionFactory().openSession();
        List<CourseRegister> courseRegisters = null;
        try{
            final String hql = "select c from CourseRegister c";
            Query query = session.createQuery(hql);
            courseRegisters = query.list();
        }
        catch (HibernateException e){
            System.out.println(e.getMessage());
        } finally {
            session.close();
        }
        return courseRegisters;
    }
    //public static Clazz getACourseOpen(String classId) {
    //    List<Clazz> classes = ClassDao.getAllClasses();
    //    int i = 0;
    //    for(;i < classes.size(); i++)
    //        if(classes.get(i).getClassId().equals(classId))
    //            return classes.get(i);
    //    return null;
    //}
    public static void deleteACourseRegister(CourseRegister c){
        Session session = HibernateUtil.getSessionFactory().openSession();
        Transaction transaction = session.beginTransaction();
        try {
            // your code

            //List<Student> students = StudentDao.getAllStudent();
            //for(Student st: students){
            //    if(st.getClassId().getClassId().equals(c.getClassId())){
            //        StudentDao.deleteAStudent(st);
            //    }
            //}
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

    public static void main(String[] args) throws SQLException {

        //insertAClass(c);
        //updateAClass(c);
        //List<Clazz> classes = getAllClasses();
        //for (Clazz cl : classes)
        //    System.out.println(cl);
    }
}
