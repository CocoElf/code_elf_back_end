package cn.edu.nju.cocoelf.code_elf_back_end.Service.impl;

import cn.edu.nju.cocoelf.code_elf_back_end.controller.TestParam;
import cn.edu.nju.cocoelf.code_elf_back_end.model.OCR;
import cn.edu.nju.cocoelf.code_elf_back_end.model.SearchResultModel;
import cn.edu.nju.cocoelf.code_elf_back_end.service.SearchService;
import cn.edu.nju.cocoelf.code_elf_back_end.service.component.SenTerm;
import cn.edu.nju.cocoelf.code_elf_back_end.service.impl.SearchServiceImpl;
import cn.edu.nju.cocoelf.code_elf_back_end.util.LogUtil;
import com.google.gson.Gson;
import org.ansj.domain.Term;
import org.ansj.splitWord.analysis.DicAnalysis;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

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
        List<Term> termList = DicAnalysis.parse(word).getTerms();
        int re = searchServiceImpl.classify(termList);
        System.out.println(SenTerm.mapToString[re]);
        LogUtil.log(SenTerm.mapToString[re]);
    }
//        System.out.

    @Test
    public void queryWord() throws Exception {
        String word = "如何在python中求数组长度";
        List<SearchResultModel> list = searchService.searchWithWord(word,"");
        list.forEach(t-> System.out.println(t));
//        LogUtil.log(SenTerm.mapToString[re]);
    }

    @Test
    public void queryWord2() throws Exception {
        String word = "python中的str模块";
        List<SearchResultModel> list = searchService.searchWithWord(word,"");
        list.forEach(t-> System.out.println(t));
//        LogUtil.log(SenTerm.mapToString[re]);
    }

}