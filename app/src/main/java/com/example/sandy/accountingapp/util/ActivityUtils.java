package com.example.sandy.accountingapp.util;

import android.support.v7.app.AppCompatActivity;

import com.example.sandy.accountingapp.list.ListActivity;

import java.util.ArrayList;
import java.util.List;

public class ActivityUtils {

    private List<AppCompatActivity> ActivityList = new ArrayList<>();

    private ActivityUtils() {
    }

    private static ActivityUtils INSTANCE = new ActivityUtils();

    public static ActivityUtils getInstance(){
        if (INSTANCE == null){
            INSTANCE = new ActivityUtils();
        }
        return INSTANCE;
    }

    public void addActivity(AppCompatActivity activity){
        if (!ActivityList.contains(activity)){
            ActivityList.add(activity);
        }
    }

    public void deleteActivity(AppCompatActivity activity){
        if (ActivityList.contains(activity)) {
            ActivityList.remove(activity);
            activity.finish();
        }
    }

    public void removeAllActivity(){
        for (AppCompatActivity activity:ActivityList){
            activity.finish();
        }
    }

    public void finishList() {
        for (AppCompatActivity activity :
                ActivityList) {
            if (activity instanceof ListActivity) {
                activity.finish();
            }
        }
    }


}
