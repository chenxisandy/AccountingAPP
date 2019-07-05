package com.example.sandy.accountingapp.model;

import android.graphics.Color;
import android.util.DisplayMetrics;
import android.view.Display;

import com.github.mikephil.charting.data.Entry;
import com.github.mikephil.charting.data.PieData;
import com.github.mikephil.charting.data.PieDataSet;
import com.github.mikephil.charting.data.PieEntry;
import com.github.mikephil.charting.formatter.PercentFormatter;

import android.icu.util.Calendar;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class LocalRepo {

    private static LocalRepo INSTANCE;

    public static LocalRepo getInstance() {     //因为不用考虑多线程，故可以用这个比较简单的单例
        if (INSTANCE == null) {
            INSTANCE = new LocalRepo();
        }
        return INSTANCE;
    }

    private LocalRepo() {}; //构造器私有化

    private List<User> userList = new ArrayList<>();

    private int currentIndexOfUser;     //用来存当前的UserIndex

    //login
    public boolean checkName(String name) {    //判断在是不是名字
        int i = userList.size();//获得list的大小
        for (int j = 0 ;j < i ; j++){//遍历找用户
            if(userList.get(j).getName().equals(name)){
                return true;
            }
        }
        return false;
    }

    public boolean checkPassWd(String passWord,String name) {
        User user = userList.get(getIndexByName(name));
        if (user.getPassword().equals(passWord)){
            return true;
        }else
            return false;

    }


    //method 或许可以加上一个sort
    public String beyondMax() {   //支出超过最大值，预警,传你要预警的语句，若为null则无是
        // TODO: 2019/6/30
        return null;
    }

    public boolean isBeyondDayMax(){////判断是否超过每日最大金额
        double total = 0;
        Calendar calendar = Calendar.getInstance();
        String year = Integer.toString(calendar.get(Calendar.YEAR));
        int i = calendar.get(Calendar.MONTH) + 1;
        String month = Integer.toString(i);
        String day = Integer.toString(calendar.get(Calendar.DAY_OF_MONTH));
        for (Account account : getAccountListByIndex(getCurrentIndexOfUser())){
            if (year.equals(account.getYear())){
                if (month.equals(account.getMonth())){
                    if (day.equals(account.getDay())){
                        if (!account.isSignal()){
                            total = total + account.getMoney();
                        }
                    }
                }
            }
        }
        if (total >= getUserList().get(getCurrentIndexOfUser()).getDayMoney()){
            return true;
        }else {
            return false;
        }
    }

    public boolean isBeyondMonthMax(){//判断是否超过每月最大金额
        double total = 0;
        Calendar calendar = Calendar.getInstance();
        String year = Integer.toString(calendar.get(Calendar.YEAR));
        for (Account account : getAccountListByIndex(getCurrentIndexOfUser())){
            if (year.equals(account.getYear())){
                if (!account.isSignal()){
                    total = total + account.getMoney();
                }
            }
        }
        if (total >= getUserList().get(getCurrentIndexOfUser()).getMonthMoney()){
            return true;
        }else {
          return false;
        }
    }

    public boolean isBeyondWeekMax(){//判断是否超过每周最大金额
        double total = 0;
        Calendar calendar = Calendar.getInstance();
        int i = calendar.get(Calendar.WEEK_OF_YEAR);
        String year = Integer.toString(calendar.get(Calendar.YEAR));
        for (Account account : getAccountListByIndex(getCurrentIndexOfUser())){
              if (year.equals(account.getYear())){
                  if (i == getWeek(account.getYear()+"-"+account.getMonth()+"-"+
                          account.getDay())){
                      if (! account.isSignal()){
                          total = total + account.getMoney();
                      }
                  }
              }
        }
        if (total >= getUserList().get(getCurrentIndexOfUser()).getWeekMoney()){
            return true;
        }else {
            return false;
        }
    }

    public int getWeek(String str){
        SimpleDateFormat sdf= new SimpleDateFormat("yyyy-MM-dd");
        Date date = null;
        try {
            date = sdf.parse(str);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        int week = calendar.get(Calendar.WEEK_OF_YEAR);
        return week;
    }



    public List<User> getUserList() {
        return userList;
    }

    public int getIndexByName(String name) {
        int i = userList.size();//获得list的大小
        for (int j = 0 ;j < i ; j++){//遍历找用户
            if(userList.get(j).getName().equals(name)){
                return j;
            }
        }
        return 0;
    }

    public void createUser(String name, String passWd) {
        User user = new User(name,passWd);
        List<Account> list = new ArrayList<>(); //必须创建一个list以免为空
        user.setAccountList(list);
        userList.add(user);
    }

    //list
    public List<Account> getAccountListByIndex(int index){
        return userList.get(index).getAccountList();
    }

    public int getCurrentIndexOfUser() {
        return currentIndexOfUser;
    }

    public void setCurrentIndexOfUser(int currentIndexOfUser) {
        this.currentIndexOfUser = currentIndexOfUser;
    }

    //chart table
    public PieData getPieData(float range) {
//        List<String> xValues = new ArrayList<>();   //存数据名称
//        xValues.add("衣服");
//        xValues.add("食物");
//        xValues.add("出行");
//        xValues.add("学习");
//        xValues.add("娱乐");
//
        List<PieEntry> values = new ArrayList<>();    //存实际数据

        float clothQuarter = 0;
        float eatQuarter = 0;
        float goQuarter = 0;
        float studyQuarter = 0;
        float playQuarter = 0;

        //userList.get(currentIndexOfUser).getAccountList();
        for (Account account :
                userList.get(currentIndexOfUser).getAccountList()) {
            switch (account.getType()) {
                case Account.CLOTH:
                    clothQuarter = (float) (account.getMoney()) + clothQuarter;
                    break;
                case Account.EAT:
                    eatQuarter += (float) (account.getMoney());
                    break;
                case Account.GO:
                    goQuarter += (float) (account.getMoney());
                    break;
                case Account.STUDY:
                    studyQuarter += (float) (account.getMoney());
                    break;
                case Account.PLAY:
                    playQuarter += (float) (account.getMoney());
                    break;
            }
        }

        values.add(new PieEntry(clothQuarter, "衣服"));
        values.add(new PieEntry(eatQuarter, "食物"));
        values.add(new PieEntry(goQuarter, "出行"));
        values.add(new PieEntry(studyQuarter, "学习"));
        values.add(new PieEntry(playQuarter, "娱乐"));

        //y轴集合
        PieDataSet pieDataSet = new PieDataSet(values, "消费分类统计");
        pieDataSet.setSliceSpace(0f);   //设置饼状图地之间地距离
        pieDataSet.setValueFormatter(new PercentFormatter());
        pieDataSet.setColors(Color.BLUE, Color.GREEN, Color.GRAY, Color.YELLOW, Color.DKGRAY);

        return new PieData(pieDataSet);


    }

}
