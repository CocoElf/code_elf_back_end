package cn.edu.nju.cocoelf.code_elf_back_end.controller;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/api/suggest", produces = "application/json;charset=UTF-8")
public class SuggestController {

    /**
     * 根据用户的历史情况生成建议
     * @param signature 签名
     * @return 生成的建议
     */
    @PostMapping("/generateSuggestion")
    public String generateSuggestion(String signature) {
        return null;
    }
}
