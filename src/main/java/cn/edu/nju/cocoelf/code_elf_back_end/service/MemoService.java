package cn.edu.nju.cocoelf.code_elf_back_end.service;

import cn.edu.nju.cocoelf.code_elf_back_end.model.MemoModel;

import java.util.List;

public interface MemoService {

    List<MemoModel> getMemoList(String username, Integer pageNum, Integer pageSize);

    MemoModel getMemoDetail(Integer memoId, String username);

    MemoModel addMemo(MemoModel memoModel, String username);

    Boolean deleteMemo(MemoModel memoModel, String username);
}
