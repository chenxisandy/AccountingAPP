package com.example.sandy.accountingapp.edit;

import com.example.sandy.accountingapp.model.Account;
import com.example.sandy.accountingapp.model.LocalRepo;

public class EditPresenter implements EditContract.Presenter {

    private EditContract.View view;
    private LocalRepo repo;

    public EditPresenter(EditContract.View view, LocalRepo repo) {
        this.view = view;
        this.repo = repo;
    }

    @Override
    public void BackToList(boolean isOld) {
        Double money = view.getMoney();
        int type = view.getType();
        int mood = view.getMood();
        String note = view.getNote();
        String year = view.getYear();
        String month = view.getMonth();
        String day = view.getDay();
        boolean signal = view.getSignal();
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
            view.DoFinish();//结束创建界面，返回List界面
        } else {//更改老账单
            repo.getAccountListByIndex(repo.getCurrentIndexOfUser()).get(view.getIndex()).setYear(year);
            repo.getAccountListByIndex(repo.getCurrentIndexOfUser()).get(view.getIndex()).setMonth(month);
            repo.getAccountListByIndex(repo.getCurrentIndexOfUser()).get(view.getIndex()).setDay(day);
            repo.getAccountListByIndex(repo.getCurrentIndexOfUser()).get(view.getIndex()).setSignal(signal);
            repo.getAccountListByIndex(repo.getCurrentIndexOfUser()).get(view.getIndex()).setMoney(money);
            repo.getAccountListByIndex(repo.getCurrentIndexOfUser()).get(view.getIndex()).setType(type);
            repo.getAccountListByIndex(repo.getCurrentIndexOfUser()).get(view.getIndex()).setNote(note);
            repo.getAccountListByIndex(repo.getCurrentIndexOfUser()).get(view.getIndex()).setMood(mood);
            isBeyondMax();
            repo.saveUser();
            view.DoFinish();
        }
    }

    public void createOldEdit(int accountIndex) {
        int userIndex = repo.getCurrentIndexOfUser();//从repo获取当前用户的Index;
        view.setAll(repo.getAccountListByIndex(userIndex).get(accountIndex));
    }

    public void isBeyondMax() {
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
