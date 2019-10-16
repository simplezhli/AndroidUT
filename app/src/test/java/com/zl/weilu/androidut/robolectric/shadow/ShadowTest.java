package com.zl.weilu.androidut.robolectric.shadow;

import android.util.Log;

import com.zl.weilu.androidut.bean.Person;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.robolectric.annotation.Config;
import org.robolectric.shadows.ShadowLog;

import androidx.test.ext.junit.runners.AndroidJUnit4;

import static junit.framework.Assert.assertEquals;
import static org.robolectric.shadow.api.Shadow.extract;

/**
 * @Description: 自定义Shadow测试
 * @Author: weilu
 * @Time: 2017/12/4 13:07.
 */
@RunWith(AndroidJUnit4.class)
@Config(shadows = {ShadowPerson.class})
public class ShadowTest {

    @Before
    public void setUp() {
        ShadowLog.stream = System.out;
    }

    @Test
    public void testShadowShadow(){
        Person person = new Person();
        //实际上调用的是ShadowPerson的方法
        Log.d("test", person.getName());
        Log.d("test", String.valueOf(person.getAge()));
        Log.d("test", String.valueOf(person.getSex()));

        //获取Person对象对应的Shadow对象
        ShadowPerson shadowPerson = extract(person);
        assertEquals("AndroidUT", shadowPerson.getName());
        assertEquals(18, shadowPerson.getAge());
        assertEquals(0, person.getSex());
    }
}
