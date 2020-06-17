package org.example.javaee.class01.jdbc;

import org.example.javaee.class01.model.Homework;
import org.example.javaee.class01.model.Student;
import org.example.javaee.class01.model.StudentHomework;
import org.example.javaee.class01.model.Teacher;


import java.util.List;

public interface BlogMapper {
    int Studentc(Long studentid);
    int Teacherc(Long teacherid);
    Student Student(Long studentid);
    Teacher Teacher(Long teacherid);
    Homework StudentHomeworkH(Long homeworkid);
    StudentHomework StudentHomeworkSH(Long shid);
    void addStudentHomework(StudentHomework sh);
    void addReview(StudentHomework sh);
    void Revisehomework(StudentHomework sh);
    List<StudentHomework> selectAll();
    List<StudentHomework> selectAllMyHomework(Long sid);
    List<Homework> selectAllHomework();
    void addHomework(Homework h);
    void addStudent(Student s);
    void addTeacher(Teacher t);
}
