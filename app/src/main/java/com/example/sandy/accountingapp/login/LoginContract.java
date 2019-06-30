package com.example.sandy.accountingapp.login;

public interface LoginContract {

    interface View {
        String getName();    //得到登录所用数据
        String getPassWord();
        void loginRemind(boolean isRight);
        void toEdit(int index);     //得到用户在用户列表的索引，让下一个edit activity加载数据
    }

    interface Presenter {
        void LoginIn();     //登录，密码登录，如果原本存在就登录，否则自己创建一个
    }

}
