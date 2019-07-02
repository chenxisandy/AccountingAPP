package com.example.sandy.accountingapp.login;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.sandy.accountingapp.R;
import com.example.sandy.accountingapp.list.ListActivity;
import com.example.sandy.accountingapp.model.LocalRepo;

public class LoginActivity extends AppCompatActivity implements LoginContract.View , View.OnClickListener {

    private EditText account;
    private EditText password;
    private Button signin;
    private LoginPresenter mpresenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        signin = findViewById(R.id.Sign_in_or_register);
        account = findViewById(R.id.account);
        password = findViewById(R.id.password);
        mpresenter = new LoginPresenter(this, LocalRepo.getInstance());
        signin.setOnClickListener(this);
    }

    @Override
    public String getName() {
        return account.getText().toString();
    }

    @Override
    public String getPassWord() {
        return password.getText().toString();
    }

    @Override
    public void loginRemind(boolean isRight) {
       if (isRight == true){
           Toast.makeText(this, "登陆成功", Toast.LENGTH_SHORT).show();
       }else {
           Toast.makeText(this, "登陆失败，请检查账号密码", Toast.LENGTH_SHORT).show();
       }
    }

    @Override
    public void toList(int index) {
        Intent intent = new Intent(this, ListActivity.class);
        intent.putExtra(getString(R.string.user_index), index);
        startActivity(intent);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.Sign_in_or_register:
                mpresenter.LoginIn();
                break;
        }
    }
}
