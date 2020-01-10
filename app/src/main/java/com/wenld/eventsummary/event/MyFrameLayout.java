package com.wenld.eventsummary.event;

import android.content.Context;
import android.util.AttributeSet;
import android.util.Log;
import android.view.MotionEvent;
import android.widget.FrameLayout;

/**
 * <p/> 经理
 * Author: wenld on 2017/7/26 14:52.
 * blog: http://www.jianshu.com/u/99f514ea81b3
 * github: https://github.com/LidongWen
 * <p>
 * 代码 来自   http://www.cnblogs.com/net168/p/4165970.html
 */

public class MyFrameLayout extends FrameLayout {


    public static final String TAGActivity = "TAGActivity";

    private IShowLog mIShowLog;

    public void setIShowLog(IShowLog IShowLog) {
        mIShowLog = IShowLog;
    }

    public MyFrameLayout(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    @Override
    public boolean dispatchTouchEvent(MotionEvent ev) {
//        在 view里也可以使用 Log。
        Log.e(TAGActivity, "【经理】分派任务：" + Util.actionToString(ev.getAction())  + "，找个人帮我完成，任务往下分发。");
        if (mIShowLog!=null){
            mIShowLog.log("# dispatchTouchEvent【经理】分派任务：" + Util.actionToString(ev.getAction())  + "，找个人帮我完成，任务往下分发。");
        }
//         sca: 调用同名方法，可以保证事件向下传递
        return super.dispatchTouchEvent(ev);

//        boolean result = Util.经理分发;
//        Log.e(TAGActivity, "#dispatchTouchEvent【经理】分派任务：" + Util.actionToString(ev.getAction()) + "，找个人帮我完成，任务往下分发。");
//        if (mIShowLog != null) {
//            mIShowLog.log("#dispatchTouchEvent【经理】分派任务：" + Util.actionToString(ev.getAction()) + "，找个人帮我完成，任务往下分发。");
//        }
//        return result;

//        Log.e(TAGActivity, "【经理】分派任务：" + Util.actionToString(ev.getAction()) + "，找个人帮我完成，任务往下分发。");
//        if (mIShowLog != null) {
//            mIShowLog.log("# dispatchTouchEvent【经理】分派任务：" + Util.actionToString(ev.getAction()) + "，找个人帮我完成，任务往下分发。");
//        }
//        return true;
    }

    @Override
    public boolean onInterceptTouchEvent(MotionEvent ev) {
        boolean relust = Util.经理拦截;
        Log.e(TAGActivity, "#onInterceptTouchEvent【经理】是否拦截任务：" + Util.actionToString(ev.getAction()) + "，拦下来？" + relust);
        if (mIShowLog != null) {
            mIShowLog.log("#onInterceptTouchEvent【经理】是否拦截任务：" + Util.actionToString(ev.getAction()) + "，拦下来？" + relust);
        }
        return relust;
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        boolean relust = Util.经理消费;
        Log.e(TAGActivity, "# onTouchEvent【经理】完成任务：" + Util.actionToString(event.getAction()) + "，【组长】太差劲了，以后不再找你干活了，我自来搞定！是否解决：" + Util.canDoTask(relust));
        if (mIShowLog != null) {
            mIShowLog.log("# onTouchEvent【经理】完成任务：" + Util.actionToString(event.getAction()) + "，【组长】太差劲了，以后不再找你干活了，我自来搞定！是否解决：" + Util.canDoTask(relust) + "\n =====");
        }
        return relust;
    }
}