package cn.edu.nju.cocoelf.code_elf_back_end.model;

public class TipModel {

    /**
     * 是否需要休息
     */
    private boolean needRest;

    /**
     * 建议
     */
    private String tipBody;

    /**
     * 建议休息的时间
     */
    private Long restTime;

    public TipModel() {
        this.needRest = false;
        this.tipBody = "没有建议";
        this.restTime = 0L;
    }

    public Long getRestTime() {
        return restTime;
    }

    public void setRestTime(Long restTime) {
        this.restTime = restTime;
    }

    public boolean isNeedRest() {
        return needRest;
    }

    public void setNeedRest(boolean needRest) {
        this.needRest = needRest;
    }

    public String getTipBody() {
        return tipBody;
    }

    public void setTipBody(String tipBody) {
        this.tipBody = tipBody;
    }

    @Override
    public String toString() {
        return "TipModel{" + "needRest=" + needRest + ", tipBody='" + tipBody + '\'' + ", restTime=" + restTime + '}';
    }
}
