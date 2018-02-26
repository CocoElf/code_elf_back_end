package cn.edu.nju.cocoelf.code_elf_back_end.controller;

import cn.edu.nju.cocoelf.code_elf_back_end.model.QueryResultModel;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

@RestController
@RequestMapping(value = "/api/search", produces = "application/json;charset=UTF-8")
public class SearchController {

    /**
     * 根据文字进行查询
     * @param keyWord 文字
     * @param signature 签名
     * @return 查询结果
     */
    @PostMapping("/queryWithWord")
    public QueryResultModel queryWithWord(String keyWord, String signature) {
        return null;
    }

    /**
     * 根据图片进行查询
     * @param img 图片
     * @param signature 文字
     * @return 查询结果
     */
    @PostMapping("/queryWithImg")
    public QueryResultModel queryWithImg(MultipartFile img, String signature) {
        return null;
    }

    /**
     * 根据图像和文字进行查询
     * @param img 图像
     * @param word 文字
     * @param signature 签名
     * @return 查询结果
     */
    @PostMapping("/queryWithImgAndWord")
    public QueryResultModel queryWithImgAndWord(MultipartFile img, String word, String signature) {
        return null;
    }
}
