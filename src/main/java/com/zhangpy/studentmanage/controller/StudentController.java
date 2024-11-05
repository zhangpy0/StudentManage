package com.zhangpy.studentmanage.controller;

import com.zhangpy.studentmanage.bean.Student;
import com.zhangpy.studentmanage.service.StudentService;
import com.zhangpy.studentmanage.util.Result;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class StudentController {

    private static final Logger logger = org.slf4j.LoggerFactory.getLogger(LoginController.class);

    @Autowired
    private RedisTemplate<String, String> redisTemplate;

    @Autowired
    private StudentService StudentService;

    private boolean checkToken(long id, String token){
        String redisToken = redisTemplate.opsForValue().get(String.valueOf(id));
        return token.equals(redisToken);
    }

    @Operation(summary = "获取学生列表")
    @RequestMapping(value = "/getStudentList", method = RequestMethod.POST)
    public Result getStudentList(
            @Parameter(description = "用户id")
            @RequestParam(value = "id")
            long id,
            @Parameter(description = "token")
            @RequestParam(value = "token")
            String token
    ) {
        logger.info("id:"+id+" token:"+token);
        if (checkToken(id,token)){
            List<Student> studentList = StudentService.getAllStudent();
            if (studentList != null) {
                return Result.ok(studentList, "获取成功");
            }
            else {
                return Result.fail(404,"获取学生列表失败",null);
            }
        }else {
            return Result.fail(403,"登录状态异常",null);
        }
    }

    @Operation(summary = "添加学生")
    @RequestMapping(value = "/addStudent", method = RequestMethod.POST)
    public Result addStudent(
            @Parameter(description = "用户id")
            @RequestParam(value = "id")
            long id,
            @Parameter(description = "token")
            @RequestParam(value = "token")
            String token,
            @Parameter(description = "学生姓名")
            @RequestParam(value = "name")
            String name,
            @Parameter(description = "学生学号")
            @RequestParam(value = "studentId")
            String studentId,
            @Parameter(description = "学生电话")
            @RequestParam(value = "tel")
            String tel,
            @Parameter(description = "学生邮箱")
            @RequestParam(value = "email")
            String email
    ) {
        logger.info("id:"+id+" token:"+token+" name:"+name+" studentId:"+studentId+" tel:"+tel+" email:"+email);
        if (checkToken(id,token)){
            if (name == null || studentId == null || tel == null || email == null){
                return Result.fail(405,"参数错误",null);
            }
            else {
                Student student = StudentService.getStudentById(studentId);
                if (student != null){
                    return Result.fail(406,"学号已存在",null);
                }
                else {
                    StudentService.addStudent(name,studentId,tel,email);
                    student = StudentService.getStudentById(studentId);
                    if (student != null){
                        return Result.ok(student,"添加成功");
                    }
                    else {
                        return Result.fail(407,"添加失败",null);
                    }
                }
            }
        }else {
            return Result.fail(403,"登录状态异常",null);
        }
    }

    @Operation(summary = "删除学生")
    @RequestMapping(value = "/deleteStudent", method = RequestMethod.POST)
    public Result deleteStudent(
            @Parameter(description = "用户id")
            @RequestParam(value = "id")
            long id,
            @Parameter(description = "token")
            @RequestParam(value = "token")
            String token,
            @Parameter(description = "学生学号")
            @RequestParam(value = "studentId")
            String studentId
    ) {
        logger.info("id:"+id+" token:"+token+" studentId:"+studentId);
        if (checkToken(id,token)){
            Student student = StudentService.getStudentById(studentId);
            if (student != null){
                StudentService.deleteStudent(student);
                student = StudentService.getStudentById(studentId);
                if (student == null){
                    return Result.ok(null,"删除成功");
                }
                else {
                    return Result.fail(408,"删除失败",null);
                }
            }
            else {
                return Result.fail(409,"学号不存在",null);
            }
        }else {
            return Result.fail(403,"登录状态异常",null);
        }
    }

    @Operation(summary = "更新学生")
    @RequestMapping(value = "/updateStudent", method = RequestMethod.POST)
    public Result updateStudent(
            @Parameter(description = "用户id")
            @RequestParam(value = "id")
            long id,
            @Parameter(description = "token")
            @RequestParam(value = "token")
            String token,
            @Parameter(description = "学生姓名")
            @RequestParam(value = "name")
            String name,
            @Parameter(description = "学生学号")
            @RequestParam(value = "studentId")
            String studentId,
            @Parameter(description = "学生电话")
            @RequestParam(value = "tel")
            String tel,
            @Parameter(description = "学生邮箱")
            @RequestParam(value = "email")
            String email
    ) {
        logger.info("id:"+id+" token:"+token+" name:"+name+" studentId:"+studentId+" tel:"+tel+" email:"+email);
        if (checkToken(id,token)){
            if (name == null || studentId == null || tel == null || email == null){
                return Result.fail(405,"参数错误",null);
            }
            else {
                Student student = StudentService.getStudentById(studentId);
                if (student == null){
                    return Result.fail(409,"学号不存在",null);
                }
                else {
                    student.setName(name);
                    student.setTel(tel);
                    student.setEmail(email);
                    StudentService.updateStudent(student);
                    student = StudentService.getStudentById(studentId);
                    if (student != null){
                        return Result.ok(student,"更新成功");
                    }
                    else {
                        return Result.fail(410,"更新失败",null);
                    }
                }
            }
        }else {
            return Result.fail(403,"登录状态异常",null);
        }
    }
}
