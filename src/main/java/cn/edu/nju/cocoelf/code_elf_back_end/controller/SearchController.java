package cn.edu.nju.cocoelf.code_elf_back_end.controller;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

@RestController
@RequestMapping(value = "/api/search", produces = "application/json;charset=UTF-8")
public class SearchController {

    /**
     * 将语音传换成文字
     * @param soundFile 语音
     * @param signature 签名
     * @return 转换结果
     */
    @PostMapping("/translate")
    public String translate(MultipartFile soundFile, String signature) {
        return null;
    }

    /**
     * 根据文字进行查询
     * @param keyWord 文字
     * @param signature 签名
     * @return 查询结果
     */
    @PostMapping("/queryWithWord")
    public String queryWithWord(String keyWord, String signature) {
        return null;
    }

    /**
     * 根据图片进行查询
     * @param img 图片
     * @param signature 文字
     * @return 查询结果
     */
    @PostMapping("/queryWithImg")
    public String queryWithImg(MultipartFile img, String signature) {
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
    public String queryWithImg(MultipartFile img, String word, String signature) {
        return null;
    }
}
