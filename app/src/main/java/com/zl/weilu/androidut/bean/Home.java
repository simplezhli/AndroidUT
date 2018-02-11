package com.zl.weilu.androidut.bean;

/**
 * @Description:
 * @Author: weilu
 * @Time: 2018/2/11 0011 15:00.
 */

public class Home {
    
    private Person mPerson;

    public Home(Person person) {
        mPerson = person;
    }
    
    public String getMaster(){
        return mPerson.getName();
    }
}
