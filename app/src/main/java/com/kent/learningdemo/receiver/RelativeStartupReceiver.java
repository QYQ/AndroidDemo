package com.kent.learningdemo.receiver;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.text.TextUtils;
import android.util.Log;
import android.widget.Toast;

/**
 * Created by kent on 16/7/13.
 */
public class RelativeStartupReceiver extends BroadcastReceiver {

//    public static final String ACTION = "com.kent.autostartup";

    @Override
    public void onReceive(Context context, Intent intent) {
        String action = intent.getAction();

        Log.e("broadcast", action + " is received 1111 ");

        if(TextUtils.isEmpty(action)){
            return;
        }

        Toast.makeText(context, action + " is received 1111 ", Toast.LENGTH_SHORT).show();

//        if(ACTION.equals(action)){
//            UserCenterProxy.login(context);
//        }
    }
}
