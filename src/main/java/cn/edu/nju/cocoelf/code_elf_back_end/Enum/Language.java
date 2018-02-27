package cn.edu.nju.cocoelf.code_elf_back_end.Enum;

import cn.edu.nju.cocoelf.code_elf_back_end.model.APISearchTitle;
import com.gargoylesoftware.htmlunit.BrowserVersion;
import com.gargoylesoftware.htmlunit.WebClient;
import com.gargoylesoftware.htmlunit.html.HtmlPage;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

public enum Language implements LanguageFunction {
    python3 {
        @Override
        public List<APISearchTitle> getContentList(String html, String baseUrl) {
            Document doc = Jsoup.parse(html,"UTF-8");
            org.jsoup.nodes.Element content = doc.getElementById("search-results");
            List<APISearchTitle> apiSearchTitleList =  new ArrayList<>();
            Elements list = content.getElementsByTag("li");
            for (Element single : list) {
                if(single.getElementsByTag("span")==null
                        || !single.getElementsByTag("span").hasText())
                    continue;
                Element span = single.getElementsByTag("span").first();
                if(span.text() == null || !span.text().trim().startsWith("(Python"))
                    continue;
                Element a = single.getElementsByTag("a").first();
                String show = a.text();
                String url = baseUrl+a.attr("href");
                String desc = span.text().trim();
                APISearchTitle apiSearchTitle = new APISearchTitle(show,url,desc);
                apiSearchTitleList.add(apiSearchTitle);
            }
            return apiSearchTitleList;
        }

        @Override
        public String getBaseURL() {
            return "file:///api/python3/";
        }

        @Override
        public String getSearchURL(String keyword) {
            return "file:///Users/wshwbluebird/javaNeon/code_elf_back_end/api/python3/search.html?q="+keyword;
        }
    };


    public String  getSearchPage(String url, int speed) throws IOException {
        assert speed >=1 && speed <=5 : "search speed is required to be 1-5";
        int wait = speed * 1000;
        URL url1 = new URL(url);
        WebClient webClient = new WebClient(BrowserVersion.CHROME);
        HtmlPage page = webClient.getPage(url1);
        webClient.waitForBackgroundJavaScript(wait);
        webClient.close();
        return page.asXml();
    }

    /**
     * 根据输入的名字和版本号，获得实际的language 实体
     * @param name   输入的语言名称
     * @param version  语言版本
     * @return  Language
     */
    public LanguageFunction getLanguageType(String name, double version){
        Language choosed = null;
        for (Language language : Language.values()) {
            String lname = language.name();
            if(!lname.startsWith(name)){
                continue;
            }
            String lVersion = lname.substring(name.length());
            lVersion = lVersion.replaceAll("_",".");
            double lVer = Double.valueOf(lVersion);
            if(lVer == version){
                return language;
            }
            if(choosed == null || lVer > version){
                choosed = language;
            }

        }
        return choosed;
    }
}
