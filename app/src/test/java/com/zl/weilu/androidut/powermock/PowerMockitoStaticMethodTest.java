package com.zl.weilu.androidut.powermock;

import com.zl.weilu.androidut.bean.Banana;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.powermock.api.mockito.PowerMockito;
import org.powermock.core.classloader.annotations.PrepareForTest;
import org.powermock.modules.junit4.PowerMockRunner;
import org.powermock.reflect.Whitebox;

/**
 * @Description: mock静态方法
 * @Author: weilu
 * @Time: 2017/11/18 11:12.
 */

@RunWith(PowerMockRunner.class)
@PrepareForTest({Banana.class})
public class PowerMockitoStaticMethodTest {

    @Test
    public void testStaticMethod() { 
        PowerMockito.mockStatic(Banana.class); //<-- mock静态类
        Mockito.when(Banana.getColor()).thenReturn("绿色");
        Assert.assertEquals("绿色", Banana.getColor());
    }

    /**
     * 更改类的私有static常量
     */
    @Test
    public void testChangeColor() { 
        Whitebox.setInternalState(Banana.class, "COLOR", "红色的");
        Assert.assertEquals("红色的", Banana.getColor());
    }
}
