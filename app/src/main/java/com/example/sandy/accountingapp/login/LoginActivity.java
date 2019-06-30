package com.example.sandy.accountingapp.login;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.example.sandy.accountingapp.R;

public class LoginActivity extends AppCompatActivity implements LoginContract.View{

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    @Override
    public String getName() {
        return null;
    }

    @Override
    public String getPassWord() {
        return null;
    }

    @Override
    public void loginRemind(boolean isRight) {

    }

    @Override
    public void toEdit(int index) {

    }
}
