package com.example.sandy.accountingapp.edit;

import com.example.sandy.accountingapp.model.Account;
import com.example.sandy.accountingapp.model.LocalRepo;

public class EditPresenter implements EditContract.Presenter {

    private EditContract.View View;
    private LocalRepo repo;

    public EditPresenter(EditContract.View view, LocalRepo repo) {
        View = view;
        this.repo = repo;
    }

    @Override
    public void BackToList() {
        Double money = View.getmoney();
        long time = View.gettime();
        int type = View.getType();
        int mood = View.getmood();
        String note = View.getnote();
        Account account = new Account();

    }
}
