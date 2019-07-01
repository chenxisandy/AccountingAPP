package com.example.sandy.accountingapp.list;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;

import com.example.sandy.accountingapp.R;
import com.example.sandy.accountingapp.model.Account;

public class ListActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.list_layout);
        initView();
    }

    private void initView() {
//        Intent intent = getIntent();
//        intent.getIntExtra(getString(R.string.user_index), 0);
//
//        Toolbar toolbar = findViewById(R.id.tool_bar);
//        setSupportActionBar(toolbar);
//
//        RecyclerView recyclerView = findViewById(R.id.recycler_view);
    }
}
