package com.example.sandy.accountingapp.list;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.design.widget.NavigationView;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.example.sandy.accountingapp.R;
import com.example.sandy.accountingapp.model.Account;
import com.example.sandy.accountingapp.model.LocalRepo;

import java.util.List;

public class ListActivity extends AppCompatActivity implements ListContract.View, NavigationView.OnNavigationItemSelectedListener {

    private ListContract.Presenter presenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.list_layout);
        initView();
    }

    private void initView() {
        presenter = new ListPresenter(this, LocalRepo.getInstance());
        presenter.fillList();       //填补list已经包括了getIntent
        DrawerLayout drawerLayout = findViewById(R.id.drawer_layout);
        Toolbar toolbar = findViewById(R.id.tool_bar);
        setSupportActionBar(toolbar);
        NavigationView navigationView = findViewById(R.id.nav_view);
        ActionBar actionBar = getSupportActionBar();
        if (actionBar != null) {
            actionBar.setHomeAsUpIndicator(R.drawable.ic_menu_white_24dp);
            actionBar.setDisplayHomeAsUpEnabled(true);
        }
        navigationView.setNavigationItemSelectedListener(this);

    }

    @Override
    public void setRecyclerView(List<Account> list) {
        RecyclerView recyclerView = findViewById(R.id.recycler_view);
        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);
        AccountAdapter adapter = new AccountAdapter(list);
        recyclerView.setAdapter(adapter);
    }

    @Override
    public int getIndexFromIntent() {
        Intent intent = getIntent();
        return intent.getIntExtra(getString(R.string.user_index), 0);
    }

    @Override
    public void onBackPressed() {
        // TODO: 2019/7/1 直接结束我们的app，并且储存好数据
        finish();
    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
        switch (menuItem.getItemId()) {
            case R.id.user_quit:
                Toast.makeText(this, "退出登录成功", Toast.LENGTH_SHORT).show();
                // TODO: 2019/7/1 处理数据，存好后回到登录界面
                finish();
                break;
            case R.id.data_table:
                Toast.makeText(this, "进入数据统计界面", Toast.LENGTH_SHORT).show();
                // TODO: 2019/7/1 进入数据内容
                break;
            case R.id.setting:
                Toast.makeText(this, "进入设置界面", Toast.LENGTH_SHORT).show();
                //todo 进入设置
                break;
        }
        return true;
    }
}
