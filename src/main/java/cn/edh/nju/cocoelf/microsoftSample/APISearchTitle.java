package cn.edh.nju.cocoelf.microsoftSample;

public class APISearchTitle {
    private String show;
    private String url;

    public String getShow() {
        return show;
    }

    public void setShow(String show) {
        this.show = show;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public APISearchTitle(String show ,String url){
        this.show = show;
        this.url = url;
    }
}
