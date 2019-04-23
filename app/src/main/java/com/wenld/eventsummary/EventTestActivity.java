package com.wenld.eventsummary;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.view.MotionEvent;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.TextView;

import com.wenld.eventsummary.event.Util;

/**
 * <p/> 老板
 * Author: wenld on 2017/7/26 14:56.
 * blog: http://www.jianshu.com/u/99f514ea81b3
 * 本案例的博客  https://www.jianshu.com/p/e00b5668ee39
 * 7.0  https://www.jianshu.com/p/78d39dc82a88
 * github: https://github.com/LidongWen
 */

public class EventTestActivity extends Activity {

    /**
     * checkBox 1、3是拦截，其他都是消费
     */
    private CheckBox checkBox;  //拦截
    private CheckBox checkBox2;
    private CheckBox checkBox3;  //拦截
    private CheckBox checkBox4;
    private CheckBox checkBox5;
    private CheckBox checkBox7;
    private TextView textView;
    private TextView textView2;
    private TextView textView3;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_event);
        initView();
    }

    @Override
    public boolean dispatchTouchEvent(MotionEvent ev) {

        Log.i("test", "【老板】下达任务：" + Util.actionToString(ev.getAction()) + "，找个人帮我完成，任务往下分发。");
        return super.dispatchTouchEvent(ev);
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        boolean relust = Util.老板消费;
        Log.i("test", "【老板】完成任务：" + Util.actionToString(event.getAction()) + "，【经理】太差劲了，以后不再找你干活了，我自来搞定！是否解决：" + Util.canDoTaskTop(relust));
        return relust;
    }

    private void initView() {
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


    }

    CompoundButton.OnCheckedChangeListener checkedChangeListener = new CompoundButton.OnCheckedChangeListener() {
        @Override
        public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
            switch (buttonView.getId()) {
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
                default:
                    break;
            }
        }
    };
}