package com.zl.weilu.androidut.bean;

/**
 * @Description:
 * @Author: weilu
 * @Time: 2017/11/4 10:45.
 */

public class Person {
    
    private String name;
    private int sex;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getSex() {
        return sex;
    }

    public void setSex(int sex) {
        this.sex = sex;
    }
    
    public int getAge(){
        return 11;
    }
    
    public String eat(String food){
        return food;
    }
}
