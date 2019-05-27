package com.example.dao;

import com.example.entity.UserEntity;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @Author created by wxb007 on 2019/5/8 0008 11:29
 */
@Repository
public interface UserDao {
    /**
     * 获取所有用户
     */
    List<UserEntity> findAll();

    /**
     * 添加一个用户
     */
    Integer insertOneData(@Param("user") UserEntity userEntity);

    /**
     * 更新一个用户
     */
    Integer updateOneData(@Param("id") Integer id, @Param("userName") String userName);

    /**
     * 删除一条数据
     */
    Integer deleteOneData(@Param("id") Integer id);
}
