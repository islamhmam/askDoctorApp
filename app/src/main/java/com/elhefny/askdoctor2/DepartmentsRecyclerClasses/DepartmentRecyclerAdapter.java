package com.elhefny.askdoctor2.DepartmentsRecyclerClasses;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.elhefny.askdoctor2.R;
import com.google.android.material.textview.MaterialTextView;

import java.util.ArrayList;

public class DepartmentRecyclerAdapter extends RecyclerView.Adapter<DepartmentRecyclerAdapter.departmentViewHolder> {
    Context context;
    ArrayList<DepartmentDetails> departmentDetails;
    onDepartmentClicked listener;

    public DepartmentRecyclerAdapter(Context context, ArrayList<DepartmentDetails> departmentDetails, onDepartmentClicked listener) {
        this.context = context;
        this.departmentDetails = departmentDetails;
        this.listener = listener;
    }

    @NonNull
    @Override
    public departmentViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new departmentViewHolder(LayoutInflater.from(context).inflate(R.layout.recycler_departments_design, parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull departmentViewHolder holder, int position) {
        DepartmentDetails currentDepartment = departmentDetails.get(position);
        holder.itemView.setAnimation(AnimationUtils.loadAnimation(context, android.R.anim.slide_in_left));
        holder.department_title.setText(currentDepartment.getDepartmentName());
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                listener.getClickedDepartment(currentDepartment);
            }
        });
        holder.department_iv.setImageResource(currentDepartment.getDepartmentImageID());
    }

    @Override
    public int getItemCount() {
        return departmentDetails.size() == 0 ? 0 : departmentDetails.size();
    }

    public class departmentViewHolder extends RecyclerView.ViewHolder {
        ImageView department_iv;
        MaterialTextView department_title;

        public departmentViewHolder(@NonNull View itemView) {
            super(itemView);
            department_iv = itemView.findViewById(R.id.recycler_department_design_iv);
            department_title = itemView.findViewById(R.id.recycler_department_design_department_tv);
        }
    }

    public interface onDepartmentClicked {
        void getClickedDepartment(DepartmentDetails clickedDepartment);
    }
}
