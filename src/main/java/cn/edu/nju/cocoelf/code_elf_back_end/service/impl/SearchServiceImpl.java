package cn.edu.nju.cocoelf.code_elf_back_end.service.impl;

import cn.edu.nju.cocoelf.code_elf_back_end.entity.Search;
import cn.edu.nju.cocoelf.code_elf_back_end.entity.User;
import cn.edu.nju.cocoelf.code_elf_back_end.exception.InvalidRequestException;
import cn.edu.nju.cocoelf.code_elf_back_end.model.OCR;
import cn.edu.nju.cocoelf.code_elf_back_end.model.QueryResultModel;
import cn.edu.nju.cocoelf.code_elf_back_end.repository.SearchRepository;
import cn.edu.nju.cocoelf.code_elf_back_end.service.SearchService;
import cn.edu.nju.cocoelf.code_elf_back_end.service.UserService;
import cn.edu.nju.cocoelf.code_elf_back_end.service.component.SearchFilter;
import cn.edu.nju.cocoelf.code_elf_back_end.util.SearchUtil;
import com.alibaba.fastjson.JSON;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
public class SearchServiceImpl implements SearchService {

    @Autowired
    UserService userService;

    @Autowired
    SearchRepository searchRepository;

    @Override
    public List<QueryResultModel> queryWithWord(String keyWord, String username) {
        recordSearch(keyWord, username);
        //TODO search api

        // search web
        List<QueryResultModel> webList = searchWeb(keyWord);

        //TODO merge

        return webList;
    }

    @Override
    public String imgToWord(OCR ocr, String username) {
        return null;
    }

    private void recordSearch(String keyWord, String username) {
        User user = userService.verifyUsername(username);
        Search search = new Search();
        search.setKeyword(keyWord);
        search.setSearchDate(new Date());
        search.setUser(user);
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
        System.out.println(searchService.searchWeb("textview"));
    }
}
