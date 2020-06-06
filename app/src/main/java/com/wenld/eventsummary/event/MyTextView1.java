package com.wenld.eventsummary.event;

import android.content.Context;
import android.support.v7.widget.AppCompatTextView;
import android.util.AttributeSet;
import android.util.Log;
import android.view.MotionEvent;

/**
 * <p/>  员工
 * Author: wenld on 2017/7/26 14:54.
 * blog: http://www.jianshu.com/u/99f514ea81b3
 * github: https://github.com/LidongWen
 * * 代码 来自   http://www.cnblogs.com/net168/p/4165970.html
 * <p>
 * <p>
 * 主要用于测试 enable  onclick  onLongClick  等对 ouTouch 的影响
 */

public class MyTextView1 extends AppCompatTextView {


    private IShowLog mIShowLog;

    public void setIShowLog(IShowLog IShowLog) {
        mIShowLog = IShowLog;
    }


    public MyTextView1(Context context, AttributeSet attrs) {
        super(context, attrs);
        // TODO Auto-generated constructor stub
    }

    @Override
    public boolean dispatchTouchEvent(MotionEvent event) {
        Log.e("test", "【员工】分派任务：" + Util.actionToString(event.getAction()) + "，我没手下了，唉~自己干吧");
        if (mIShowLog != null) {
            mIShowLog.log("# dispatchTouchEvent 【员工】分派任务：" + Util.actionToString(event.getAction()) + "，我没手下了，唉~自己干吧");
        }
        return super.dispatchTouchEvent(event);
    }


    @Override
    public boolean onTouchEvent(MotionEvent event) {
        /**
         * relust true为消费处理，false为不消费
         */
        boolean relust = Util.员工消费;
        Log.i("test", "【员工】完成任务：" + Util.actionToString(event.getAction()) + "，【员工】现在只能靠自己了！是否解决：" + Util.canDoTask(relust));
        if (mIShowLog != null) {
            mIShowLog.log("# onTouchEvent【员工】完成任务：" + Util.actionToString(event.getAction()) + "，【员工】现在只能靠自己了！是否解决：" + Util.canDoTask(relust) + "\n =====");
        }
        return super.onTouchEvent(event);
    }


    /**
     * View的enable属性不影响onTouchEvent的默认返回值。
     * 哪怕一个View是disable状态的,只要它的clickable或者longClickable有一个为true,
     * 那么它的onTouchEvent就返回true。
     *
     * sca:  onTouchEvent 返回 true,会有什么影响，  down 事件就在此消费， 不再传递到父 view。
     *
     */

}