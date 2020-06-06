package com.wenld.eventsummary.nested;

import android.content.Context;
import android.support.annotation.Nullable;
import android.support.v7.widget.RecyclerView;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.ViewParent;

/**
 * <p/>
 * Author: 温利东 on 2017/7/26 11:22.
 * blog: http://blog.csdn.net/sinat_15877283
 * github: https://github.com/LidongWen
 */

public class MyRecyclerView extends RecyclerView {
    public MyRecyclerView(Context context) {
        super(context);
    }

    public MyRecyclerView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
    }

    public MyRecyclerView(Context context, @Nullable AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
    }

    float x = 0;
    float y = 0;


    /**
     * 拦截
     * @param ev
     * @return
     */

    @Override
    public boolean onInterceptTouchEvent(MotionEvent ev) {

        boolean bol = super.onInterceptTouchEvent(ev);
        final int action = ev.getAction();
        // sca： & 前后都会判断，  ACTION_MASK： http://www.jcodecraeer.com/a/anzhuokaifa/androidkaifa/2013/0226/911.html
        switch (action & MotionEvent.ACTION_MASK) {
            case MotionEvent.ACTION_DOWN: {
                final ViewParent parent = getParent();
                if (parent != null) {
                    //诉父View，也就是ViewParent不要拦截该控件上的触摸事件
                    parent.requestDisallowInterceptTouchEvent(true);
                }
//              sca: 记录 down事件 定位的位置
                x = ev.getX();
                y = ev.getY();
                break;
            }
            default:
                break;
        }
        return bol;
    }

    @Override
    public boolean onTouchEvent(MotionEvent ev) {

        final int action = ev.getAction();
        switch (action & MotionEvent.ACTION_MASK) {
            case MotionEvent.ACTION_MOVE: {
                final boolean scrollup;
                //这句代码字面意思我能读懂，但是，为什么呢？ "手指向下移动， ev.getY() - y > 0"
                if (ev.getY() - y > 0) {
                    //向下方向滚动，
                    scrollup = false;
                } else {
                    //向上方向滚动
                    scrollup = true;
                }
                /**
                 *canScrollVertically，判断是否可以在竖直方向上下滚动，-1 向上滚动，1向下滚动。
                 *
                 * sca: MyRecyclerView已经滚动到底了，告诉父可以拦截了。父拦截，则事件给父 View的 onTouchEvent 处理。
                 *
                 */
                if (!canScrollVertically(scrollup ? 1 : -1)) {//scrollup为false 父类拦截， 子类不可以滚动了
                    /**
                     * 参数为1，当不能继续向下滚动时，canScrollVertically返回false。
                     * 参数为-1，当不能继续下上滚动时，canScrollVertically返回false。
                     */
                    final ViewParent parent = getParent();
                    if (parent != null) {
                        //告诉父类可以拦截了。
                        parent.requestDisallowInterceptTouchEvent(false);
                    }
                }
                y = ev.getY();
            }
            break;
            default:
                break;
        }
        //  调用父类的先后，有什么区别？ sca:
        return super.onTouchEvent(ev);
    }
}
