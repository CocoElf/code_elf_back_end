package cn.edu.nju.cocoelf.code_elf_back_end.model;

import java.util.List;

public class Line {
    public boolean isVertical;
    public List<Word> words;
    public String boundingBox;

    public Line() {
    }

    @Override
    public String toString() {
        return "Line{" + "isVertical=" + isVertical + ", words=" + words + ", boundingBox='" + boundingBox + '\'' + '}';
    }
}
