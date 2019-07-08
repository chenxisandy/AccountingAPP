package com.example.sandy.accountingapp.edit;

import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.RadioGroup;

import com.example.sandy.accountingapp.R;


public class IncomeDialog extends Dialog implements View.OnClickListener {

    private Context context;
    private RadioGroup group;
    private Button create;
    private Button cancel;
    private DialogListener.incomeListener incomeListener;

    public IncomeDialog(Context context) {
        super(context);
        this.context = context;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        View view = View.inflate(context, R.layout.incomedialog, null);
        setContentView(view);
        setCanceledOnTouchOutside(false);
        init();
    }

    private void init() {
        group = findViewById(R.id.group);
        create = findViewById(R.id.create_income_type);
        cancel = findViewById(R.id.cancel_income_type);
        create.setOnClickListener(this);
        cancel.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.cancel_income_type:
                dismiss();
                break;
            case R.id.create_income_type:
                switch (group.getCheckedRadioButtonId()) {
                    case R.id.box_wages:
                        incomeListener.setIncomeType("工资");
                        break;
                    case R.id.box_gift:
                        incomeListener.setIncomeType("礼物");
                        break;
                    case R.id.box_manage:
                        incomeListener.setIncomeType("理财");
                        break;
                    case R.id.box_else:
                        incomeListener.setIncomeType("其他");
                        break;
                }
                dismiss();
                break;
        }

    }

    public void setIncomeListener(DialogListener.incomeListener listener) {
        incomeListener = listener;
    }

}
