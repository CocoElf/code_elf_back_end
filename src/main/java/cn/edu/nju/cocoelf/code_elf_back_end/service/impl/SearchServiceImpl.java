package cn.edu.nju.cocoelf.code_elf_back_end.service.impl;

import cn.edu.nju.cocoelf.code_elf_back_end.entity.Search;
import cn.edu.nju.cocoelf.code_elf_back_end.entity.StubApi;
import cn.edu.nju.cocoelf.code_elf_back_end.entity.User;
import cn.edu.nju.cocoelf.code_elf_back_end.exception.InvalidRequestException;
import cn.edu.nju.cocoelf.code_elf_back_end.model.OCR;
import cn.edu.nju.cocoelf.code_elf_back_end.repository.PythonAPIDao;

import cn.edu.nju.cocoelf.code_elf_back_end.model.SearchResultModel;

import cn.edu.nju.cocoelf.code_elf_back_end.repository.SearchRepository;
import cn.edu.nju.cocoelf.code_elf_back_end.service.LanguageService;
import cn.edu.nju.cocoelf.code_elf_back_end.service.SearchService;
import cn.edu.nju.cocoelf.code_elf_back_end.service.UserService;
import cn.edu.nju.cocoelf.code_elf_back_end.service.component.OCRFilter;
import cn.edu.nju.cocoelf.code_elf_back_end.service.component.SearchFilter;
import cn.edu.nju.cocoelf.code_elf_back_end.service.component.SenTerm;
import cn.edu.nju.cocoelf.code_elf_back_end.service.component.KNNModel;
import cn.edu.nju.cocoelf.code_elf_back_end.util.LogUtil;
import cn.edu.nju.cocoelf.code_elf_back_end.util.SearchUtil;
import cn.edu.nju.cocoelf.code_elf_back_end.util.TranslateUtil;
import com.alibaba.fastjson.JSON;
import org.ansj.domain.Term;
import org.ansj.splitWord.analysis.DicAnalysis;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;
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

    @Autowired
    PythonAPIDao pythonAPIDao;

    @Autowired
    TranslateUtil translateUtil;


    @Override
    public List<SearchResultModel> searchWithWord(String keyWord, String username) {
        // record
//        recordSearch(keyWord, username);
        keyWord = keyWord.toLowerCase();
        LogUtil.log(keyWord);

        List<Term> termList = DicAnalysis.parse(keyWord).getTerms();
        int type = classify(termList);
        Map<String,List<String>> comp = getComponent(termList);

        List<? extends SearchResultModel> apiList = new ArrayList<>();
        List<? extends SearchResultModel> webList = new ArrayList<>();

        switch (type){
            case 0:{
                apiList = apiSearch(keyWord,comp);
                break;
            }
            case 3:{
                apiList = apiSearchBaseFuntion(keyWord,termList,comp);
                break;
            }

            default:{
               webList = searchWeb(keyWord, type);
            }
        }

        // merge
        //TODO add web search
        return merge(apiList, webList);
    }


    @Override
    public String imgToWord(OCR ocr, String username) {
        System.out.println(ocr);
        userService.verifyUsername(username);
        return ocrFilter.translate(ocr);
    }

    private List<SearchResultModel> merge(List<? extends SearchResultModel> a, List<? extends
            SearchResultModel> b) {
        List<SearchResultModel> res = new ArrayList<>();
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


    //TODO wait to add sqlite source
    private List<SearchResultModel> apiSearch(String sen, Map<String,List<String>> map){
        String language = map.get("lan").get(0);
        String version = getNum(sen);
        String method =  map.get("class").size() == 0 ? map.get("in").get(0):map.get("class").get(0);
        List<StubApi> stubApiList = pythonAPIDao.searchResult(method,new ArrayList<>());
        List<String> keywords = new ArrayList<>();
        keywords.addAll(map.get("lan"));
        keywords.addAll(map.get("in"));
        keywords.addAll(map.get("class"));
        List<SearchResultModel> searchResultModelList  =
                stubApiList.stream().map(t->{
                    SearchResultModel s = new SearchResultModel();
                    s.setDate(new Date());
                    s.setKeywords(keywords);
                    s.setType(t.getType());
                    s.setSnippet("api查询");
                    s.setUrl(t.getPage()+t.getPosition());
                    s.setName(t.getPrint());
                    return s;
                }).collect(Collectors.toList());
        return searchResultModelList;
    }

    //TODO wait to add sqlite source
    private List<SearchResultModel> apiSearchBaseFuntion(String sen, List<Term> termList, Map<String,List<String>> map){
//        String language = map.get("lan").get(0);
//        String version = getNum(sen);
//        String method  = map.get("class").get(0);
        List<StubApi> stubApiList;
        if(map.get("class").size()==0){
            List<String> functions =  getFuntion(termList,true);
            stubApiList= pythonAPIDao.searchResult("",functions);
        }else{
            List<String> functions =  getFuntion(termList,false);
            stubApiList= pythonAPIDao.searchResult(map.get("class").get(0),functions);
        }
        List<String> keywords = new ArrayList<>();
        keywords.addAll(map.get("lan"));
        keywords.addAll(map.get("in"));
        keywords.addAll(map.get("class"));
        List<SearchResultModel> searchResultModelList  =
                stubApiList.stream().map(t->{
                    SearchResultModel s = new SearchResultModel();
                    s.setDate(new Date());
                    s.setKeywords(keywords);
                    s.setType(t.getType());
                    s.setSnippet("根据功能查找函数");
                    s.setUrl(t.getPage()+t.getPosition());
                    s.setName(t.getPrint());
                    return s;
                }).collect(Collectors.toList());

        return searchResultModelList;
    }

    private List<SearchResultModel> searchWeb(String keyWord, Integer type) {

        String akeyWord = SearchFilter.convertKeyWord(keyWord);
        String searchResult;
        try {
            searchResult = SearchUtil.SearchWeb(akeyWord, 100).pureResult;
        } catch (Exception e) {
            System.out.println(e.getMessage());
            throw new InvalidRequestException("搜索api出bug了");
        }
        System.out.println(searchResult);

        List<SearchResultModel> searchResultModelList = JSON.parseArray(searchResult, SearchResultModel.class);

        return SearchFilter.sortAndCutResult(searchResultModelList);
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
        searchService.searchWithWord("C++11的字符串转化成数字的方法","");
//        System.out.println(searchService.searchWeb("textview"));
    }


    /**
     * 将用户输入的语句进行分类
     * @return
     */
    public int classify( List<Term> termList){
        List<String> tagList = termList.stream().map(Term::getNatureStr).filter(t->!t.equals("null")).collect(Collectors.toList());
        SenTerm senTerm = new SenTerm();
        senTerm.tokens = new String[tagList.size()];
        for(int i = 0 ; i < senTerm.tokens.length ; i++){
            senTerm.tokens[i] = tagList.get(i);
        }
        KNNModel knnModel = new KNNModel("library/dictionary.txt",3);
        return  knnModel.getType(senTerm);
    }


    /**
     * 解析用户输入语句的关键信息
     * @param termList
     * @return
     */
    private Map<String,List<String>> getComponent(List<Term> termList){
        Map<String,List<String>> map = new HashMap<>();
        map.put("lan",new ArrayList<>());
        map.put("in",new ArrayList<>());
        map.put("class",new ArrayList<>());
        for(Term term : termList){
            if(term.getNatureStr().equals("lan")){
                map.get("lan").add(term.getName());
            }else if (term.getNatureStr().equals("in")){
                map.get("in").add(term.getName());
            }else if (term.getNatureStr().equals("class")){
                String name = term.getName();
                map.get("class").add(translateUtil.translate(name));
            }
        }

        return map;
    }

    private List<String> getFuntion(List<Term> termList, boolean builtIn){
        List<String> list = new ArrayList<>();
        boolean begin = builtIn;
        for(Term term : termList){
            if(!begin && !term.getNatureStr().equals("class")){
                continue;
            }else if(begin){
                if(Arrays.asList("n","nv","v","d","a").contains(term.getNatureStr()))
                    list.add(term.getName());
            }else{
                begin = true;
            }
        }

        return list;
    }
}
