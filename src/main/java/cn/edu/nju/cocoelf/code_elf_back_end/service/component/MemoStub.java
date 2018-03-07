package cn.edu.nju.cocoelf.code_elf_back_end.service.component;

import cn.edu.nju.cocoelf.code_elf_back_end.model.MemoModel;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * stub列表显示的备忘录：(演示的时候应该按如下顺序进行)
 * 1、（api查询）python abs函数的使用方法
 * 2、（功能查找api）java如何读写文件
 * 3、（语音web搜索）spring data jpa的教程
 * 4、（图片异常web搜索）javax.servlet.ServletException: bean [name] not found within scope
 */
@Component
public class MemoStub {

    public List<MemoModel> getMemoList(String username, Integer pageNum, Integer pageSize){
        return null;
    }

    public MemoModel getMemoDetail(Integer memoId, String username) {
        return null;
    }
}
