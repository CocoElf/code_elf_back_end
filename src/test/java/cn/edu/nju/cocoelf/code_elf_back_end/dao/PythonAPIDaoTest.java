package cn.edu.nju.cocoelf.code_elf_back_end.dao;

import cn.edu.nju.cocoelf.code_elf_back_end.controller.TestParam;
import cn.edu.nju.cocoelf.code_elf_back_end.model.OCR;
import cn.edu.nju.cocoelf.code_elf_back_end.util.LogUtil;
import com.google.gson.Gson;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
public class PythonAPIDaoTest {

    @Autowired
    PythonAPIDao pythonAPIDao;

    @Test
    public void testConfigure() throws Exception {

        LogUtil.log(pythonAPIDao.testConnection());
    }
}
