package com.example.sandy.accountingapp.login;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Build;
import android.support.annotation.NonNull;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.sandy.accountingapp.R;
import com.example.sandy.accountingapp.list.ListActivity;
import com.example.sandy.accountingapp.model.LocalRepo;
import com.example.sandy.accountingapp.util.ActivityUtils;

import org.litepal.LitePal;

import java.util.jar.Pack200;

public class LoginActivity extends AppCompatActivity implements LoginContract.View , View.OnClickListener {

    private EditText account;
    private EditText password;
    private Button signin;
    private LoginPresenter mpresenter;
    private static String[] PERMISSIONS_STORAGE = {
            Manifest.permission.READ_EXTERNAL_STORAGE,
            Manifest.permission.WRITE_EXTERNAL_STORAGE};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        signin = findViewById(R.id.Sign_in_or_register);
        account = findViewById(R.id.account);
        password = findViewById(R.id.password);
//        if (Build.VERSION.SDK_INT > Build.VERSION_CODES.LOLLIPOP) {
//            if (ActivityCompat.checkSelfPermission(this, Manifest.permission.
//                    WRITE_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED) {
//                ActivityCompat.requestPermissions(this, PERMISSIONS_STORAGE,1);
//            }
//        }
        LitePal.getDatabase();
        mpresenter = new LoginPresenter(this, LocalRepo.getInstance());
        signin.setOnClickListener(this);
        ActivityUtils.getInstance().addActivity(this);
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
                if (account.getText().toString().length() == 0) {
                    Toast.makeText(this, "请输入用户名", Toast.LENGTH_SHORT).show();
                } else if (password.getText().toString().length() >= 3) {
                    mpresenter.LoginIn();
                } else {
                    Toast.makeText(this, "密码必须大于3位", Toast.LENGTH_SHORT).show();
                }
                break;
        }
    }

//    @Override
//    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions,
//                                           @NonNull int[] grantResults) {
//        switch (requestCode){
//            case 1:
//                if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED){
//                    Toast.makeText(this, "权限获取成功", Toast.LENGTH_SHORT).show();
//                }
//                break;
//            default:
//                break;
//        }
//    }

}
