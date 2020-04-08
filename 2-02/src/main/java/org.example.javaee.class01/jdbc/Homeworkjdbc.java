package org.example.javaee.class01.jdbc;

import org.example.javaee.class01.model.StudentHomework;
import org.example.javaee.class01.model.Homework;

import java.sql.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Homeworkjdbc {
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
}
