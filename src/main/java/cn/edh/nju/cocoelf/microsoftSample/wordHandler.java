//package cn.edh.nju.cocoelf.microsoftSample;
//
//
//import cn.edu.nju.cocoelf.code_elf_back_end.service.component.SenTerm;
//import org.ansj.domain.Result;
//import org.ansj.domain.Term;
//import org.ansj.library.DicLibrary;
//import org.ansj.recognition.Recognition;
//import org.ansj.recognition.impl.NatureRecognition;
//import org.ansj.splitWord.analysis.DicAnalysis;
//import org.ansj.splitWord.analysis.NlpAnalysis;
//import org.ansj.splitWord.analysis.ToAnalysis;
//import org.nlpcn.commons.lang.tire.domain.Forest;
//import org.nlpcn.commons.lang.tire.library.Library;
//
//import java.io.BufferedReader;
//import java.io.InputStreamReader;
//import java.lang.reflect.Array;
//import java.util.ArrayList;
//import java.util.Arrays;
//import java.util.List;
//import java.util.stream.Collectors;
//
//public class wordHandler {
//    public static void main(String[] args) throws Exception {
//       DicAnalysis.parse("");
//       BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
//       while (true){
//           System.out.println("用户搜索：");
//           String sen = br.readLine();
//
//           if(sen.equals("quit")){
//               break;
//           }
////           String result = analyze2(sen);
////           System.out.println(result);
//       }
//
//    }
//
//    public static String analyze(String sen){
//        String language = "";
//        String result;
//        List<Term> termList = DicAnalysis.parse(sen).getTerms();
//        List<String> dic = new ArrayList<>();
//        for (Term term: termList) {
////            System.out.println(term.getName()+"  "+term.getNatureStr());
//            if(term.getNatureStr().equals("lan")){
//                language  =  language + term.getName()+" ";
//            }
//            dic.add(term.getNatureStr());
//        }
//        if(language.length() == 0){
//            return "网页搜索";
//        }
//        language = language.trim();
//
//        if(dic.contains("master")) {
//            result = "内容推荐";
//        }else if(dic.contains("diff") && dic.contains("class")){
//            result = "比较不同";
//        }else if(!dic.contains("ques") && dic.contains("class")){
//            String last = dic.get(dic.size()-1);
//            if(Arrays.asList("class","desc").contains(last))
//                result = "api查找";
//            else
//                result = "根据功能查找函数";
//        }else if(dic.contains("ques") && dic.contains("class")){
//            result = "根据功能查找函数";
//        }else {
//            result = "网页搜索";
//        }
//        return "语言：" + language+", 分类："+result;
//    }
//
//
//}
