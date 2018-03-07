package cn.edu.nju.cocoelf.code_elf_back_end.service.component;

import cn.edu.nju.cocoelf.code_elf_back_end.model.QueryResultModel;
import cn.edu.nju.cocoelf.code_elf_back_end.model.SearchResultModel;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class SearchFilter {

    private static final String[] stackOverFlowKey = {"exception", "error"};

    public static String convertKeyWord(String keyWord) {
        String res = keyWord.toLowerCase();

        if (containKey(res)) {
            res += " site:https://stackoverflow.com";
        }
        return res;
    }

    public static List<SearchResultModel> sortAndCutResult(List<SearchResultModel> searchResultModelList) {
        List<SearchResultModel> firstList = new ArrayList<>();
        List<SearchResultModel> secondList = new ArrayList<>();
        List<SearchResultModel> thirdList = new ArrayList<>();

        for (SearchResultModel searchResultModel: searchResultModelList) {
            if (!searchResultModel.getUrl().contains("www.stackoverflow.com")) {
                if (searchResultModel.getUrl().contains("www.csdn.net")) {
                    secondList.add(searchResultModel);
                } else {
                    thirdList.add(searchResultModel);
                }
                continue;
            }
            firstList.add(searchResultModel);
        }

        firstList.addAll(secondList);
        firstList.addAll(thirdList);

        if (firstList.size() <= 20) {
            return firstList;
        }
        return firstList.subList(0, 20);
    }

    private static boolean containKey(String key) {
        for (String s: stackOverFlowKey) {
            if (key.contains(s)) {
                return true;
            }
        }
        return false;
    }
}
