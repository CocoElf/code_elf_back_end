package cn.edu.nju.cocoelf.code_elf_back_end.entity;

import javax.persistence.*;
import java.util.Date;

@Entity
public class Memo {

    private String name;

    private String snippet;

    private String url;

    private String keyWord;

    private String contentPath;

    private Date memoDate;

    private String type;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer memoId;

    @ManyToOne(cascade = {CascadeType.MERGE, CascadeType.PERSIST, CascadeType.REFRESH, CascadeType.DETACH})
    @JoinColumn(name = "username")
    private User user;

    public String getContentPath() {
        return contentPath;
    }

    public void setContentPath(String contentPath) {
        this.contentPath = contentPath;
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

    public String getKeyWord() {
        return keyWord;
    }

    public void setKeyWord(String keyWord) {
        this.keyWord = keyWord;
    }

    public Date getMemoDate() {
        return memoDate;
    }

    public void setMemoDate(Date memoDate) {
        this.memoDate = memoDate;
    }

    public Integer getMemoId() {
        return memoId;
    }

    public void setMemoId(Integer memoId) {
        this.memoId = memoId;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    @Override
    public String toString() {
        return "Memo{" + "name='" + name + '\'' + ", snippet='" + snippet + '\'' + ", url='" + url + '\'' + ", " +
                "keyWord='" + keyWord + '\'' + ", contentPath='" + contentPath + '\'' + ", memoDate=" + memoDate + "," +
                " type='" + type + '\'' + ", memoId=" + memoId + '}';
    }
}
