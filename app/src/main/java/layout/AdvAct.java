package layout;

import android.app.Activity;
import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.IdRes;
import android.support.annotation.Nullable;
import android.support.design.widget.Snackbar;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.RadioGroup;

import com.example.srikant.appathon.ApiClient;
import com.example.srikant.appathon.ApiInterface;
import com.example.srikant.appathon.CustomDialog;
import com.example.srikant.appathon.R;
import com.example.srikant.appathon.dateDia;
import com.example.srikant.appathon.numDialog;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class AdvAct extends Fragment {

    RadioGroup rg;
    numDialog numDia;
    dateDia dateDia;
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

    public AdvAct() {
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
        // Inflate the  for this fragment
        View view = inflater.inflate(R.layout.fragment_adv, container, false);
        return view;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        rg = view.findViewById(R.id.advRG);
        rg.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup radioGroup, @IdRes int i) {
                if (!isConnected()) {
                    //Show a snackbar if not connected to internet.
                    Snackbar.make(getView(), "Oopss Internet and I are not talking right now  Plz Connect first", Snackbar.LENGTH_LONG).show();
                } else {
                    try {
                        switch (i) {
                            case R.id.advMath:
                                numDia = new numDialog(activity, i);
                                numDia.show();
                                return;

                            case R.id.advTrivia:
                                numDia = new numDialog(activity, i);
                                numDia.show();
                                return;
                            case R.id.advYear:
                                numDia = new numDialog(activity, i);
                                numDia.show();
                                return;
                            case R.id.advDate:
                                dateDia = new dateDia(activity);
                                dateDia.show();
                        }
                    } catch (Exception e) {
                        Log.i(Log_TAG,e.getMessage());
                        e.printStackTrace();
                    }
                }
            }
        });
    }
}
