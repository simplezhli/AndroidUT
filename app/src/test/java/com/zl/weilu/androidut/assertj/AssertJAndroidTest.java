package com.zl.weilu.androidut.assertj;

import android.widget.Button;
import android.widget.CheckBox;
import android.widget.LinearLayout;

import com.zl.weilu.androidut.R;
import com.zl.weilu.androidut.ui.MainActivity;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.robolectric.Robolectric;
import org.robolectric.RobolectricTestRunner;
import org.robolectric.shadows.ShadowLog;

import static org.assertj.android.api.Assertions.assertThat;

/**
 * @Description: 
 * @Author: weilu
 * @Time: 2018/5/15 0015 10:33.
 */
@RunWith(RobolectricTestRunner.class)
public class AssertJAndroidTest {

    private MainActivity mainActivity;
    private Button mJumpBtn;
    private LinearLayout mRoot;
    private CheckBox checkBox;

    @Before
    public void setUp(){
        //输出日志
        ShadowLog.stream = System.out;
        mainActivity = Robolectric.setupActivity(MainActivity.class);
        mJumpBtn = mainActivity.findViewById(R.id.button1);
        mRoot = mainActivity.findViewById(R.id.root);
        checkBox = mainActivity.findViewById(R.id.checkbox);
    }

    @Test
    public void testView() {
        // Button是否可见
        assertThat(mJumpBtn).isVisible();
        // LinearLayout 方向，子View数量
        assertThat(mRoot)
                .isVertical()
                .hasChildCount(4);
        // CheckBox是否未选中
        assertThat(checkBox).isNotChecked();
    }
}
