package com.kent.learningdemo.item.applicationinfo;

import android.content.pm.ApplicationInfo;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.util.LogPrinter;
import android.util.Printer;
import android.util.StringBuilderPrinter;
import android.widget.TextView;

import com.kent.learningdemo.R;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class ApplicationInfoActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_application_info);

        TextView textView = (TextView) findViewById(R.id.content);

        ApplicationInfo applicationInfo = getApplicationInfo();
        StringBuilder stringBuilder = new StringBuilder();
        StringBuilderPrinter printer = new StringBuilderPrinter(stringBuilder){
            @Override
            public void println(String x) {
                super.println(x);
                Log.e("application_info", x);
            }
        };

        applicationInfo.dump(printer, "\n");
        textView.setText(stringBuilder.toString());

        Log.e("test","getCacheDir:" + getApplicationContext().getCacheDir());
        Log.e("test","getExternalCacheDir:" + getApplicationContext().getExternalCacheDir());
        Log.e("test","getExternalCacheDirs:" + getApplicationContext().getExternalCacheDirs().length);
        Log.e("test","getExternalMediaDirs:" + getApplicationContext().getExternalMediaDirs().length);
        Log.e("test","getFilesDir:" + getApplicationContext().getFilesDir());
        Log.e("test","getObbDir:" + getApplicationContext().getObbDir());
        Log.e("test","getNoBackupFilesDir:" + getApplicationContext().getNoBackupFilesDir());


        Log.e("isFormatted","isFormatted" + isFormatted("7.2.0.2"));
        Log.e("isFormatted","isFormatted" + isFormatted("7.2."));
        Log.e("isFormatted","isFormatted" + isFormatted("7.2.0"));
        Log.e("isFormatted","isFormatted" + isFormatted("7.2.0."));
        Log.e("isFormatted","isFormatted" + isFormatted("2"));
        Log.e("isFormatted","isFormatted" + isFormatted(".0.2"));

    }

    public static boolean isFormatted(String version){
        if(version == null){
            return false;
        }

        String regex = "(\\d{1,2}\\.){2}\\d{1,2}";
        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher(version);
        return matcher.matches();
    }
}
