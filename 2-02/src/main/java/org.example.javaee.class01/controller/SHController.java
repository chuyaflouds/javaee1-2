package org.example.javaee.class01.controller;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.example.javaee.class01.jdbc.BlogMapper;
import org.example.javaee.class01.model.Homework;
import org.example.javaee.class01.model.Student;
import org.example.javaee.class01.model.StudentHomework;
import org.example.javaee.class01.model.Teacher;
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
    public Long hid;
    public Long shid;
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

    @RequestMapping(path="/Register")
    public String Register(@RequestParam(value = "id")Long id,
                           @RequestParam(value = "n")String name,
                           @RequestParam(value = "pw")String pw,
                           @RequestParam(value = "role")String role) {

        if(role.equals("student")&&BlogMapper.Studentc(id)==0){
            Student s=new Student();
            s.setId(id);
            s.setName(name);
            s.setPassword(pw);
            BlogMapper.addStudent(s);
            sqlSession.commit();
            return "success";
        }
        else if(role.equals("teacher")&&BlogMapper.Teacherc(id)==0){
            Teacher t=new Teacher();
            t.setId(id);
            t.setName(name);
            t.setPassword(pw);
            BlogMapper.addTeacher(t);
            sqlSession.commit();
            return "success";
        }
        else
            return "fail";
    }

    @RequestMapping(path="/Login")
    public String Login(@RequestParam(value = "id")Long id,
                        @RequestParam(value = "pw")String pw,
                        @RequestParam(value = "role")String role) {
    int s=-1;
    int t=-1;
    String password;
    if(role.equals("student")){
        s=BlogMapper.Studentc(id);
        sid=id;
    }
    else{
        t=BlogMapper.Teacherc(id);
    }
    if(s==1&&t==-1){
        Student s1=BlogMapper.Student(id);
        password=s1.getPassword();
        if(password.equals(pw))
            return "StudentH";
        else
            return "fail";

    }
    else if(t==1&&s==-1){
        Teacher t1=BlogMapper.Teacher(id);
        password=t1.getPassword();
        if(password.equals(pw))
            return "TeacherH";
        else
            return "fail";
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
    public String AddStudentHomework(@RequestParam(value = "s_content")String submitcontent) {

        StudentHomework sh=new StudentHomework();
        Homework h=BlogMapper.StudentHomeworkH(hid);
        sh.setStudent_id(sid);
        sh.setHomework_id(h.getId());
        sh.setHomework_title(h.getTitle());
        sh.setHomework_content(h.getContent());
        sh.setSubmit_content(submitcontent);
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
        s.setPassword("000000");
        BlogMapper.addStudent(s);
        sqlSession.commit();
        return "success";
    }


    @RequestMapping(path="/AddReview")
    public String AddReview(@RequestParam(value = "review")String review) {
        StudentHomework sh=BlogMapper.StudentHomeworkSH(shid);
        sh.setReview_content(review);
        BlogMapper.addReview(sh);
        sqlSession.commit();
        return "success";
    }

    @RequestMapping(path="/Revise")
    public String Revise(@RequestParam(value = "revise")String revise) {
        StudentHomework sh=BlogMapper.StudentHomeworkSH(shid);
        sh.setSubmit_content(revise);
        BlogMapper.Revisehomework(sh);
        sqlSession.commit();
        return "success";
    }

    @RequestMapping(path="/GetSHid")
    public String GetSHid(@RequestParam(value = "id")Long id) {
        shid=id;
        return "review";
    }

    @RequestMapping(path="/GetHid")
    public String GetHid(@RequestParam(value = "id")Long id) {
        hid=id;
        return "addStudentHomework";
    }

    @RequestMapping(path="/GetNSHid")
    public String GetNSHid(@RequestParam(value = "id")Long id) {
        shid=id;
        return "revise";
    }


    @RequestMapping(path="/list")
    public void StudentHomework(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        List<StudentHomework> list = BlogMapper.selectAll();
        req.setAttribute("list",list);
        req.getRequestDispatcher("StudentHomework.jsp").forward(req,resp);
    }

    @RequestMapping(path="/homeworklist")
    public void Homework(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        List<Homework> list = BlogMapper.selectAllHomework();
        req.setAttribute("list",list);
        req.getRequestDispatcher("uncompleted.jsp").forward(req,resp);
    }

    @RequestMapping(path="/mylist")
    public void MyHomework(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        List<StudentHomework> list = BlogMapper.selectAllMyHomework(sid);
        req.setAttribute("list",list);
        req.getRequestDispatcher("updatehomework.jsp").forward(req,resp);
    }


}
