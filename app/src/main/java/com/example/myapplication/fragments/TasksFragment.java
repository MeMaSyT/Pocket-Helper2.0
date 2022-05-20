package com.example.myapplication.fragments;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Debug;
import android.transition.TransitionInflater;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.myapplication.R;
import com.example.myapplication.exams.Exam;
import com.example.myapplication.exams.ExamsAdapter;

import java.util.ArrayList;
import java.util.List;

public class TasksFragment extends Fragment {

    private RecyclerView examsRecycler;
    ExamsAdapter examAdapter;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_tasks, container, false);
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        TransitionInflater inflater = TransitionInflater.from(requireContext());
        setExitTransition(inflater.inflateTransition(R.transition.fade));
    }
    private void setExamRecycler(List<Exam> examsList) {
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getActivity() , RecyclerView.HORIZONTAL, false);


        examsRecycler.setLayoutManager(layoutManager);

        examAdapter = new ExamsAdapter(getContext(), examsList);
        examsRecycler.setAdapter(examAdapter);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        List<Exam> examsList = new ArrayList<>();
        examsList.add(new Exam(1, "oge_logo", "ОГЭ","#BC4B4B",
                "ОГЭ - экзамен, который сдают все школьники вконце 9 класса, данный тренажёр поможет вам подготовиться к этому экзамену",
                "Время тренажёра: 1 час", "Класс: 9"));
        examsList.add(new Exam(2, "ege_logo", "ЕГЭ","#965AE2",
                "В разработке",
                "Время тренажёра: 1 час", "Класс: 11"));
        examsList.add(new Exam(3, "vpr_logo", "ВПР","#00AD46",
                "В разработке",
                "Время тренажёра: 1 час", "Класс: 4 - 11"));

        examsRecycler = view.findViewById(R.id.recyclerViev_exams);
        setExamRecycler(examsList);
    }
}