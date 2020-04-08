package org.example.javaee.class01.servlet;

import org.example.javaee.class01.jdbc.Studentjdbc;
import org.example.javaee.class01.model.Homework;
import org.example.javaee.class01.model.Student;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Date;

@WebServlet("/addStudent")
public class AddStudentServlet extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        //Student s= new Student();
        ApplicationContext ac=new ClassPathXmlApplicationContext("app-context.xml");
        Student s=(Student)ac.getBean("student");
        s.setId(Long.valueOf(req.getParameter("id")));
        s.setName(req.getParameter("name"));
        Date date = new Date();
        s.setCreateTime(date);
        s.setUpdateTime(date);
        Studentjdbc.addStudent(s);
    }


}
