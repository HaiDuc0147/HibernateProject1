package dao;

import hibernate.Subject;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;
import utils.HibernateUtil;

import java.sql.*;
import java.util.ArrayList;
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
    public static void deleteASubject(Subject s){
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
    public static Subject getASubject(String subjectId) {
        List<Subject> subjects = SubjectDao.getAllSubjects();
        int i = 0;
        for(;i < subjects.size(); i++)
            if(subjects.get(i).getSubjectId().equals(subjectId))
                return subjects.get(i);
        return null;
    }
    public static boolean updateASubject(Subject s) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        if (SubjectDao.getASubject(s.getSubjectId())==null) {
            return false;
        }
        Transaction t = null;
        try {
            t = session.beginTransaction();
            session.update(s);
            t.commit();
        } catch (HibernateException ex) {
            t.rollback();
            System.err.println(ex);
        } finally {
            session.close();
        }
        return true;
    }
    public static List<Subject> search(String keyword) throws SQLException {
        List<Subject> subjects = new ArrayList<>();
        String host="localhost";
        String port="5432";
        String dbname="StudentDB";
        String user="postgres";
        String pass="zxcvbnmA8";
        String dburl = "jdbc:postgresql://"+host+":"+port+"/"+dbname+"?loggerLevel=OFF";
        Connection con = DriverManager.getConnection(dburl, "postgres", "zxcvbnmA8");
        Statement stmt = con.createStatement();
        String query = "SELECT * FROM subject WHERE subject_name LIKE ? or subject_id LIKE ?";
        PreparedStatement ps = con.prepareStatement(query);
        ps.setString(1, "%" + keyword + "%");
        ps.setString(2, "%" + keyword + "%");
        ResultSet rs = ps.executeQuery();
        while (rs.next()){
            String subjectId = rs.getString("subject_id");
            int credit = rs.getInt("credit");
            String subjectName = rs.getString("subject_name");
            Subject s = new Subject(subjectId, subjectName, credit);
            subjects.add(s);
        }
        return subjects;
    }
    public static void main(String[] args) throws SQLException {
        List<Subject> subjects = SubjectDao.search("Hệ thống");
        for(Subject s: subjects){
            System.out.println(s.getSubjectName());
        }
    }
}
