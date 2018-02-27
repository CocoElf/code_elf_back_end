package cn.edu.nju.cocoelf.code_elf_back_end.service.impl;

import cn.edu.nju.cocoelf.code_elf_back_end.Enum.Language;
import cn.edu.nju.cocoelf.code_elf_back_end.Enum.LanguageFunction;
import cn.edu.nju.cocoelf.code_elf_back_end.model.APISearchTitle;
import cn.edu.nju.cocoelf.code_elf_back_end.service.LanguageService;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@Service
public class LanguageServiceImpl implements LanguageService{
    @Override
    public List<APISearchTitle> searchAPIByKeyword(String languageName, double version, String keyword, int speed) {
        if(speed < 1) speed =1;
        if(speed > 5) speed = 5;
        LanguageFunction language = Language.python3.getLanguageType(languageName,version);
        String baseURL = language.getBaseURL();
        String searchURL = language.getSearchURL(keyword);
        try {
            String html = Language.python3.getSearchPage(searchURL,speed);
            return language.getContentList(html,baseURL);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return new ArrayList<>();
    }
}
