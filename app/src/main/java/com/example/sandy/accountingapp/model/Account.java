package com.example.sandy.accountingapp.model;

//一些注意点写在前面
//暂时没自己写构造器，所以，new然后set
//repo:


public class Account {

    //账单类型，衣，食，行，学，玩
    public static final int CLOTH = 0;

    public static final int EAT = 1;

    public static final int GO = 2;

    public static final int STUDY = 3;

    public static final int PLAY = 4;

    //mood
    public static final int HAPPY = 5;

    public static final int SAD = 6;

    public static final int EXITED = 7;

    public static final int OTHER = 8;

    //收入支出
    public static final boolean NEGATIVE = false;

    public static final boolean POSITIVE = true;


    //private
    private double money;   //钱

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
}
