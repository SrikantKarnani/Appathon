package com.example.srikant.appathon;

import android.app.Activity;
import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.view.Window;
import android.widget.Button;
import android.widget.TextView;

/**
 * Created by Srikant on 10/10/2017.
 */

public class CustomDialog extends Dialog {
    String msg;
    TextView tv;
    Activity a;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.msgdialog);
        tv = findViewById(R.id.msg);
        tv.setText(msg);
    }

    public CustomDialog(Activity activity, String msg) {
        super(activity);
        a = activity;
        this.msg = msg;
    }
}
