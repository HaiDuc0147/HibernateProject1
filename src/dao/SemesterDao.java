package dao;

import hibernate.Clazz;
import hibernate.Login;
import hibernate.Semester;
import hibernate.Student;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;
import utils.HibernateUtil;

import java.sql.Date;
import java.util.Calendar;
import java.util.List;

public class SemesterDao {
    public static void insertASemester(Semester s){
        Session session = HibernateUtil.getSessionFactory().openSession();
        Transaction t = session.beginTransaction();
        session.save(s);
        t.commit();
        session.close();
    }
    public static void updateASemester(Semester s){
        Session session = HibernateUtil.getSessionFactory().openSession();
        Transaction t = session.beginTransaction();
        session.update(s);
        t.commit();
        session.close();
    }
    public static List<Semester> getAllSemester(){
        Session session = HibernateUtil.getSessionFactory().openSession();
        List<Semester> semesters = null;
        try{
            final String hql = "select s from Semester s";
            Query query = session.createQuery(hql);
            semesters = query.list();
        }
        catch (HibernateException e){
            System.out.println(e.getMessage());
        } finally {
            session.close();
        }
        return semesters;
    }

 //public static Semester getAClass(String classId) {
 //    List<Semester> semesters = ClassDao.getAllClasses();
 //    int i = 0;
 //    for(;i < classes.size(); i++)
 //        if(classes.get(i).getClassId().equals(classId))
 //            return classes.get(i);
 //    return null;
 //}
    public static void deleteASemester(Semester s){
        Session session = HibernateUtil.getSessionFactory().openSession();
        Transaction transaction = session.beginTransaction();
        //s.setId(SemesterDao.getASemester(l.getUsername()).getId());
        try {
            // your code

       //List<Student> students = StudentDao.getAllStudent();
       //for(Student st: students){
       //    if(st.getClassId().getClassId().equals(c.getClassId())){
       //        StudentDao.deleteAStudent(st);
       //    }
       //}
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
    public static void main(String[] args){
        Semester s = new Semester();
        s.setSemesterName("Học kỳ I");
        s.setYear("2019-2020");
        Date d = new Date(2019 - 1900, 1 - 1, 1);


        s.setStartDay(d);
        s.setEndDay(d);

        insertASemester(s);
    }
}
