package com.wenld.eventsummary.event;

import android.content.Context;
import android.util.AttributeSet;
import android.util.Log;
import android.view.MotionEvent;
import android.widget.LinearLayout;

/**
 * <p/> 组长
 * Author: wenld on 2017/7/26 14:54.
 * blog: http://www.jianshu.com/u/99f514ea81b3
 * github: https://github.com/LidongWen
 * 代码 来自   http://www.cnblogs.com/net168/p/4165970.html
 */

public class MyLinearLayout extends LinearLayout {

    public static final String TAGActivity = "TAGActivity";


    private IShowLog mIShowLog;

    public void setIShowLog(IShowLog IShowLog) {
        mIShowLog = IShowLog;
    }


    public MyLinearLayout(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    @Override
    public boolean dispatchTouchEvent(MotionEvent ev) {
        Log.e(TAGActivity, "# dispatchTouchEvent【组长】分派任务：" + Util.actionToString(ev.getAction()) + "，找个人帮我完成，任务往下分发。");

        if (mIShowLog != null) {
            mIShowLog.log("# dispatchTouchEvent 【组长】分派任务：" + Util.actionToString(ev.getAction()) + "，找个人帮我完成，任务往下分发。");
        }
        return super.dispatchTouchEvent(ev);
    }


    // 组长拦截，如果拦截，就会自己消费
    @Override
    public boolean onInterceptTouchEvent(MotionEvent ev) {
        boolean relust = Util.组长拦截;// （默认false值）默认状态下组长是不拦截的，会传递到 组员
        Log.e(TAGActivity, "# onInterceptTouchEvent 【组长】是否拦截任务：" + Util.actionToString(ev.getAction()) + "，拦下来？" + relust);
        if (mIShowLog != null) {
            mIShowLog.log("# onInterceptTouchEvent 【组长】是否拦截任务：" + Util.actionToString(ev.getAction()) + "，拦下来？" + relust);
        }
        return relust;
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        boolean relust = Util.组长消费;// 默认false值）默认状态下组长是不消费的，会
        Log.e(TAGActivity, "# onTouchEvent【组长】完成任务：" + Util.actionToString(event.getAction()) + "，【员工】太差劲了，以后不再找你干活了，我自来搞定！是否解决：" + Util.canDoTask(relust));
        if (mIShowLog != null) {
            mIShowLog.log("# onTouchEvent 【组长】完成任务：" + Util.actionToString(event.getAction()) + "，【员工】太差劲了，以后不再找你干活了，我自来搞定！是否解决：" + Util.canDoTask(relust) +"\n =====");
        }
        return relust;
//        Log.i(TAGActivity, "# onTouchEvent 【组长】调用父类同名方法。");
//        return super.onTouchEvent(event);
    }
}