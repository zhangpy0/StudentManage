package com.zhangpy.studentmanage.service;

import com.zhangpy.studentmanage.bean.User;
import com.zhangpy.studentmanage.dao.UserMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserService {
    @Autowired
    private UserMapper userMapper;

    public void addUser(String username,String password){
        userMapper.addUser(username,password);
    }

    public void deleteUser(User user){
        userMapper.deleteUser(user);
    }

    public void updateUser(User user){
        userMapper.updateUser(user);
    }

    public String getPassword(String username){
        return userMapper.getPassword(username);
    }

    public User getUserByNameAndPassword(String username,String password){
        return userMapper.getUserByNameAndPassword(username,password);
    }

    public User getUserByName(String username) {
        return userMapper.getUserByName(username);
    }
}
