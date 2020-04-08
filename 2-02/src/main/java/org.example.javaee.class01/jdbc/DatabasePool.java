package org.example.javaee.class01.jdbc;


import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;


public class DatabasePool {

    private static HikariDataSource hikariDataSource;

    public static HikariDataSource getHikariDataSource(){
        if(hikariDataSource != null){
            return hikariDataSource;
        }
        synchronized (DatabasePool.class){
            if(hikariDataSource == null){
                String url="jdbc:mysql://127.0.0.1:3306/school?serverTimezone=UTC&useUnicode=true&characterEncoding=utf-8&useSSL=false";
                String driverName="com.mysql.jdbc.Driver";
                HikariConfig hikariConfig=new HikariConfig();
                hikariConfig.setUsername("root");
                hikariConfig.setPassword("123456");
                hikariConfig.setDriverClassName(driverName);
                hikariConfig.setJdbcUrl(url);
                hikariDataSource= new HikariDataSource(hikariConfig);
                return hikariDataSource;
            }
        }
        return null;
    }
}
