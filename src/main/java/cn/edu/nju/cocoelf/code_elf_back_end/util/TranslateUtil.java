package cn.edu.nju.cocoelf.code_elf_back_end.util;

import org.springframework.stereotype.Component;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Component
public class TranslateUtil {
    private Map<String, String> map = new HashMap<>();

    private String tranlatiionPath = "library/translate.txt";

    public TranslateUtil() throws IOException {
        Stream<String> stream = Files.lines(Paths.get(tranlatiionPath));
        List<String> list =  stream.collect(Collectors.toList());
        for(String str : list){
            String[] spilt = str.split(" ");
            map.put(spilt[0],spilt[1]);
        }
    }

    public String translate(String word){
        if(word == null || word.trim().equals("")) return "";
        if(word.charAt(0) < 128 ) return word;
        if(map.containsKey(word)) return map.get(word);
        return "UN_KNOWN";
    }
}
