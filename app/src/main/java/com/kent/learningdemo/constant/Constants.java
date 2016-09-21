package com.kent.learningdemo.constant;


import com.kent.learningdemo.item.applicationinfo.ApplicationInfoActivity;
import com.kent.learningdemo.item.lifecycle.LifeCycleActivity;
import com.kent.learningdemo.item.lifecycle.LifeCycleBActivity;
import com.kent.learningdemo.item.startmode.SingelTaskActivity;
import com.kent.learningdemo.item.switchline.SwitchLineActivity;
import com.kent.learningdemo.item.webnativecommunicate.WebViewActivity;
import com.kent.learningdemo.model.HomeItemBean;

import java.util.ArrayList;

/**
 * Created by kent on 16/6/16.
 */
public class Constants {

    public static final ArrayList<HomeItemBean> mDemoList;


    static {
        mDemoList = new ArrayList<>();
        mDemoList.add(new HomeItemBean("生命周期", LifeCycleBActivity.class,"2016.06.16"));
        mDemoList.add(new HomeItemBean("web&naitve通信", WebViewActivity.class, "2016.08.03"));
        mDemoList.add(new HomeItemBean("自定义自动换行ViewGroup", SwitchLineActivity.class, "2016.08.29"));
        mDemoList.add(new HomeItemBean("Activity 启动模式", SingelTaskActivity.class, "2016.08.30"));
        mDemoList.add(new HomeItemBean("ApplicationInfo", ApplicationInfoActivity.class, "2016.08.31"));
    }
}
