package cn.edu.nju.cocoelf.code_elf_back_end.controller;

import cn.edu.nju.cocoelf.code_elf_back_end.model.SuggestModel;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping(value = "/api/suggest", produces = "application/json;charset=UTF-8")
public class SuggestController {

    @ApiOperation(value = "根据用户历史情况获得建议")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "username", value = "用户名", required = true, dataType = "String"),
    })
    @PostMapping("/generateSuggestion")
    public SuggestModel generateSuggestion(String username) {
        return null;
    }

    @ApiOperation(value = "获得建议界面提示的列表")
    @GetMapping("/suggestionTips")
    public List<String> getSuggestTips() {
        return null;
    }
}
