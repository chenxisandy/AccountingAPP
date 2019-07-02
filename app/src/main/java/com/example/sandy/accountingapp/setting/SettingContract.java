package com.example.sandy.accountingapp.setting;

public interface SettingContract {

    interface View{
        void setDayMax();
        void getDayMax();
        void setWeekMax();
        void getWeekMax();
        void setMonthMax();
        void getMonthMAx();
    }

    interface Presenter{
        void getUserMax();
        void gsetUserWeekMax();
    }
}
