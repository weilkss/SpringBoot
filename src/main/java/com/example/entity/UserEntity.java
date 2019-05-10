package com.example.entity;

import lombok.Data;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.NotBlank;

/**
 * @Author created by wxb007 on 2019/5/8 0008 11:37
 */
@Data
public class UserEntity {
    private Integer id;


    @NotBlank(message = "用户名不能为空")
    private String userName;


    @NotBlank(message = "密码不能为空")
    @Length(min = 6, message = "密码长度至少为6位")
    @Length(max = 18, message = "密码长度最大为18位")
    private String passWord;


    private String realName;
}

