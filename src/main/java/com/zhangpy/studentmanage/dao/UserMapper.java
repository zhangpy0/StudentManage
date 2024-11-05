package com.zhangpy.studentmanage.dao;

import com.zhangpy.studentmanage.bean.User;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

@Mapper
@Repository
public interface UserMapper {
    void addUser(String username,String password);

    void deleteUser(User user);

    void updateUser(User user);

    String getPassword(String username);

    User getUserByNameAndPassword(String username,String password);

    User getUserByName(String username);
}
