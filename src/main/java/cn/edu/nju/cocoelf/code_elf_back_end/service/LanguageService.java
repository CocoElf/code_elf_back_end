package cn.edu.nju.cocoelf.code_elf_back_end.service;

import cn.edu.nju.cocoelf.code_elf_back_end.model.APISearchTitle;

import java.util.List;

public interface LanguageService {
    List<APISearchTitle> searchAPIByKeyword(String languageName, double version , String keyword, int speed);
}
