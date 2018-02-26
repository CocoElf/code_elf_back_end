package cn.edu.nju.cocoelf.code_elf_back_end.controller;

import cn.edu.nju.cocoelf.code_elf_back_end.model.UserModel;
import cn.edu.nju.cocoelf.code_elf_back_end.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.security.NoSuchAlgorithmException;

@RestController
@RequestMapping(value = "/api/user", produces = "application/json;charset=UTF-8")
public class UserController {

    @Autowired
    private UserService userService;

    /**
     * 注册
     *
     * @param username 用户名，可重复
     * @param email    用户邮箱，不可重复
     * @param password 用户密码
     * @return 是否注册成功 "SUCCESS, "FAIL"
     */
    @PostMapping("/signUp")
    public UserModel signUp(String username, String email, String password) {
        return null;
//        return JSON.toJSONString(userService.signIn(username, email, password));
    }

    /**
     * 登录
     *
     * @param email    邮箱
     * @param password 密码
     * @return 成功： {"user": user model, "signature": 用户签名} 失败："FAIL"
     */
    @PostMapping("/login")
    public UserModel login(String email, String password) throws NoSuchAlgorithmException {
        return null;
    }

    /**
     * 获得用户详细信息
     *
     * @param signature 用户签名
     * @return user model
     */
    @PostMapping("/userDetail")
    public UserModel getUserDetail(String signature) {
        return null;
    }

    /**
     * 修改用户信息
     *
     * @param userModel userModel model
     * @param signature 签名
     * @return 修改后的user model
     */
    @PostMapping("/modifyUser")
    public UserModel modifyUser(UserModel userModel, String signature) {
        return null;
    }

}
