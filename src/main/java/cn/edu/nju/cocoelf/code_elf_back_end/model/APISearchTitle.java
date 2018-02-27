package cn.edu.nju.cocoelf.code_elf_back_end.model;

/**
 * 查询分条返回目录
 */
public class APISearchTitle {
    /**
     * 目录项标题
     */
    private String title;

    /**
     * 目录项指定连接的url
     */
    private String url;

    /**
     * 描述
     */
    private String description;

    /**
     *  全参数构造方法
     * @param title 标题
     * @param url    资源路径
     * @param description  描述
     */
    public APISearchTitle(String title, String url, String description) {
        this.title = title;
        this.url = url;
        this.description = description;
    }


    /**
     * 无参数构造方法m
     */
    public APISearchTitle() {
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
