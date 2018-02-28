package cn.edu.nju.cocoelf.code_elf_back_end.model;

import java.util.Date;

public class MemoModel {

    private String name;

    private String snippet;

    private String url;

    private String keyWord;

    private Date date;

    private Integer memoId;

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

    public String getKeyWord() {
        return keyWord;
    }

    public void setKeyWord(String keyWord) {
        this.keyWord = keyWord;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public Integer getMemoId() {
        return memoId;
    }

    public void setMemoId(Integer memoId) {
        this.memoId = memoId;
    }

    @Override
    public String toString() {
        return "MemoModel{" + "name='" + name + '\'' + ", snippet='" + snippet + '\'' + ", url='" + url + '\'' + ", " +
                "keyWord='" + keyWord + '\'' + ", date=" + date + ", memoId=" + memoId + '}';
    }
}
