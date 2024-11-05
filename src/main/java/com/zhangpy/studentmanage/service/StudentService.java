package com.zhangpy.studentmanage.service;

import com.zhangpy.studentmanage.bean.Student;
import com.zhangpy.studentmanage.dao.StudentMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class StudentService {
    @Autowired
    private StudentMapper studentMapper;

    public void addStudent(String name,String id,String tel,String email){
        studentMapper.addStudent(name,id,tel,email);
    }

    public void deleteStudent(Student student){
        studentMapper.deleteStudent(student);
    }

    public void updateStudent(Student student){
        studentMapper.updateStudent(student);
    }

    public String getTel(String name){
        return studentMapper.getTel(name);
    }

    public String getEmail(String name){
        return studentMapper.getEmail(name);
    }

    public Student getStudentByName(String name){
        return studentMapper.getStudentByName(name);
    }

    public Student getStudentById(String id){
        return studentMapper.getStudentById(id);
    }

    public List<Student> getAllStudent(){
        return studentMapper.getAllStudent();
    }
}
