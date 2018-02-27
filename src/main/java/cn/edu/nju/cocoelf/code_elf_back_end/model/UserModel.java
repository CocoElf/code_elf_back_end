package cn.edu.nju.cocoelf.code_elf_back_end.model;

public class UserModel {

    private String username;

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    @Override
    public String toString() {
        return "UserModel{" + "username='" + username + '\'' + '}';
    }
}
