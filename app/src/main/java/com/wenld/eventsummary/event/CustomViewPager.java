package com.wenld.eventsummary.event;


import android.content.Context;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.widget.RecyclerView;
import android.util.AttributeSet;
import android.util.Log;
import android.view.MotionEvent;

/**
 * Project_Name:EventSummary
 *
 * @author zhangxc
 * @date 2018/12/31 00:03
 *  ViewPager的 getChildAt 获取的是 当前 view的 -1 —— 1 这里采用Adapter的方式获取
 *  参考 http://www.jcodecraeer.com/a/anzhuokaifa/androidkaifa/2013/0804/1504.html
 *
 */

public class CustomViewPager extends ViewPager {
    private RecyclerView recyclerView;


    public CustomViewPager(Context context) {
        this(context, null);
    }

    @Override
    public PagerAdapter getAdapter() {
        return (PagerAdapter) super.getAdapter();
    }


    public CustomViewPager(Context context, AttributeSet attrs) {
        super(context, attrs);

    }


    @Override
    public boolean onInterceptTouchEvent(MotionEvent ev) {
        float y = ev.getY();
        if (recyclerView == null)
//            recyclerView = (RecyclerView) getAdapter().getPrimaryItem();
        switch (ev.getAction()) {
            case MotionEvent.ACTION_DOWN:
                downY = ev.getY();
                getParent().requestDisallowInterceptTouchEvent(true);
                break;
            case MotionEvent.ACTION_MOVE:
                float dy = y - downY;
                if (dy >= 0) {
                    Log.d("=====bottom", !recyclerView.canScrollVertically(-1) + "");
                    if (!recyclerView.canScrollVertically(-1))//的值表示是否能向上滚动，false表示已经滚动到底部)
                    {
                        getParent().requestDisallowInterceptTouchEvent(false);
                        break;
                    }
                } else {
                    Log.d("=====Top", !recyclerView.canScrollVertically(1) + "");
                    if (!recyclerView.canScrollVertically(1))//的值表示是否能向上滚动，false表示已经滚动到底部)
                    {
                        getParent().requestDisallowInterceptTouchEvent(false);
                        break;
                    }
                }
                break;
            case MotionEvent.ACTION_UP:
                Log.d("====ACTION_CANCEL", getCurrentItem() + "");
                break;
            case MotionEvent.ACTION_CANCEL:
                Log.d("====ACTION_CANCEL", getCurrentItem() + "");
                getParent().requestDisallowInterceptTouchEvent(false);
                recyclerView = null;
                break;
            default:
                break;
        }
        return super.onInterceptTouchEvent(ev);
    }


    private float downY;


    protected boolean isSlideToBottom(RecyclerView recyclerView) {
        if (recyclerView == null) return false;
        if (recyclerView.computeVerticalScrollExtent() + recyclerView.computeVerticalScrollOffset() >= recyclerView.computeVerticalScrollRange())
            return true;
        return false;
    }

    private void test(RecyclerView recyclerView) {
        recyclerView.canScrollVertically(1); //的值表示是否能向上滚动，false表示已经滚动到底部
        recyclerView.canScrollVertically(-1);//的值表示是否能向下滚动，false表示已经滚动到顶部

        Log.d("=====bottom", recyclerView.canScrollVertically(1) + "");
        Log.d("=====Top", recyclerView.canScrollVertically(-1) + "");
    }
}