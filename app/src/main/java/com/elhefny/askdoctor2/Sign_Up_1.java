package com.elhefny.askdoctor2;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.elhefny.askdoctor2.AllFragments.Error_Fragment;
import com.elhefny.askdoctor2.AllFragments.Wait_Fragment;
import com.elhefny.askdoctor2.Patients.Patient;
import com.elhefny.askdoctor2.databinding.ActivitySignUp1Binding;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.FirebaseFirestore;

public class Sign_Up_1 extends AppCompatActivity {
    ActivitySignUp1Binding binding;
    private boolean is_Doctor = false;
    private FirebaseAuth mAuth = FirebaseAuth.getInstance();
    FirebaseFirestore db = FirebaseFirestore.getInstance();
    CollectionReference collectionReference = db.collection("all patients".toUpperCase());
    public static final String newPatientTag = "Current Patient";
    public static final String passwordTag = "Current Password";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivitySignUp1Binding.inflate(getLayoutInflater());
        View v = binding.getRoot();
        setContentView(v);
        getSentData();
    }

    private void getSentData() {
        Intent i = getIntent();
        is_Doctor = i.getBooleanExtra(Sign_Up_0.is_Doctor_TAG, false);
        if (is_Doctor) {
            binding.signUp2Btn.setText("Last Page");
            binding.signUp1Tv1.setText("Sign Up Page 2 of 3");
        }
    }

    public void Sign_Up(View view) {
        Wait_Fragment wait = Wait_Fragment.newInstance("Loading");
        wait.show(getSupportFragmentManager(), null);
        if (TextUtils.isEmpty(binding.signUp1TextInputAge.getText()) && Integer.parseInt(binding.signUp1TextInputAge.getText().toString()) >= 18) {
            binding.signUp1TextInputAge.setError("Required More Than 17");
            binding.signUp1TextInputAge.requestFocus();
            wait.dismiss();
        } else if (TextUtils.isEmpty(binding.signUp1TextInputPassword.getText()) && binding.signUp1TextInputPassword.getText().toString().trim().length() <= 6) {
            binding.signUp1TextInputPassword.setError("Required More Than 5 Characters");
            binding.signUp1TextInputPassword.requestFocus();
            wait.dismiss();
        } else if (TextUtils.isEmpty(binding.signUp1TextInputPhone.getText()) && binding.signUp1TextInputPhone.getText().toString().trim().length() <= 6) {
            binding.signUp1TextInputPhone.setError("Required Phone Number");
            binding.signUp1TextInputPhone.requestFocus();
        } else {
            Patient newPatient = new Patient(getIntent().getStringExtra(Sign_Up_0.email_TAG),
                    getIntent().getStringExtra(Sign_Up_0.name_TAG),
                    binding.countryPicker.getSelectedCountryName(),
                    binding.signUp1SpinnerGender.getSelectedItem().toString(),
                    "No Image Yet",
                    Integer.parseInt(binding.signUp1TextInputAge.getText().toString()),
                    binding.signUp1TextInputPhone.getText().toString().trim()
            );

            if (!is_Doctor) {
                mAuth.createUserWithEmailAndPassword(newPatient.getEmail(), binding.signUp1TextInputPassword.getText().toString().trim())
                        .addOnSuccessListener(new OnSuccessListener<AuthResult>() {
                            @Override
                            public void onSuccess(AuthResult authResult) {
                                collectionReference.add(newPatient);
                                startActivity(new Intent(Sign_Up_1.this, Login_In.class));
                                finish();
                            }
                        })
                        .addOnFailureListener(new OnFailureListener() {
                            @Override
                            public void onFailure(@NonNull Exception e) {
                                wait.dismiss();
                                Error_Fragment error_fragment = Error_Fragment.newInstance(e.getMessage());
                                error_fragment.show(getSupportFragmentManager(), null);
                            }
                        });
            } else {
                Intent i = new Intent(Sign_Up_1.this, Sign_Up_2.class);
                i.putExtra(newPatientTag, newPatient);
                i.putExtra(passwordTag, binding.signUp1TextInputPassword.getText().toString().trim());
                startActivity(i);
            }
        }
    }
}