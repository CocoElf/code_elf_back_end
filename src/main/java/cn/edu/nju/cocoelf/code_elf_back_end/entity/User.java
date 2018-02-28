package cn.edu.nju.cocoelf.code_elf_back_end.entity;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import java.util.List;

@Entity
public class User {

    @Id
    private String username;

    private String password;

    @OneToMany(cascade = {CascadeType.ALL}, mappedBy = "user")
    private List<Memo> memoList;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "user")
    private List<TimeRecord> timeRecordList;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "user")
    private List<Search> searchList;

    public List<TimeRecord> getTimeRecordList() {
        return timeRecordList;
    }

    public void setTimeRecordList(List<TimeRecord> timeRecordList) {
        this.timeRecordList = timeRecordList;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public List<Memo> getMemoList() {
        return memoList;
    }

    public void setMemoList(List<Memo> memoList) {
        this.memoList = memoList;
    }

    @Override
    public String toString() {
        return "User{" + "username='" + username + '\'' + ", password='" + password + '\'' + ", memoList=" + memoList
                + ", timeRecordList=" + timeRecordList + '}';
    }
}
