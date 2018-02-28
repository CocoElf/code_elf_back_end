package cn.edu.nju.cocoelf.code_elf_back_end.service.impl;

import cn.edu.nju.cocoelf.code_elf_back_end.exception.InvalidRequestException;
import cn.edu.nju.cocoelf.code_elf_back_end.model.QueryResultModel;
import cn.edu.nju.cocoelf.code_elf_back_end.service.SearchService;
import cn.edu.nju.cocoelf.code_elf_back_end.service.component.SearchFilter;
import cn.edu.nju.cocoelf.code_elf_back_end.util.SearchUtil;
import com.alibaba.fastjson.JSON;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SearchServiceImpl implements SearchService {
    @Override
    public List<QueryResultModel> queryWithWord(String keyWord, String username) {
        //TODO search api

        // search web
        List<QueryResultModel> webList = searchWeb(keyWord);

        //TODO merge

        return webList;
    }

    private List<QueryResultModel> searchWeb(String keyWord) {
        String akeyWord = SearchFilter.convertKeyWord(keyWord);
        String searchResult;
        try {
            searchResult = SearchUtil.SearchWeb(akeyWord, 100).pureResult;
        } catch (Exception e) {
            System.out.println(e.getMessage());
            throw new InvalidRequestException("搜索api出bug了");
        }
        System.out.println(searchResult);

        List<QueryResultModel> queryResultModelList = JSON.parseArray(searchResult, QueryResultModel.class);

        return SearchFilter.sortAndCutResult(queryResultModelList);
    }

    public static void main(String... args) {
        SearchServiceImpl searchService = new SearchServiceImpl();
        System.out.println(searchService.searchWeb("fabric.js how to draw a ploygon"));
    }
}
