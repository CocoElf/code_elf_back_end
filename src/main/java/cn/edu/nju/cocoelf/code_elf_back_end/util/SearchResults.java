package cn.edu.nju.cocoelf.code_elf_back_end.util;

import java.util.HashMap;

public class SearchResults {
    public HashMap<String, String> relevantHeaders;
    public String jsonResponse;
    public String pureResult;
    SearchResults(HashMap<String, String> headers, String json) {
        relevantHeaders = headers;
        jsonResponse = json;
        System.out.println(jsonResponse);
        int begin = json.indexOf("\"value\":") + 9;
        int end = json.indexOf("\"someResultsRemoved\": ") - 2;
        if (end < begin) {
            end = json.indexOf("\"relatedSearches\":") - 3;
        }
        if (end < begin) {
            end = json.indexOf("\"rankingResponse\":") - 3;
        }
        System.out.println(begin + " "  +end);
        pureResult = jsonResponse.substring(begin, end);
    }
}
