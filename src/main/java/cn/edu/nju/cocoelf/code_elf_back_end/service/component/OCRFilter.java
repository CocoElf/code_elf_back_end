package cn.edu.nju.cocoelf.code_elf_back_end.service.component;

import cn.edu.nju.cocoelf.code_elf_back_end.model.Line;
import cn.edu.nju.cocoelf.code_elf_back_end.model.OCR;
import cn.edu.nju.cocoelf.code_elf_back_end.model.Region;
import cn.edu.nju.cocoelf.code_elf_back_end.model.Word;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class OCRFilter {

    /**
     * 暂时只对异常情况做优化
     *
     * @param ocr 图片识别结果
     * @return 处理好的字符串
     */
    public String translate(OCR ocr) {
        List<String> result = new ArrayList<>();
        for (Region region : ocr.regions) {
            for (Line line : region.lines) {
                String lineString = "";
                for (Word word : line.words) {
                    lineString += word.text + " ";
                }
                System.out.println("lineString: " + lineString);
                result.add(lineString);
            }
        }
        // 过滤注释
        for (int i = 0; i < result.size(); i++) {
            String str = result.get(i);
            if (str.contains("//") || str.contains("/*") || str.contains("<!--")) {
                result.set(i, "");
            }
        }

        for (int i = 0; i < result.size(); i++) {
            String str = result.get(i);
            str = str.toLowerCase();
            if (str.contains("exception") || str.contains("error")) {
                return result.get(i);
            }
        }
        String resultString = "";
        for (String str : result) {
            resultString += str + " ";
        }
        return resultString;
    }
}
