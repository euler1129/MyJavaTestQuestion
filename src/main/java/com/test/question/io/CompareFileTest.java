package com.test.question.io;

import org.apache.commons.io.IOUtils;

import java.io.FileInputStream;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Arrays;

public class CompareFileTest {
    public static void main(String[] args)
            throws IOException {
        // JDK原生实现
        byte[] file1 = Files.readAllBytes(
                Paths.get("test.txt"));
        byte[] file2 = Files.readAllBytes(
                Paths.get("test2.txt"));
        boolean isEquals1 = Arrays.equals(file1,file2);
        System.out.printf("两个文件内容是否相等：%s\n", isEquals1);
        // 使用IOUtils工具类
        boolean isEquals2 = IOUtils.contentEquals(
                new FileInputStream("test.txt"),
                new FileInputStream("test2.txt"));
        System.out.printf("两个文件内容是否相等：%s\n", isEquals2);
    }
}
