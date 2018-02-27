package cn.edu.nju.cocoelf.code_elf_back_end.service;

import cn.edu.nju.cocoelf.code_elf_back_end.model.QueryResultModel;

import java.util.List;

public interface SearchService {

    List<QueryResultModel> queryWithWord(String keyWord, String username);
}
