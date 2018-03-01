package cn.edu.nju.cocoelf.code_elf_back_end.Service.impl;

import cn.edu.nju.cocoelf.code_elf_back_end.controller.TestParam;
import cn.edu.nju.cocoelf.code_elf_back_end.model.OCR;
import cn.edu.nju.cocoelf.code_elf_back_end.service.SearchService;
import com.google.gson.Gson;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
public class SearchServiceImplTest {

    @Autowired
    SearchService searchService;

    @Test
    public void imgToWord() throws Exception {

        Gson gson = new Gson();
        OCR ocr = gson.fromJson(TestParam.testStr, OCR.class);
        System.out.println(ocr);
//        System.out.println(searchService.imgToWord(ocr, ""));
    }

}