package cn.edu.nju.cocoelf.code_elf_back_end.model;

import cn.edu.nju.cocoelf.code_elf_back_end.util.DateUtil;

import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

public class QueryResultModel {

    private String name;

    private String snippet;

    private String url;

    private Date date; // 网页查询返回相应的日期

    private List<String> keywords; //返回用户的关键字

    private String type; //返回用户的关键字

    public QueryResultModel() {
    }

    public QueryResultModel(String name, String snippet, String url) {
    }

    public QueryResultModel(String name, String snippet, String url, Date date, List<String> keywords, String type) {
        this.name = name;
        this.snippet = snippet;
        this.url = url;
        this.date = date;
        this.keywords = keywords;
        this.type = type;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSnippet() {
        return snippet;
    }

    public void setSnippet(String snippet) {
        this.snippet = snippet;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public List<String> getKeywords() {
        return keywords;
    }

    public void setKeywords(List<String> keywords) {
        this.keywords = keywords;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    @Override
    public String toString() {
        return "QueryResultModel{" + "name='" + name + '\'' + ", snippet='" + snippet + '\'' + ", url='" + url + '\''
            + ", type='" + type + '\'' + ", date='" + DateUtil.getRawDate(date) + '\'' +
                ", keywords=" + getListString(keywords) +
                '}';
    }


    private String getListString(List<String> list){
        if(list==null || list.size() == 0) return "[]";
        List<String> ans  = list.stream().map(t->'\''+t+'\'').collect(Collectors.toList());
        return ans.toString();
    }
}
