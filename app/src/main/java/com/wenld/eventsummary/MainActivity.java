package com.wenld.eventsummary;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;


/**
 * 主界面
 */
public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    private Button event_text;
    private Button nested;
    private Button click_touch_longclick;
    private Button view_post;

    private Button mButtonlog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initView();
    }

    private void initView() {
        event_text = (Button) findViewById(R.id.event_text);
        nested = (Button) findViewById(R.id.nested);
        click_touch_longclick = (Button) findViewById(R.id.click_touch_longclick);
        view_post = (Button)findViewById(R.id.bt_view_post);

        event_text.setOnClickListener(this);
        nested.setOnClickListener(this);
        click_touch_longclick.setOnClickListener(this);
        view_post.setOnClickListener(this);

        mButtonlog = findViewById(R.id.bt_log);
        mButtonlog.setOnClickListener(this);

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.event_text:
                startActivity(new Intent(this, EventTestActivity.class));
                break;
            case R.id.nested:
                startActivity(new Intent(this, ScrollerViewNestedRecyclerViewActivity.class));
                break;
            case R.id.click_touch_longclick:
                startActivity(new Intent( this,TouchClickTestActivity.class));
                break;
            case R.id.bt_view_post:
                startActivity(new Intent(this,View0PostTestActivity.class));
                break;

            case R.id.bt_log:
                startActivity(new Intent(this,ExampleActivity.class));
                break;
            default:
                break;
        }
    }
}
