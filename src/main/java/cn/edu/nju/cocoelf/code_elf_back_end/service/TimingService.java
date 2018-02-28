package cn.edu.nju.cocoelf.code_elf_back_end.service;

import cn.edu.nju.cocoelf.code_elf_back_end.config.param.CodeType;
import cn.edu.nju.cocoelf.code_elf_back_end.model.StatisticModel;
import cn.edu.nju.cocoelf.code_elf_back_end.model.TipModel;

import java.util.Date;

public interface TimingService {

    void startTiming(CodeType codeType, String username);

    Long endTiming(CodeType codeType, String username);

    StatisticModel getStatistic(Date fromDate, Date toDate, String username);

    TipModel getTips(CodeType codeType, Long codeTime, String username);
}
