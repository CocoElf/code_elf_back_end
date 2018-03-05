package cn.edh.nju.cocoelf.microsoftSample;

import org.ansj.domain.Term;
import org.ansj.splitWord.analysis.DicAnalysis;

import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class DataSet {

    private List<SenTerm> getSenTerm(String file) throws IOException {
        List<SenTerm>  senTermList;
        Stream<String> stream = Files.lines(Paths.get(file));
        senTermList = stream.map(t->{
            String first = t.substring(0,t.length()-2);
            String last = t.substring(t.length()-1);

            List<Term> termList = DicAnalysis.parse(first).getTerms();
            List<String> tagList = termList.stream().map(Term::getNatureStr).filter(s->!s.equals("null")).collect(Collectors.toList());
            SenTerm senTerm = new SenTerm();
            senTerm.classification = Integer.valueOf(last);
            senTerm.tokens = new String[tagList.size()];
            for(int i = 0 ; i < senTerm.tokens.length ; i++){
                senTerm.tokens[i] = tagList.get(i);
            }
            return senTerm;
        }).collect(Collectors.toList());
        return senTermList;
    }

    private void  writetoFile(String file, List<SenTerm> senTerms){
        try {
            //打开一个写文件器，构造函数中的第二个参数true表示以追加形式写文件
            FileWriter writer = new FileWriter(file, true);
            for (SenTerm s: senTerms) {
                writer.write(s.getString()+"\n");
                writer.flush();
            }
            writer.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void learn(String from, String to) throws IOException {
        List<SenTerm> senTermList = getSenTerm(from);
        writetoFile(to,senTermList);
    }

    public static void main(String[] args) throws IOException {
        DataSet dataSet = new DataSet();
        dataSet.learn("library/chenshuyu.txt","library/dictionary.txt");

    }

}
