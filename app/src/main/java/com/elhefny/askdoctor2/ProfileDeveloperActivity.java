package com.elhefny.askdoctor2;

import android.os.Bundle;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;

import com.elhefny.askdoctor2.databinding.ActivityProfileDeveloperBinding;

public class ProfileDeveloperActivity extends AppCompatActivity {
    ActivityProfileDeveloperBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityProfileDeveloperBinding.inflate(getLayoutInflater());
        View v = binding.getRoot();
        setContentView(v);
    }
}