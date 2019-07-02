package com.example.sandy.accountingapp.edit;

public interface EditContract {

    interface View{
        double getmoney();
        long gettime();
        int getType();
        int getmood();
        String getnote();

    }

    interface Presenter{
        void BackToList();

    }
}
