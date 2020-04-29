package org.example.javaee.class01.servlet;

import org.example.javaee.class01.jdbc.Homeworkjdbc;
import org.example.javaee.class01.model.Homework;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

@WebServlet("/addHomework")
public class AddHomeworkServlet extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        Homework h = new Homework();

        h.setTitle(req.getParameter("title"));
        h.setContent(req.getParameter("content"));
        Date date = new Date();
        h.setCreateTime(date);
        h.setUpdateTime(date);

        Homeworkjdbc.addHomework(h);

    }

}
