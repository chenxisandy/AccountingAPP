package com.example.sandy.accountingapp.list;

import com.example.sandy.accountingapp.list.ListContract;
import com.example.sandy.accountingapp.model.LocalRepo;

public class ListPresenter implements ListContract.Presenter {

    private ListContract.View view;
    private LocalRepo repo;

    public ListPresenter(ListContract.View view, LocalRepo repo) {
        this.view = view;
        this.repo = repo;
    }

    @Override
    public void fillList() {
        view.setRecyclerView(repo.getAccountListByIndex(view.getIndexFromIntent()));
    }
}
