package com.example.sandy.accountingapp.edit;

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
    public void BackToList() {
        Double money = View.getMoney();
        int type = View.getType();
        int mood = View.getMood();
        String note = View.getNote();
        String year = View.getYear();
        String month = View.getMonth();
        String day = View.getDay();
        boolean signal = View.getSignal();
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
        View.DoFinish();//结束创建界面，返回List界面
    }

    public void createOldEdit(int acountIndex){
        int userIndex = repo.getCurrentIndexOfUser();//从repo获取当前用户的Index;
        View.setAll(repo.getAccountListByIndex(userIndex).get(acountIndex));
    }
}
