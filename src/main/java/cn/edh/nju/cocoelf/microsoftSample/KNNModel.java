//package cn.edh.nju.cocoelf.microsoftSample;
//
//import java.io.IOException;
//import java.nio.file.Files;
//import java.nio.file.Paths;
//import java.util.Comparator;
//import java.util.PriorityQueue;
//import java.util.stream.Stream;
//
//public class KNNModel {
//    private String dictionary;
//
//    private  int k;
//
//
//    public KNNModel(String dictionary, int k) {
//        this.dictionary = dictionary;
//        this.k = k;
//    }
//
//    public int getType(SenTerm senTerm){
//        Comparator<SenTerm> comparator = new Comparator<SenTerm>() {
//            @Override
//            public int compare(SenTerm o1, SenTerm o2) {
//                return (int)(senTerm.disTanceTo(o1) - senTerm.disTanceTo(o2));
//            }
//        };
//        PriorityQueue<SenTerm> queue = new PriorityQueue<SenTerm>(k, comparator);
//        try {
//            Stream<String> stream = Files.lines(Paths.get(dictionary));
//            stream.forEach(t->{
//                SenTerm sen = new SenTerm(t);
//                double dis = senTerm.disTanceTo(sen);
//                if(queue.size() < k){
//                    queue.add(sen);
//                }else if(dis > queue.peek().disTanceTo(senTerm)){
//                    queue.poll();
//                    queue.add(sen);
//                }
//
//            });
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
//
//        if(queue.size() != 0 ){
//            int[] counts = {0,0,0,0,0};
//            for(SenTerm s : queue){
//                counts[s.classification]++;
//            }
//
//            int result = 0;
//            int count = 0;
//            for (int i = 0 ; i < counts.length ; i++){
//                if(counts[i] > count){
//                    count = counts[i];
//                    result = i;
//                }
//            }
//            return  result;
//        }else {
//            return SenTerm.ERROR;
//        }
//    }
//
//}
