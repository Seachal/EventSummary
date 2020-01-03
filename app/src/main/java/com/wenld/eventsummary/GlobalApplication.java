package com.wenld.eventsummary;

import android.app.Application;

import com.github.pedrovgs.lynx.LynxShakeDetector;

/**
 * *
 * *
 * Project_Name:EventSummary
 *
 * @author zhangxc
 * @date 2020-01-03 17:38
 * *
 */
public class GlobalApplication extends Application {

    @Override
    public void onCreate() {
        super.onCreate();
        LynxShakeDetector lynxShakeDetector = new LynxShakeDetector(this);
        lynxShakeDetector.init();
    }
}
