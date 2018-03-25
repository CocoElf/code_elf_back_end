package cn.edu.nju.cocoelf.code_elf_back_end.controller;

import cn.edu.nju.cocoelf.code_elf_back_end.config.param.CodeType;
import cn.edu.nju.cocoelf.code_elf_back_end.model.StatisticModel;
import cn.edu.nju.cocoelf.code_elf_back_end.model.TipModel;
import cn.edu.nju.cocoelf.code_elf_back_end.service.TimingService;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Date;

@RestController
@RequestMapping(value = "/api/timing", produces = "application/json;charset=UTF-8")
public class TimingController {

    @Autowired
    TimingService timingService;

    @ApiOperation(value = "根据编程类型和编程时间获得提示", notes = "编程时间的单位是毫秒")
    @ApiImplicitParams({@ApiImplicitParam(name = "codeType", value = "编程类型", required = true, dataType = "CodeType"),
            @ApiImplicitParam(name = "codeTime", value = "编程时间", required = true, dataType = "Long"),
            @ApiImplicitParam(name = "username", value = "用户名", required = true, dataType = "String"),})
    @PostMapping("/tips")
    public TipModel getTips(CodeType codeType, Long codeTime, String username) {
        return timingService.getTips(codeType, codeTime, username);
    }

    @ApiOperation(value = "开始某一类型的编程计时")
    @ApiImplicitParams({@ApiImplicitParam(name = "codeType", value = "编程类型", required = true, dataType = "CodeType"),
            @ApiImplicitParam(name = "username", value = "用户名", required = true, dataType = "String"),})
    @PostMapping("/startTiming")
    public void startTiming(CodeType codeType, String username) {
        timingService.startTiming(codeType, username);
    }

    @ApiOperation(value = "停止某一类型的编程计时", notes = "返回编程时间")
    @ApiImplicitParams({@ApiImplicitParam(name = "codeType", value = "编程类型", required = true, dataType = "CodeType"),
            @ApiImplicitParam(name = "username", value = "用户名", required = true, dataType = "String"),})
    @PostMapping("/endTiming")
    public Long endTiming(CodeType codeType, String username) {
        return timingService.endTiming(codeType, username);
    }

    @ApiOperation(value = "获得用户一段时间的统计信息")
    @ApiImplicitParams({@ApiImplicitParam(name = "fromDate", value = "起始日期", required = true, dataType = "Date"),
            @ApiImplicitParam(name = "toDate", value = "截止日期", required = true, dataType = "Date"), @ApiImplicitParam
            (name = "username", value = "用户名", required = true, dataType = "String"),})
    @PostMapping("/statistic")
    public StatisticModel getStatistic(Date fromDate, Date toDate, String username) {
        return timingService.getStatistic(fromDate, toDate, username);
    }

    @ApiOperation(value = "开始计时(打开APP时调用)")
    @ApiImplicitParams({@ApiImplicitParam(name = "timingType", value = "计时类型", required = true, dataType =
            "TimingType"), @ApiImplicitParam(name = "date", value = "操作时间", required = true, dataType = "Date"),
            @ApiImplicitParam(name = "username", value = "用户名", required = true, dataType = "String"),})
    @PostMapping("/startAppTiming")
    public void startAppTiming(String timingType, String date, String username) {
        //例如：START,1520012214036
        System.out.println(timingType + "," + date);
        timingService.startAppTiming(timingType, date, username);
    }

    @ApiOperation(value = "结束及时(关闭APP时调用)")
    @ApiImplicitParams({@ApiImplicitParam(name = "timingType", value = "计时类型", required = true, dataType =
            "TimingType"), @ApiImplicitParam(name = "date", value = "操作时间", required = true, dataType = "Date"),
            @ApiImplicitParam(name = "username", value = "用户名", required = true, dataType = "String"),})
    @PostMapping("/endAppTiming")
    public void endAppTiming(String timingType, String date, String username) {
        //例如：END,1520012364999
        System.out.println(timingType + "," + date);
        timingService.endAppTiming(timingType, date, username);
    }

    @ApiOperation(value = "暂停计时")
    @ApiImplicitParams({@ApiImplicitParam(name = "timingType", value = "计时类型", required = true, dataType =
            "TimingType"), @ApiImplicitParam(name = "date", value = "操作时间", required = true, dataType = "Date"),
            @ApiImplicitParam(name = "username", value = "用户名", required = true, dataType = "String"),})
    @PostMapping("/pause")
    public void pause(String timingType, String date, String username) {
        //例如：PAUSE_START,1520012236718
        System.out.println(timingType + "," + date);
        timingService.pause(timingType, date, username);
    }

    @ApiOperation(value = "结束暂停")
    @ApiImplicitParams({@ApiImplicitParam(name = "timingType", value = "计时类型", required = true, dataType =
            "TimingType"), @ApiImplicitParam(name = "date", value = "操作时间", required = true, dataType = "Date"),
            @ApiImplicitParam(name = "username", value = "用户名", required = true, dataType = "String"),})
    @PostMapping("/endPause")
    public void endPause(String timingType, String date, String username) {
        //例如：PAUSE_END,1520012364331
        System.out.println(timingType + "," + date);
        timingService.endPause(timingType, date, username);
    }
}
