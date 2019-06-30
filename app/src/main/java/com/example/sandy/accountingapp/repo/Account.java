package com.example.sandy.accountingapp.repo;

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

    //private
    private double money;   //钱

    private long time;      //时间

    private int type;       //类型

    private int mood;       //心情

    private String note;    //备注

    public double getMoney() {
        return money;
    }

    public long getTime() {
        return time;
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

    public void setTime(long time) {
        this.time = time;
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

}
