package com.viewtest;

import android.app.Activity;
import android.os.Bundle;
import android.widget.Toast;

/**
 * Created by zml on 2016/3/28.
 */
public class TitleBarTest extends Activity {
    private TitleBar mTopbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.topbar_test);
        // 获得我们创建的topbar
        mTopbar = (TitleBar) findViewById(R.id.topBar);
        // 为topbar注册监听事件，传入定义的接口
        // 并以匿名类的方式实现接口内的方法

        mTopbar.setOnTopbarClickListener(new TitleBar.topbarClickListener() {
            @Override
            public void leftClick() {
                Toast.makeText(TitleBarTest.this,
                        "left", Toast.LENGTH_SHORT)
                        .show();
            }

            @Override
            public void rightClick() {
                Toast.makeText(TitleBarTest.this,
                        "right", Toast.LENGTH_SHORT)
                        .show();
            }
        });
    }
}
