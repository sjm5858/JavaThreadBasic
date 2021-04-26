package com.sjm.other.base.createobject.f4;

import java.io.*;

/**
 * @author sjm5858
 * @date 2021/4/25 16:52
 */
public class Test implements Serializable {

    private String name;

    public Test() {
    }

    public Test(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public static void main(String[] args) throws Exception {
        String filePath = "threadbasic/src/com/sjm/other/base/createobject/f4/sample.txt";
        Test t1 = new Test("张三1111111111");
        try {
            FileOutputStream fileOutputStream =
                    new FileOutputStream(filePath);
            ObjectOutputStream outputStream =
                    new ObjectOutputStream(fileOutputStream);
            outputStream.writeObject(t1);
            outputStream.flush();
            outputStream.close();

            FileInputStream fileInputStream =
                    new FileInputStream(filePath);
            ObjectInputStream inputStream =
                    new ObjectInputStream(fileInputStream);
            Test t2 = (Test) inputStream.readObject();
            inputStream.close();
            System.out.println(t2.getName());
        } catch (Exception ee) {
            ee.printStackTrace();
        }
    }
}