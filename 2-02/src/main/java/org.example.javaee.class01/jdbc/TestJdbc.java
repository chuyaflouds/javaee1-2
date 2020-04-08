package org.example.javaee.class01.jdbc;

import javax.swing.*;
import java.io.PrintWriter;
import java.sql.*;

public class TestJdbc {

    public static String getMysql(){
        //contract为数据库名称，可替换  limit 3为限制获取行数
        String url="jdbc:mysql://127.0.0.1:3306/contract?useUnicode=true&characterEncoding=utf-8&useSSL=false";
        String driverName="com.mysql.jdbc.Driver";
        String sqlString="select * from contract limit 3";
        DriverManager.setLogWriter(new PrintWriter(System.out));

        try {
            //加载驱动
 //           Class.forName(driverName);
            //创建连接
            Connection connection=DriverManager.getConnection(url,"root","123456");
            //通过连接获取statement
            Statement statement=connection.createStatement();
            //statement做一些增删改查
            ResultSet resultSet=statement.executeQuery(sqlString);
            //获取执行结果
            while(resultSet.next()){
                //getLong中的变量为获取的列数
                System.out.println(resultSet.getLong(1));
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }
    public static void main(String[] args) {
        getMysql();
    }
}
