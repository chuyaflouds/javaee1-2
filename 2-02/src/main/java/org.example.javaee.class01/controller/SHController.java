package org.example.javaee.class01.controller;

import org.example.javaee.class01.jdbc.StudentHomeworkjdbc;
import org.example.javaee.class01.model.Homework;
import org.example.javaee.class01.model.Student;
import org.example.javaee.class01.model.StudentHomework;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Date;
import java.util.List;
@Controller
public class SHController {

    @RequestMapping(path="/AddHomework")
    public String AddHomework(@RequestParam(value = "title")String htitle,
                            @RequestParam(value = "content")String hcontent) {

        Homework h = new Homework();
        h.setTitle(htitle);
        h.setContent(hcontent);
        Date date = new Date();
        h.setCreateTime(date);
        h.setUpdateTime(date);

        StudentHomeworkjdbc.addHomework(h);
        return "success";
    }

    @RequestMapping(path="/AddStudentHomework")
    public String AddStudentHomework(@RequestParam(value = "student_id")Long studentid,
                                   @RequestParam(value = "homework_id")Long homeworkid) {

        StudentHomeworkjdbc.addStudentHomework(studentid,homeworkid);
        return "success";
    }

    @RequestMapping(path="/AddStudent")
    public String AddStudent(@RequestParam(value = "id")Long studentid,
                           @RequestParam(value = "name")String studentname) {

        Student s= new Student();
        s.setId(studentid);
        s.setName(studentname);
        Date date = new Date();
        s.setCreateTime(date);
        s.setUpdateTime(date);
        StudentHomeworkjdbc.addStudent(s);
        return "success";
    }

    @RequestMapping(path="/list")
    public void StudentHomework(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        List<StudentHomework> list = StudentHomeworkjdbc.selectAll();
        req.setAttribute("list",list);
        req.getRequestDispatcher("StudentHomework.jsp").forward(req,resp);
    }

}
