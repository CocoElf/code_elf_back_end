package cn.edu.nju.cocoelf.code_elf_back_end.service.impl;

import cn.edu.nju.cocoelf.code_elf_back_end.config.param.CodeType;
import cn.edu.nju.cocoelf.code_elf_back_end.entity.TimeRecord;
import cn.edu.nju.cocoelf.code_elf_back_end.entity.User;
import cn.edu.nju.cocoelf.code_elf_back_end.model.StatisticModel;
import cn.edu.nju.cocoelf.code_elf_back_end.model.TipModel;
import cn.edu.nju.cocoelf.code_elf_back_end.repository.SearchRepository;
import cn.edu.nju.cocoelf.code_elf_back_end.repository.TimeRecordRepository;
import cn.edu.nju.cocoelf.code_elf_back_end.service.TimingService;
import cn.edu.nju.cocoelf.code_elf_back_end.service.UserService;
import cn.edu.nju.cocoelf.code_elf_back_end.service.component.TimingContainer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class TimingServiceImpl implements TimingService {

    @Autowired
    private TimeRecordRepository timeRecordRepository;

    @Autowired
    private UserService userService;

    @Autowired
    private SearchRepository searchRepository;

    private Map<String, Date> appTimingMap = new HashMap<>();

    @Override
    public void startTiming(CodeType codeType, String username) {
        userService.verifyUsername(username);
        TimingContainer.setStartTime(username, codeType);
    }

    @Override
    public Long endTiming(CodeType codeType, String username) {
        User user = userService.verifyUsername(username);
        TimeRecord timeRecord = new TimeRecord();

        Date beginTime = TimingContainer.getStartTime(username);

        timeRecord.setBeginTime(beginTime);
        timeRecord.setCodeType(TimingContainer.getCodeType(username));
        timeRecord.setUser(user);

        timeRecordRepository.save(timeRecord);

        return System.currentTimeMillis() - beginTime.getTime();
    }

    @Override
    public StatisticModel getStatistic(Date fromDate, Date toDate, String username) {
        userService.verifyUsername(username);

        List<TimeRecord> timeRecordList = timeRecordRepository.findByUser_Username(username);

        Long codeTime = 0L;
        Long debugTime = 0L;
        Long testTime = 0L;
        Map<Date, Date> timingMap = new HashMap<>();
        for (TimeRecord timeRecord : timeRecordList) {
            switch (timeRecord.getCodeType()) {
                case CODE:
                    codeTime += (timeRecord.getEndTime().getTime() - timeRecord.getBeginTime().getTime());
                    break;
                case TEST:
                    testTime += (timeRecord.getEndTime().getTime() - timeRecord.getBeginTime().getTime());
                    break;
                case DEBUG:
                    debugTime += (timeRecord.getEndTime().getTime() - timeRecord.getBeginTime().getTime());
                    break;
                case APP:
                    timingMap.put(timeRecord.getBeginTime(), timeRecord.getEndTime());
                    break;
            }
        }

        StatisticModel statisticModel = new StatisticModel();
        statisticModel.setCodeTime(codeTime);
        statisticModel.setDebugTime(debugTime);
        statisticModel.setTestTime(testTime);
        statisticModel.setFromDate(fromDate);
        statisticModel.setToDate(toDate);
        statisticModel.setSearchTimes(searchRepository.countBySearchDateBetween(fromDate, toDate));
        statisticModel.setUsername(username);
        statisticModel.setTimingMap(timingMap);

        return statisticModel;
    }

    @Override
    public TipModel getTips(CodeType codeType, Long codeTime, String username) {
        userService.verifyUsername(username);
        switch (codeType) {
            case DEBUG:
                return debugTips(codeTime);
            case CODE:
                return codeTips(codeTime);
            case TEST:
                return codeTips(codeTime);
        }

        return new TipModel();
    }

    @Override
    public void startAppTiming(String timingType, String date, String username) {
        appTimingMap.put(username, new Date(Long.parseLong(date)));
    }

    @Override
    public void endAppTiming(String timingType, String date, String username) {
        User user = userService.verifyUsername(username);
        Date toDate = new Date(Long.parseLong(date));
        TimeRecord timeRecord = new TimeRecord();
        timeRecord.setUser(user);
        timeRecord.setBeginTime(appTimingMap.get(username));
        timeRecord.setEndTime(toDate);
        timeRecord.setCodeType(CodeType.APP);
        appTimingMap.remove(username);
    }

    @Override
    public void pause(String timingType, String date, String username) {
        endAppTiming(timingType, date, username);
    }

    @Override
    public void endPause(String timingType, String date, String username) {
        startAppTiming(timingType, date, username);
    }

    private TipModel debugTips(Long codeTime) {
        TipModel tipModel = new TipModel();

        if (codeTime > 30 * 60 * 1000) {
            tipModel.setNeedRest(true);
            tipModel.setTipBody("您可以稍作休息，思考一下");
            tipModel.setRestTime(5 * 60 * 1000L);
        }
        if (codeTime > 60 * 60 * 1000) {
            tipModel.setNeedRest(true);
            tipModel.setTipBody("建议您先休息一会，您也可以尝试使用本系统的搜索功能");
            tipModel.setRestTime(20 * 60 * 1000L);
        }
        if (codeTime > 120 * 60 * 1000) {
            tipModel.setNeedRest(true);
            tipModel.setTipBody("建议您先休息一会，您也可以尝试使用本系统的搜索功能");
            tipModel.setRestTime(codeTime / 4);
        }

        return tipModel;
    }

    private TipModel codeTips(Long codeTime) {
        TipModel tipModel = new TipModel();

        if (codeTime > 60 * 60 * 1000) {
            tipModel.setNeedRest(true);
            tipModel.setTipBody("建议您稍作休息");
            tipModel.setRestTime(10 * 60 * 1000L);
        }
        if (codeTime > 120 * 60 * 1000) {
            tipModel.setNeedRest(true);
            tipModel.setTipBody("建议您现在休息，大脑放松后效率更高");
            tipModel.setRestTime(20 * 60 * 1000L);
        }
        if (codeTime > 360 * 60 * 1000) {
            tipModel.setNeedRest(true);
            tipModel.setTipBody("建议您现在休息，大脑放松后效率更高");
            tipModel.setRestTime(codeTime / 4);
        }
        return tipModel;
    }
}
