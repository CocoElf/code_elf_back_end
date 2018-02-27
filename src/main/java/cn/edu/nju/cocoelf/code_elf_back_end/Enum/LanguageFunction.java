package cn.edu.nju.cocoelf.code_elf_back_end.Enum;

import cn.edu.nju.cocoelf.code_elf_back_end.model.APISearchTitle;

import java.util.List;

public interface LanguageFunction {
    public List<APISearchTitle> getContentList(String html, String baseUrl);

    public String getBaseURL();

    public String getSearchURL(String keyword);
}
