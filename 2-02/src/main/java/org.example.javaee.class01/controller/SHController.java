package org.example.javaee.class01.controller;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.example.javaee.class01.jdbc.BlogMapper;
import org.example.javaee.class01.model.Homework;
import org.example.javaee.class01.model.Student;
import org.example.javaee.class01.model.StudentHomework;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.InputStream;
import java.util.List;
@Controller
public class SHController{

    public BlogMapper BlogMapper;
    public SqlSession sqlSession;

    public SHController() throws IOException {
        // 指定全局配置文件
        String resource = "mybatis-config.xml";
        // 读取配置文件
        InputStream inputStream = Resources.getResourceAsStream(resource);
        // 构建sqlSessionFactory
        SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().build(inputStream);
        // 获取sqlSession
        sqlSession = sqlSessionFactory.openSession();
        BlogMapper=sqlSession.getMapper(BlogMapper.class);
    }

    @RequestMapping(path="/AddHomework")
    public String AddHomework(@RequestParam(value = "title")String htitle,
                            @RequestParam(value = "content")String hcontent) {

        Homework h = new Homework();
        h.setTitle(htitle);
        h.setContent(hcontent);
//        Date date = new Date();
//        h.setCreateTime(date);
//        h.setUpdateTime(date);
        BlogMapper.addHomework(h);
        this.sqlSession.commit();
//        StudentHomeworkjdbc.addHomework(h);
        return "success";
    }

    @RequestMapping(path="/AddStudentHomework")
    public String AddStudentHomework(@RequestParam(value = "student_id")Long studentid,
                                   @RequestParam(value = "homework_id")Long homeworkid) {
//        boolean fs=false;
//        boolean fh=false;
//        StudentHomework sh=new StudentHomework();
//        Student s=new Student();
//        Homework h=new Homework();
//        h = this.BlogMapper.StudentHomeworkH(homeworkid);
//        StudentHomeworkjdbc.addStudentHomework(studentid,homeworkid);
        return "success";
    }

    @RequestMapping(path="/AddStudent")
    public String AddStudent(@RequestParam(value = "id")Long studentid,
                           @RequestParam(value = "name")String studentname) {

        Student s= new Student();
        s.setId(studentid);
        s.setName(studentname);
//        Date date = new Date();
//        s.setCreateTime(date);
//        s.setUpdateTime(date);
//        StudentHomeworkjdbc.addStudent(s);
        BlogMapper.addStudent(s);
        this.sqlSession.commit();
        return "success";
    }

    @RequestMapping(path="/list")
    public void StudentHomework(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        List<StudentHomework> list = BlogMapper.selectAll();
//        List<StudentHomework> list = StudentHomeworkjdbc.selectAll();
        req.setAttribute("list",list);
        req.getRequestDispatcher("StudentHomework.jsp").forward(req,resp);
    }

}
