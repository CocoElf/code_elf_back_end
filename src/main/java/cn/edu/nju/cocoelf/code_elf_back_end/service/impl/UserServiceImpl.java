package cn.edu.nju.cocoelf.code_elf_back_end.service.impl;

import cn.edu.nju.cocoelf.code_elf_back_end.entity.User;
import cn.edu.nju.cocoelf.code_elf_back_end.exception.InvalidRequestException;
import cn.edu.nju.cocoelf.code_elf_back_end.exception.ResourceConflictException;
import cn.edu.nju.cocoelf.code_elf_back_end.exception.ResourceNotFoundException;
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
    public UserModel veryUser(String username, String password) {
        User user = userRepository.findByUsernameAndPassword(username, password);
        return toModel(user);
    }

    @Override
    public UserModel signUp(String username, String password) {
        User user = userRepository.findOne(username);
        if (user != null && user.getUsername() != null) {
            throw new ResourceConflictException("用户名重复");
        }
        user = new User();
        user.setUsername(username);
        user.setPassword(password);
        user = userRepository.save(user);
        return  toModel(user);
    }

    @Override
    public UserModel getUserDetail(String username) {
        return toModel(userRepository.findOne(username));
    }

    @Override
    public UserModel modifyUser(UserModel userModel) {
        return toModel(userRepository.saveAndFlush(toEntity(userModel)));
    }

    @Override
    public User verifyUsername(String username) {
        User user = userRepository.findOne(username);
        if (user == null || user.getUsername() == null) {
            throw new InvalidRequestException("没有权限");
        }
        return user;
    }

    private UserModel toModel(User user) {
        UserModel userModel = new UserModel();
        if (user == null || user.getUsername() == null) {
           throw new ResourceNotFoundException("没有找到此用户");
        }
        userModel.setUsername(user.getUsername());
        return userModel;
    }

    private User toEntity(UserModel userModel) {
        User user = userRepository.findOne(userModel.getUsername());
        if (user == null || user.getUsername() == null) {
            throw new ResourceNotFoundException("没有找到此用户");
        }
        return user;
    }
}
