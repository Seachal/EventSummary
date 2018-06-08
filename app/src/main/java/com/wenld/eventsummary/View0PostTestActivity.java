package com.wenld.eventsummary;

import android.app.Activity;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.util.Log;
import android.widget.TextView;

/**
 * *
 * *
 * Project_Name:EventSummary
 *
 * @author zhangxc
 * @date 2018/2/9 上午8:52
 * *
 */

public class View0PostTestActivity extends Activity {

    private TextView tv_view_post_test;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_view_post_test);
        tv_view_post_test = (TextView) findViewById(R.id.tv_view_post_test);
        // 下面这一行log打印的是0，0
        Log.d("View0PostTest", "1 post前_mTextView width : " + tv_view_post_test.getMeasuredWidth() + " - height : " + tv_view_post_test.getMeasuredHeight());
        tv_view_post_test.post(new Runnable() {
            @Override
            public void run() {
                // 下面这一行log打印的是TextView测量后的宽高
                Log.d("View0PostTest", "3 post Runnable_mTextView width : " + tv_view_post_test.getMeasuredWidth() + " - height : " + tv_view_post_test.getMeasuredHeight());
            }
        });
        Log.d("View0PostTest", "2 post后_mTextView width:  " + tv_view_post_test.getMeasuredWidth() + " - height : " +  tv_view_post_test.getMeasuredHeight());

    }
}
