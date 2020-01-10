package com.wenld.eventsummary;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.TextView;

import com.wenld.eventsummary.event.IShowLog;
import com.wenld.eventsummary.event.MyFrameLayout;
import com.wenld.eventsummary.event.MyLinearLayout;
import com.wenld.eventsummary.event.MyTextView;
import com.wenld.eventsummary.event.MyTextView1;
import com.wenld.eventsummary.event.Util;

import io.reactivex.Observable;
import io.reactivex.android.schedulers.AndroidSchedulers;

/**
 * <p/> 老板
 * Author: wenld on 2017/7/26 14:56.
 * 作者blog地址 : http://www.jianshu.com/u/99f514ea81b3
 * 本案例的博客  https://www.jianshu.com/p/e00b5668ee39
 * 7.0 源码分析 https://www.jianshu.com/p/78d39dc82a88
 * 源码 github: https://github.com/LidongWen
 * <p>
 * 在本案例中，没有干预 dispatchTouchEvent 方法。
 */

public class EventTestActivity extends Activity implements IShowLog {


    public static final String TAG = "event";

    public static final String TAGActivity = "TAGActivity";
    /**
     * checkBox 1、3是拦截，其他都是消费
     */
    private CheckBox checkBoxDispatch;//  是否消费， 消费就是不分发。
    private CheckBox checkBox;  //拦截
    private CheckBox checkBox2;
    private CheckBox checkBox3;  //拦截
    private CheckBox checkBox4;
    private CheckBox checkBox5;
    private CheckBox checkBox7;
    private TextView textView;
    private TextView textView2;
    private TextView textView3;

    //    显示 log
    private TextView tvText;

    private MyFrameLayout mMyFrameLayout;
    private MyLinearLayout mMyLinearLayout;
    private MyTextView mMyTextView;


    private MyTextView1 mMyTextView1;
    //sca： 测试兄弟 View A如果遮挡住遮挡住了 自自己的兄弟 View B，那么B能收到事件吗？  答：收不到
    private MyTextView mMyTextView2;


    // 让 Activity 只打印一次 log， 正常情况下，如果 View 不消费事件， 那么一系列的Down Move ... Move UP,都会传到  Actvity。
//  - 所以 正常情况为了验证 demo，把这个控制去掉。
    int mInt = 0;

    private CheckBox checkBoxOnTouch;
    private CheckBox checkBoxEnable;
    private CheckBox checkBoxClick;
    private CheckBox checkBoxLongClick;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_event);
        initView();
    }

    /**
     * sca
     * @param ev
     * @return
     */
    @Override
    public boolean dispatchTouchEvent(MotionEvent ev) {
        Log.i(TAGActivity, "#dispatchTouchEvent【老板】分派任务：" + Util.actionToString(ev.getAction()) + "，找个人帮我完成，任务往下分发。");
        if (mInt == 0) {
            mInt = 1;
            log("#dispatchTouchEvent【老板】分派任务：" + Util.actionToString(ev.getAction()) + "，找个人帮我完成，任务往下分发。");
        }
//        log("#dispatchTouchEvent【老板】分派任务：" + Util.actionToString(ev.getAction()) + "，找个人帮我完成，任务往下分发。");

        return super.dispatchTouchEvent(ev);
    }


    /**
     * sc:
     * @param event
     * @return
     */
    @Override
    public boolean onTouchEvent(MotionEvent event) {
        boolean relust = Util.老板消费;
        Log.i(TAGActivity, "#onTouchEvent【老板】完成任务：" + Util.actionToString(event.getAction()) + "，【经理】太差劲了，以后不再找你干活了，我自来搞定！是否解决：" + Util.canDoTaskTop(relust));
        if (mInt > 0) {
            mInt = 1;
            log("#onTouchEvent【老板】完成任务：" + Util.actionToString(event.getAction()) + "，【经理】太差劲了，以后不再找你干活了，我自来搞定！是否解决：" + Util.canDoTaskTop(relust));
        }
//        log("#onTouchEvent【老板】完成任务：" + Util.actionToString(event.getAction()) + "，【经理】太差劲了，以后不再找你干活了，我自来搞定！是否解决：" + Util.canDoTaskTop(relust));

        return relust;
    }

    private void initView() {

        checkBoxDispatch = findViewById(R.id.checkBox_dispatch);
        checkBox = (CheckBox) findViewById(R.id.checkBox);
        checkBox2 = (CheckBox) findViewById(R.id.checkBox2);
        checkBox3 = (CheckBox) findViewById(R.id.checkBox3);
        checkBox4 = (CheckBox) findViewById(R.id.checkBox4);
        checkBox5 = (CheckBox) findViewById(R.id.checkBox5);
        checkBox7 = (CheckBox) findViewById(R.id.checkBox7);
        textView = (TextView) findViewById(R.id.textView);
        textView2 = (TextView) findViewById(R.id.textView2);
        textView3 = (TextView) findViewById(R.id.textView3);

        checkBox.setOnCheckedChangeListener(checkedChangeListener);
        checkBox2.setOnCheckedChangeListener(checkedChangeListener);
        checkBox3.setOnCheckedChangeListener(checkedChangeListener);
        checkBox4.setOnCheckedChangeListener(checkedChangeListener);
        checkBox5.setOnCheckedChangeListener(checkedChangeListener);
        checkBox7.setOnCheckedChangeListener(checkedChangeListener);
        tvText = findViewById(R.id.tv_text);
        mMyFrameLayout = findViewById(R.id.fl_my);
        mMyFrameLayout.setIShowLog(this);

        mMyLinearLayout = findViewById(R.id.ll_my);
        mMyLinearLayout.setIShowLog(this);
        mMyTextView = findViewById(R.id.tv_my);
        mMyTextView.setIShowLog(this);
        mMyTextView1 = findViewById(R.id.tv_my1);
        mMyTextView1.setIShowLog(this);
        mMyTextView1.setClickable(true);


        mMyTextView2 = findViewById(R.id.tv_my2);
        mMyTextView2.setIShowLog(this);
//        mMyTextView2.setEnabled(false);
        mMyTextView2.setClickable(false);
        mMyTextView2.setLongClickable(false);


        checkBoxOnTouch = findViewById(R.id.checkBoxOnTouch);
        checkBoxEnable = findViewById(R.id.checkBoxEnable);
        checkBoxClick = findViewById(R.id.checkBoxClick);
        checkBoxLongClick = findViewById(R.id.checkBoxLongClick);


    }

    CompoundButton.OnCheckedChangeListener checkedChangeListener = new CompoundButton.OnCheckedChangeListener() {
        @Override
        public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
            switch (buttonView.getId()) {

                case R.id.checkBox_dispatch:
                    Util.经理分发 = isChecked;
                    break;
                case R.id.checkBox:
                    Util.经理拦截 = isChecked;
                    break;
                case R.id.checkBox2:
                    Util.经理消费 = isChecked;
                    break;
                case R.id.checkBox3:
                    Util.组长拦截 = isChecked;
                    break;
                case R.id.checkBox4:
                    Util.组长消费 = isChecked;
                    break;
                case R.id.checkBox5:
                    Util.员工消费 = isChecked;
                    break;
                case R.id.checkBox7:
                    Util.老板消费 = isChecked;
                    break;
                case R.id.checkBoxOnTouch:
                    Util.onTouch = isChecked;
                    mMyTextView1.setOnTouchListener(new View.OnTouchListener() {
                        @Override
                        public boolean onTouch(View v, MotionEvent event) {
                            //   1、如果setOnTouchListener中的onTouch方法返回值是true（事件被消费）时，则onTouchEvent方法将不会被执行；
//  2、只有当setOnTouchListener中的onTouch方法返回值是false（事件未被消费，向下传递）时，onTouchEvent方法才被执行。

                            return isChecked;
                        }
                    });
                    break;
                case R.id.checkBoxEnable:
                    Util.enable = isChecked;
                    mMyTextView1.setEnabled(isChecked);
                    break;
                case R.id.checkBoxClick:
                    Util.click = isChecked;
                    mMyTextView1.setClickable(isChecked);
                    break;
                case R.id.checkBoxLongClick:
                    Util.longClick = isChecked;
                    mMyTextView1.setLongClickable(isChecked);
                    break;
                default:
                    break;
            }
        }
    };


    @Override
    public void log(Object s) {
//        Log.i(TAG, String.valueOf(s));
//      sca:  把从其他页面接收到的Observable发出的数据，再用just发出去，然后再切换到UI线程处理
        Observable.just(s).observeOn(AndroidSchedulers.mainThread()).subscribe(i -> {
            tvText.setText(tvText.getText() + "\n" + i);
        });
    }
}