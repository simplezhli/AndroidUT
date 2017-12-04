package com.zl.weilu.androidut.robolectric.shadow;

import com.zl.weilu.androidut.bean.Person;

import org.robolectric.annotation.Implementation;
import org.robolectric.annotation.Implements;

/**
 * {@link Person} 的影子类
 * @Description: 自定义Shadow
 * @Author: weilu
 * @Time: 2017/12/4 13:05.
 */
@Implements(Person.class)
public class ShadowPerson {

    @Implementation
    public String getName() {
        return "AndroidUT";
    }
    
    @Implementation
    public int getSex() {
        return 0;
    }
    
    @Implementation
    public int getAge(){
        return 18;
    }
}
