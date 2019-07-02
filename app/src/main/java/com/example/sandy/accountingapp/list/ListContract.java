package com.example.sandy.accountingapp.list;

import com.example.sandy.accountingapp.model.Account;

import java.util.List;

public interface ListContract {
    interface View {
        void setRecyclerView(List<Account> list);
        int getIndexFromIntent();
    }

    interface Presenter {
        void fillList();    //填充list
    }
}
