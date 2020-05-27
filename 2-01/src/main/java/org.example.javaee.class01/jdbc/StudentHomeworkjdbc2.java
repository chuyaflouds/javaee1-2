package org.example.javaee.class01.jdbc;

import org.example.javaee.class01.model.Homework;
import org.example.javaee.class01.model.Student;
import org.example.javaee.class01.model.StudentHomework;
//import org.springframework.context.ApplicationContext;
//import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.sql.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

public class StudentHomeworkjdbc2 {
    public static void addStudentHomework(Long studentid,Long homeworkid){

        String selects_sql="select * from school.s_student where id='"+studentid+"'";
        String selecth_sql="select * from school.s_homework where id='"+homeworkid+"'";

        boolean fs=false;
        boolean fh=false;
        StudentHomework sh=new StudentHomework();
        Student s=new Student();
        Homework h=new Homework();
        //ApplicationContext ac=new ClassPathXmlApplicationContext("app-context.xml");
        //Student s=(Student)ac.getBean("student");
        //Homework h=(Homework)ac.getBean("homework");
        //StudentHomework sh=(StudentHomework)ac.getBean("studenthomework");
        try{
            Connection connection= DatabasePool.getHikariDataSource().getConnection();
            Statement statement=connection.createStatement();
            ResultSet resultSet=statement.executeQuery(selects_sql);
            while(resultSet.next()){
                if(resultSet.getLong("id")==studentid){
                    s.setId(resultSet.getLong("id"));
                    s.setName(resultSet.getString("name"));
                    fs=true;
                }
            }
            ResultSet resultSet2=statement.executeQuery(selecth_sql);
            while(resultSet2.next()){
                if(resultSet2.getLong("id")==homeworkid){
                    h.setId(resultSet2.getLong("id"));
                    h.setTitle(resultSet2.getString("title"));
                    h.setContent(resultSet2.getString("content"));
                    fh=true;
                }
            }
        }catch (SQLException e) {
            e.printStackTrace();
        }
        if(fs==true&&fh==true){
            sh.setStudentId(s.getId());
            sh.setHomeworkId(h.getId());
            sh.setHomeworkTitle(h.getTitle());
            sh.setHomeworkContent(h.getContent());
            Date date = new Date();
            sh.setCreateTime(date);
            sh.setUpdateTime(date);
            String insert_sql = "insert into school.s_student_homework (student_id,homework_id,homework_title" +
                    ",homework_content,create_time,update_time) VALUES (?,?,?,?,?,?)";
            try(Connection connection= DatabasePool.getHikariDataSource().getConnection()){
                PreparedStatement stat = connection.prepareStatement(insert_sql);
                stat.setLong(1,sh.getStudentId());
                stat.setLong(2,sh.getHomeworkId());
                stat.setString(3, sh.getHomeworkTitle());
                stat.setString(4, sh.getHomeworkContent());
                stat.setObject(5, sh.getCreateTime());
                stat.setObject(6, sh.getUpdateTime());
                int i = stat.executeUpdate();
            }catch (SQLException e) {
                e.printStackTrace();
            }
        }

    }
    public static List<StudentHomework> selectAll(){

        String sqlString="select * from school.s_student_homework";
        List<StudentHomework> list = new ArrayList<>();
        try(Connection connection= DatabasePool.getHikariDataSource().getConnection()){
            try(Statement statement=connection.createStatement()){
                try(ResultSet resultSet=statement.executeQuery(sqlString)){
                    while(resultSet.next()){
                        StudentHomework sh = new StudentHomework();
                        sh.setId(resultSet.getLong("id"));
                        sh.setStudentId(resultSet.getLong("student_id"));
                        sh.setHomeworkId(resultSet.getLong("id"));
                        sh.setHomeworkTitle(resultSet.getString("homework_title"));
                        sh.setHomeworkContent(resultSet.getString("homework_content"));
                        sh.setCreateTime(resultSet.getTimestamp("create_time"));
                        list.add(sh);
                    }
                }
            }
        }catch (SQLException e) {
            e.printStackTrace();
        }
        return list;
    }
    public static void addHomework(Homework h){
        String insert_sql = "insert into school.s_homework (title,content,create_time,update_time) "
                + "VALUES (?,?,?,?)";
        try(Connection connection= DatabasePool.getHikariDataSource().getConnection()){
            PreparedStatement stat = connection.prepareStatement(insert_sql);
            stat.setString(1, h.getTitle());
            stat.setString(2, h.getContent());
            stat.setObject(3, h.getCreateTime());
            stat.setObject(4, h.getUpdateTime());
            int i = stat.executeUpdate();
        }catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static void addStudent(Student s){

        String insert_sql = "insert into school.s_student VALUES (?,?,?,?)";
        try(Connection connection= DatabasePool.getHikariDataSource().getConnection()){
            PreparedStatement stat = connection.prepareStatement(insert_sql);
            stat.setLong(1, s.getId());
            stat.setString(2, s.getName());
            stat.setObject(3, s.getCreateTime());
            stat.setObject(4, s.getUpdateTime());
            int i = stat.executeUpdate();
        }catch (SQLException e) {
            e.printStackTrace();
        }
    }


}
