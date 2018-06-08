package com.wenld.eventsummary;

import android.app.Activity;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

/**
 * *
 * *
 * Project_Name:EventSummary
 *
 * @author zhangxc
 * @date 2018/2/7 上午10:59
 * *
 */

public class TouchClickTestActivity extends Activity {

    private ImageView imageView = null;
    private String TAG = "TouchClickTestActivity";

    private Button mButton = null;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_touch_click_test);

        imageView = (ImageView) findViewById(R.id.imageView);
        imageView.setOnTouchListener(mOnTouchListener);
        imageView.setOnLongClickListener(mOnLongClickListener);
        imageView.setOnClickListener(mOnClickListener);
        imageView.setClickable(true);

        mButton = (Button) findViewById(R.id.bt_touch);
        mButton.setOnTouchListener(mOnTouchListener);
        mButton.setOnLongClickListener(mOnLongClickListener);
        mButton.setOnClickListener(mOnClickListener);
        mButton.setClickable(true);
    }

    View.OnTouchListener mOnTouchListener = new View.OnTouchListener() {
        @Override
        public boolean onTouch(View arg0, MotionEvent event) {
            // TODO Auto-generated method stub
//            if (event.getAction() == MotionEvent.ACTION_DOWN) {
//                Log.d(TAG, "touch down");
//                return false;  //1 FALSE
//            } else if (event.getAction() == MotionEvent.ACTION_MOVE) {
//                Log.d(TAG, "touch move");
//                return true;  //2 FALSE
//            } else if (event.getAction() == MotionEvent.ACTION_UP) {
//                Log.d(TAG, "touch up");
//                return false;  //3 FALSE
//            }
            Log.d(TAG, "onTouch execute, action " + event.getAction());
            return false;   // 这个的作用是什么？  返回false,才会执行onTouchEvent，onTouchEvent中调用了Onclick
        }
    };
    View.OnLongClickListener mOnLongClickListener = new View.OnLongClickListener() {

        @Override
        public boolean onLongClick(View arg0) {
            // TODO Auto-generated method stub
            Log.d(TAG, "long click");
//            return false;  //4 FALSE
            return false;  //4 FALSE
        }
    };

    View.OnClickListener mOnClickListener = new View.OnClickListener() {

        @Override
        public void onClick(View arg0) {
            // TODO Auto-generated method stub
            Log.d(TAG, "click");  //5 NULL
        }
    };


}