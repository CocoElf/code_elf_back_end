package cn.edu.nju.cocoelf.code_elf_back_end.service.component;

import cn.edu.nju.cocoelf.code_elf_back_end.config.param.CodeType;
import cn.edu.nju.cocoelf.code_elf_back_end.exception.InvalidRequestException;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

public class TimingContainer {

    private static Map<String, Date> startTimeMap = new HashMap<>();

    private static Map<String, CodeType> codeTypeMap = new HashMap<>();

    public static void setStartTime(String username, CodeType codeType) {
        if (startTimeMap.containsKey(username)) {
            throw new InvalidRequestException("你已经开始计时了");
        }
        startTimeMap.put(username, new Date());
        codeTypeMap.put(username, codeType);
    }

    public static Date getStartTime(String username) {
        if (!startTimeMap.containsKey(username)) {
            throw new InvalidRequestException("你还没有开始计时");
        }
        Date res = startTimeMap.get(username);
        startTimeMap.remove(username);
        return res;
    }

    public static CodeType getCodeType(String username) {
        if (!codeTypeMap.containsKey(username)) {
            throw new InvalidRequestException("你还没有开始计时");
        }
        CodeType res = codeTypeMap.get(username);
        codeTypeMap.remove(username);
        return res;
    }
}
