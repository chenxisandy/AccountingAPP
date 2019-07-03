package com.example.sandy.accountingapp.setting;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.Switch;
import android.widget.TextView;
import android.widget.Toast;

import com.example.sandy.accountingapp.R;
import com.example.sandy.accountingapp.model.LocalRepo;

public class SettingActivity extends AppCompatActivity implements SettingContract.View {

    private TextView dayMax;
    private TextView weekMax;
    private TextView monthMax;
    private EditText dayMaxEdit;
    private EditText weekMaxEdit;
    private EditText monthMaxEdit;
    private Switch maxWarning;
    private Button createSetting;
    private SettingPresenter settingPresenter;
    private boolean warningSignal;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_setting);
        initView();
    }


    public void initView(){
        dayMax = findViewById(R.id.day_max);
        weekMax = findViewById(R.id.week_max);
        monthMax = findViewById(R.id.month_max);
        dayMaxEdit = findViewById(R.id.day_max_edit);
        weekMaxEdit = findViewById(R.id.week_max_edit);
        monthMaxEdit = findViewById(R.id.month_max_edit);
        maxWarning = findViewById(R.id.max_warning);
        createSetting = findViewById(R.id.create_setting);
        settingPresenter = new SettingPresenter(this, LocalRepo.getInstance());
        maxWarning.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked){
                    dayMax.setVisibility(View.VISIBLE);
                    weekMax.setVisibility(View.VISIBLE);
                    monthMax.setVisibility(View.VISIBLE);
                    dayMaxEdit.setVisibility(View.VISIBLE);
                    weekMaxEdit.setVisibility(View.VISIBLE);
                    monthMaxEdit.setVisibility(View.VISIBLE);
                    createSetting.setVisibility(View.VISIBLE);
                    warningSignal = true;//展示界面
                    settingPresenter.changeWarningSignal(true);
                }else {
                    dayMax.setVisibility(View.INVISIBLE);
                    weekMax.setVisibility(View.INVISIBLE);
                    monthMax.setVisibility(View.INVISIBLE);
                    dayMaxEdit.setVisibility(View.INVISIBLE);
                    weekMaxEdit.setVisibility(View.INVISIBLE);
                    monthMaxEdit.setVisibility(View.INVISIBLE);
                    createSetting.setVisibility(View.INVISIBLE);
                    warningSignal = false;//隐藏界面
                    settingPresenter.changeWarningSignal(false);
                }
            }
        });
        createSetting.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                settingPresenter.setUserMax();//保存设置
                Toast.makeText(SettingActivity.this, "设置成功", Toast.LENGTH_SHORT)
                        .show();
            }
        });
        warningSignal = settingPresenter.isWarningSignal();
        if (warningSignal == true){
            maxWarning.setChecked(true);
            settingPresenter.setMaxFromUser();
        }
    }


    @Override
    public void setDayMax(String s) {
        dayMaxEdit.setText(s);
    }

    @Override
    public String getDayMax() {
        return dayMaxEdit.getText().toString();
    }

    @Override
    public void setWeekMax(String s) {
        weekMaxEdit.setText(s);
    }

    @Override
    public String getWeekMax() {
        return weekMaxEdit.getText().toString();
    }

    @Override
    public void setMonthMax(String s) {
        monthMaxEdit.setText(s);
    }

    @Override
    public String getMonthMAx() {
        return monthMaxEdit.getText().toString();
    }
}
