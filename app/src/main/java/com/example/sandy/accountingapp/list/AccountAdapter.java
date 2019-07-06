package com.example.sandy.accountingapp.list;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Color;
import android.os.Handler;
import android.os.Message;
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
import com.example.sandy.accountingapp.model.LocalRepo;

import java.util.List;


public class AccountAdapter extends RecyclerView.Adapter<AccountAdapter.ViewHolder> implements ItemTouchHelperCallback.OnItemTouchListener{

    private List<Account> accountList;

    private Context mContext;

    public static final int QUIT_DELETE = 1;



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
            viewHolder.textMoney.setTextColor(Color.RED);
            switch (account.getType()) {
                case Account.CLOTH:
                    viewHolder.typeImg.setImageResource(R.drawable.cloth);
                    break;
                case Account.EAT:
                    viewHolder.typeImg.setImageResource(R.drawable.eat);
                    break;
                case Account.GO:
                    viewHolder.typeImg.setImageResource(R.drawable.go);
                    break;
                case Account.STUDY:
                    viewHolder.typeImg.setImageResource(R.drawable.study);
                    break;
                case Account.PLAY:
                    viewHolder.typeImg.setImageResource(R.drawable.play);
                    break;
            }
        } else {
            viewHolder.textMoney.setText("+" + account.getMoney() + "元");
            viewHolder.textMoney.setTextColor(Color.GREEN);
            switch (account.getType()) {
                case Account.WAGES:
                    viewHolder.typeImg.setImageResource(R.drawable.wages);
                    break;
                case Account.GIFT:
                    viewHolder.typeImg.setImageResource(R.drawable.gift);
                    break;
                case Account.FINANCIAL_MANAGEMENT:
                    viewHolder.typeImg.setImageResource(R.drawable.management);
                    break;
                case Account.ELSE:
                    viewHolder.typeImg.setImageResource(R.drawable.other);
                    break;
            }
        }

        viewHolder.textTime.setText(account.getYear() + "年" + account.getMonth() + "月" + account.getDay() + "日");




        viewHolder.cardView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(mContext, EditActivity.class);
                intent.putExtra("accountIndex", i);
                intent.putExtra("type", "old");
                mContext.startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() {
        return accountList.size();
    }

    @Override
    public void onSwiped(final int position) {
        AlertDialog.Builder dialog = new AlertDialog.Builder(mContext);
        dialog.setTitle("确认删除")
                .setMessage("是否要删除这个账单？")
                .setPositiveButton("确定", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        accountList.remove(position);
//        LocalRepo.getInstance().getUserList().get(LocalRepo.getInstance().getCurrentIndexOfUser()).getAccountList().remove(position);
                        notifyItemRemoved(position);
                    }
                })
                .setNegativeButton("取消", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.dismiss();
                        Intent intent = new Intent(mContext, ListActivity.class);
                        intent.putExtra("userIndex", LocalRepo.getInstance().getCurrentIndexOfUser());
                        mContext.startActivity(intent);
                    }
                })
                .show();
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
