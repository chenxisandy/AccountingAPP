package com.example.sandy.accountingapp.List;

public interface ListContract {

    interface View{
        void createAccount();
    }

    interface Presenter{
        void showAccounts();
    }
}
