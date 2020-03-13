package com.java.code.servlet;

import com.java.code.jdbc.StudentHomeworkjdbc;
import com.java.code.model.StudentHomework;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/addStudentHomework")
public class AddStudentHomeworkServlet extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        Long studentid=(Long.valueOf(req.getParameter("student_id")));
        Long homeworkid=(Long.valueOf(req.getParameter("homework_id")));

        StudentHomeworkjdbc.addStudentHomework(studentid,homeworkid);

    }


}