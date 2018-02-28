package cn.edu.nju.cocoelf.code_elf_back_end.service;

import cn.edu.nju.cocoelf.code_elf_back_end.entity.User;
import cn.edu.nju.cocoelf.code_elf_back_end.model.UserModel;

public interface UserService {

    UserModel veryUser(String username, String password);

    UserModel signUp(String username, String password);

    UserModel getUserDetail(String username);

    UserModel modifyUser(UserModel userModel);

    User verifyUsername(String username);
}
