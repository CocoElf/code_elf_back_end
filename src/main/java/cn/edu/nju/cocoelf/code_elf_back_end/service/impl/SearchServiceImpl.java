package cn.edu.nju.cocoelf.code_elf_back_end.service.impl;

import cn.edu.nju.cocoelf.code_elf_back_end.entity.Search;
import cn.edu.nju.cocoelf.code_elf_back_end.entity.User;
import cn.edu.nju.cocoelf.code_elf_back_end.exception.InvalidRequestException;
import cn.edu.nju.cocoelf.code_elf_back_end.model.OCR;
import cn.edu.nju.cocoelf.code_elf_back_end.model.QueryResultModel;
import cn.edu.nju.cocoelf.code_elf_back_end.repository.SearchRepository;
import cn.edu.nju.cocoelf.code_elf_back_end.service.LanguageService;
import cn.edu.nju.cocoelf.code_elf_back_end.service.SearchService;
import cn.edu.nju.cocoelf.code_elf_back_end.service.UserService;
import cn.edu.nju.cocoelf.code_elf_back_end.service.component.OCRFilter;
import cn.edu.nju.cocoelf.code_elf_back_end.service.component.SearchFilter;
import cn.edu.nju.cocoelf.code_elf_back_end.service.component.SenTerm;
import cn.edu.nju.cocoelf.code_elf_back_end.service.component.KNNModel;
import cn.edu.nju.cocoelf.code_elf_back_end.util.SearchUtil;
import com.alibaba.fastjson.JSON;
import org.ansj.domain.Term;
import org.ansj.splitWord.analysis.DicAnalysis;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

@Service
public class SearchServiceImpl implements SearchService {

    @Autowired
    UserService userService;

    @Autowired
    SearchRepository searchRepository;

    @Autowired
    LanguageService languageService;

    @Autowired
    OCRFilter ocrFilter;

    @Override
    public List<QueryResultModel> queryWithWord(String keyWord, String username) {
        // record
        recordSearch(keyWord, username);
        keyWord = keyWord.toLowerCase();

        int type = classify(keyWord);

        // search api
        String languageName = "";
        String versionString = "";
        double version = 0.0;
        String apiKeyWord = "";
        boolean apiSearch = false;
        if (keyWord.contains("python")) {
            apiSearch = true;
            languageName = "python";
            versionString = getNum(keyWord);
            apiKeyWord = keyWord.replaceAll(languageName, "").replaceAll(versionString, "");
            try {
                version = Double.parseDouble(versionString);
            } catch (Exception e) {
                apiSearch = false;
            }
        }
        List<? extends QueryResultModel> apiList = new ArrayList<>();
        if (apiSearch) {
            apiList = languageService.searchAPIByKeyword(languageName, version,
                    apiKeyWord, 5);
        }


        // search web
        List<? extends QueryResultModel> webList = searchWeb(keyWord);

        // merge
        return merge(apiList, webList);
    }

    @Override
    public String imgToWord(OCR ocr, String username) {
        System.out.println(ocr);
        userService.verifyUsername(username);
        return ocrFilter.translate(ocr);
    }

    private List<QueryResultModel> merge(List<? extends QueryResultModel> a, List<? extends
            QueryResultModel> b) {
        List<QueryResultModel> res = new ArrayList<>();
        res.addAll(a);
        res.addAll(b);
        return res;

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

    private String getNum(String word) {
        // 需要取整数和小数的字符串
        String str = word;
        // 控制正则表达式的匹配行为的参数(小数)
        Pattern p = Pattern.compile("(\\d+\\.\\d+)");
        //Matcher类的构造方法也是私有的,不能随意创建,只能通过Pattern.matcher(CharSequence input)方法得到该类的实例.
        Matcher m = p.matcher(str);
        //m.find用来判断该字符串中是否含有与"(\\d+\\.\\d+)"相匹配的子串
        if (m.find()) {
            //如果有相匹配的,则判断是否为null操作
            //group()中的参数：0表示匹配整个正则，1表示匹配第一个括号的正则,2表示匹配第二个正则,在这只有一个括号,即1和0是一样的
            str = m.group(1) == null ? "" : m.group(1);
        } else {
            //如果匹配不到小数，就进行整数匹配
            p = Pattern.compile("(\\d+)");
            m = p.matcher(str);
            if (m.find()) {
                //如果有整数相匹配
                str = m.group(1) == null ? "" : m.group(1);
            } else {
                //如果没有小数和整数相匹配,即字符串中没有整数和小数，就设为空
                str = "";
            }
        }
        System.out.println(str);
        return str;

    }

    public static void main(String... args) {
        SearchServiceImpl searchService = new SearchServiceImpl();
        System.out.println(searchService.searchWeb("textview"));
    }


    /**
     * 将用户输入的语句进行分类
     * @param sen
     * @return
     */
    public int classify(String sen){
        List<Term> termList = DicAnalysis.parse(sen).getTerms();
        List<String> tagList = termList.stream().map(Term::getNatureStr).filter(t->!t.equals("null")).collect(Collectors.toList());
        SenTerm senTerm = new SenTerm();
        senTerm.tokens = new String[tagList.size()];
        for(int i = 0 ; i < senTerm.tokens.length ; i++){
            senTerm.tokens[i] = tagList.get(i);
        }
        KNNModel knnModel = new KNNModel("library/dictionary.txt",3);
        return  knnModel.getType(senTerm);
    }
}
