package com.example.sandy.accountingapp.edit;


import android.app.DatePickerDialog;
import android.app.Notification;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.RadioGroup;

import com.example.sandy.accountingapp.R;
import com.example.sandy.accountingapp.model.Account;
import com.example.sandy.accountingapp.model.LocalRepo;

import java.util.Calendar;

public class EditActivity extends AppCompatActivity implements EditContract.View , View.
        OnClickListener ,DialogListener.typeListener ,DialogListener.moodListener,DialogListener.
        incomeListener{

    private EditText moneytext;
    private EditText timetext;
    private EditText typetext;
    private EditText moodtext;
    private EditText notetext;
    private Button create;
    private EditPresenter mpresenter;
    private int Myyear,Mymonth,Mydate;
    private DatePickerDialog datePickerDialog;
    private DatePickerDialog.OnDateSetListener listener;
    private RadioGroup radioGroup;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit);
        initView();
    }

    @Override
    public double getMoney() {
        if (moneytext.getText().toString().length() != 0) {
            return Double.parseDouble(moneytext.getText().toString());
        } else {
            return 0;
        }
    }

    @Override
    public String getYear() {
        return Integer.toString(Myyear);
    }

    @Override
    public String getMonth() {
        return Integer.toString(Mymonth);
    }

    @Override
    public String getDay() {
        return Integer.toString(Mydate);
    }


    @Override
    public int getType() {
        switch (typetext.getText().toString()){
            case "衣" :
                return Account.CLOTH;
            case "食" :
                return Account.EAT;
            case "行" :
                return Account.GO;
            case "学" :
                return Account.STUDY;
            case "玩" :
                return Account.PLAY;
            default:
                return Account.CLOTH;
        }
    }

    @Override
    public int getMood() {
        switch (moodtext.getText().toString()){
            case "Happy" :
                return Account.HAPPY;
            case "Sad" :
                return Account.SAD;
            case "Excited" :
                return Account.EXITED;
            case "Other" :
                return Account.OTHER;
            default:
                return Account.HAPPY;
        }
    }

    @Override
    public String getNote() {
        return notetext.getText().toString();
    }

    @Override
    public boolean getSignal() {
        switch (radioGroup.getCheckedRadioButtonId()){
            case R.id.income : return true;
            case R.id.outcome : return false;
            default: return false;
        }
    }

    @Override
    public void DoFinish() {
        this.finish();
    }

    @Override
    public void setAll(Account account) {
        moneytext.setText(Double.toString(account.getMoney()));
        timetext.setText(account.getYear()+" "+account.getMonth()+" "+account.getDay());
        switch (account.getType()){
            case Account.CLOTH: typetext.setText("衣");break;
            case Account.EAT: typetext.setText("食");break;
            case Account.GO: typetext.setText("行");break;
            case Account.STUDY: typetext.setText("学");break;
            case Account.PLAY: typetext.setText("玩");break;
            default: typetext.setText("衣");break;
        }
        switch (account.getMood()){
            case Account.HAPPY:moodtext.setText("Happy");break;
            case Account.SAD:moodtext.setText("Sad");break;
            case Account.EXITED:moodtext.setText("Excited");break;
            case Account.OTHER:moodtext.setText("Other");break;
            default:moodtext.setText("Happy");break;
        }
        if (account.isSignal()){
            radioGroup.check(R.id.income);
        }else {
            radioGroup.check(R.id.outcome);
        }
        notetext.setText(account.getNote());
    }

    @Override
    public void sendDayNotification() {
        NotificationManager manager = (NotificationManager) getSystemService(NOTIFICATION_SERVICE);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O){
            String channelId = "DayMax";
            String chanelName = "日最大金额警告";
            manager.createNotificationChannel(new NotificationChannel(channelId,chanelName,
                    NotificationManager.IMPORTANCE_HIGH));
        }
        Notification notification = new Notification.Builder(this,"DayMax")
                .setContentTitle("警告")
                .setContentText("你的日消费金额已超过预定最大值，请理性消费")
                .setAutoCancel(true)
                .setWhen(System.currentTimeMillis())
                .build();
        manager.notify(1,notification);
    }

    @Override
    public void sendWeekNotification() {
        NotificationManager manager = (NotificationManager) getSystemService(NOTIFICATION_SERVICE);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O){
            String channelId = "WeekMax";
            String chanelName = "周最大金额警告";
            manager.createNotificationChannel(new NotificationChannel(channelId,chanelName,
                    NotificationManager.IMPORTANCE_HIGH));
        }
        Notification notification = new Notification.Builder(this,"WeekMax")
                .setContentTitle("警告")
                .setContentText("你的周消费金额已超过预定最大值，请理性消费")
                .setAutoCancel(true)
                .setWhen(System.currentTimeMillis())
                .build();
        manager.notify(1,notification);
    }

    @Override
    public void sendMonthNotification() {
        NotificationManager manager = (NotificationManager) getSystemService(NOTIFICATION_SERVICE);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O){
            String channelId = "MonthMax";
            String chanelName = "月最大金额警告";
            manager.createNotificationChannel(new NotificationChannel(channelId,chanelName,
                    NotificationManager.IMPORTANCE_HIGH));
        }
        Notification notification = new Notification.Builder(this,"MonthMax")
                .setContentTitle("警告")
                .setContentText("你的月消费金额已超过预定最大值，请理性消费")
                .setAutoCancel(true)
                .setWhen(System.currentTimeMillis())
                .build();
        manager.notify(1,notification);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.create:
                mpresenter.BackToList();//创建账单并返回
                break;
            case R.id.time_edit://选择时间
                datePickerDialog.show();
                break;
            case R.id.mood_edit://选择心情
                MoodDialog moodDialog = new MoodDialog(this);
                moodDialog.setMoodListener(this);
                moodDialog.show();
                break;
            case R.id.type_edit://选择类型
                switch (radioGroup.getCheckedRadioButtonId()){
                    case R.id.outcome:
                        TypeDialog typeDialog = new TypeDialog(this);
                        typeDialog.setTypeListener(this);
                        typeDialog.show();
                        break;
                    case R.id.income:
                        IncomeDialog incomeDialog = new IncomeDialog(this);
                        incomeDialog.setIncomeListener(this);
                        incomeDialog.show();
                        break;
                }
                break;
            }
        }

    private void getCalendar(){//获取日历时间
        Calendar calendar = Calendar.getInstance();
        Myyear = calendar.get(calendar.YEAR);
        Mymonth = calendar.get(calendar.MONTH)+1;
        Mydate = calendar.get(calendar.DAY_OF_MONTH);
        timetext.setText(Myyear + " "+ Mymonth +" "+Mydate);
        listener = new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
                Myyear = year;
                Mymonth = month + 1 ;
                Mydate = dayOfMonth;
                timetext.setText(Myyear+" "+ Mymonth +" "+Mydate);
            }
        };
        datePickerDialog = new DatePickerDialog(this , listener, Myyear ,Mymonth,Mydate);
    }

    private void initView(){
        moneytext = findViewById(R.id.money_edit);
        timetext = findViewById(R.id.time_edit);
        moodtext = findViewById(R.id.mood_edit);
        notetext = findViewById(R.id.note_edit);
        typetext = findViewById(R.id.type_edit);
        create = findViewById(R.id.create);
        create.setOnClickListener(this);
        timetext.setOnClickListener(this);
        moodtext.setOnClickListener(this);
        typetext.setOnClickListener(this);
        radioGroup = findViewById(R.id.account_type);
        mpresenter = new EditPresenter(this, LocalRepo.getInstance());
        Intent intent = getIntent();
        String type = intent.getStringExtra("type");
        if (type.equals("new")){
            getCalendar();
        }else {
            int index = intent.getIntExtra("accountIndex",0);
            mpresenter.createOldEdit(index);
        }
    }


    @Override
    public void setType(String type) {
        typetext.setText(type);
    }

    @Override
    public void setMood(String mood) {
        moodtext.setText(mood);
    }


    @Override
    public void setIncomeType(String incomeType) {
        typetext.setText(incomeType);
    }
}

