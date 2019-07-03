package com.example.sandy.accountingapp.list;

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
        int curIndex = view.getIndexFromIntent();
        view.setRecyclerView(repo.getAccountListByIndex(curIndex));
        repo.setCurrentIndexOfUser(curIndex);
    }

    @Override
    public void toEdit() {
        view.toEdit();
    }

    @Override
    public void toDataTable() {

    }

    // TODO: 2019/7/3 to delete
    @Override
    public void upDateList() {
        view.setRecyclerView(repo.getAccountListByIndex(repo.getCurrentIndexOfUser()));
    }

}
