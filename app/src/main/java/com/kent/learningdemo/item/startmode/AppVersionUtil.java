package com.kent.learningdemo.item.startmode;

import android.util.Log;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by kent on 16/9/1.
 */
public class AppVersionUtil {

    private static final String TAG = "AppVersionUtil";

    /**
     * 版本号是否符合 xx.xx.xx 的标准格式
     * @param version
     * @return
     */
    public static boolean isFormatted(String version){
        if(version == null){
            return false;
        }

        String regex = "(\\d{1,2}\\.){2}\\d{1,2}";
        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher(version);
        return matcher.matches();
    }

    /**
     * 转换成标准格式版本号，如 7.3.0
     * @param version
     * @return
     */
    public static String format(String version){

        if(version == null){
            Log.e(TAG, "The version is null");
            return AppVersion.createDefault().toString();
        }

        //符合格式要求直接返回
        if(isFormatted(version)){
            return version;
        }

        //符合xx.xx.xx.xx....这种格式
        String regex = "(\\d{1,2}\\.){2,}\\d{1,2}";
        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher(version);

        if(!matcher.matches()){
            Log.e(TAG, "The version: " + version + "‘s format is invalid !");
            return AppVersion.createDefault().toString();
        }

        String[] versionArr = version.split("\\.");
        int len = versionArr.length;

        StringBuilder stringBuilder = new StringBuilder();

        for(int i = 0 ; i < len ; i ++){
            if(i >= AppVersion.FORMATTED_VERSION_PART_COUNT){
                break;
            }
            if(i == 0){
                stringBuilder.append(versionArr[i]);
            }else {
                stringBuilder.append(AppVersion.FORMATTED_VERSION_PART_SEPERATOR).append(versionArr[i]);
            }
        }

        String result = stringBuilder.toString();

        if(!isFormatted(result)){
            Log.e(TAG, "Format fail, check your version format !");
            return AppVersion.createDefault().toString();
        }

        return stringBuilder.toString();
    }

    /**
     * 比较版本号
     * @param oldVer
     * @param newVer
     * @return
     */
    public static boolean isNewerVersion(AppVersion oldVer, AppVersion newVer){

        return oldVer.compareTo(newVer) == -1;
    }
}
