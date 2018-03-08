package cn.edu.nju.cocoelf.code_elf_back_end.service.impl;

import cn.edu.nju.cocoelf.code_elf_back_end.entity.Memo;
import cn.edu.nju.cocoelf.code_elf_back_end.exception.InvalidRequestException;
import cn.edu.nju.cocoelf.code_elf_back_end.model.MemoModel;
import cn.edu.nju.cocoelf.code_elf_back_end.repository.MemoRepository;
import cn.edu.nju.cocoelf.code_elf_back_end.service.MemoService;
import cn.edu.nju.cocoelf.code_elf_back_end.service.UserService;
import cn.edu.nju.cocoelf.code_elf_back_end.util.FileUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.rest.webmvc.ResourceNotFoundException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class MemoServiceImpl implements MemoService {

    private static Map<String, Integer> memoMap = new HashMap<>();

    @Autowired
    private MemoRepository memoRepository;

    @Autowired
    private UserService userService;

    @Override
    public List<MemoModel> getMemoList(String username, Integer pageNum, Integer pageSize) {
        Pageable pageable = new PageRequest(pageNum, pageSize, Sort.Direction.DESC, "`date`");
        Page<Memo> page = memoRepository.findByUser_Username(username, pageable);
        List<MemoModel> memoModelList = new ArrayList<>();
        for (Memo memo : page.getContent()) {
            memoModelList.add(toModel(memo));
        }
        return memoModelList;
    }

    @Override
    public MemoModel getMemoDetail(Integer memoId, String username) {
        Memo memo = memoRepository.findOne(memoId);
        if (memo == null || memo.getMemoId() == null) {
            throw new ResourceNotFoundException("没有这个备忘录哦");
        }
        if (!memo.getUser().getUsername().equals(username)) {
            throw new InvalidRequestException("没有权限查看此备忘录");
        }
        return toModel(memo);
    }

    @Override
    public MemoModel addMemo(MemoModel memoModel, String username) {
        Memo memo = toEntity(memoModel);
        userService.verifyUsername(username);

        if (memoMap.containsKey(username)) {
            Integer memoId = memoMap.get(username);
            memo.setMemoId(memoId);
        }

        memo = saveMemo(memo, memoModel.getContent(), username);
        memoMap.put(username, memo.getMemoId());

        return toModel(memo);
    }

    @Override
    public Boolean deleteMemo(MemoModel memoModel, String username) {
        Memo memo = toEntity(memoModel);
        userService.verifyUsername(username);
        memoRepository.delete(memo);
        return true;
    }


    private Memo saveMemo(Memo memo, String content, String username) {
        String contentPath = FileUtil.saveFile(content, username);
        memo.setContentPath(contentPath);
        memo = memoRepository.saveAndFlush(memo);
        return  memo;
    }

    private MemoModel toModel(Memo memo) {
        if (memo == null || memo.getMemoId() == null) {
            throw new ResourceNotFoundException("没有这个备忘录哦");
        }
        MemoModel memoModel = new MemoModel();
        memoModel.setDate(memo.getDate());
//        memoModel.setKeywords(memo.getKeyWord());
        memoModel.setMemoId(memo.getMemoId());
        memoModel.setSnippet(memo.getSnippet());
        memoModel.setContent(FileUtil.readFile(memo.getContentPath()));
        return memoModel;
    }

    private Memo toEntity(MemoModel memoModel) {
        if (memoModel == null) {
            throw new InvalidRequestException("无法添加空备忘录");
        }

        Memo memo = new Memo();

        if (memoModel.getMemoId() != null) {
            memo = memoRepository.findOne(memoModel.getMemoId());
            if (memo == null || memo.getMemoId() == null) {
                throw new InvalidRequestException("没有这个备忘录哦");
            }
        }

        if (memoModel.getDate() != null) {
            memo.setDate(memoModel.getDate());
        }
//        if (memoModel.getKeyWord() != null) {
//            memo.setKeyWord(memoModel.getKeyWord());
//        }
        if (memoModel.getName() != null) {
            memo.setSnippet(memoModel.getName());
        }
        if (memoModel.getUrl() != null) {
            memo.setUrl(memoModel.getUrl());
        }
        if (memoModel.getSnippet() != null) {
            memo.setSnippet(memoModel.getSnippet());
        }

        return memo;
    }
}
