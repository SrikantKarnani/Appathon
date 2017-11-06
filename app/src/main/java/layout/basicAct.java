package layout;

import android.app.Activity;
import android.app.Dialog;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.IdRes;
import android.support.annotation.Nullable;
import android.support.design.widget.Snackbar;
import android.support.v4.app.DialogFragment;
import android.support.v4.app.Fragment;
import android.support.v7.app.AlertDialog;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.example.srikant.appathon.ApiClient;
import com.example.srikant.appathon.ApiInterface;
import com.example.srikant.appathon.CustomDialog;
import com.example.srikant.appathon.MainActivity;
import com.example.srikant.appathon.R;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class basicAct extends Fragment {
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    RadioGroup rg;
    ProgressBar pg;
    Button bt;
    CustomDialog dia;
    private String Log_TAG = basicAct.class.getSimpleName();
    Activity activity;
    //check if mobile is connected to internet or not.
    private boolean isConnected() {
        ConnectivityManager connectivityManager = (ConnectivityManager) activity.getSystemService(Context.CONNECTIVITY_SERVICE);
        if (connectivityManager.getNetworkInfo(ConnectivityManager.TYPE_MOBILE).getState() == NetworkInfo.State.CONNECTED ||
                connectivityManager.getNetworkInfo(ConnectivityManager.TYPE_WIFI).getState() == NetworkInfo.State.CONNECTED) {
            //we are connected to a network
            return  true;
        } else
            return false;
    }
    public basicAct() {
        // Required empty public constructor
    }


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        activity = getActivity();
    }
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the basic fragment layout for this fragment
        View view = inflater.inflate(R.layout.fragment_basic,container,false);
        return view;
    }
    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        rg = view.findViewById(R.id.basicRG);
        bt = view.findViewById(R.id.subRan);
        pg = view.findViewById(R.id.progressbar);
        final ApiInterface service = ApiClient.getClient().create(ApiInterface.class);
        rg.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup radioGroup, @IdRes int i) {
                bt.setVisibility(View.VISIBLE);
            }
        });
        bt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(final View view) {
                if (!isConnected()) {
                    Snackbar.make(view, "Oopss Internet and I are not talking right now  Plz Connect first", Snackbar.LENGTH_LONG).show();
                } else {
                     pg.setVisibility(View.VISIBLE);
                    try {
                        // to get response from the server we create a call variable from retrofit
                        Call<String> response = null;
                        switch (rg.getCheckedRadioButtonId()) {
                            case R.id.basicMath:
                                response = service.getRandomMath();
                                response.enqueue(new Callback<String>() {
                                    @Override
                                    public void onResponse(Call<String> call, Response<String> response) {
                                        Log.i(Log_TAG, response.body().toString());
                                        pg.setVisibility(View.GONE);
                                        dia = new CustomDialog(activity, response.body().toString());
                                        dia.show();
                                    }

                                    @Override
                                    public void onFailure(Call<String> call, Throwable t) {
                                        Log.i(Log_TAG, t.getMessage());
                                    }
                                });
                                return;
                            case R.id.basicTrivia:
                                response = service.getRandomTrivia();
                                response.enqueue(new Callback<String>() {
                                    @Override
                                    public void onResponse(Call<String> call, Response<String> response) {
                                        Log.i(Log_TAG, response.body().toString());
                                        pg.setVisibility(View.GONE);
                                        dia = new CustomDialog(activity, response.body().toString());
                                        dia.show();
                                    }

                                    @Override
                                    public void onFailure(Call<String> call, Throwable t) {
                                        Log.i(Log_TAG, t.getMessage());
                                    }
                                });
                                return;
                            case R.id.basicDate:
                                response = service.getRandomDate();
                                response.enqueue(new Callback<String>() {
                                    @Override
                                    public void onResponse(Call<String> call, Response<String> response) {
                                        Log.i(Log_TAG, response.body().toString());
                                        pg.setVisibility(View.GONE);
                                        dia = new CustomDialog(activity, response.body().toString());
                                        dia.show();
                                    }

                                    @Override
                                    public void onFailure(Call<String> call, Throwable t) {
                                        Log.i(Log_TAG, t.getMessage());
                                    }
                                });
                                return;
                            case R.id.basicYear:
                                response = service.getRandomYear();
                                response.enqueue(new Callback<String>() {
                                    @Override
                                    public void onResponse(Call<String> call, Response<String> response) {
                                        Log.i(Log_TAG, response.body().toString());
                                        pg.setVisibility(View.GONE);
                                        dia = new CustomDialog(activity, response.body().toString());
                                        dia.show();
                                    }

                                    @Override
                                    public void onFailure(Call<String> call, Throwable t) {
                                        Log.i(Log_TAG, t.getMessage());
                                    }
                                });
                                return;
                        }
                    } catch (Exception e) {
                        Log.i(Log_TAG, e.getMessage());
                    }
                }
            }
        });
    }
}
