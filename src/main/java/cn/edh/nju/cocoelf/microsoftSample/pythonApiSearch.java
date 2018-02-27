package cn.edh.nju.cocoelf.microsoftSample;

import com.gargoylesoftware.htmlunit.BrowserVersion;
import com.gargoylesoftware.htmlunit.WebClient;
import com.gargoylesoftware.htmlunit.html.DomNode;
import com.gargoylesoftware.htmlunit.html.HtmlDivision;
import com.gargoylesoftware.htmlunit.html.HtmlInput;
import com.gargoylesoftware.htmlunit.html.HtmlPage;
import org.apache.commons.logging.LogFactory;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.IOException;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;

public class pythonApiSearch {
    public static void main(String[] args) throws IOException {
        java.util.logging.Logger.getLogger("org.apache.http.client").setLevel(Level.OFF);
        String url = "file:///Users/wshwbluebird/Desktop/doc/search.html?q=array";
        String ans = getResult(url);
        System.out.println("here");

        getContentList(ans,"base/");
    }


    public static String getResult(String url) throws IOException {
        URL url1 = new URL(url);
        WebClient webClient = new WebClient(BrowserVersion.CHROME);
        HtmlPage page = webClient.getPage(url1);
        webClient.waitForBackgroundJavaScript(3000);
        webClient.close();
        return page.asXml();
    }

    public static void  getContentList(String html, String baseurl){
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
           String show = a.text()+span.text().trim();
           String url = baseurl+a.attr("href");
           APISearchTitle apiSearchTitle = new APISearchTitle(show,url);

        }

    }


}
