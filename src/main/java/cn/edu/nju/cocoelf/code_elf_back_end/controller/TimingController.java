package cn.edu.nju.cocoelf.code_elf_back_end.controller;

import cn.edu.nju.cocoelf.code_elf_back_end.config.param.CodeType;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Date;

@RestController
@RequestMapping(value = "/api/timing", produces = "application/json;charset=UTF-8")
public class TimingController {

    /**
     * 根据时长获得建议
     * @param codeTime 编程时长，单位毫秒
     * @param codeType 编程类型
     * @param signature 用户签名
     * @return 建议
     */
    @PostMapping("/tips")
    public String getTips(CodeType codeType, Long codeTime, String signature) {
        return null;
    }

    /**
     * 开始计时
     * @param codeType 编程类型
     * @param signature 用户签名
     * @return 是否成功
     */
    @PostMapping("/startTiming")
    public String startTiming(CodeType codeType, String signature) {
        return null;
    }

    /**
     * 结束计时
     * @param codeType 编程类型
     * @param signature 用户签名
     * @return 是否成功
     */
    @PostMapping("/endTiming")
    public String endTiming(CodeType codeType, String signature) {
        return null;
    }

    /**
     * 获得某段时间的统计数据
     * @param fromDate 起始日期
     * @param toDate 截止日期
     * @param signature 用户签名
     * @return 统计数据
     */
    @PostMapping("/statistic")
    public String getStatistic(Date fromDate, Date toDate, String signature) {
        return null;
    }
}
