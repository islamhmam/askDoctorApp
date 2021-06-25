package com.elhefny.askdoctor2.Patients;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.amulyakhare.textdrawable.TextDrawable;
import com.amulyakhare.textdrawable.util.ColorGenerator;
import com.elhefny.askdoctor2.R;
import com.firebase.ui.firestore.FirestoreRecyclerAdapter;
import com.firebase.ui.firestore.FirestoreRecyclerOptions;
import com.squareup.picasso.Picasso;

public class DepartmentsPublicQuestionsAdapter extends FirestoreRecyclerAdapter<PublicQuestion, DepartmentsPublicQuestionsAdapter.PublicQuestionsViewHolder> {
    Context context;

    /**
     * Create a new RecyclerView adapter that listens to a Firestore Query.  See {@link
     * FirestoreRecyclerOptions} for configuration options.
     *
     * @param options
     */
    public DepartmentsPublicQuestionsAdapter(Context context, @NonNull FirestoreRecyclerOptions<PublicQuestion> options) {
        super(options);
        this.context = context;
    }

    @Override
    protected void onBindViewHolder(@NonNull PublicQuestionsViewHolder holder, int position, @NonNull PublicQuestion model) {
        holder.tv_answer.setText(model.getAnswer());
        holder.tv_date.setText(model.getDate());
        holder.tv_body.setText(model.getQuestionBody());
        ColorGenerator generator = ColorGenerator.MATERIAL;
        int color1 = generator.getRandomColor();
        TextDrawable.IBuilder builder = TextDrawable.builder()
                .beginConfig()
                .withBorder(4)
                .endConfig()
                .round();
        TextDrawable ic1 = builder.build(model.getPatient().getName().trim().toUpperCase().substring(0, 1), color1);
        if (model.getPatient().getPatientImageURL() == null || model.getPatient().getPatientImageURL().isEmpty()) {
            holder.iv_patient.setImageDrawable(ic1);
        } else {
            Picasso.get()
                    .load(model.getPatient().getPatientImageURL())
                    .placeholder(ic1)
                    .fit()
                    .centerCrop()
                    .into(holder.iv_patient);
        }
        holder.itemView.setAnimation(AnimationUtils.loadAnimation(context, android.R.anim.slide_in_left));
    }

    @NonNull
    @Override
    public PublicQuestionsViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.recycler_message_review_design, parent, false);
        return new PublicQuestionsViewHolder(v);
    }

    class PublicQuestionsViewHolder extends RecyclerView.ViewHolder {
        ImageView iv_patient;
        TextView tv_body, tv_answer, tv_date;

        public PublicQuestionsViewHolder(@NonNull View itemView) {
            super(itemView);
            iv_patient = itemView.findViewById(R.id.recycler_message_review_iv_person_first_character_name);
            tv_body = itemView.findViewById(R.id.recycler_message_review_tv_message_content);
            tv_date = itemView.findViewById(R.id.recycler_message_review_tv_message_date);
            tv_answer = itemView.findViewById(R.id.recycler_message_review_tv_message_answer);
        }
    }
}
