package cn.edu.nju.cocoelf.code_elf_back_end.service.component;

import cn.edu.nju.cocoelf.code_elf_back_end.model.SearchResultModel;
import org.springframework.stereotype.Component;

/**
 * stub可以处理的搜索结果：(演示的时候前端的输入应为如下列表中之一)
 * 1、（api查询）python abs函数的使用方法
 * 2、（功能查找api）java如何读写文件
 * 3、（语音web搜索）spring data jpa的教程
 * 4、（图片异常web搜索）javax.servlet.ServletException: bean [name] not found within scope
 */
@Component
public class WebSearchStub {

    public SearchResultModel fakeSearchResult(String keywords) {
        keywords = keywords.toLowerCase();
        SearchResultModel searchResultModel = new SearchResultModel();
        if (keywords.contains("python")) {
            // TODO case 1
        } else if (keywords.contains("java")) {
            // TODO case 2
        } else if (keywords.contains("spring")) {
            // TODO case 3
        } else {
            // TODO case 4
        }

        return searchResultModel;
    }
}
