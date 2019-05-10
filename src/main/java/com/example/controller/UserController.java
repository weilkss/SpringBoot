package com.example.controller;

import com.example.constants.WebMessageConstants;
import com.example.encapsulation.ResultUtil;
import com.example.entity.UserEntity;
import com.example.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

/**
 * @Author created by wxb007 on 2019/5/8 0008 11:37
 */
@RestController
@RequestMapping(value = "/api/user")
public class UserController {

    @Autowired
    private UserService userService;

    /**
     * 获取所有数据 查
     *
     * @Author created by wxb007 on 2019/5/8 0008 17:07
     * @Param: []
     * @Api: /api/user/getUser
     */
    @GetMapping("/getUser")
    public ResultUtil<UserEntity> findAll() {
        return ResultUtil.success(userService.findAll());
    }

    /**
     * 用户注册 添加一条数据 增
     *
     * @Author created by wxb007 on 2019/5/8 0008 17:07
     * @Param: [userEntity]
     * @Api: /api/user/register
     */
    @PostMapping("/register")
    public ResultUtil register(@Valid @RequestBody UserEntity userEntity) {
        Integer result = userService.addNewUser(userEntity);
        if (null == result || result <= 0) {
            return ResultUtil.error(500, WebMessageConstants.FAIL, "注册失败");
        }
        return ResultUtil.success(WebMessageConstants.SUCCESS);
    }

    /**
     * 更新一条数据 改
     *
     * @Author created by wxb007 on 2019/5/9 0009 11:20
     * @Param:
     * @Api: /api/user/update
     */
    @PostMapping("/update")
    public ResultUtil update(@RequestParam Integer id, @RequestParam String userName) {
        Integer result = userService.updateUser(id, userName);
        if (null == result || result <= 0) {
            return ResultUtil.error(500, WebMessageConstants.FAIL, "更新失败，用户ID不存在或者已经删除");
        }
        return ResultUtil.success(WebMessageConstants.SUCCESS);
    }


    /**
     * 删除一条数据 删
     *
     * @Author created by wxb007 on 2019/5/9 0009 14:24
     * @Param: [id]
     * @Api: /api/user/delete
     */
    @PostMapping("/delete")
    public ResultUtil delete(@RequestParam Integer id) {
        Integer result = userService.deleteUser(id);

        System.out.println(result);
        if (null == result || result <= 0) {
            return ResultUtil.error(500, WebMessageConstants.FAIL, "删除失败，用户ID不存在或者已经删除");
        }
        return ResultUtil.success(WebMessageConstants.SUCCESS);
    }
}
