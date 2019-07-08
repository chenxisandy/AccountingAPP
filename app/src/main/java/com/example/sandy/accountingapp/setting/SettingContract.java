package com.example.sandy.accountingapp.setting;

public interface SettingContract {

    interface View {
        void setDayMax(String s);

        String getDayMax();

        void setWeekMax(String s);

        String getWeekMax();

        void setMonthMax(String s);

        String getMonthMAx();

        void sendDayNotification();

        void sendWeekNotification();

        void sendMonthNotification();
    }

    interface Presenter {
        void setMaxFromUser();

        void setUserMax();

        boolean isWarningSignal();

        void changeWarningSignal(boolean signal);

        void beyondMax();
    }
}
