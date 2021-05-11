package com.wenld.eventsummary;

import android.app.Activity;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.Toast;

/**
 * *
 * *
 * Project_Name:EventSummary
 *
 * @author zhangxc
 * @date 2018/2/7 上午10:59
 * <p>
 * <p>
 * *
 */
public class TouchClickTestActivity extends Activity {


    private ImageView imageView = null;
    private String TAG = "TouchClickTestActivity";

    private Button mButton = null;

    private RelativeLayout layout;


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_touch_click_test);




        imageView = (ImageView) findViewById(R.id.imageView);
        imageView.setOnTouchListener(mOnTouchListener);
        imageView.setOnLongClickListener(mOnLongClickListener);
        imageView.setOnClickListener(mOnClickListener);


        mButton = (Button) findViewById(R.id.bt_touch);
        mButton.setOnTouchListener(mOnTouchListener);
        mButton.setOnLongClickListener(mOnLongClickListener);
        mButton.setOnClickListener(mOnClickListener);
        mButton.setClickable(true);






    }

    /**
     *
     */
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
            Toast.makeText(TouchClickTestActivity.this, "onTouch", Toast.LENGTH_SHORT).show();
            return false;   // 这个的作用是什么？  返回false,才会执行onTouchEvent，onTouchEvent中调用了Onclick
        }
    };

    View.OnLongClickListener mOnLongClickListener = new View.OnLongClickListener() {

        @Override
        public boolean onLongClick(View arg0) {
            // TODO Auto-generated method stub
            Log.d(TAG, "long click");
//            return false;  //4 FALSE
            Toast.makeText(TouchClickTestActivity.this, "onLongClick", Toast.LENGTH_SHORT).show();
            return false;  //4 FALSE
//
        }
    };

    View.OnClickListener mOnClickListener = new View.OnClickListener() {

        @Override
        public void onClick(View arg0) {
            // TODO Auto-generated method stub
            Toast.makeText(TouchClickTestActivity.this, "onClick", Toast.LENGTH_SHORT).show();
            Log.d(TAG, "click");  //5 NULL
        }
    };


    public void myClick(View v) {
        // does something very interesting
        Toast.makeText(TouchClickTestActivity.this, "onClick", Toast.LENGTH_SHORT).show();
        Log.d(TAG, "RelativeLayout click");  //5 NULL
    }


    /**
     * public boolean onLongClick(View v)
     * 　　参数v：参数v为事件源控件，当长时间按下此控件时才会触发该方法。
     * 　　返回值：该方法的返回值为一个boolean类型的变量，当返回true时，表示已经完整地处理了这个事件，并不希望其他的回调方法再次进行处理；当返回false时，表示并没有完全处理完该事件，更希望其他方法继续对其进行处理。
     *
     * 如果将onLongClick返回false，那么执行完长按事件后，还有执行单击(onClick)事件。
     * 如果返回true，只执行长按事件
     * 原文链接：https://blog.csdn.net/daoxiaomianzi/article/details/53021766
     */



}