package cn.edu.nju.cocoelf.code_elf_back_end.model;

public class SearchModel {
    private String keyword;

    public String getKeyword() {
        return keyword;
    }

    public void setKeyword(String keyword) {
        this.keyword = keyword;
    }

    @Override
    public String toString() {
        return "SearchModel{" + "keyword='" + keyword + '\'' + '}';
    }
}
