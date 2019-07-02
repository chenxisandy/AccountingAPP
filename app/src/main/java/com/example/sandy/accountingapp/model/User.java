package com.example.sandy.accountingapp.model;

import java.util.ArrayList;
import java.util.List;

public class User {

    private String name;

    private String password;

    private List<Account> accountList = new ArrayList<>();      //账单表

    private double dayMoney;

    private double weekMoney;

    private double monthMoney;

    public User(String name, String password) {
        this.name = name;
        this.password = password;
    }

    //get
    // TODO: 2019/6/30 to disable
    public double getDayMoney() {
        return dayMoney;
    }

    public double getWeekMoney() {
        return weekMoney;
    }

    public double getMonthMoney() {
        return monthMoney;
    }

    public List<Account> getAccountList() {
        return accountList;
    }

    public String getName() {
        return name;
    }

    public String getPassword() {
        return password;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setDayMoney(double dayMoney) {
        this.dayMoney = dayMoney;
    }

    public void setWeekMoney(double weekMoney) {
        this.weekMoney = weekMoney;
    }

    public void setMonthMoney(double monthMoney) {
        this.monthMoney = monthMoney;
    }

    public void addAccount(Account account){
        accountList.add(account);
    }

    public void setAccountList(List<Account> accountList) {
        this.accountList = accountList;
    }
}
