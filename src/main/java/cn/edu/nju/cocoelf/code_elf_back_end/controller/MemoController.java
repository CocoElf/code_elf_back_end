package cn.edu.nju.cocoelf.code_elf_back_end.controller;

import cn.edu.nju.cocoelf.code_elf_back_end.model.MemoModel;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping(value = "/api/memo", produces = "application/json;charset=UTF-8")
public class MemoController {

    /**
     * 获得备忘录列表
     * @param signature 签名
     * @return {{memo1}, {memo2}, ...}
     */
    @PostMapping("/memoList")
    public List<MemoModel> getMemoList(String signature) {
        return null;
    }

    /**
     * 获得备忘录详情
     * @param memoId 备忘录id
     * @param signature 签名
     * @return memo model
     */
    @PostMapping("/memoDetail")
    public MemoModel getMemoDetail(String memoId, String signature) {
        return null;
    }

    /**
     * 添加备忘录
     * @param memoText 备忘录文本
     * @param signature 签名
     * @return 被添加的memo model
     */
    @PostMapping("/addMemo")
    public MemoModel addMemo(String memoText, String signature) {
        return null;
    }

    /**
     * 编辑备忘录
     * @param memoText 编辑后的备忘录文本
     * @param memoId 备忘录id
     * @param signature 签名
     * @return 编辑后的memo model
     */
    @PostMapping("/editMemo")
    public MemoModel editMemo(String memoText, String memoId, String signature) {
        return null;
    }
}
