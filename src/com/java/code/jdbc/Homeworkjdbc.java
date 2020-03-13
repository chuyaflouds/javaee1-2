package com.java.code.jdbc;

import com.java.code.model.Homework;
import com.java.code.model.StudentHomework;

import java.sql.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Homeworkjdbc {
    public static void addHomework(Homework h){
        String url="jdbc:mysql://127.0.0.1:3306/school?useUnicode=true&characterEncoding=utf-8&useSSL=false";
        String driverName="com.mysql.jdbc.Driver";
        String insert_sql = "insert into school.s_homework (title,content,create_time,update_time) "
                + "VALUES (?,?,?,?)";
        try(Connection connection= DriverManager.getConnection(url,"root","123456")){
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
}
/*public class Homeworkjdbc {
    public static void addHomework(Homework h){
        String url="jdbc:mysql://127.0.0.1:3306/school?useUnicode=true&characterEncoding=utf-8&useSSL=false";
        String driverName="com.mysql.jdbc.Driver";
        Timestamp timestamp1 = new Timestamp(h.getCreateTime().getTime());
        Timestamp timestamp2 = new Timestamp(h.getUpdateTime().getTime());
        String insert_sql = "insert into school.s_homework (title,content,create_time,update_time) " + "values ('" + h.getTitle()
                + "','" +h.getContent() + "'," + timestamp1  + ","+ timestamp2 + ")";
        try(Connection connection= DriverManager.getConnection(url,"root","123456")){
            try(Statement statement=connection.createStatement()){
                try(ResultSet resultSet=statement.executeQuery(insert_sql)){
                }
            }
        }catch (SQLException e) {
            e.printStackTrace();
        }
    }
}

 */
