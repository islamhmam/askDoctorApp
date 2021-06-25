package com.elhefny.askdoctor2;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;

import androidx.appcompat.app.AppCompatActivity;

import com.elhefny.askdoctor2.AllFragments.Error_Fragment;

public class Dashboard extends AppCompatActivity {
    final private int waitingTime = 3000;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dashboard);
        if (!NetworkUtil.getConnectivityStatusString(this).equals(getString(R.string.no_internet_connection))) {
            new Handler().postDelayed(new Runnable() {
                @Override
                public void run() {
                    startActivity(new Intent(Dashboard.this, Login_In.class));
                    finish();
                }
            }, waitingTime);
        } else {
            Error_Fragment error_fragment = Error_Fragment.newInstance("No Internet Connection !");
            error_fragment.show(getSupportFragmentManager(), null);
        }
    }
}