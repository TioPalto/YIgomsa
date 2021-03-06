package com.example.testapplication.view;

import android.app.Dialog;
import android.content.Context;
import android.text.TextUtils;
import android.view.Gravity;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;

import androidx.appcompat.widget.AppCompatTextView;
import com.example.testapplication.R;

public class MsgCenterDialog extends Dialog implements View.OnClickListener {
    private String title;
    private String content;
    private String txtCancel, txtDefine;

    public MsgCenterDialog(Context context, String title, String content,
                           String txtCancel, String txtDefine) {
        super(context, R.style.dialog_pickerview);
        setContentView(R.layout.dialog_center_msg);
        setCanceledOnTouchOutside(true);
        Window window = getWindow();
        if (window != null) {
            window.setWindowAnimations(R.style.picker_dialog_anim);
            window.setLayout(WindowManager.LayoutParams.MATCH_PARENT,
                    WindowManager.LayoutParams.WRAP_CONTENT);
            window.setGravity(Gravity.CENTER);
        }
        this.title = title;
        this.content = content;
        this.txtCancel = txtCancel;
        this.txtDefine = txtDefine;
        initView();
    }

    private void initView() {
        AppCompatTextView txtTitle = findViewById(R.id.dialog_title);
        AppCompatTextView txtContent = findViewById(R.id.dialog_content);
        AppCompatTextView txtClickCancel = findViewById(R.id.txt_click_cancel);
        AppCompatTextView txtClickDefine = findViewById(R.id.txt_click_define);
        txtClickCancel.setOnClickListener(this);
        txtClickDefine.setOnClickListener(this);
        if(TextUtils.isEmpty(title)) {
            txtTitle.setVisibility(View.GONE);
        } else {
            txtTitle.setVisibility(View.VISIBLE);
        }
        txtContent.setText(content);
        if(!TextUtils.isEmpty(txtCancel)) {
            txtClickCancel.setText(txtCancel);
        }
        if(!TextUtils.isEmpty(txtDefine)) {
            txtClickDefine.setText(txtDefine);
        }
    }
    @Override
    public void onClick(View v) {
        if(v.getId() == R.id.txt_click_cancel) {
            if(mDialogClickListener != null) {
                mDialogClickListener.onCancelClick(v);
            }
        } else if(v.getId()== R.id.txt_click_define) {
            if(mDialogClickListener != null) {
                mDialogClickListener.onDefineClick(v);
            }
        }
    }
    public interface onDialogClickListener {
        void onCancelClick(View v);
        void onDefineClick(View v);
    }
    private onDialogClickListener mDialogClickListener;

    public void setDialogClickListener(onDialogClickListener dialogClickListener) {
        this.mDialogClickListener = dialogClickListener;
    }

}
