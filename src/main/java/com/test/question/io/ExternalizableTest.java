package com.test.question.io;

import lombok.Data;
import java.io.*;

public class ExternalizableTest {
    public static void main(String[] args)
            throws IOException, ClassNotFoundException {
        User user = new User();
        user.setUsername("xxx");
        user.setId("xid");
        ObjectOutput  out = new ObjectOutputStream(
                new FileOutputStream("test"));
        out.writeObject(user);
        ObjectInput in = new ObjectInputStream(
                new FileInputStream("test"));
        user = (User) in.readObject();
        System.out.println("username: " + user.getUsername());
        System.out.println("id: " + user.getId());
        out.close();
        in.close();
    }
}
@Data
class User implements Externalizable {
    private static final long serialVersionUID = 1L;
    private String username;
    private transient String id;
    public User(){}
    @Override
    public void writeExternal(ObjectOutput out)
            throws IOException {
        out.writeObject(id);
    }
    @Override
    public void readExternal(ObjectInput in)
            throws IOException, ClassNotFoundException {
        id = (String)in.readObject();
    }
}
