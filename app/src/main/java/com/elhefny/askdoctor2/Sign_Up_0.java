package com.elhefny.askdoctor2;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.RadioGroup;

import androidx.appcompat.app.AppCompatActivity;

import com.elhefny.askdoctor2.databinding.ActivitySignUp0Binding;

public class Sign_Up_0 extends AppCompatActivity {
    ActivitySignUp0Binding binding;
    public static String email_TAG = "Email";
    public static String name_TAG = "Name";
    public static String is_Doctor_TAG = "Is_A_Doctor";
    private boolean is_Doctor = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivitySignUp0Binding.inflate(getLayoutInflater());
        View v = binding.getRoot();
        setContentView(v);
        binding.signUp0RadioGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                if (checkedId == R.id.sign_up_0_doctor) {
                    binding.signUp0Tv1.setText("Sign Up Page 1 of 3");
                    is_Doctor = true;
                } else {
                    binding.signUp0Tv1.setText("Sign Up Page 1 of 2");
                    is_Doctor = false;
                }
            }
        });
    }

    public void openNextSignUpActivity(View view) {
        if (TextUtils.isEmpty(binding.signUp0TextInputEditTextEmail.getText())) {
            binding.signUp0TextInputEditTextEmail.setError("Required");
            binding.signUp0TextInputEditTextEmail.requestFocus();
        } else if (TextUtils.isEmpty(binding.signUpTextInputEditTextPersonName.getText())) {
            binding.signUpTextInputEditTextPersonName.setError("Required");
            binding.signUpTextInputEditTextPersonName.requestFocus();
        } else {
            if (checkEmail(binding.signUp0TextInputEditTextEmail.getText().toString().trim())) {
                Intent i = new Intent(this, Sign_Up_1.class);
                i.putExtra(email_TAG, binding.signUp0TextInputEditTextEmail.getText().toString().trim());
                i.putExtra(name_TAG, binding.signUpTextInputEditTextPersonName.getText().toString().trim());
                i.putExtra(is_Doctor_TAG, is_Doctor);
                startActivity(i);
            } else {
                binding.signUp0TextInputEditTextEmail.setError("Error Format");
                binding.signUp0TextInputEditTextEmail.requestFocus();
            }
        }
    }

    private boolean checkEmail(String trim) {
        String EMAIL_REGEX = "^[\\w-_\\.+]*[\\w-_\\.]\\@([\\w]+\\.)+[\\w]+[\\w]$";
        return trim.matches(EMAIL_REGEX);
    }


}