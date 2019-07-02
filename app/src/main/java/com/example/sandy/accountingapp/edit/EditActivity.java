package com.example.sandy.accountingapp.edit;


import android.app.DatePickerDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import android.widget.DatePicker;
import android.widget.EditText;

import com.example.sandy.accountingapp.R;
import com.example.sandy.accountingapp.model.LocalRepo;

import java.util.Calendar;

public class EditActivity extends AppCompatActivity implements EditContract.View ,View.OnClickListener {

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

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit);
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
        mpresenter = new EditPresenter(this, LocalRepo.getInstance());
        getCalendar();
    }

    @Override
    public double getmoney() {
        return Double.parseDouble(moneytext.getText().toString());
    }

    @Override
    public long gettime() {
        return 0;
    }

    @Override
    public int getType() {
        switch (typetext.getText().toString()){
            case "衣" :
                return 0;
            case "食" :
                return 1;
            case "行" :
                return 2;
            case "学" :
                return 3;
            case "玩" :
                return 4;
            default:
                return 10;
        }
    }

    @Override
    public int getmood() {
        switch (moodtext.getText().toString()){
            case "Happy" :
                return 5;
            case "Sad" :
                return 6;
            case "Excited" :
                return 7;
            case "Other" :
                return 8;
            default:
                return 9;
        }
    }

    @Override
    public String getnote() {
        return notetext.getText().toString();
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
                moodDialog.show();
                break;
            case R.id.type_edit://选择类型
                TypeDialog typeDialog = new TypeDialog(this);
                typeDialog.show();
                break;
            }
        }

    private void getCalendar(){//获取日历时间
        Calendar calendar = Calendar.getInstance();
        Myyear = calendar.get(calendar.YEAR);
        Mymonth = calendar.get(calendar.MONTH);
        Mydate = calendar.get(calendar.DAY_OF_MONTH);
        timetext.setText(calendar.DATE);
        listener = new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
                Myyear = year;
                Mymonth = month;
                Mydate = dayOfMonth;
            }
        };
        datePickerDialog = new DatePickerDialog(this , listener, Myyear ,Mymonth,Mydate);
    }
}

