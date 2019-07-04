package com.example.sandy.accountingapp.list;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;

import com.example.sandy.accountingapp.R;
import com.example.sandy.accountingapp.dateChart.ChartActivity;
import com.example.sandy.accountingapp.edit.EditActivity;
import com.example.sandy.accountingapp.model.Account;
import com.example.sandy.accountingapp.model.LocalRepo;
import com.example.sandy.accountingapp.setting.SettingActivity;
import com.example.sandy.accountingapp.util.ActivityUtils;

import java.util.List;

public class ListActivity extends AppCompatActivity implements ListContract.View, NavigationView.OnNavigationItemSelectedListener {

    private ListContract.Presenter presenter;

    private DrawerLayout mDrawerLayout;

    private AccountAdapter mAdapter;

    private static final int DOUBLE_CLICK_TIME_SUB = 1000;

    private long PreBackTime;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.list_layout);
        initView();
        ActivityUtils.getInstance().addActivity(this);
    }

    private void initView() {
        presenter = new ListPresenter(this, LocalRepo.getInstance());
        presenter.fillList();       //填补list已经包括了getIntent
        mDrawerLayout = findViewById(R.id.drawer_layout);
        Toolbar toolbar = findViewById(R.id.tool_bar);
        setSupportActionBar(toolbar);
        NavigationView navigationView = findViewById(R.id.nav_view);
        ActionBar actionBar = getSupportActionBar();
        if (actionBar != null) {
            actionBar.setHomeAsUpIndicator(R.drawable.ic_menu_white_24dp);
            actionBar.setDisplayHomeAsUpEnabled(true);
        }
        navigationView.setNavigationItemSelectedListener(this);

        FloatingActionButton addFab = findViewById(R.id.add_fab);
        addFab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                presenter.toEdit();
            }
        });

    }

    @Override
    public void setRecyclerView(List<Account> list) {
        RecyclerView recyclerView = findViewById(R.id.recycler_view);
        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);
        mAdapter = new AccountAdapter(list);
        recyclerView.setAdapter(mAdapter);
    }

    @Override
    public int getIndexFromIntent() {
        Intent intent = getIntent();
        return intent.getIntExtra(getString(R.string.user_index), 0);
    }

    @Override
    public void toEdit() {
        Intent intent = new Intent(this, EditActivity.class);
        intent.putExtra("type", "new");
        startActivity(intent);
    }

    @Override
    public void toDataTable() {
        Intent intent = new Intent(this, ChartActivity.class);
        startActivity(intent);
    }

    @Override
    public void onBackPressed() {
        if (System.currentTimeMillis() - PreBackTime < DOUBLE_CLICK_TIME_SUB){
               ActivityUtils.getInstance().removeAllActivity();
        }else {
                Toast.makeText(this, "再按一次退出应用", Toast.LENGTH_SHORT).show();
                PreBackTime = System.currentTimeMillis();
        }
    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
        switch (menuItem.getItemId()) {
            case R.id.user_quit:
                Toast.makeText(this, "退出登录成功", Toast.LENGTH_SHORT).show();
                finish();
                break;
            case R.id.data_table:
                Toast.makeText(this, "进入数据统计界面", Toast.LENGTH_SHORT).show();
                presenter.toDataTable();
                break;
            case R.id.setting:
                Toast.makeText(this, "进入设置界面", Toast.LENGTH_SHORT).show();
                Intent intent = new Intent(this, SettingActivity.class);
                startActivity(intent);
                break;

        }
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                mDrawerLayout.openDrawer(GravityCompat.START);
                break;
        }
        return true;
    }

    @Override
    protected void onRestart() {
        super.onRestart();
        mAdapter.notifyDataSetChanged();
        //presenter.upDateList();

    }

}
