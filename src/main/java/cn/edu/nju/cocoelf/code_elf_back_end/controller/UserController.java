package cn.edu.nju.cocoelf.code_elf_back_end.controller;

import cn.edu.nju.cocoelf.code_elf_back_end.config.param.ResultMessage;
import cn.edu.nju.cocoelf.code_elf_back_end.json.UserJson;
import cn.edu.nju.cocoelf.code_elf_back_end.model.UserModel;
import cn.edu.nju.cocoelf.code_elf_back_end.service.UserService;
import cn.edu.nju.cocoelf.code_elf_back_end.util.SignatureUtil;
import com.alibaba.fastjson.JSON;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.security.NoSuchAlgorithmException;
import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping(value = "/api/user", produces = "application/json;charset=UTF-8")
public class UserController {

    @Autowired
    private UserService userService;

    /**
     * 注册
     * @return 是否注册成功 "SUCCESS, "FAIL"
     */
    @PostMapping("/signUp")
    @ResponseBody
    public UserJson signUp(@RequestBody UserJson userJson) {
        System.out.println(userJson);
//        return JSON.toJSONString(userService.signIn(username, email, password));
        userJson.setUsername("123");
        userJson.setUsername("dd");
        return userJson;
    }

    /**
     * 登录
     * @param email 邮箱
     * @param password 密码
     * @return 成功： {"user": user model, "signature": 用户签名} 失败："FAIL"
     */
    @PostMapping("/login")
    public String login(String email, String password) throws NoSuchAlgorithmException {
        if (!userService.veryUser(email, password)) {
            return JSON.toJSONString(ResultMessage.FAIL);
        }
        Map<String, Object> res = new HashMap<>();
        res.put("user", userService.getUserDetail(email));
        res.put("signature", SignatureUtil.getSignature(email));
        return JSON.toJSONString(res);
    }

    /**
     * 获得用户详细信息
     * @param signature 用户签名
     * @return user model
     */
    @PostMapping("/userDetail")
    public String getUserDetail(String signature) {
        String email = SignatureUtil.getEmail(signature);
        if (email == null || email.equals("")) {
            return JSON.toJSONString(ResultMessage.FAIL);
        }
        UserModel userModel = userService.getUserDetail(email);
        return JSON.toJSONString(userModel);
    }

    /**
     * 修改用户信息
     * @param userModel userModel model
     * @param signature 签名
     * @return 修改后的user model
     */
    @PostMapping("/modifyUser")
    public String modifyUser(UserModel userModel, String signature) {
        String email = SignatureUtil.getEmail(signature);
        if (email == null || email.equals("")) {
            return JSON.toJSONString(ResultMessage.FAIL);
        }
        userModel = userService.ModifyUser(userModel);
        return JSON.toJSONString(userModel);
    }

}
