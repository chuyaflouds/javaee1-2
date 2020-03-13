package com.java.code.servlet;

import com.java.code.jdbc.StudentHomeworkjdbc;
import com.java.code.model.StudentHomework;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@WebServlet("/list")
public class StudentHomeworkServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        List<StudentHomework> list = StudentHomeworkjdbc.selectAll();
        req.setAttribute("list",list);
        req.getRequestDispatcher("StudentHomework.jsp").forward(req,resp);

    }


}
