package com.example.sandy.accountingapp.setting;

import com.example.sandy.accountingapp.model.LocalRepo;

public class SettingPresenter implements SettingContract.Presenter {

    private SettingContract.View view;
    private LocalRepo repo;

    public SettingPresenter(SettingContract.View view, LocalRepo repo) {
        this.view = view;
        this.repo = repo;
    }

    @Override
    public void getUserMax() {

    }

    @Override
    public void gsetUserWeekMax() {

    }
}
