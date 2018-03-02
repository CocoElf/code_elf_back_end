package cn.edu.nju.cocoelf.code_elf_back_end.model;

import java.util.Date;
import java.util.Map;

public class StatisticModel {

    private String username;

    private Date fromDate;

    private Date toDate;

    /**
     * 编码时间
     */
    private Long codeTime;

    /**
     * 调试时间
     */
    private Long debugTime;

    /**
     * 测试时间
     */
    private Long testTime;

    /**
     * 搜索次数
     */
    private Integer searchTimes;

    private Map<Date, Date> timingMap;

    public Map<Date, Date> getTimingMap() {
        return timingMap;
    }

    public void setTimingMap(Map<Date, Date> timingMap) {
        this.timingMap = timingMap;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public Date getFromDate() {
        return fromDate;
    }

    public void setFromDate(Date fromDate) {
        this.fromDate = fromDate;
    }

    public Date getToDate() {
        return toDate;
    }

    public void setToDate(Date toDate) {
        this.toDate = toDate;
    }

    public Long getCodeTime() {
        return codeTime;
    }

    public void setCodeTime(Long codeTime) {
        this.codeTime = codeTime;
    }

    public Long getDebugTime() {
        return debugTime;
    }

    public void setDebugTime(Long debugTime) {
        this.debugTime = debugTime;
    }

    public Long getTestTime() {
        return testTime;
    }

    public void setTestTime(Long testTime) {
        this.testTime = testTime;
    }

    public Integer getSearchTimes() {
        return searchTimes;
    }

    public void setSearchTimes(Integer searchTimes) {
        this.searchTimes = searchTimes;
    }

    @Override
    public String toString() {
        return "StatisticModel{" + "username='" + username + '\'' + ", fromDate=" + fromDate + ", toDate=" + toDate +
                ", codeTime=" + codeTime + ", debugTime=" + debugTime + ", testTime=" + testTime + ", searchTimes=" +
                searchTimes + ", timingMap=" + timingMap + '}';
    }
}
