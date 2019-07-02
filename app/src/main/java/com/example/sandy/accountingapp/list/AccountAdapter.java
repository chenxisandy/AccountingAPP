package com.example.sandy.accountingapp.list;

import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.sandy.accountingapp.R;
import com.example.sandy.accountingapp.edit.EditActivity;
import com.example.sandy.accountingapp.model.Account;

import java.util.List;


public class AccountAdapter extends RecyclerView.Adapter<AccountAdapter.ViewHolder>{

    private List<Account> accountList;

    private Context mContext;

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        if (mContext == null) {
            mContext = viewGroup.getContext();
        }
        View view = LayoutInflater.from(mContext).inflate(R.layout.item, viewGroup, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder viewHolder, final int i) {
        Account account = accountList.get(i);

        if (!account.isSignal()) {  //如果金额为负
            viewHolder.textMoney.setText("-" + account.getMoney() + "元");
        } else {
            viewHolder.textMoney.setText(account.getMoney() + "元");
        }

        viewHolder.textTime.setText(account.getYear() + "年" + account.getMonth() + "月" + account.getDay() + "日");

        switch (account.getType()) {
            case Account.CLOTH:
                viewHolder.typeImg.setImageResource(R.drawable.clothe);
                break;
            case Account.EAT:
                viewHolder.typeImg.setImageResource(R.drawable.eat);
                break;
            case Account.GO:
                viewHolder.typeImg.setImageResource(R.drawable.car);
                break;
            case Account.STUDY:
                viewHolder.typeImg.setImageResource(R.drawable.book);
                break;
            case Account.PLAY:
                viewHolder.typeImg.setImageResource(R.drawable.play);
                break;
        }

        viewHolder.cardView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(mContext, EditActivity.class);
                intent.putExtra("accountIndex", i);
                mContext.startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() {
        return accountList.size();
    }

    static class ViewHolder extends RecyclerView.ViewHolder{

        CardView cardView;

        TextView textMoney;

        TextView textTime;

        ImageView typeImg;

        ViewHolder(@NonNull View itemView) {
            super(itemView);
            cardView = itemView.findViewById(R.id.card_view);
            textMoney = itemView.findViewById(R.id.money_text);
            textTime = itemView.findViewById(R.id.time_text);
            typeImg = itemView.findViewById(R.id.type_img);
        }
    }

    public AccountAdapter(List<Account> accountList) {
        this.accountList = accountList;
    }
}
