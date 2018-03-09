package cn.edu.nju.cocoelf.code_elf_back_end.controller;

import cn.edu.nju.cocoelf.code_elf_back_end.model.OCR;
import cn.edu.nju.cocoelf.code_elf_back_end.model.SearchModel;
import cn.edu.nju.cocoelf.code_elf_back_end.model.SearchResultModel;
import cn.edu.nju.cocoelf.code_elf_back_end.service.SearchService;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "/api/search", produces = "application/json;charset=UTF-8")
public class SearchController {

    @Autowired
    private SearchService searchService;

    private Logger logger = LoggerFactory.getLogger(SearchController.class);

    @ApiOperation(value = "使用文字进行查询")
    @ApiImplicitParams({@ApiImplicitParam(name = "username", value = "用户名", required = true, dataType = "String"),
            @ApiImplicitParam(name = "keyWord", value = "关键词", required = true, dataType = "String"),})
    @PostMapping("/queryWithWord/{username}")
    public List<SearchResultModel> queryWithWord(@RequestBody SearchModel keyWord, @PathVariable String username) {
        assert keyWord!=null:"can not pass parameter";
        logger.info(keyWord.toString());
        return searchService.searchWithWord(keyWord.getKeyword(),username);
    }

    @ApiOperation(value = "将图片转换成文字")
    @ApiImplicitParams({@ApiImplicitParam(name = "username", value = "用户名", required = true, dataType = "String"),
            @ApiImplicitParam(name = "ocr", value = "查询的图片转成的ocr", required = true, dataType = "String"),})
    @PostMapping("/imgToWord/{username}")
    public SearchModel imgToWord(OCR ocr, @PathVariable String username) {
//        Gson gson = new Gson();
//        ocr = gson.fromJson(TestParam.testStr, OCR.class);
//        System.out.println(ocr);
        return new SearchModel("Exception in thread \"main\" java.lang.IndexOutOfBoundsException: Index: 0, Size: 0");
    }

    public static void main(String... args) {
        System.out.println(new SearchController().imgToWord(null, null));
    }

}
