package com.example.sandy.accountingapp.login;

import com.example.sandy.accountingapp.model.LocalRepo;

public class LoginPresenter implements LoginContract.Presenter {

    private LoginContract.View view;

    private LocalRepo repo;

    public LoginPresenter(LoginContract.View view, LocalRepo repo) {
        this.repo = repo;
        this.view = view;
    }

    @Override
    public void LoginIn() {
        String name = view.getName();
        String password = view.getPassWord();
        if (repo.checkName(name)) {   //有重复并且都正确，从数据中调取数据
            if (repo.checkPassWd(password)) {
                view.toEdit(repo.getIndexByName(name));
            } else {
                view.loginRemind(false); //提醒登陆失败，已有账号登陆失败,
            }
        } else {//创新号再进去
            repo.createUser(name, password);
            view.loginRemind(true); //提醒已经默认创建新账号
            view.toEdit(repo.getIndexByName(name));
        }
    }
}
