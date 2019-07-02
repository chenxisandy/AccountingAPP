package com.example.sandy.accountingapp.Edit;

import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;

import com.example.sandy.accountingapp.R;

public class TypeDialog extends Dialog implements View.OnClickListener ,
        RadioGroup.OnCheckedChangeListener {

    private Context mcontext;
    private RadioGroup group;
    private Button create;
    private Button cancel;
    private EditText text;



    public TypeDialog(Context context) {
        super(context);
        mcontext = context;

    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        View view = View.inflate(mcontext,R.layout.typedialog_item,null);
        setContentView(view);
        setCanceledOnTouchOutside(false);//点击外侧无法取消
        init();
    }

    private void init(){
        create = findViewById(R.id.create_type);
        cancel = findViewById(R.id.cancel_type);
        create.setOnClickListener(this);
        cancel.setOnClickListener(this);
        group = findViewById(R.id.group);
        text = findViewById(R.id.type_edit);
        group.setOnCheckedChangeListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.create_type:
                switch (group.getCheckedRadioButtonId()){
                    case R.id.box_clothes:
                        text.setText("衣");
                        break;
                    case R.id.box_eat:
                        text.setText("食");
                        break;
                    case R.id.box_go:
                        text.setText("行");
                        break;
                    case R.id.box_study:
                        text.setText("学");
                        break;
                    case R.id.box_play:
                        text.setText("玩");
                        break;
                }
                dismiss();
                break;
            case R.id.cancel_type:
                dismiss();
                break;

        }
    }

    @Override
    public void onCheckedChanged(RadioGroup group, int checkedId) {

    }
}