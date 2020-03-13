package com.java.code.jdbc;

import com.java.code.model.Homework;
import com.java.code.model.Student;
import com.java.code.model.StudentHomework;

import java.sql.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

public class StudentHomeworkjdbc {
    public static void addStudentHomework(Long studentid,Long homeworkid){
        String url="jdbc:mysql://127.0.0.1:3306/school?useUnicode=true&characterEncoding=utf-8&useSSL=false";
        String driverName="com.mysql.jdbc.Driver";
        String selects_sql="select * from school.s_student where id='"+studentid+"'";
        String selecth_sql="select * from school.s_homework where id='"+homeworkid+"'";

        boolean fs=false;
        boolean fh=false;
        StudentHomework sh=new StudentHomework();
        Student s=new Student();
        Homework h=new Homework();
        try{
            Connection connection= DriverManager.getConnection(url,"root","123456");
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
            try(Connection connection= DriverManager.getConnection(url,"root","123456")){
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
        String url="jdbc:mysql://127.0.0.1:3306/school?useUnicode=true&characterEncoding=utf-8&useSSL=false";
        String driverName="com.mysql.jdbc.Driver";
        String sqlString="select * from school.s_student_homework";
        try {
            Class.forName(driverName);
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        List<StudentHomework> list = new ArrayList<>();
        try(Connection connection= DriverManager.getConnection(url,"root","123456")){
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
}
