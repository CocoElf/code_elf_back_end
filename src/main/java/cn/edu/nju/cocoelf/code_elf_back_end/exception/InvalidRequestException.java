package cn.edu.nju.cocoelf.code_elf_back_end.exception;

public class InvalidRequestException extends RuntimeException {
    public InvalidRequestException(String message) {
        super(message);
    }
}
