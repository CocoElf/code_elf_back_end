package cn.edu.nju.cocoelf.code_elf_back_end.config.file;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.*;
import java.util.stream.Collectors;


@Configuration
public class FilePathConfig extends WebMvcConfigurerAdapter {

    public static Map<String,String> developerPror = new HashMap<>();

    static {
        try {
            List<String> list =  Files.lines(Paths.get("developers/developers.txt")).collect(Collectors.toList());
            for(String str : list){
                String[] split = str.split(":");
                developerPror.put(split[0],split[1]);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    //供客户端使用的url前缀
    public static final String AVATAR_URL = "http://localhost:8081/api/";

    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        registry.addResourceHandler("/api/**").addResourceLocations("file:" + developerPror.get("absPath"));
        super.addResourceHandlers(registry);
    }

    public static void main(String[] args){
        File file = new File("developers/absPath_zh_CN.properties");
        System.out.println(file.getAbsolutePath());
        System.out.println(file.exists());
//        System.out.println(rb.getString("API_PATH"));
    }
}

