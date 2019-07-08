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
        view.setDayMax(Double.toString(repo.getUserList().get(repo.getCurrentIndexOfUser()).
                getDayMoney()));
        view.setWeekMax(Double.toString(repo.getUserList().get(repo.getCurrentIndexOfUser()).
                getWeekMoney()));
        view.setMonthMax(Double.toString(repo.getUserList().get(repo.getCurrentIndexOfUser()).
                getMonthMoney()));
    }

    @Override
    public void setUserMax() {
        double dayMoney = Double.parseDouble(view.getDayMax());
        double weekMoney = Double.parseDouble(view.getWeekMax());
        double monthMoney = Double.parseDouble(view.getMonthMAx());
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
