package com.zhangpy.studentmanage;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@MapperScan("com.zhangpy.studentmanage.dao")
public class StudentManageApplication {

	public static void main(String[] args) {
		SpringApplication.run(StudentManageApplication.class, args);
	}

}
