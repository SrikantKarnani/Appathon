package com.example.srikant.appathon;

import android.app.Activity;
import android.app.DatePickerDialog;
import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.annotation.RequiresApi;
import android.support.design.widget.Snackbar;
import android.text.Editable;
import android.text.InputType;
import android.text.TextWatcher;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Locale;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static android.R.attr.editable;
import static android.R.attr.id;

/**
 * Created by Srikant on 10/11/2017.
 */

public class dateDia extends Dialog {
    EditText et;
    EditText etD;
    Activity a;
    Button bt;
    ProgressBar pg;
    TextView tv;
    Calendar myCalendar;
    TextView dateF;
    TextView digitF;
    LinearLayout ll;
    CustomerDatePickerDialog mDialog;
    SimpleDateFormat sdf;
    CustomerDatePickerDialog.OnDateSetListener lis;
    @RequiresApi(api = Build.VERSION_CODES.N)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.datedialog);
        et = findViewById(R.id.dateId);
        etD = findViewById(R.id.numDat);
        bt = findViewById(R.id.subDate);
        tv = findViewById(R.id.dateMsg);
        ll = findViewById(R.id.option);
        pg = findViewById(R.id.progressbar);
        dateF = findViewById(R.id.dateF);
        digitF = findViewById(R.id.digitF);
        final ApiInterface service = ApiClient.getClient().create(ApiInterface.class);
        sdf = new SimpleDateFormat("MM/dd", Locale.ENGLISH);
        myCalendar = Calendar.getInstance();
        dateF.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ll.setVisibility(View.GONE);
                et.setVisibility(View.VISIBLE);
            }
        });
        digitF.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ll.setVisibility(View.GONE);
                etD.setVisibility(View.VISIBLE);
            }
        });

        et.setText(sdf.format(myCalendar.getTime()));
        etD.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {
            }
            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
            }
            @Override
            public void afterTextChanged(Editable editable) {
                if(etD.getText()!=null){
                    bt.setVisibility(View.VISIBLE);
                    bt.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View view) {
                            pg.setVisibility(View.VISIBLE);
                            Call<String> response = null;
                                response = service.getDate(etD.getText().toString());
                            response.enqueue(new Callback<String>() {
                                @Override
                                public void onResponse(Call<String> call, Response<String> response) {
                                    tv.setVisibility(View.VISIBLE);
                                    pg.setVisibility(View.GONE);
                                    etD.setVisibility(View.GONE);
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
        lis = new CustomerDatePickerDialog.OnDateSetListener(){
            @Override
            public void onDateSet(DatePicker view, int year, int monthOfYear,
                                  int dayOfMonth) {
                // TODO Auto-generated method stub
                myCalendar.set(Calendar.YEAR, year);
                myCalendar.set(Calendar.MONTH, monthOfYear);
                myCalendar.set(Calendar.DAY_OF_MONTH, dayOfMonth);
                et.setText(sdf.format(myCalendar.getTime()));
            }
        };
        et.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mDialog = new CustomerDatePickerDialog(getContext(), lis, myCalendar
                        .get(Calendar.YEAR), myCalendar.get(Calendar.MONTH),
                        myCalendar.get(Calendar.DAY_OF_MONTH));
                mDialog.show();
                DatePicker dp = findDatePicker((ViewGroup) mDialog.getWindow().getDecorView());
                if (dp != null) {
                    ((ViewGroup) dp.getChildAt(0)).getChildAt(0).setVisibility(View.GONE);
                }
            }
        });
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
                                response = service.getDate(et.getText().toString());
                                response.enqueue(new Callback<String>() {
                                    @Override
                                    public void onResponse(Call<String> call, Response<String> response) {
                                        tv.setVisibility(View.VISIBLE);
                                        pg.setVisibility(View.GONE);
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
    public dateDia(Activity activity) {
        super(activity);
        a = activity;
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        dismiss();
    }
    private DatePicker findDatePicker(ViewGroup group) {
        if (group != null) {
            for (int i = 0, j = group.getChildCount(); i < j; i++) {
                View child = group.getChildAt(i);
                if (child instanceof DatePicker) {
                    return (DatePicker) child;
                } else if (child instanceof ViewGroup) {
                    DatePicker result = findDatePicker((ViewGroup) child);
                    if (result != null)
                        return result;
                }
            }
        }
        return null;
    }
    class CustomerDatePickerDialog extends DatePickerDialog {
        public CustomerDatePickerDialog(Context context, OnDateSetListener callBack, int year, int monthOfYear, int dayOfMonth) {
            super(context, callBack, year, monthOfYear, dayOfMonth);
        }
        @Override
        public void onDateChanged(DatePicker view, int year, int month, int day) {
            super.onDateChanged(view, year, month, day);
        }
        @Override
        public void setOnDateSetListener(@Nullable OnDateSetListener listener) {
            super.setOnDateSetListener(listener);
        }
    }
}

