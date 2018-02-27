package cn.edu.nju.cocoelf.code_elf_back_end.controller;

import cn.edu.nju.cocoelf.code_elf_back_end.model.UserModel;
import cn.edu.nju.cocoelf.code_elf_back_end.service.UserService;
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


    @ApiOperation(value = "用户注册", notes = "")
    @PostMapping("/signUp")
    public UserModel signUp(String username, String password) {
        return userService.signUp(username, password);
    }


    @PostMapping("/login")
    public UserModel login(String username, String password) {
        return userService.veryUser(username, password);
    }


    @PostMapping("/userDetail")
    public UserModel getUserDetail(String username) {
        return userService.getUserDetail(username);
    }


    @PostMapping("/modifyUser")
    public UserModel modifyUser(UserModel userModel) {
        return userService.modifyUser(userModel);
    }

}
