package com.java.code.jdbc;

import javax.swing.*;
import java.sql.*;

public class TestJdbc3 {

    public static String getMysql(){
         String url="jdbc:mysql://127.0.0.1:3306/contract?useUnicode=true&characterEncoding=utf-8&useSSL=false";
         String driverName="com.mysql.jdbc.Driver";
         String sqlString="select * from contract.contract";
        try {
            Class.forName(driverName);
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }

        try(Connection connection=DriverManager.getConnection(url,"root","123456")){
            try(Statement statement=connection.createStatement()){
                try(ResultSet resultSet=statement.executeQuery(sqlString)){
                    while(resultSet.next()){
                        System.out.println(resultSet.getLong(1));
                    }
                }
            }
        }catch (SQLException e) {
        e.printStackTrace();
    }
        return null;
    }
    public static void main(String[] args) {
        getMysql();
    }
}
