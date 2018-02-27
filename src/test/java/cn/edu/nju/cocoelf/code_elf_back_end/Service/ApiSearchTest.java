package cn.edu.nju.cocoelf.code_elf_back_end.Service;

import cn.edu.nju.cocoelf.code_elf_back_end.CodeElfBackEndApplicationTests;
import cn.edu.nju.cocoelf.code_elf_back_end.model.APISearchTitle;
import cn.edu.nju.cocoelf.code_elf_back_end.service.LanguageService;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

public class ApiSearchTest extends CodeElfBackEndApplicationTests {

    @Autowired
    LanguageService languageService;

    @Test
    public void ApiSearch1(){
        List<APISearchTitle> list =
                languageService.searchAPIByKeyword("python",3,"dict",1);
        System.out.println(list.size());
        list.forEach(t-> System.out.println(t.getUrl()));
    }
}
