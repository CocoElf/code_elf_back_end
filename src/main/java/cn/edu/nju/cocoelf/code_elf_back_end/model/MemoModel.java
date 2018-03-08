package cn.edu.nju.cocoelf.code_elf_back_end.model;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

public class MemoModel implements Serializable {
    private String name;

    private String url;

    private String snippet;

    private Date date;

    private List<String> keywords;

    private String type;

    private Integer memoId;

    private String content;

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

    public Integer getMemoId() {
        return memoId;
    }

    public void setMemoId(Integer memoId) {
        this.memoId = memoId;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    @Override
    public String toString() {
        return "MemoModel{" + "name='" + name + '\'' + ", url='" + url + '\'' + ", snippet='" + snippet + '\'' + ", " +
                "date=" + date + ", keywords=" + keywords + ", type='" + type + '\'' + ", memoId=" + memoId + ", " +
                "content='" + content + '\'' + '}';
    }
}
