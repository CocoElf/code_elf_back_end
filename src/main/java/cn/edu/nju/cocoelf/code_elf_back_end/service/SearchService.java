package cn.edu.nju.cocoelf.code_elf_back_end.service;

import cn.edu.nju.cocoelf.code_elf_back_end.model.OCR;
import cn.edu.nju.cocoelf.code_elf_back_end.model.QueryResultModel;
import cn.edu.nju.cocoelf.code_elf_back_end.model.SearchResultModel;

import java.util.List;

public interface SearchService {

    List<SearchResultModel> searchWithWord(String keyWord, String username);

    String imgToWord(OCR ocr, String username);
}
