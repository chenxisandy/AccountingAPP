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
    public void setMaxFromUser() {
        if (repo.getUserList().get(repo.getCurrentIndexOfUser()).getDayMoney() != 0)
            view.setDayMax(Double.toString(repo.getUserList().get(repo.getCurrentIndexOfUser()).
                    getDayMoney()));
        if (repo.getUserList().get(repo.getCurrentIndexOfUser()).getWeekMoney() != 0)
            view.setWeekMax(Double.toString(repo.getUserList().get(repo.getCurrentIndexOfUser()).
                    getWeekMoney()));
        if (repo.getUserList().get(repo.getCurrentIndexOfUser()).getMonthMoney() != 0)
            view.setMonthMax(Double.toString(repo.getUserList().get(repo.getCurrentIndexOfUser()).
                    getMonthMoney()));
    }

    @Override
    public void setUserMax() {
        double dayMoney;
        double weekMoney;
        double monthMoney;
        if (view.getDayMax().length() == 0) {
            dayMoney = 0.0;
        } else {
            dayMoney = Double.parseDouble(view.getDayMax());
        }
        if (view.getMonthMAx().length() == 0) {
            monthMoney = 0.0;
        } else {
            monthMoney = Double.parseDouble(view.getMonthMAx());
        }
        if (view.getWeekMax().length() == 0) {
            weekMoney = 0.0;
        } else {
            weekMoney = Double.parseDouble(view.getWeekMax());
        }
        repo.getUserList().get(repo.getCurrentIndexOfUser()).setDayMoney(dayMoney);
        repo.getUserList().get(repo.getCurrentIndexOfUser()).setWeekMoney(weekMoney);
        repo.getUserList().get(repo.getCurrentIndexOfUser()).setMonthMoney(monthMoney);
    }

    @Override
    public boolean isWarningSignal() {
        return repo.getUserList().get(repo.getCurrentIndexOfUser()).isWarning();
    }

    @Override
    public void changeWarningSignal(boolean signal) {
        repo.getUserList().get(repo.getCurrentIndexOfUser()).setWarning(signal);
    }

    @Override
    public void beyondMax() {
        if (repo.getUserList().get(repo.getCurrentIndexOfUser()).isWarning()) {
            if (repo.isBeyondDayMax()) {
                view.sendDayNotification();
            }
            if (repo.isBeyondWeekMax()) {
                view.sendWeekNotification();
            }
            if (repo.isBeyondMonthMax()) {
                view.sendMonthNotification();
            }
        }
    }


}
