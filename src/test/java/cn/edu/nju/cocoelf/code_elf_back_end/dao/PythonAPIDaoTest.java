package cn.edu.nju.cocoelf.code_elf_back_end.dao;

import cn.edu.nju.cocoelf.code_elf_back_end.entity.StubApi;
import cn.edu.nju.cocoelf.code_elf_back_end.repository.PythonAPIDao;
import cn.edu.nju.cocoelf.code_elf_back_end.util.LogUtil;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.ArrayList;
import java.util.List;

@RunWith(SpringRunner.class)
@SpringBootTest
public class PythonAPIDaoTest {

    @Autowired
    PythonAPIDao pythonAPIDao;

    @Test
    public void testConfigure() throws Exception {

        LogUtil.log(pythonAPIDao.testConnection());
    }

    @Test
    public void searchFuntion() throws Exception {
        List<String> list = new ArrayList<>();
        list.add("返回");
        list.add("目录");
        List<StubApi> stubApis = pythonAPIDao.searchResult("os",list);
        System.out.println(stubApis.size());
        stubApis.forEach(t->LogUtil.log(t.getPrint()));

    }
}
