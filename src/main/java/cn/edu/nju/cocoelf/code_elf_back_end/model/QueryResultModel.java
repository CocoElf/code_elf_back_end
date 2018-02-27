package cn.edu.nju.cocoelf.code_elf_back_end.model;

public class QueryResultModel {

    private String name;

    private String snippet;

    private String url;

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

    @Override
    public String toString() {
        return "QueryResultModel{" + "name='" + name + '\'' + ", snippet='" + snippet + '\'' + ", url='" + url + '\''
                + '}';
    }
}
