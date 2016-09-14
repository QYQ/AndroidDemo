package com.kent.learningdemo.model;

/**
 * Created by kent on 16/6/3.
 */
public class HomeItemBean implements BaseModel {

    private String title;
    private String date;
    private Class targetActivity;

    public HomeItemBean(String title, Class targetActivity, String date) {
        this.title = title;
        this.targetActivity = targetActivity;
        this.date = date;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public Class getTargetActivity() {
        return targetActivity;
    }

    public void setTargetActivity(Class targetActivity) {
        this.targetActivity = targetActivity;
    }
}
