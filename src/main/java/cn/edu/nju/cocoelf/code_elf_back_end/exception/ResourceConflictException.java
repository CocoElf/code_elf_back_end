package cn.edu.nju.cocoelf.code_elf_back_end.exception;

public class ResourceConflictException extends RuntimeException {
    public ResourceConflictException(String message) {
        super(message);
    }
}
