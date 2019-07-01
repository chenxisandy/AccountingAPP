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

    //login
    public boolean checkName(String name) {    //判断在是不是名字
        // TODO: 2019/6/30
        int i = userList.size();//获得list的大小
        for (int j = 0 ;j < i ; j++){//遍历找用户
            if(userList.get(j).getName().equals(name)){
                return true;
            }
        }
        return false;
    }

    public boolean checkPassWd(String passWord,String name) {
        // TODO: 2019/6/30
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
        // TODO: 2019/6/30
        int i = userList.size();//获得list的大小
        for (int j = 0 ;j < i ; j++){//遍历找用户
            if(userList.get(j).getName().equals(name)){
                return j;
            }
        }
        return 0;
    }

    public void createUser(String name, String passWd) {
        // TODO: 2019/6/30
        User user = new User(name,passWd);
        userList.add(user);
    }
}