package cn.edu.nju.cocoelf.code_elf_back_end.controller;

import cn.edu.nju.cocoelf.code_elf_back_end.model.MemoModel;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping(value = "/api/memo", produces = "application/json;charset=UTF-8")
public class MemoController {

    @ApiOperation(value = "获得用户的备忘录列表")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "username", value = "用户名", required = true, dataType = "String"),
    })
    @PostMapping("/memoList")
    public List<MemoModel> getMemoList(String username) {
        return null;
    }

    @ApiOperation(value = "获得用户的指定备忘录详情")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "memoId", value = "备忘录id", required = true, dataType = "Integer"),
            @ApiImplicitParam(name = "username", value = "用户名", required = true, dataType = "String")
    })
    @PostMapping("/memoDetail")
    public MemoModel getMemoDetail(Integer memoId, String username) {
        return null;
    }

    @ApiOperation(value = "增加备忘录", notes = "返回的备忘录model中会增加memoId")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "memoModel", value = "备忘录", required = true, dataType = "MemoModel"),
    })
    @PostMapping("/addMemo")
    public MemoModel addMemo(MemoModel memoModel) {
        return null;
    }

    @ApiOperation(value = "修改备忘录")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "memoModel", value = "备忘录", required = true, dataType = "MemoModel"),
    })
    @PostMapping("/editMemo")
    public MemoModel editMemo(MemoModel memoModel) {
        return null;
    }
}
