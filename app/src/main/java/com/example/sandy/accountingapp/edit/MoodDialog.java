package com.example.sandy.accountingapp.edit;

import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.RadioGroup;

import com.example.sandy.accountingapp.R;

public class MoodDialog extends Dialog implements View.OnClickListener,
        RadioGroup.OnCheckedChangeListener {

    private Context mContext;
    private RadioGroup group;
    private Button create;
    private Button cancel;
    private DialogListener.moodListener moodListener;

    public MoodDialog(Context context) {
        super(context);
        mContext = context;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        View view = View.inflate(mContext, R.layout.mooddialog, null);
        setContentView(view);
        setCanceledOnTouchOutside(false);
        init();
    }

    private void init() {
        group = findViewById(R.id.mood_group);
        create = findViewById(R.id.create_mood);
        cancel = findViewById(R.id.cancel_mood);
        create.setOnClickListener(this);
        cancel.setOnClickListener(this);
        group.setOnCheckedChangeListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.create_mood:
                switch (group.getCheckedRadioButtonId()) {
                    case R.id.happy_button:
                        moodListener.setMood("开心");
                        break;
                    case R.id.Sad_button:
                        moodListener.setMood("难过");
                        break;
                    case R.id.Excited_button:
                        moodListener.setMood("兴奋");
                        break;
                    case R.id.Other_button:
                        moodListener.setMood("其他");
                        break;
                }
                dismiss();
                break;
            case R.id.cancel_mood:
                dismiss();
        }

    }

    @Override
    public void onCheckedChanged(RadioGroup group, int checkedId) {

    }

    public void setMoodListener(DialogListener.moodListener listeneer) {
        moodListener = listeneer;
    }


}
