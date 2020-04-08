package org.example.javaee.class01.jdbc;

import org.example.javaee.class01.model.Homework;
import org.example.javaee.class01.model.Student;

import java.sql.*;

public class Studentjdbc {
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
