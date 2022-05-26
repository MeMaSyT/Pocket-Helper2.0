package com.example.pocketHelper.exams;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.pocketHelper.R;
import com.example.pocketHelper.Tasks_OGE_Activity;

import java.util.List;

public class ExamsAdapter extends RecyclerView.Adapter<ExamsAdapter.ExamViewHolder> {

    Context context;
    List<Exam> exams;

    public ExamsAdapter(Context context, List<Exam> exams) {
        this.context = context;
        this.exams = exams;
    }

    @NonNull
    @Override
    public ExamViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View examItems = LayoutInflater.from(context).inflate(R.layout.exam_item, parent, false);
        return new ExamsAdapter.ExamViewHolder(examItems);
    }

    @Override
    public void onBindViewHolder(@NonNull ExamViewHolder holder, int position) {
        holder.examBg.setBackgroundColor(Color.parseColor(exams.get(position).getColor()));
        int idImage = context.getResources().getIdentifier("ic_" + exams.get(position).getImage(), "drawable", context.getPackageName());
        holder.image.setImageResource(idImage);
        holder.examTitle.setText(exams.get(position).getTitle());
        holder.examClass.setText(exams.get(position).getNeedClass());
        holder.examTime.setText(exams.get(position).getTime());
        holder.examText.setText(exams.get(position).getText());

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(holder.examTitle.getText() == "ОГЭ") {
                    Intent intent = new Intent(context, Tasks_OGE_Activity.class);
                    context.startActivity(intent);

                }
            }
        });
    }

    @Override
    public int getItemCount() {
        return exams.size();
    }

    public static final class ExamViewHolder extends  RecyclerView.ViewHolder{

        LinearLayout examBg;
        ImageView image;
        TextView examTitle, examClass,examTime, examText;
        int id;

        public ExamViewHolder(@NonNull View itemView) {
            super(itemView);

            examBg = itemView.findViewById(R.id.exam_bg);
            image = itemView.findViewById(R.id.examImage);
            examTitle = itemView.findViewById(R.id.title_text);
            examClass = itemView.findViewById(R.id.needClass_text);
            examTime = itemView.findViewById(R.id.needTime_text);
            examText = itemView.findViewById(R.id.exam_text);

        }
    }
}
