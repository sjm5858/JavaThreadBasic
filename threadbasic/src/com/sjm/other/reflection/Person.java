package com.sjm.other.reflection;

/**
 * @author sjm5858
 * @date 2021/4/23 15:14
 */
public class Person {
    private String name;
    private String sexy;
    private Integer age;

    public Person() {
    }

    public Person(String name, String sexy, Integer age) {
        this.name = name;
        this.sexy = sexy;
        this.age = age;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSexy() {
        return sexy;
    }

    public void setSexy(String sexy) {
        this.sexy = sexy;
    }

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    @Override
    public String toString() {
        return "Person{" +
                "name='" + name + '\'' +
                ", sexy='" + sexy + '\'' +
                ", age=" + age +
                '}';
    }
}
