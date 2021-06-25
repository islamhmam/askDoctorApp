package com.elhefny.askdoctor2;

import android.os.Bundle;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;

import com.amulyakhare.textdrawable.TextDrawable;
import com.amulyakhare.textdrawable.util.ColorGenerator;
import com.elhefny.askdoctor2.Doctors.Doctor;
import com.elhefny.askdoctor2.databinding.ActivityDoctorProfileBinding;

public class DoctorProfile extends AppCompatActivity {
    ActivityDoctorProfileBinding binding;
    Doctor sentDoctor;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityDoctorProfileBinding.inflate(getLayoutInflater());
        View v = binding.getRoot();
        setContentView(v);
        sentDoctor = (Doctor) getIntent().getSerializableExtra(getString(R.string.SentDoctor));
        buildDoctorProfile(sentDoctor);
    }

    private void buildDoctorProfile(Doctor sentDoctor) {
        binding.profileDoctorTvName.setText(sentDoctor.getName());
        binding.profileDoctorCardEmailTv.setText(sentDoctor.getEmail());
        binding.profileDoctorCardPhoneTv.setText(sentDoctor.getPhone());
        binding.profileDoctorCardYearsOfExperienceTv.setText("Years Of Experience : " + sentDoctor.getYearsOfExperience());
        setDoctorImage(sentDoctor.getName());
    }

    private void setDoctorImage(String name) {
        ColorGenerator generator = ColorGenerator.MATERIAL;
        int color1 = generator.getRandomColor();
        TextDrawable.IBuilder builder = TextDrawable.builder()
                .beginConfig()
                .withBorder(4)
                .endConfig()
                .round();
        TextDrawable ic1 = builder.build(name.trim().toUpperCase().substring(0, 1), color1);
        binding.profileDoctorIv.setImageDrawable(ic1);
    }

    public void ShowPrivateMessages(View view) {
    }
}