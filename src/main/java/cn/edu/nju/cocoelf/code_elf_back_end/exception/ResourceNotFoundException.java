package cn.edu.nju.cocoelf.code_elf_back_end.exception;

public class ResourceNotFoundException extends RuntimeException {

    public ResourceNotFoundException(String message) {
        super(message);
    }

}
