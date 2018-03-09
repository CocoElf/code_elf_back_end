package cn.edu.nju.cocoelf.code_elf_back_end.util;

import java.io.*;

import static cn.edu.nju.cocoelf.code_elf_back_end.config.file.FilePathConfig.developerPror;

public class FileUtil {

    public static String saveFile(String content, String username) {
        String filePath = developerPror.get("memoContentPath") + username + "/" + System.currentTimeMillis();
        File file = new File(filePath);
        try {
            if(!file.getParentFile().exists()) {
                //如果目标文件所在的目录不存在，则创建父目录
                System.out.println("目标文件所在目录不存在，准备创建它！");
                file.getParentFile().mkdirs();
            }
            file.createNewFile();
            BufferedWriter bw = new BufferedWriter(new FileWriter(file));
            bw.write(content);
            bw.flush();
            bw.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return filePath;
    }

    public static String readFile(String filePath) {
        File file = new File(filePath);
        StringBuilder sb = new StringBuilder("");
        try {
            BufferedReader br = new BufferedReader(new FileReader(file));
            String str;
            while ((str = br.readLine()) != null) {
                sb.append(str);
            }
        }catch (IOException e) {
            e.printStackTrace();
        }
        return sb.toString();
    }
}
