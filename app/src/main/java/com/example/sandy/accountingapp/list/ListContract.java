package com.example.sandy.accountingapp.list;

import com.example.sandy.accountingapp.model.Account;

import java.util.List;

public interface ListContract {
    interface View {
        void setRecyclerView(List<Account> list);
        int getIndexFromIntent();
        void toEdit();
        void toDataTable();
    }

    interface Presenter {
        void fillList();    //填充list

        void toEdit();      //去编辑界面

        void toDataTable(); //去用户表格界面

        void upDateList();  // TODO: 2019/7/3 to delete

    }
}
