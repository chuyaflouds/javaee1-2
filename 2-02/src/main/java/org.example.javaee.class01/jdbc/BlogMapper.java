package org.example.javaee.class01.jdbc;

import org.example.javaee.class01.model.Homework;
import org.example.javaee.class01.model.Student;
import org.example.javaee.class01.model.StudentHomework;


import java.util.List;

public interface BlogMapper {
    int Student(Long studentid);
    int Teacher(Long teacherid);
    Homework StudentHomeworkH(Long homeworkid);
    void addStudentHomework(StudentHomework sh);
    List<StudentHomework> selectAll();
    void addHomework(Homework h);
    void addStudent(Student s);
}
