package com.example.sandy.accountingapp.model;

//一些注意点写在前面
//暂时没自己写构造器，所以，new然后set
//repo:


import org.litepal.crud.LitePalSupport;

public class Account extends LitePalSupport implements Comparable {

    //账单类型，衣，食，行，学，玩
    public static final int CLOTH = 0;

    public static final int EAT = 1;

    public static final int GO = 2;

    public static final int STUDY = 3;

    public static final int PLAY = 4;

    //收入类型 工资，礼物，理财

    public static final int WAGES = 9;

    public static final int GIFT = 10;

    public static final int FINANCIAL_MANAGEMENT = 11;

    public static final int ELSE = 12;

    //mood
    public static final int HAPPY = 5;

    public static final int SAD = 6;

    public static final int EXITED = 7;

    public static final int OTHER = 8;

    //收入支出
    public static final boolean NEGATIVE = false;

    public static final boolean POSITIVE = true;


    //private
    private User user;

    private double money;   //income

    private int type;       //类型

    private int mood;       //心情

    private String note;    //备注

    private boolean signal; //正负符号

    private String year;

    private String month;

    private String day;


    public double getMoney() {
        return money;
    }

    public int getType() {
        return type;
    }

    public int getMood() {
        return mood;
    }

    public String getNote() {
        return note;
    }


    //set

    public void setMoney(double money) {
        this.money = money;
    }

    public void setType(int type) {
        this.type = type;
    }

    public void setMood(int mood) {
        this.mood = mood;
    }

    public void setNote(String note) {
        this.note = note;
    }

    public boolean isSignal() {
        return signal;
    }

    public void setSignal(boolean signal) {
        this.signal = signal;
    }

    public String getYear() {
        return year;
    }

    public void setYear(String year) {
        this.year = year;
    }

    public String getMonth() {
        return month;
    }

    public void setMonth(String month) {
        this.month = month;
    }

    public String getDay() {
        return day;
    }

    public void setDay(String day) {
        this.day = day;
    }

    @Override
    public int compareTo(Object o) {
        if (Integer.parseInt(this.year) > Integer.parseInt(((Account) o).year)) {
            return 1;
        } else if (Integer.parseInt(this.year) < Integer.parseInt(((Account) o).year)) {
            return -1;
        } else {
            if (Integer.parseInt(this.month) > Integer.parseInt(((Account) o).month)) {
                return 1;
            } else if (Integer.parseInt(this.month) < Integer.parseInt(((Account) o).month)) {
                return -1;
            } else {
                return 0;
            }
        }
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }
}
