package cn.edu.nju.cocoelf.code_elf_back_end.controller;

import cn.edu.nju.cocoelf.code_elf_back_end.model.MemoModel;
import cn.edu.nju.cocoelf.code_elf_back_end.service.MemoService;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping(value = "/api/memo", produces = "application/json;charset=UTF-8")
public class MemoController {

    @Autowired
    private MemoService memoService;


    @ApiOperation(value = "获得用户的备忘录列表")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "username", value = "用户名", required = true, dataType = "String"),
            @ApiImplicitParam(name = "pageNum", value = "页码", required = true, dataType = "String"),
            @ApiImplicitParam(name = "pageSize", value = "每页大小", required = true, dataType = "String"),
    })
    @PostMapping("/memoList")
    public List<MemoModel> getMemoList(String username, Integer pageNum, Integer pageSize) {
        return memoService.getMemoList(username, pageNum, pageSize);
    }

    @ApiOperation(value = "获得用户的指定备忘录详情")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "memoId", value = "备忘录id", required = true, dataType = "Integer"),
            @ApiImplicitParam(name = "username", value = "用户名", required = true, dataType = "String")
    })
    @PostMapping("/memoDetail")
    public MemoModel getMemoDetail(Integer memoId, String username) {
        return memoService.getMemoDetail(memoId, username);
    }

    @ApiOperation(value = "增加备忘录", notes = "返回的备忘录model中会增加memoId，用户每点击一次搜索结果前端都应该调用这个方法")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "memoModel", value = "备忘录", required = true, dataType = "MemoModel"),
            @ApiImplicitParam(name = "username", value = "用户名", required = true, dataType = "String"),
    })
    @PostMapping("/addMemo")
    public MemoModel addMemo(MemoModel memoModel, String username) {
        return memoService.addMemo(memoModel, username);
    }

    @ApiOperation(value = "删除备忘录", notes = "返回的备忘录model中会增加memoId")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "memoModel", value = "备忘录", required = true, dataType = "MemoModel"),
            @ApiImplicitParam(name = "username", value = "用户名", required = true, dataType = "String"),
    })
    @PostMapping("/deleteMemo")
    public Boolean deleteMemo(MemoModel memoModel, String username) {
        return memoService.deleteMemo(memoModel, username);
    }

}
