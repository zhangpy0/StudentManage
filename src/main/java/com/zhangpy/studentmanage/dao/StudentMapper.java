package com.zhangpy.studentmanage.dao;

import com.zhangpy.studentmanage.bean.Student;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import java.util.List;

@Mapper
@Repository
public interface StudentMapper {
    void addStudent(String name,String id,String tel,String email);

    void deleteStudent(Student student);

    void updateStudent(Student student);

    String getTel(String name);

    String getEmail(String name);

    Student getStudentByName(String name);

    Student getStudentById(String id);

    List<Student> getAllStudent();
}
