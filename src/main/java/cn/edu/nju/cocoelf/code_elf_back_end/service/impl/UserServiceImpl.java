package cn.edu.nju.cocoelf.code_elf_back_end.service.impl;

import cn.edu.nju.cocoelf.code_elf_back_end.Entity.User;
import cn.edu.nju.cocoelf.code_elf_back_end.config.param.ResultMessage;
import cn.edu.nju.cocoelf.code_elf_back_end.model.UserModel;
import cn.edu.nju.cocoelf.code_elf_back_end.repository.UserRepository;
import cn.edu.nju.cocoelf.code_elf_back_end.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserRepository userRepository;

    @Override
    public boolean veryUser(String email, String password) {
        User user = userRepository.findByEmailAndPassword(email, password);
        return user != null && user.getEmail() != null;
    }

    @Override
    public ResultMessage signUp(String email, String username, String password) {
        User user = userRepository.findOne(email);
        if (user != null && user.getEmail() != null) {
            user = new User();
            user.setEmail(email);
            user.setUsername(username);
            user.setPassword(password);
            userRepository.save(user);
            return ResultMessage.SUCCESS;
        }
        return ResultMessage.FAIL;
    }

    @Override
    public UserModel getUserDetail(String email) {
        return toModel(userRepository.findOne(email));
    }

    @Override
    public UserModel ModifyUser(UserModel userModel) {
        return toModel(userRepository.saveAndFlush(toEntity(userModel)));
    }

    private UserModel toModel(User user) {
        UserModel userModel = new UserModel();
        if (user != null && user.getEmail() != null) {
            userModel.setEmail(user.getEmail());
            userModel.setUsername(user.getUsername());
        }
        return userModel;
    }

    private User toEntity(UserModel userModel) {
        User user = new User();
        if (userModel != null && userModel.getEmail() != null) {
            user.setEmail(userModel.getEmail());
            user.setUsername(userModel.getUsername());
        }
        return user;
    }
}
