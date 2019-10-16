package com.zl.weilu.androidut.assertj;

import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.LinearLayout;

import com.zl.weilu.androidut.R;
import com.zl.weilu.androidut.ui.MainActivity;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;

import androidx.lifecycle.Lifecycle;
import androidx.test.core.app.ActivityScenario;
import androidx.test.ext.junit.runners.AndroidJUnit4;

import static androidx.test.core.app.ActivityScenario.launch;
import static org.assertj.core.api.Assertions.assertThat;

/**
 * @Description: 
 * @Author: weilu
 * @Time: 2018/5/15 0015 10:33.
 */
@RunWith(AndroidJUnit4.class)
public class AssertJAndroidTest {

    private Button mJumpBtn;
    private LinearLayout mRoot;
    private CheckBox checkBox;

    @Before
    public void setUp(){
        ActivityScenario<MainActivity> scenario = launch(MainActivity.class);
        scenario.moveToState(Lifecycle.State.CREATED);
        scenario.onActivity(activity -> {
            mJumpBtn = activity.findViewById(R.id.button1);
            mRoot = activity.findViewById(R.id.root);
            checkBox = activity.findViewById(R.id.checkbox);
        });
    }

    @Test
    public void testView() {
//        AssertJ-Android (已不在维护)，这里就是用普通方法实现
        // Button是否可见
        assertThat(mJumpBtn.getVisibility()).isEqualTo(View.VISIBLE);
        // LinearLayout 方向，子View数量
        assertThat(mRoot.getOrientation()).isEqualTo(LinearLayout.VERTICAL);
        assertThat(mRoot.getChildCount()).isEqualTo(4);
        // CheckBox是否未选中
        assertThat(checkBox.isChecked()).isEqualTo(false);
    }
}
