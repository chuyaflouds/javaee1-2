package org.example.javaee.class01.jdbc;

import javax.swing.*;
import java.sql.*;

public class TestJdbc2 {

    public static String getMysql(){
         String url="jdbc:mysql://127.0.0.1:3306/contract?useUnicode=true&characterEncoding=utf-8&useSSL=false";
         String driverName="com.mysql.jdbc.Driver";
         String sqlString="select * from contract";
        try {
            //加载驱动
            Class.forName(driverName);
            //创建连接
            Connection connection=DriverManager.getConnection(url,"root","123456");
            //通过连接获取statement
            Statement statement=connection.createStatement();
            //statement做一些增删改查
            ResultSet resultSet=statement.executeQuery(sqlString);
            //获取执行结果
            while(resultSet.next()){
                System.out.println(resultSet.getLong(1));
            }

        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
        }
        return null;
    }
    public static void main(String[] args) {
        getMysql();
    }
}
