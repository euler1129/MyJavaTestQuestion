package com.test.question.io;

import java.io.*;

public class ReadWriteFileTest {
    public static void main(String[] args){
        String fileName = "test.txt";
        String content = "技术栈";
        // 写文件
        try(FileWriter fileWriter = new FileWriter(fileName)){
            BufferedWriter bufferedWriter =
                    new BufferedWriter(fileWriter);
            bufferedWriter.write(content);
            bufferedWriter.flush();
        } catch (IOException e) {
            System.out.println("写文件出错");
        }
        // 读取文件
        StringBuilder fileContent = new StringBuilder();
        try(FileReader fileReader = new FileReader(fileName)){
            BufferedReader bufferedReader =
                    new BufferedReader(fileReader);
            String line;
            while ((line = bufferedReader.readLine()) != null){
                fileContent.append(line);
                fileContent.append("\n");
            }
        } catch (IOException e) {
            System.out.println("读文件出错");
        }
        System.out.println("文件内容：" + fileContent);
    }
}
