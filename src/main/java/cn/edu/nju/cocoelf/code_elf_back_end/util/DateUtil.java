package cn.edu.nju.cocoelf.code_elf_back_end.util;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class DateUtil {
    private static SimpleDateFormat Raw = new SimpleDateFormat("yyyy-MM-dd");
    private static SimpleDateFormat Accuracy = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

    public static Date instanceRawDate(String string) {
        try {
            return Raw.parse(string);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return new Date();
    }

    public static Date instanceAccuracyDate(String string) {
        try {
            return Accuracy.parse(string);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return new Date();
    }

    public static String getRawDate(Date date) {
        return Raw.format(date);
    }

    public static String getAccuracyDate(Date date) {
        return Accuracy.format(date);
    }


    public static void main(String[] args) {

    }
}
