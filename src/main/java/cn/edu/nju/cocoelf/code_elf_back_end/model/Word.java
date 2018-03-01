package cn.edu.nju.cocoelf.code_elf_back_end.model;

public class Word {
    public String boundingBox;
    public String text;

    public Word() {
    }

    @Override
    public String toString() {
        return "Word{" + "boundingBox='" + boundingBox + '\'' + ", text='" + text + '\'' + '}';
    }
}
