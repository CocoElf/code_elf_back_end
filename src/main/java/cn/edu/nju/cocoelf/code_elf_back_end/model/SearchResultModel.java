package cn.edu.nju.cocoelf.code_elf_back_end.model;

import cn.edu.nju.cocoelf.code_elf_back_end.util.DateUtil;

import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

public class SearchResultModel implements Serializable {
    private String name;
    private String url;
    private String snippet;
    private Date dateLastCrawled;
    private List<String> keywords;
    private String type;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getSnippet() {
        return snippet;
    }

    public void setSnippet(String snippet) {
        this.snippet = snippet;
    }

    public Date getDateLastCrawled() {
        return dateLastCrawled;
    }

    public void setDateLastCrawled(Date dateLastCrawled) {
        this.dateLastCrawled = dateLastCrawled;
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
        return "SearchResultModel{" + "name='" + name + '\'' + ", url='" + url + '\'' + ", snippet='" + snippet +
                '\'' + ", dateLastCrawled=" + dateLastCrawled + ", keywords=" + keywords + ", type='" + type + '\'' +
                '}';
    }

}

