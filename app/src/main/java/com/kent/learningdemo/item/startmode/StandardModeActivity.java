package com.kent.learningdemo.item.startmode;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import com.kent.learningdemo.R;

public class StandardModeActivity extends Activity implements View.OnClickListener{

    private Button mSingleTaskBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_standard_mode);
        this.mSingleTaskBtn = (Button)findViewById(R.id.start_singleTask_activity);
        this.mSingleTaskBtn.setOnClickListener(this);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        Log.e("StandardModeActivity", "onDestroy");

    }

    @Override
    public void onClick(View v) {
        int id = v.getId();
        if(id == R.id.start_singleTask_activity){
            Intent intent = new Intent(StandardModeActivity.this, SingelTaskActivity.class);
            startActivity(intent);
        }
    }

    @Override
    protected void onNewIntent(Intent intent) {
        Log.e("test", "onNewIntent");
        super.onNewIntent(intent);
    }
}
