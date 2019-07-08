package com.example.sandy.accountingapp.edit;

import com.example.sandy.accountingapp.model.Account;

public interface EditContract {

    interface View{
        double getMoney();
        String getYear();
        String getMonth();
        String getDay();
        int getType();
        int getMood();
        String getNote();
        boolean getSignal();
        void DoFinish();
        void setAll(Account account);
        void sendDayNotification();
        void sendWeekNotification();
        void sendMonthNotification();
        int getIndex();
    }

    interface Presenter{
        void BackToList(boolean isOld);
        void isBeyondMax();
    }
}
