package cn.edu.nju.cocoelf.code_elf_back_end.util;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import static org.junit.Assert.assertEquals;

@RunWith(SpringRunner.class)
@SpringBootTest
public class TranslateUtilTest {
    @Autowired
    TranslateUtil translateUtil;


    @Test
    public void testTranslate(){
        String re = translateUtil.translate("操作系统");
        assertEquals(re,"os");
    }

}
