package com.java.code.jdbc;

import com.java.code.model.Homework;
import com.java.code.model.Student;

import java.sql.*;

public class Studentjdbc {
    public static void addStudent(Student s){
        String url="jdbc:mysql://127.0.0.1:3306/school?useUnicode=true&characterEncoding=utf-8&useSSL=false";
        String driverName="com.mysql.jdbc.Driver";
        String insert_sql = "insert into school.s_student VALUES (?,?,?,?)";
        try(Connection connection= DriverManager.getConnection(url,"root","123456")){
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
