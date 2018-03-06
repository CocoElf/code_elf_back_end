package cn.edu.nju.cocoelf.code_elf_back_end.Service.impl;

import cn.edu.nju.cocoelf.code_elf_back_end.controller.TestParam;
import cn.edu.nju.cocoelf.code_elf_back_end.model.OCR;
import cn.edu.nju.cocoelf.code_elf_back_end.service.SearchService;
import cn.edu.nju.cocoelf.code_elf_back_end.service.component.SenTerm;
import cn.edu.nju.cocoelf.code_elf_back_end.service.impl.SearchServiceImpl;
import cn.edu.nju.cocoelf.code_elf_back_end.util.LogUtil;
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

    @Autowired
    SearchServiceImpl searchServiceImpl;

    @Test
    public void imgToWord() throws Exception {

        Gson gson = new Gson();
        OCR ocr = gson.fromJson(TestParam.testStr, OCR.class);
        System.out.println(ocr);
//        System.out.println(searchService.imgToWord(ocr, ""));
    }

    @Test
    public void classify() throws Exception {
        String word = "c++的字符串";
        int re = searchServiceImpl.classify(word);
        System.out.println(SenTerm.mapToString[re]);
        LogUtil.log(SenTerm.mapToString[re]);
    }
//        System.out.

}