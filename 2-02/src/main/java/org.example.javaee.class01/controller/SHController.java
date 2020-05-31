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
    public Long sid;
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

    @RequestMapping(path="/Login")
    public String Login(@RequestParam(value = "id")Long id,
                              @RequestParam(value = "role")String role) {
    int s=-1;
    int t=-1;

    if(role.equals("student")){
        s=BlogMapper.Student(id);
        sid=id;
    }
    else if(role.equals("teacher")){
        t=BlogMapper.Teacher(id);
    }
    else{
        System.out.println(role);
    }
    if(s==1&&t==-1){
        return "StudentH";
    }
    else if(t==1&&s==-1){
        return "TeacherH";
    }
    else{
        return "fail";
    }

    }

    @RequestMapping(path="/AddHomework")
    public String AddHomework(@RequestParam(value = "title")String htitle,
                            @RequestParam(value = "content")String hcontent) {

        Homework h = new Homework();
        h.setTitle(htitle);
        h.setContent(hcontent);
        BlogMapper.addHomework(h);
        sqlSession.commit();
        return "success";
    }

    @RequestMapping(path="/AddStudentHomework")
    public String AddStudentHomework(@RequestParam(value = "homework_id")Long homeworkid) {

        StudentHomework sh=new StudentHomework();
        Homework h=BlogMapper.StudentHomeworkH(homeworkid);
        sh.setStudent_id(sid);
        sh.setHomework_id(h.getId());
        sh.setHomework_title(h.getTitle());
        sh.setHomework_content(h.getContent());
        BlogMapper.addStudentHomework(sh);
        sqlSession.commit();
        return "success";
    }

    @RequestMapping(path="/AddStudent")
    public String AddStudent(@RequestParam(value = "id")Long studentid,
                           @RequestParam(value = "name")String studentname) {

        Student s= new Student();
        s.setId(studentid);
        s.setName(studentname);
        BlogMapper.addStudent(s);
        sqlSession.commit();
        return "success";
    }

    @RequestMapping(path="/list")
    public void StudentHomework(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        List<StudentHomework> list = BlogMapper.selectAll();
        req.setAttribute("list",list);
        req.getRequestDispatcher("StudentHomework.jsp").forward(req,resp);
    }

}
