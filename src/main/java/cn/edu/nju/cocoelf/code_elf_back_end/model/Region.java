package cn.edu.nju.cocoelf.code_elf_back_end.model;

import java.util.List;

public class Region {

    public String boundingBox;
    public List<Line> lines;

    public Region() {
    }

    @Override
    public String toString() {
        return "Region{" + "boundingBox='" + boundingBox + '\'' + ", lines=" + lines + '}';
    }
}
