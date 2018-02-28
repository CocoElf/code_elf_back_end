package cn.edu.nju.cocoelf.code_elf_back_end.entity;

import javax.persistence.*;
import java.util.Date;

@Entity
public class Memo {

    private String name;

    private String snippet;

    private String url;

    private String keyWord;

    @Column(name = "`date`")
    private Date date;

    @Id
    private Integer memoId;

    @ManyToOne(cascade = {CascadeType.MERGE, CascadeType.PERSIST, CascadeType.REFRESH, CascadeType.DETACH})
    @JoinColumn(name = "username")
    private User user;

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

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    @Override
    public String toString() {
        return "Memo{" + "name='" + name + '\'' + ", snippet='" + snippet + '\'' + ", url='" + url + '\'' + ", " +
                "keyWord='" + keyWord + '\'' + ", date=" + date + ", memoId=" + memoId + ", user=" + user + '}';
    }
}
