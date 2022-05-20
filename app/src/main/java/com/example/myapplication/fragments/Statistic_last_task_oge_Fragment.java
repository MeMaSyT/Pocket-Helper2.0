package com.example.myapplication.fragments;

import android.graphics.Color;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.myapplication.R;
import com.example.myapplication.Tasks_OGE_Activity;
import com.example.myapplication.statistic.MainStatistic;

public class Statistic_last_task_oge_Fragment extends Fragment {

    private int trueVotes, falseVotes;
    private int trueVotes_previous, falseVotes_previous;

    private TextView trueVotes_text, falseVotes_text, uppingResult_text;
    private TextView btn_back;
    private int id_task;


    Statistic_last_task_oge_Fragment(int trueVotes, int id_Task) {
        this.trueVotes = trueVotes;
        falseVotes = 10 - trueVotes;
        MainStatistic.plussedInt(MainStatistic.APP_STATISTIC_TrueVote_OGE, this.trueVotes);
        MainStatistic.plussedInt(MainStatistic.APP_STATISTIC_FalseVote_OGE, this.falseVotes);
        this.id_task = id_Task;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        switch (id_task) {
            case 6:
                trueVotes_previous = MainStatistic.mStatistic.getInt(MainStatistic.APP_STATISTIC_TrueVote_OGE_6_now, 0);
                falseVotes_previous = MainStatistic.mStatistic.getInt(MainStatistic.APP_STATISTIC_FalseVote_OGE_6_now, 0);
            break;

            case 7:
            trueVotes_previous = MainStatistic.mStatistic.getInt(MainStatistic.APP_STATISTIC_TrueVote_OGE_7_now, 0);
            falseVotes_previous = MainStatistic.mStatistic.getInt(MainStatistic.APP_STATISTIC_FalseVote_OGE_7_now, 0);
            break;
        }
        return inflater.inflate(R.layout.fragment_statistic_last_task_oge, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        trueVotes_text = view.findViewById(R.id.trueVotes_score);
        falseVotes_text = view.findViewById(R.id.falseVotes_score);
        uppingResult_text = view.findViewById(R.id.uppingResult_text);
        btn_back = view.findViewById(R.id.button_back_statistic_oge);
        btn_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                switch (id_task) {
                    case 6:
                        MainStatistic.editor
                                .putInt(MainStatistic.APP_STATISTIC_TrueVote_OGE_6_now, trueVotes)
                                .putInt(MainStatistic.APP_STATISTIC_FalseVote_OGE_6_now, falseVotes)
                                .apply();
                        break;

                    case 7:
                    MainStatistic.editor
                            .putInt(MainStatistic.APP_STATISTIC_TrueVote_OGE_7_now, trueVotes)
                            .putInt(MainStatistic.APP_STATISTIC_FalseVote_OGE_7_now, falseVotes)
                            .apply();
                    break;
                }
                getActivity().getSupportFragmentManager().beginTransaction()
                        .remove(getActivity().getSupportFragmentManager().findFragmentByTag("statistic_oge"))
                        .commit();
                Tasks_OGE_Activity.levels_layout.setVisibility(View.VISIBLE);
            }
        });
        trueVotes_text.setText(String.valueOf(trueVotes));
        falseVotes_text.setText(String.valueOf(falseVotes));
        int upPresents = trueVotes * 10 - trueVotes_previous * 10;
        if (trueVotes_previous != 0) {
            if (upPresents > 0) {
                uppingResult_text.setText("Вы улучшили свой результат, по сравнению с предыдущим на " + upPresents + "%");
                uppingResult_text.setTextColor(getResources().getColor(R.color.green_true));
            } else if (upPresents < 0) {
                uppingResult_text.setText("Вы уухудшили свой результат, по сравнению с предыдущим на " + (-upPresents) + "%");
                uppingResult_text.setTextColor(getResources().getColor(R.color.red_false));
            } else {
                uppingResult_text.setText("Вы не улучшили ствой результат, по сравнению с предыдущим");
                uppingResult_text.setTextColor(getResources().getColor(R.color.black));
            }
        } else {
            uppingResult_text.setText("Не плохо для первого раза");
            uppingResult_text.setTextColor(getResources().getColor(R.color.black));
        }
    }

}