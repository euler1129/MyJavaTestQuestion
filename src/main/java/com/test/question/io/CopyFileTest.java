package com.test.question.io;

import java.io.*;

public class CopyFileTest {
    public static void main(String[] args) {
        String sourceFile = "test.txt";
        String targetFile = "test2.txt";
        try(BufferedReader br = new BufferedReader(
                new FileReader(sourceFile));
            BufferedWriter bw = new BufferedWriter(
                    new FileWriter(targetFile))){
            String line;
            while((line = br.readLine()) != null){
                bw.write(line);
                //bw.newLine();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
