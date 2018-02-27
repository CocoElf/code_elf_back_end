package cn.edu.nju.cocoelf.code_elf_back_end.util;

import org.apache.http.client.utils.URIBuilder;

import javax.net.ssl.HttpsURLConnection;
import java.io.InputStream;
import java.net.URL;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

public class SearchUtil {
    // Replace the subscriptionKey string value with your valid subscription key.
    static String subscriptionKey = "6374bb8323af4b189c338460ada5465c";

    static String host = "https://api.cognitive.microsoft.com";
    static String path = "/bing/v7.0/search";

    static String count = "count";
    static String ansCount = "ansCount";

    public static SearchResults SearchWeb(String searchQuery, Integer itemNum) throws Exception {
        URIBuilder ub = new URIBuilder(host + path);
        ub.addParameter("q", searchQuery);
        ub.addParameter(count, itemNum.toString());
        ub.addParameter(ansCount, "1");
        URL url = new URL(ub.toString());

        HttpsURLConnection connection = (HttpsURLConnection) url.openConnection();
        connection.setRequestProperty("Ocp-Apim-Subscription-Key", subscriptionKey);

        // receive JSON bod
        InputStream stream = connection.getInputStream();
        String response = new Scanner(stream).useDelimiter("\\A").next();

        // construct result object for return
        SearchResults results = new SearchResults(new HashMap<>(), response);

        // extract Bing-related HTTP headers
        Map<String, List<String>> headers = connection.getHeaderFields();
        for (String header : headers.keySet()) {
            if (header == null) continue;      // may have null key
            if (header.startsWith("BingAPIs-") || header.startsWith("X-MSEdge-")) {
                results.relevantHeaders.put(header, headers.get(header).get(0));
            }
        }

        stream.close();
        return results;
    }
}
