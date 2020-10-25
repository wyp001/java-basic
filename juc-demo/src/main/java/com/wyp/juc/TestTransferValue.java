package com.wyp.juc;

class Persion{
    private String name;
    private int age;

    public Persion(String name) {
        this.name = name;
    }

    public Persion() {
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }
}

public class TestTransferValue {
    public void changeValue1(int age){
        age = 30;
    }

    public void changeValue2(Persion persion){
        persion.setName("xxx");
    }

    public void changeValue3(String str){
        str = "xxx";
    }

    public static void main(String[] args) {
        TestTransferValue transfer = new TestTransferValue();
        int age = 30;
        transfer.changeValue1(age);
        System.out.println("age------"+age);

        Persion persion = new Persion("abc");
        transfer.changeValue2(persion);
        System.out.println("persion name ---------"+ persion.getName());

        String str = "abc";
        transfer.changeValue3(str);
        System.out.println("string-----"+ str);

    }



}
