package cn.edu.nju.cocoelf.code_elf_back_end.util;

import java.util.logging.Level;
import java.util.logging.Logger;

public class LogUtil {
    static Logger logger = Logger.getAnonymousLogger();

    public static void log(Object message){
        logger.log(Level.INFO,String.valueOf(message));
    }
}
