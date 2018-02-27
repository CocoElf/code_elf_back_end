package cn.edu.nju.cocoelf.code_elf_back_end.controller;

import cn.edu.nju.cocoelf.code_elf_back_end.model.QueryResultModel;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

@RestController
@RequestMapping(value = "/api/search", produces = "application/json;charset=UTF-8")
public class SearchController {

    @ApiOperation(value = "使用文字进行查询")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "username", value = "用户名", required = true, dataType = "String"),
            @ApiImplicitParam(name = "keyWord", value = "关键词", required = true, dataType = "String"),
    })
    @PostMapping("/queryWithWord")
    public QueryResultModel queryWithWord(String keyWord, String username) {
        return null;
    }

    @ApiOperation(value = "使用图片进行查询")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "username", value = "用户名", required = true, dataType = "String"),
            @ApiImplicitParam(name = "img", value = "查询的图片", required = true, dataType = "String"),
    })
    @PostMapping("/queryWithImg")
    public QueryResultModel queryWithImg(MultipartFile img, String username) {
        return null;
    }

    @ApiOperation(value = "使用文字和图片进行查询")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "username", value = "用户名", required = true, dataType = "String"),
            @ApiImplicitParam(name = "keyWord", value = "关键词", required = true, dataType = "String"),
            @ApiImplicitParam(name = "img", value = "查询的图片", required = true, dataType = "String"),
    })
    @PostMapping("/queryWithImgAndWord")
    public QueryResultModel queryWithImgAndWord(MultipartFile img, String word, String username) {
        return null;
    }
}
