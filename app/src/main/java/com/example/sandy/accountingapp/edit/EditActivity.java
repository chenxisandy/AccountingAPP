package com.example.sandy.accountingapp.edit;


import android.app.AlertDialog;
import android.app.DatePickerDialog;
import android.app.Notification;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.BitmapFactory;
import android.os.Build;
import android.support.v4.app.NotificationCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.View;
import android.widget.Button;

import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.RadioGroup;
import android.widget.Toast;

import com.example.sandy.accountingapp.R;
import com.example.sandy.accountingapp.model.Account;
import com.example.sandy.accountingapp.model.LocalRepo;

import java.util.Calendar;

public class EditActivity extends AppCompatActivity implements EditContract.View, View.
        OnClickListener, DialogListener.typeListener, DialogListener.moodListener, DialogListener.
        incomeListener {

    private EditText moneyText;
    private EditText timeText;
    private EditText typeText;
    private EditText moodText;
    private EditText noteText;
    private Button create;
    private EditPresenter mPresenter;
    private int MyYear, MyMonth, MyDate;
    private DatePickerDialog datePickerDialog;
    private DatePickerDialog.OnDateSetListener listener;
    private RadioGroup radioGroup;
    private boolean isOldAccount;
    private int index;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit);
        initView();
    }

    @Override
    public double getMoney() {
        if (moneyText.getText().toString().length() != 0) {
            return Double.parseDouble(moneyText.getText().toString());
        } else {
            return 0;
        }
    }

    @Override
    public String getYear() {
        return Integer.toString(MyYear);
    }

    @Override
    public String getMonth() {
        return Integer.toString(MyMonth + 1);
    }

    @Override
    public String getDay() {
        return Integer.toString(MyDate);
    }


    @Override
    public int getType() {
        switch (radioGroup.getCheckedRadioButtonId()) {
            case R.id.income:
                switch (typeText.getText().toString()) {
                    case "工资":
                        return Account.WAGES;
                    case "礼物":
                        return Account.GIFT;
                    case "理财":
                        return Account.FINANCIAL_MANAGEMENT;
                    case "其他":
                        return Account.ELSE;
                    default:
                        return Account.ELSE;
                }
            case R.id.outcome:
                switch (typeText.getText().toString()) {
                    case "衣":
                        return Account.CLOTH;
                    case "食":
                        return Account.EAT;
                    case "行":
                        return Account.GO;
                    case "学":
                        return Account.STUDY;
                    case "玩":
                        return Account.PLAY;
                    default:
                        return Account.CLOTH;
                }
            default:
                return Account.ELSE;
        }
    }

    @Override
    public int getMood() {
        switch (moodText.getText().toString()) {
            case "Happy":
                return Account.HAPPY;
            case "Sad":
                return Account.SAD;
            case "Excited":
                return Account.EXITED;
            case "Other":
                return Account.OTHER;
            default:
                return Account.HAPPY;
        }
    }

    @Override
    public String getNote() {
        return noteText.getText().toString();
    }

    @Override
    public boolean getSignal() {
        switch (radioGroup.getCheckedRadioButtonId()) {
            case R.id.income:
                return true;
            case R.id.outcome:
                return false;
            default:
                return false;
        }
    }

    @Override
    public void DoFinish() {
        this.finish();
    }

    @Override
    public void setAll(Account account) {
        MyYear = Integer.parseInt(account.getYear());
        MyMonth = Integer.parseInt(account.getMonth()) - 1;
        MyDate = Integer.parseInt(account.getDay());
        moneyText.setText(Double.toString(account.getMoney()));
        timeText.setText(account.getYear() + " " + account.getMonth() + " " + account.getDay());
        noteText.setText(account.getNote());
        listener = new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
                MyYear = year;
                MyMonth = month;
                MyDate = dayOfMonth;
                int j = MyMonth + 1;
                timeText.setText(MyYear + " " + j + " " + MyDate);
            }
        };
        datePickerDialog = new DatePickerDialog(this, listener, MyYear, MyMonth, MyDate);
        switch (account.getMood()) {
            case Account.HAPPY:
                moodText.setText("开心");
                break;
            case Account.SAD:
                moodText.setText("难过");
                break;
            case Account.EXITED:
                moodText.setText("兴奋");
                break;
            case Account.OTHER:
                moodText.setText("其他");
                break;
            default:
                moodText.setText("开心");
                break;
        }
        if (account.isSignal()) {
            radioGroup.check(R.id.income);
            switch (account.getType()) {
                case Account.WAGES:
                    typeText.setText("工资");
                    break;
                case Account.GIFT:
                    typeText.setText("礼物");
                    break;
                case Account.FINANCIAL_MANAGEMENT:
                    typeText.setText("理财");
                    break;
                case Account.ELSE:
                    typeText.setText("其他");
                    break;
                default:
                    typeText.setText("其他");
                    break;
            }
        } else {
            radioGroup.check(R.id.outcome);
            switch (account.getType()) {
                case Account.CLOTH:
                    typeText.setText("衣");
                    break;
                case Account.EAT:
                    typeText.setText("食");
                    break;
                case Account.GO:
                    typeText.setText("行");
                    break;
                case Account.STUDY:
                    typeText.setText("学");
                    break;
                case Account.PLAY:
                    typeText.setText("玩");
                    break;
                default:
                    typeText.setText("衣");
                    break;
            }
        }
    }

    @Override
    public void sendDayNotification() {
        NotificationManager manager = (NotificationManager) getSystemService(NOTIFICATION_SERVICE);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            String channelId = "DayMax";
            String chanelName = "日最大金额警告";
            manager.createNotificationChannel(new NotificationChannel(channelId, chanelName,
                    NotificationManager.IMPORTANCE_HIGH));
        }
        Notification notification = new NotificationCompat.Builder(this, "DayMax")
                .setSmallIcon(R.mipmap.ic_launcher)
                .setLargeIcon(BitmapFactory.decodeResource(getResources(), R.mipmap.ic_launcher))
                .setContentTitle("警告")
                .setContentText("你的日消费金额已超过预定最大值，请理性消费")
                .setAutoCancel(true)
                .setWhen(System.currentTimeMillis())
                .build();
        manager.notify(1, notification);
    }

    @Override
    public void sendWeekNotification() {
        NotificationManager manager = (NotificationManager) getSystemService(NOTIFICATION_SERVICE);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            String channelId = "WeekMax";
            String chanelName = "周最大金额警告";
            manager.createNotificationChannel(new NotificationChannel(channelId, chanelName,
                    NotificationManager.IMPORTANCE_HIGH));
        }
        Notification notification = new NotificationCompat.Builder(this, "WeekMax")
                .setSmallIcon(R.mipmap.ic_launcher)
                .setLargeIcon(BitmapFactory.decodeResource(getResources(), R.mipmap.ic_launcher))
                .setContentTitle("警告")
                .setContentText("你的周消费金额已超过预定最大值，请理性消费")
                .setAutoCancel(true)
                .setWhen(System.currentTimeMillis())
                .build();
        manager.notify(2, notification);
    }

    @Override
    public void sendMonthNotification() {
        NotificationManager manager = (NotificationManager) getSystemService(NOTIFICATION_SERVICE);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            String channelId = "MonthMax";
            String chanelName = "月最大金额警告";
            manager.createNotificationChannel(new NotificationChannel(channelId, chanelName,
                    NotificationManager.IMPORTANCE_HIGH));
        }
        Notification notification = new NotificationCompat.Builder(this, "MonthMax")
                .setSmallIcon(R.mipmap.ic_launcher)
                .setLargeIcon(BitmapFactory.decodeResource(getResources(), R.mipmap.ic_launcher))
                .setContentTitle("警告")
                .setContentText("你的月消费金额已超过预定最大值，请理性消费")
                .setAutoCancel(true)
                .setWhen(System.currentTimeMillis())
                .build();
        manager.notify(3, notification);
    }

    @Override
    public int getIndex() {
        return index;
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.create:
                if (moneyText.getText().toString().length() == 0) {
                    Toast.makeText(this, "金额不可为空", Toast.LENGTH_SHORT).show();
                } else {
                    mPresenter.BackToList(isOldAccount);//创建账单并返回
                }
                break;
            case R.id.time_edit://选择时间
                datePickerDialog.setCancelable(false);
                datePickerDialog.show();
                break;
            case R.id.mood_edit://选择心情
                MoodDialog moodDialog = new MoodDialog(this);
                moodDialog.setCancelable(false);
                moodDialog.setMoodListener(this);
                moodDialog.show();
                break;
            case R.id.type_edit://选择类型
                switch (radioGroup.getCheckedRadioButtonId()) {
                    case R.id.outcome:
                        TypeDialog typeDialog = new TypeDialog(this);
                        typeDialog.setCancelable(false);
                        typeDialog.setTypeListener(this);
                        typeDialog.show();
                        break;
                    case R.id.income:
                        IncomeDialog incomeDialog = new IncomeDialog(this);
                        incomeDialog.setCancelable(false);
                        incomeDialog.setIncomeListener(this);
                        incomeDialog.show();
                        break;
                }
                break;
        }
    }

    private void getCalendar() {//获取日历时间
        Calendar calendar = Calendar.getInstance();
        MyYear = calendar.get(calendar.YEAR);
        MyMonth = calendar.get(calendar.MONTH);
        MyDate = calendar.get(calendar.DAY_OF_MONTH);
        int i = MyMonth + 1;
        timeText.setText(MyYear + " " + i + " " + MyDate);
        listener = new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
                MyYear = year;
                MyMonth = month;
                MyDate = dayOfMonth;
                int j = MyMonth + 1;
                timeText.setText(MyYear + " " + j + " " + MyDate);
            }
        };
        datePickerDialog = new DatePickerDialog(this, listener, MyYear, MyMonth, MyDate);
    }

    private void initView() {
        moneyText = findViewById(R.id.money_edit);
        timeText = findViewById(R.id.time_edit);
        moodText = findViewById(R.id.mood_edit);
        noteText = findViewById(R.id.note_edit);
        typeText = findViewById(R.id.type_edit);
        create = findViewById(R.id.create);
        create.setOnClickListener(this);
        timeText.setOnClickListener(this);
        moodText.setOnClickListener(this);
        typeText.setOnClickListener(this);
        radioGroup = findViewById(R.id.account_type);
        mPresenter = new EditPresenter(this, LocalRepo.getInstance());
        Intent intent = getIntent();
        String type = intent.getStringExtra("type");
        if (type.equals("new")) {
            getCalendar();
            isOldAccount = false;
        } else {
            index = intent.getIntExtra("accountIndex", 0);
            isOldAccount = true;
            mPresenter.createOldEdit(index);
        }
    }


    @Override
    public void setType(String type) {
        typeText.setText(type);
    }

    @Override
    public void setMood(String mood) {
        moodText.setText(mood);
    }


    @Override
    public void setIncomeType(String incomeType) {
        typeText.setText(incomeType);
    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (keyCode == KeyEvent.KEYCODE_BACK) {
            AlertDialog.Builder dialog = new AlertDialog.Builder(this);
            dialog.setTitle("退出提示");
            dialog.setMessage("账单未完成，确定要退出吗?若要保存草稿，请点取消后点击最下方按钮");
            dialog.setCancelable(false);
            dialog.setPositiveButton("确定", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    finish();
                }
            });
            dialog.setNegativeButton("取消", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    dialog.dismiss();
                }
            });
            dialog.show();
        }
        return false;
    }
}

