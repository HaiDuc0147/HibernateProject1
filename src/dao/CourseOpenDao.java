package dao;

import hibernate.Course;
import hibernate.CourseOpen;
import hibernate.Subject;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;
import utils.HibernateUtil;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class CourseOpenDao {
    public static void insertACourseOpen(CourseOpen c){
        Session session = HibernateUtil.getSessionFactory().openSession();
        Transaction t = session.beginTransaction();
        session.save(c);
        t.commit();
        session.close();
    }
    public static void updateACourseOpen(CourseOpen c){
        Session session = HibernateUtil.getSessionFactory().openSession();
        Transaction t = session.beginTransaction();
        session.update(c);
        t.commit();
        session.close();
    }
    public static List<CourseOpen> getAllCourseOpen(){
        Session session = HibernateUtil.getSessionFactory().openSession();
        List<CourseOpen> courseOpens = null;
        try{
            final String hql = "select c from CourseOpen c";
            Query query = session.createQuery(hql);
            courseOpens = query.list();
        }
        catch (HibernateException e){
            System.out.println(e.getMessage());
        } finally {
            session.close();
        }
        return courseOpens;
    }
  //public static Clazz getACourseOpen(String classId) {
  //    List<Clazz> classes = ClassDao.getAllClasses();
  //    int i = 0;
  //    for(;i < classes.size(); i++)
  //        if(classes.get(i).getClassId().equals(classId))
  //            return classes.get(i);
  //    return null;
  //}
    public static void deleteACourseOpen(CourseOpen c){
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
    public static List<CourseOpen> search(String keyword) throws SQLException {

        String host="localhost";
        String port="5432";
        String dbname="StudentDB";
        String user="postgres";
        String pass="zxcvbnmA8";
        String dburl = "jdbc:postgresql://"+host+":"+port+"/"+dbname+"?loggerLevel=OFF";
        Connection con = DriverManager.getConnection(dburl, "postgres", "zxcvbnmA8");
        Statement stmt = con.createStatement();
        String query = "SELECT * FROM course WHERE course_id LIKE ? ";
        PreparedStatement ps = con.prepareStatement(query);
        ps.setString(1, "%" + keyword + "%");
        ResultSet rs = ps.executeQuery();
        List<Course> courses = new ArrayList<>();
        List<Subject> subjects = SubjectDao.getAllSubjects();
        while (rs.next()){
            Course c = new Course();
            c.setId(rs.getInt("id"));
            c.setSlot(rs.getInt("slot"));
            c.setTeacherName(rs.getString("teacher_name"));
            c.setStudyDay(rs.getString("study_day"));
            c.setStudyTime(rs.getString("study_time"));
            c.setClassroom(rs.getString("classroom"));
            String subject_id = rs.getString("course_id");
            for(Subject s: subjects){
                if(s.getSubjectId().equals(subject_id)) {
                    c.setCourseId(s);
                    break;
                }
            }
            courses.add(c);
        }
        List<CourseOpen> courseOpens = CourseOpenDao.getAllCourseOpen();
        List<CourseOpen> result = new ArrayList<>();
        for(CourseOpen courseOpen: courseOpens){
            for(Course course: courses){
                if(courseOpen.getCourseId().toString().equals(course.toString())){
                    result.add(courseOpen);
                }
            }
        }
        return result;
    }
    public static void main(String[] args) throws SQLException {
        List<CourseOpen> c  = search("CSC");
        System.out.println(c.get(0).getStartDay());

        //insertAClass(c);
        //updateAClass(c);
        //List<Clazz> classes = getAllClasses();
        //for (Clazz cl : classes)
        //    System.out.println(cl);
    }
}
