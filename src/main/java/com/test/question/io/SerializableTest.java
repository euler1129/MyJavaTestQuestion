package com.test.question.io;

import lombok.*;
import org.springframework.util.SerializationUtils;

import java.io.*;

public class SerializableTest {
    public static void main(String[] args) {
        User2 user = new User2();
        user.setUsername("Xiao");
        user.setAddress("China");
        byte[] bytes = SerializationUtils.serialize(user);
        User2 u = (User2) SerializationUtils.deserialize(bytes);
        System.out.println(u);
    }
}
@Data
@ToString
@NoArgsConstructor
@AllArgsConstructor
class User2 implements Serializable {
    private static final long serialVersionUID = 1L;
    private String username;
    private String address;
}
