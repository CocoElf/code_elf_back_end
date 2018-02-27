package cn.edu.nju.cocoelf.code_elf_back_end.service.component;

import cn.edu.nju.cocoelf.code_elf_back_end.model.QueryResultModel;
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

    public static List<QueryResultModel> sortAndCutResult(List<QueryResultModel> queryResultModelList) {
        List<QueryResultModel> firstList = new ArrayList<>();
        List<QueryResultModel> secondList = new ArrayList<>();
        List<QueryResultModel> thirdList = new ArrayList<>();

        for (QueryResultModel queryResultModel: queryResultModelList) {
            if (!queryResultModel.getUrl().contains("www.stackoverflow.com")) {
                if (queryResultModel.getUrl().contains("www.csdn.net")) {
                    secondList.add(queryResultModel);
                } else {
                    thirdList.add(queryResultModel);
                }
                continue;
            }
            firstList.add(queryResultModel);
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
