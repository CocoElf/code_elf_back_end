package cn.edu.nju.cocoelf.code_elf_back_end.service;

import cn.edu.nju.cocoelf.code_elf_back_end.config.param.ResultMessage;
import cn.edu.nju.cocoelf.code_elf_back_end.model.UserModel;

public interface UserService {

    boolean veryUser(String email, String password);

    ResultMessage signUp(String email, String username, String password);

    UserModel getUserDetail(String email);

    UserModel ModifyUser(UserModel userModel);
}
