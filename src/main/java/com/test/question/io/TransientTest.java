package com.test.question.io;

import lombok.Data;
import lombok.ToString;

import java.io.*;

public class TransientTest {
    public static void main(String[] args)
            throws IOException, ClassNotFoundException {
        User3 user = new User3();
        user.setUsername("技术栈");
        user.setId("stack");
        System.out.println("\n序列化之前：" + user);
        ObjectOutputStream os = new ObjectOutputStream(
                new FileOutputStream("user.txt"));
        os.writeObject(user);
        os.flush();
        os.close();
        ObjectInputStream is = new ObjectInputStream(
                new FileInputStream("user.txt"));
        user = (User3) is.readObject();
        is.close();
        System.out.println("\n序列化之后：" + user);
    }
}
@ToString
@Data
class User3 implements Serializable {
    private static final long serialVersionUID = 1L;
    private String username;
    private transient String id;
}
