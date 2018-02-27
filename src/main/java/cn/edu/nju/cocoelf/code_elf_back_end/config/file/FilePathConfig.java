package cn.edu.nju.cocoelf.code_elf_back_end.config.file;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;


@Configuration
public class FilePathConfig extends WebMvcConfigurerAdapter {
    public static final String API_PATH = "/Users/gyue/Downloads/api/python3";

    //供客户端使用的url前缀
    public static final String AVATAR_URL = "http://localhost:8081/api/";

    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        registry.addResourceHandler("/api/**").addResourceLocations("file:" + API_PATH);
        super.addResourceHandlers(registry);
    }
}

