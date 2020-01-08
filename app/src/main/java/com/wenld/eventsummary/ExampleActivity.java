package com.wenld.eventsummary;

import android.app.Activity;
import android.graphics.Color;
import android.os.Bundle;

import com.inaka.galgo.Galgo;
import com.inaka.galgo.GalgoOptions;

/**
 * *
 * *
 * Project_Name:EventSummary
 *
 * @author zhangxc
 * @date 2020-01-08 15:05
 * *
 */
public class ExampleActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_example);

        // add some customization to the log messages
        GalgoOptions options = new GalgoOptions.Builder()
                .numberOfLines(15)
                .backgroundColor(Color.parseColor("#D9d6d6d6"))
                .textColor(Color.BLACK)
                .textSize(15)
                .build();
        Galgo.enable(this, options);

        Galgo.log("I am a log message");
    }

    @Override
    protected void onStart() {
        super.onStart();
        Galgo.log("I am a log message onstart");
    }

    @Override
    protected void onResume() {
        super.onResume();
        Galgo.log("I am a log message onResume");
    }

    @Override
    public void onDestroy() {
        super.onDestroy();

        // always call disable to avoid memory leaks
        Galgo.disable(this);
    }
}