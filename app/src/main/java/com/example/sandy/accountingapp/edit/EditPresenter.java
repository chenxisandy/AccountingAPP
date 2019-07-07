package com.example.sandy.accountingapp.edit;

import android.app.Notification;
import android.app.NotificationManager;
import android.support.v4.app.NotificationCompat;

import com.example.sandy.accountingapp.model.Account;
import com.example.sandy.accountingapp.model.LocalRepo;

import java.util.List;

public class EditPresenter implements EditContract.Presenter {

    private EditContract.View View;
    private LocalRepo repo;

    public EditPresenter(EditContract.View view, LocalRepo repo) {
        View = view;
        this.repo = repo;
    }

    @Override
    public void BackToList(boolean isOld) {
        Double money = View.getMoney();
        int type = View.getType();
        int mood = View.getMood();
        String note = View.getNote();
        String year = View.getYear();
        String month = View.getMonth();
        String day = View.getDay();
        boolean signal = View.getSignal();
        if (!isOld) {
            Account account = new Account();//创建新帐单
            account.setMoney(money);
            account.setNote(note);
            account.setMood(mood);
            account.setType(type);
            account.setYear(year);
            account.setMonth(month);
            account.setDay(day);
            account.setSignal(signal);
            repo.getAccountListByIndex(repo.getCurrentIndexOfUser()).add(account);//添加到AccountList
            isBeyondMax();
            repo.saveUser();
            View.DoFinish();//结束创建界面，返回List界面
        }else {//更改老账单
            repo.getAccountListByIndex(repo.getCurrentIndexOfUser()).get(View.getIndex()).setYear(year);
            repo.getAccountListByIndex(repo.getCurrentIndexOfUser()).get(View.getIndex()).setMonth(month);
            repo.getAccountListByIndex(repo.getCurrentIndexOfUser()).get(View.getIndex()).setDay(day);
            repo.getAccountListByIndex(repo.getCurrentIndexOfUser()).get(View.getIndex()).setSignal(signal);
            repo.getAccountListByIndex(repo.getCurrentIndexOfUser()).get(View.getIndex()).setMoney(money);
            repo.getAccountListByIndex(repo.getCurrentIndexOfUser()).get(View.getIndex()).setType(type);
            repo.getAccountListByIndex(repo.getCurrentIndexOfUser()).get(View.getIndex()).setNote(note);
            repo.getAccountListByIndex(repo.getCurrentIndexOfUser()).get(View.getIndex()).setMood(mood);
            isBeyondMax();
            repo.saveUser();
            View.DoFinish();
        }
    }

    public void createOldEdit(int accountIndex){
        int userIndex = repo.getCurrentIndexOfUser();//从repo获取当前用户的Index;
        View.setAll(repo.getAccountListByIndex(userIndex).get(accountIndex));
    }

    public void isBeyondMax(){
        if (repo.getUserList().get(repo.getCurrentIndexOfUser()).isWarning()) {
            if (repo.isBeyondDayMax()) {
                View.sendDayNotification();
            }
            if (repo.isBeyondWeekMax()) {
                View.sendWeekNotification();
            }
            if (repo.isBeyondMonthMax()) {
                View.sendMonthNotification();
            }
        }
    }
}
