package cn.edu.nju.cocoelf.code_elf_back_end.util;

import java.util.HashMap;

public class SearchResults {
    public HashMap<String, String> relevantHeaders;
    public String jsonResponse;
    public String pureResult;
    SearchResults(HashMap<String, String> headers, String json) {
        relevantHeaders = headers;
        jsonResponse = json;
        int begin = json.indexOf("\"value\":") + 9;
        int end = json.indexOf("\"someResultsRemoved\": ") - 2;
        pureResult = jsonResponse.substring(begin, end);
        System.out.println(jsonResponse);
        System.out.println(pureResult);
    }
}
