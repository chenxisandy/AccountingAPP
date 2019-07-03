package com.example.sandy.accountingapp.model;

import java.util.ArrayList;
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

}
