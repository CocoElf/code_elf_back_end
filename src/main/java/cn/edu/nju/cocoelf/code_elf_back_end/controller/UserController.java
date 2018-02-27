package cn.edu.nju.cocoelf.code_elf_back_end.controller;

import cn.edu.nju.cocoelf.code_elf_back_end.model.UserModel;
import cn.edu.nju.cocoelf.code_elf_back_end.service.UserService;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequestMapping(value = "/api/user", produces = "application/json;charset=UTF-8")
public class UserController {

    @Autowired
    private UserService userService;


    @ApiOperation(value = "用户注册", notes = "用户名不能重复")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "username", value = "用户名", required = true, dataType = "String"),
            @ApiImplicitParam(name = "password", value = "密码", required = true, dataType = "String")
    })
    @PostMapping("/signUp")
    public UserModel signUp(String username, String password) {
        return userService.signUp(username, password);
    }

    @ApiOperation(value = "用户登录")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "username", value = "用户名", required = true, dataType = "String"),
            @ApiImplicitParam(name = "password", value = "密码", required = true, dataType = "String")
    })
    @PostMapping("/login")
    public UserModel login(String username, String password) {
        return userService.veryUser(username, password);
    }

    @ApiOperation(value = "获取用户详情")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "username", value = "用户名", required = true, dataType = "String"),
    })
    @PostMapping("/userDetail")
    public UserModel getUserDetail(String username) {
        return userService.getUserDetail(username);
    }


    @ApiOperation(value = "修改用户信息")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "userModel", value = "修改后的用户model", required = true, dataType = "UserModel")
    })
    @PostMapping("/modifyUser")
    public UserModel modifyUser(UserModel userModel) {
        return userService.modifyUser(userModel);
    }

}
