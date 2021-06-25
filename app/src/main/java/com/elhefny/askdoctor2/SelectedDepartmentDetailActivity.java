package com.elhefny.askdoctor2;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.recyclerview.widget.StaggeredGridLayoutManager;

import com.elhefny.askdoctor2.DepartmentsRecyclerClasses.DepartmentDetails;
import com.elhefny.askdoctor2.Doctors.DepartmentDoctorsAdapter;
import com.elhefny.askdoctor2.Doctors.Doctor;
import com.elhefny.askdoctor2.Patients.DepartmentsPublicQuestionsAdapter;
import com.elhefny.askdoctor2.Patients.PublicQuestion;
import com.elhefny.askdoctor2.databinding.ActivitySelectedDepartmentDetailBinding;
import com.firebase.ui.firestore.FirestoreRecyclerOptions;
import com.google.android.material.appbar.CollapsingToolbarLayout;
import com.google.android.material.snackbar.Snackbar;
import com.google.android.material.textview.MaterialTextView;
import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.Query;

public class SelectedDepartmentDetailActivity extends AppCompatActivity {
    private FirebaseFirestore fs = FirebaseFirestore.getInstance();
    private CollectionReference doctorRef;
    private CollectionReference publicQuestionsRef;
    private ActivitySelectedDepartmentDetailBinding binding;
    private DepartmentDetails currentDepartment;
    private MaterialTextView tv_description;
    private RecyclerView rv_doctors, rv_public_messages;
    private DepartmentDoctorsAdapter adapter;
    private DepartmentsPublicQuestionsAdapter adapter2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivitySelectedDepartmentDetailBinding.inflate(getLayoutInflater());
        View v = binding.getRoot();
        setContentView(v);
        initializeViews();
        setRecyclerAdapter();
        setSupportActionBar(binding.toolbar);
    }

    private void setRecyclerAdapter() {
        Query query = doctorRef.orderBy("yearsOfExperience", Query.Direction.ASCENDING);
        FirestoreRecyclerOptions options = new FirestoreRecyclerOptions.Builder<Doctor>()
                .setQuery(query, Doctor.class)
                .build();
        adapter = new DepartmentDoctorsAdapter(this, new DepartmentDoctorsAdapter.DoctorInterface() {
            @Override
            public void getClickedDoctor(Doctor clickedDoctor) {
                Intent i = new Intent(SelectedDepartmentDetailActivity.this, DoctorProfile.class);
                i.putExtra(getString(R.string.SentDoctor), clickedDoctor);
                startActivity(i);
            }
        }, options);
        rv_doctors.setHasFixedSize(true);
        rv_doctors.setLayoutManager(new LinearLayoutManager(this, RecyclerView.HORIZONTAL, false));
        rv_doctors.setAdapter(adapter);


        Query query2 = publicQuestionsRef.orderBy("date", Query.Direction.DESCENDING);
        FirestoreRecyclerOptions options2 = new FirestoreRecyclerOptions.Builder<PublicQuestion>()
                .setQuery(query2, PublicQuestion.class)
                .build();
        adapter2 = new DepartmentsPublicQuestionsAdapter(this, options2);
        rv_public_messages.setHasFixedSize(true);
        rv_public_messages.setLayoutManager(new StaggeredGridLayoutManager(1, StaggeredGridLayoutManager.VERTICAL));


        rv_public_messages.setAdapter(adapter2);
    }

    private void initializeViews() {
        currentDepartment = (DepartmentDetails) getIntent().getSerializableExtra(getString(R.string.sendDepartmentDetails));
        tv_description = findViewById(R.id.content_scroll_department_description);
        rv_doctors = findViewById(R.id.content_scroll_doctors_rv);
        rv_public_messages = findViewById(R.id.content_scroll_department_questions);
        tv_description.setText(currentDepartment.getDepartmentDescription().trim());
        doctorRef = fs.collection(currentDepartment.getDepartmentName());
        publicQuestionsRef = fs.collection(currentDepartment.getDepartmentName() + " " + getString(R.string.publicQuestions));
        binding.departmentDetailActivityIv.setImageResource(currentDepartment.getDepartmentImageID());
        CollapsingToolbarLayout toolBarLayout = (CollapsingToolbarLayout) findViewById(R.id.toolbar_layout);
        toolBarLayout.setTitle(currentDepartment.getDepartmentName());
        binding.departmentDetailActivityFloatingActionButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });
    }

    @Override
    protected void onStart() {
        super.onStart();
        adapter.startListening();
        adapter2.startListening();
    }

    @Override
    protected void onStop() {
        super.onStop();
        adapter.stopListening();
        adapter2.stopListening();
    }
}