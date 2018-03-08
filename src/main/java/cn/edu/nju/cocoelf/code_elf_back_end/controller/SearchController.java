package cn.edu.nju.cocoelf.code_elf_back_end.controller;

import cn.edu.nju.cocoelf.code_elf_back_end.model.OCR;
import cn.edu.nju.cocoelf.code_elf_back_end.model.SearchModel;
import cn.edu.nju.cocoelf.code_elf_back_end.model.SearchResultModel;
import cn.edu.nju.cocoelf.code_elf_back_end.service.SearchService;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping(value = "/api/search", produces = "application/json;charset=UTF-8")
public class SearchController {

    @Autowired
    private SearchService searchService;

    @ApiOperation(value = "使用文字进行查询")
    @ApiImplicitParams({@ApiImplicitParam(name = "username", value = "用户名", required = true, dataType = "String"),
            @ApiImplicitParam(name = "keyWord", value = "关键词", required = true, dataType = "String"),})
    @PostMapping("/queryWithWord")
    public List<SearchResultModel> queryWithWord(String keyWord, String username) {
        assert keyWord!=null:"can not pass parameter";
        return searchService.searchWithWord(keyWord,username);
    }

    @ApiOperation(value = "将图片转换成文字")
    @ApiImplicitParams({@ApiImplicitParam(name = "username", value = "用户名", required = true, dataType = "String"),
            @ApiImplicitParam(name = "ocr", value = "查询的图片转成的ocr", required = true, dataType = "String"),})
    @PostMapping("/imgToWord/{username}")
    public SearchModel imgToWord(OCR ocr, @PathVariable String username) {
//        Gson gson = new Gson();
//        ocr = gson.fromJson(TestParam.testStr, OCR.class);
//        System.out.println(ocr);
        return new SearchModel(searchService.imgToWord(ocr, username));
    }

    public static void main(String... args) {
        System.out.println(new SearchController().imgToWord(null, null));
    }

}
