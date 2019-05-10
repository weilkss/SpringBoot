package com.example.service;

import com.example.entity.UserEntity;
import com.example.mapper.UserMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @Author created by wxb007 on 2019/5/8 0008 11:37
 */
@Service
public class UserService {
    @Autowired
    UserMapper userMapper;

    public List<UserEntity> findAll() {
        return userMapper.findAll();
    }

    public Integer addNewUser(UserEntity userEntity) {
        return userMapper.insertOneData(userEntity);
    }

    public Integer updateUser(Integer id, String userName) {
        return userMapper.updateOneData(id, userName);
    }

    public Integer deleteUser(Integer id) {
        return userMapper.deleteOneData(id);
    }
}
