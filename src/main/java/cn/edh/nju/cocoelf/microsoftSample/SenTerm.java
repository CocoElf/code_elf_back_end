package cn.edh.nju.cocoelf.microsoftSample;

import java.util.*;

public class SenTerm {
    public static int API_SEARCH = 0;
    public static int WEB_SEARCH = 1;
    public static int RECCOMMAND = 2;
    public static int FUNCTION_SEARCH = 3;
    public static int DIFFERENCE = 4;
    public static int ERROR = 5;

    public static String[] mapToString = {"API搜索","网页搜索","推荐","根据功能查询函数","比较差别"};

    public String[] tokens ;

    public int classification;

    public double disTanceTo(SenTerm senTerm){
        Map<String, int[]> AlgorithmMap = new HashMap<String, int[]>();
        for(int i = 0 ; i < tokens.length ; i++){
            int[] fq = AlgorithmMap.get(tokens[i]);
            if(fq != null && fq.length == 2){
                fq[0]++;//已有该字符，加1
            }else {
                fq = new int[2];
                fq[0] = 1;
                fq[1] = 0;
                AlgorithmMap.put(tokens[i], fq);//新增字符入map
            }
        }

        for(int i = 0 ; i < senTerm.tokens.length ; i++){
            int[] fq = AlgorithmMap.get(senTerm.tokens[i]);
            if(fq != null && fq.length == 2){
                fq[1]++;//已有该字符，加1
            }else {
                fq = new int[2];
                fq[0] = 1;
                fq[1] = 0;
                AlgorithmMap.put(senTerm.tokens[i], fq);//新增字符入map
            }
        }

        Iterator<String> iterator = AlgorithmMap.keySet().iterator();
        double sqdoc1 = 0;
        double sqdoc2 = 0;
        double denominator = 0;
        while(iterator.hasNext()){
            int[] c = AlgorithmMap.get(iterator.next());
            denominator += c[0]*c[1];
            sqdoc1 += c[0]*c[0];
            sqdoc2 += c[1]*c[1];
        }

        return denominator / Math.sqrt(sqdoc1*sqdoc2);//余弦计算
    }

    public SenTerm(){

    }

    public SenTerm(String str){
        String[] spit = str.split(" ");
        this.classification = Integer.valueOf(spit[1]);
        tokens = spit[0].split("/");
    }

    public String getString(){
        if(tokens.length == 0) return "";
        StringBuilder ans = new StringBuilder();
        ans.append(tokens[0]);
        for(int i = 1 ; i < tokens.length ; i++){
            ans.append("/").append(tokens[i]);
        }
        ans.append(" ");
        ans.append(classification);
        return ans.toString();
    }


}
