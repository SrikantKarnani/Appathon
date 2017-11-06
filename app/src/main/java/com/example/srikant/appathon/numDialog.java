package com.example.srikant.appathon;

import android.app.Activity;
import android.app.Dialog;
import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.support.design.widget.Snackbar;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.ScrollView;
import android.widget.TextView;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static com.example.srikant.appathon.R.id.msg;

/**
 * Created by Srikant on 10/10/2017.
 */

public class numDialog extends Dialog {
    EditText et;
    Activity a;
    ProgressBar pg;
    Button bt;
    TextView tv;
    int id;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.numdialog);
        et = findViewById(R.id.numId);
        bt = findViewById(R.id.subNum);
        tv = findViewById(R.id.numMsg);
        pg = findViewById(R.id.progressbar);
        final ApiInterface service = ApiClient.getClient().create(ApiInterface.class);
        et.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {
            }
            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
            }
            @Override
            public void afterTextChanged(Editable editable) {
                if(et.getText()!=null){
                    bt.setVisibility(View.VISIBLE);
                    bt.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View view) {
                            pg.setVisibility(View.VISIBLE);
                                Call<String> response = null;
                                if (id == R.id.advMath) {
                                    response = service.getMath(et.getText().toString());
                                } else if (id == R.id.advTrivia)
                                    response = service.getTrivia(et.getText().toString());
                                else if (id == R.id.advYear) {
                                    et.setHint("Enter a year...");
                                    response = service.getYear(et.getText().toString());
                                }
                                response.enqueue(new Callback<String>() {
                                    @Override
                                    public void onResponse(Call<String> call, Response<String> response) {
                                        pg.setVisibility(View.GONE);
                                        tv.setVisibility(View.VISIBLE);
                                        et.setVisibility(View.GONE);
                                        bt.setVisibility(View.GONE);
                                        tv.setText(response.body().toString());
                                    }

                                    @Override
                                    public void onFailure(Call<String> call, Throwable t) {
                                        t.printStackTrace();
                                    }
                                });
                            }
                    });
                }
            }
        });

    }
    public numDialog(Activity activity,int id) {
        super(activity);
        a = activity;
        this.id = id;
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        dismiss();
    }
}
