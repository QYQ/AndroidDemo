package com.kent.learningdemo.item.startmode;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import com.kent.learningdemo.R;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class SingelTaskActivity extends Activity implements View.OnClickListener{

    private Button mStandardBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_singel_task);

        this.mStandardBtn = (Button)findViewById(R.id.start_standard_activity);
        this.mStandardBtn.setOnClickListener(this);

        Log.e("test", getApplicationInfo().dataDir);

        Log.e("version", "version :" + AppVersion.create("730").toString());
//        Log.e("version", "version :" + AppVersion.create("7.3.11").toInt());
//        Log.e("version", "version int :" + AppVersion.create("7.3").toInt());

        String test = "7.3.2";
        String[] result = test.split("\\.", 4);

        try {
            isNewerVersion("wuba.7.3.2-asdf", "7.8.5.3-32f");
        }catch (Exception e){

        }

        String ver = "5.5o.3";
        //过滤出真正的版本号
        String verRegex = "(\\d+\\.){2,}(\\d+)";
        Pattern verPattern = Pattern.compile(verRegex);
        Matcher verMatcher = verPattern.matcher(ver);
        Log.e("verMatcher:",verMatcher.find()+"");
    }

    @Override
    public void onClick(View v) {
        int id = v.getId();
        if(id == R.id.start_standard_activity){
            Intent intent = new Intent(SingelTaskActivity.this, StandardModeActivity.class);
            startActivity(intent);
        }
    }

    /**
     * 比较版本号大小
     * @param oldVer
     * @param newVer
     * @return
     * @throws Exception
     */
    public static boolean isNewerVersion(String oldVer, String newVer) throws Exception{

        //版本号格式要求：xx.xx.xx....
        String regex = "[\\s\\S]*(\\d+\\.)+(\\d+)[\\s\\S]*";
        Pattern pattern = Pattern.compile(regex);

        if(!pattern.matcher(oldVer).matches() || !pattern.matcher(newVer).matches()){
            throw new Exception("The version params is invalid !");
        }

        String realOldVer = getRealVer(oldVer);
        String realNewVer = getRealVer(newVer);

        String [] oldVerArr = realOldVer.split("\\.");
        String [] newVerArr = realNewVer.split("\\.");

        int oldVerArrLen = oldVerArr.length;
        int newVerArrLen = newVerArr.length;

        int maxLen = Math.max(oldVerArrLen, newVerArrLen);

        int oldInt ;
        int newInt ;

        for(int i = 0 ; i < maxLen ; i++){
            if(i < oldVerArrLen - 1){
                oldInt = Integer.parseInt(oldVerArr[i]);
            }else {
                oldInt = 0;
            }
            if(i < newVerArrLen - 1){
                newInt = Integer.parseInt(newVerArr[i]);
            }else {
                newInt = 0;
            }
            if( oldInt < newInt){
                return true;
            }else if(oldInt > newInt){
                return false;
            }
        }
        return false;
    }

    /**
     * 从字符串中过滤出真正的版本号
     * @param ver
     * @return
     */
    private static String getRealVer(String ver){
        String realVer = "";
        //过滤出真正的版本号
        String verRegex = "(\\d+\\.)+(\\d+)";
        Pattern verPattern = Pattern.compile(verRegex);
        Matcher verMatcher = verPattern.matcher(ver);

        int start ;
        int end ;

        if(verMatcher.find()){
            start = verMatcher.start();
            end = verMatcher.end();
            realVer = ver.substring(start, end);
        }

        return realVer;
    }
}
