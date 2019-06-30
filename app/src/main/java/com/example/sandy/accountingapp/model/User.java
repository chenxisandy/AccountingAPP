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

}
