package com.example.pocketHelper.fragments;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.transition.TransitionInflater;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.pocketHelper.R;
import com.example.pocketHelper.Tasks_OGE_Activity;
import com.example.pocketHelper.statistic.MainStatistic;

public class Statistic_last_task_oge_Fragment extends Fragment {

    private int trueVotes, falseVotes;
    private int trueVotes_previous, falseVotes_previous;

    private TextView trueVotes_text, falseVotes_text, uppingResult_text;
    private TextView btn_back;
    private int id_task;

    private ImageView arrow;

    Statistic_last_task_oge_Fragment(int trueVotes, int id_Task) {
        this.trueVotes = trueVotes;
        if(id_Task > 5 && id_Task != 17 && id_Task != 18) {
            falseVotes = 10 - trueVotes;
        }else {
            falseVotes = 5 - trueVotes;
        }
        MainStatistic.plussedInt(MainStatistic.APP_STATISTIC_TrueVote_OGE, this.trueVotes);
        MainStatistic.plussedInt(MainStatistic.APP_STATISTIC_FalseVote_OGE, this.falseVotes);
        this.id_task = id_Task;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        TransitionInflater inflater = TransitionInflater.from(requireContext());
        setExitTransition(inflater.inflateTransition(R.transition.fade));
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        switch (id_task) {
            case 1:
                trueVotes_previous = MainStatistic.mStatistic.getInt(MainStatistic.APP_STATISTIC_TrueVote_OGE_1_now, 0);
                falseVotes_previous = MainStatistic.mStatistic.getInt(MainStatistic.APP_STATISTIC_FalseVote_OGE_1_now, 0);
                break;
            case 2:
                trueVotes_previous = MainStatistic.mStatistic.getInt(MainStatistic.APP_STATISTIC_TrueVote_OGE_2_now, 0);
                falseVotes_previous = MainStatistic.mStatistic.getInt(MainStatistic.APP_STATISTIC_FalseVote_OGE_2_now, 0);
                break;
            case 3:
                trueVotes_previous = MainStatistic.mStatistic.getInt(MainStatistic.APP_STATISTIC_TrueVote_OGE_3_now, 0);
                falseVotes_previous = MainStatistic.mStatistic.getInt(MainStatistic.APP_STATISTIC_FalseVote_OGE_3_now, 0);
                break;
            case 4:
                trueVotes_previous = MainStatistic.mStatistic.getInt(MainStatistic.APP_STATISTIC_TrueVote_OGE_4_now, 0);
                falseVotes_previous = MainStatistic.mStatistic.getInt(MainStatistic.APP_STATISTIC_FalseVote_OGE_4_now, 0);
                break;
            case 5:
                trueVotes_previous = MainStatistic.mStatistic.getInt(MainStatistic.APP_STATISTIC_TrueVote_OGE_5_now, 0);
                falseVotes_previous = MainStatistic.mStatistic.getInt(MainStatistic.APP_STATISTIC_FalseVote_OGE_5_now, 0);
                break;
            case 6:
                trueVotes_previous = MainStatistic.mStatistic.getInt(MainStatistic.APP_STATISTIC_TrueVote_OGE_6_now, 0);
                falseVotes_previous = MainStatistic.mStatistic.getInt(MainStatistic.APP_STATISTIC_FalseVote_OGE_6_now, 0);
                break;
            case 7:
                trueVotes_previous = MainStatistic.mStatistic.getInt(MainStatistic.APP_STATISTIC_TrueVote_OGE_7_now, 0);
                falseVotes_previous = MainStatistic.mStatistic.getInt(MainStatistic.APP_STATISTIC_FalseVote_OGE_7_now, 0);
                break;
            case 8:
                trueVotes_previous = MainStatistic.mStatistic.getInt(MainStatistic.APP_STATISTIC_TrueVote_OGE_8_now, 0);
                falseVotes_previous = MainStatistic.mStatistic.getInt(MainStatistic.APP_STATISTIC_FalseVote_OGE_8_now, 0);
                break;
            case 9:
                trueVotes_previous = MainStatistic.mStatistic.getInt(MainStatistic.APP_STATISTIC_TrueVote_OGE_9_now, 0);
                falseVotes_previous = MainStatistic.mStatistic.getInt(MainStatistic.APP_STATISTIC_FalseVote_OGE_9_now, 0);
                break;
            case 10:
                trueVotes_previous = MainStatistic.mStatistic.getInt(MainStatistic.APP_STATISTIC_TrueVote_OGE_10_now, 0);
                falseVotes_previous = MainStatistic.mStatistic.getInt(MainStatistic.APP_STATISTIC_FalseVote_OGE_10_now, 0);
                break;
            case 11:
                trueVotes_previous = MainStatistic.mStatistic.getInt(MainStatistic.APP_STATISTIC_TrueVote_OGE_11_now, 0);
                falseVotes_previous = MainStatistic.mStatistic.getInt(MainStatistic.APP_STATISTIC_FalseVote_OGE_11_now, 0);
                break;
            case 12:
                trueVotes_previous = MainStatistic.mStatistic.getInt(MainStatistic.APP_STATISTIC_TrueVote_OGE_12_now, 0);
                falseVotes_previous = MainStatistic.mStatistic.getInt(MainStatistic.APP_STATISTIC_FalseVote_OGE_12_now, 0);
                break;
            case 13:
                trueVotes_previous = MainStatistic.mStatistic.getInt(MainStatistic.APP_STATISTIC_TrueVote_OGE_13_now, 0);
                falseVotes_previous = MainStatistic.mStatistic.getInt(MainStatistic.APP_STATISTIC_FalseVote_OGE_13_now, 0);
                break;
            case 14:
                trueVotes_previous = MainStatistic.mStatistic.getInt(MainStatistic.APP_STATISTIC_TrueVote_OGE_14_now, 0);
                falseVotes_previous = MainStatistic.mStatistic.getInt(MainStatistic.APP_STATISTIC_FalseVote_OGE_14_now, 0);
                break;
            case 15:
                trueVotes_previous = MainStatistic.mStatistic.getInt(MainStatistic.APP_STATISTIC_TrueVote_OGE_15_now, 0);
                falseVotes_previous = MainStatistic.mStatistic.getInt(MainStatistic.APP_STATISTIC_FalseVote_OGE_15_now, 0);
                break;
            case 16:
                trueVotes_previous = MainStatistic.mStatistic.getInt(MainStatistic.APP_STATISTIC_TrueVote_OGE_16_now, 0);
                falseVotes_previous = MainStatistic.mStatistic.getInt(MainStatistic.APP_STATISTIC_FalseVote_OGE_16_now, 0);
                break;
            case 17:
                trueVotes_previous = MainStatistic.mStatistic.getInt(MainStatistic.APP_STATISTIC_TrueVote_OGE_17_now, 0);
                falseVotes_previous = MainStatistic.mStatistic.getInt(MainStatistic.APP_STATISTIC_FalseVote_OGE_17_now, 0);
                break;
            case 18:
                trueVotes_previous = MainStatistic.mStatistic.getInt(MainStatistic.APP_STATISTIC_TrueVote_OGE_18_now, 0);
                falseVotes_previous = MainStatistic.mStatistic.getInt(MainStatistic.APP_STATISTIC_FalseVote_OGE_18_now, 0);
                break;
            case 19:
                trueVotes_previous = MainStatistic.mStatistic.getInt(MainStatistic.APP_STATISTIC_TrueVote_OGE_19_now, 0);
                falseVotes_previous = MainStatistic.mStatistic.getInt(MainStatistic.APP_STATISTIC_FalseVote_OGE_19_now, 0);
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
        arrow = view.findViewById(R.id.image_stats_arrow_oge);
        btn_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                switch (id_task) {
                    case 1:
                        MainStatistic.editor
                                .putInt(MainStatistic.APP_STATISTIC_TrueVote_OGE_1_now, trueVotes)
                                .putInt(MainStatistic.APP_STATISTIC_FalseVote_OGE_1_now, falseVotes)
                                .apply();
                        break;
                    case 2:
                        MainStatistic.editor
                                .putInt(MainStatistic.APP_STATISTIC_TrueVote_OGE_2_now, trueVotes)
                                .putInt(MainStatistic.APP_STATISTIC_FalseVote_OGE_2_now, falseVotes)
                                .apply();
                        break;
                    case 3:
                        MainStatistic.editor
                                .putInt(MainStatistic.APP_STATISTIC_TrueVote_OGE_3_now, trueVotes)
                                .putInt(MainStatistic.APP_STATISTIC_FalseVote_OGE_3_now, falseVotes)
                                .apply();
                        break;
                    case 4:
                        MainStatistic.editor
                                .putInt(MainStatistic.APP_STATISTIC_TrueVote_OGE_4_now, trueVotes)
                                .putInt(MainStatistic.APP_STATISTIC_FalseVote_OGE_4_now, falseVotes)
                                .apply();
                        break;
                    case 5:
                        MainStatistic.editor
                                .putInt(MainStatistic.APP_STATISTIC_TrueVote_OGE_5_now, trueVotes)
                                .putInt(MainStatistic.APP_STATISTIC_FalseVote_OGE_5_now, falseVotes)
                                .apply();
                        break;
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
                    case 8:
                        MainStatistic.editor
                                .putInt(MainStatistic.APP_STATISTIC_TrueVote_OGE_8_now, trueVotes)
                                .putInt(MainStatistic.APP_STATISTIC_FalseVote_OGE_8_now, falseVotes)
                                .apply();
                        break;
                    case 9:
                        MainStatistic.editor
                                .putInt(MainStatistic.APP_STATISTIC_TrueVote_OGE_9_now, trueVotes)
                                .putInt(MainStatistic.APP_STATISTIC_FalseVote_OGE_9_now, falseVotes)
                                .apply();
                        break;
                    case 10:
                        MainStatistic.editor
                                .putInt(MainStatistic.APP_STATISTIC_TrueVote_OGE_10_now, trueVotes)
                                .putInt(MainStatistic.APP_STATISTIC_FalseVote_OGE_10_now, falseVotes)
                                .apply();
                        break;
                    case 11:
                        MainStatistic.editor
                                .putInt(MainStatistic.APP_STATISTIC_TrueVote_OGE_11_now, trueVotes)
                                .putInt(MainStatistic.APP_STATISTIC_FalseVote_OGE_11_now, falseVotes)
                                .apply();
                        break;
                    case 12:
                        MainStatistic.editor
                                .putInt(MainStatistic.APP_STATISTIC_TrueVote_OGE_12_now, trueVotes)
                                .putInt(MainStatistic.APP_STATISTIC_FalseVote_OGE_12_now, falseVotes)
                                .apply();
                        break;
                    case 13:
                        MainStatistic.editor
                                .putInt(MainStatistic.APP_STATISTIC_TrueVote_OGE_13_now, trueVotes)
                                .putInt(MainStatistic.APP_STATISTIC_FalseVote_OGE_13_now, falseVotes)
                                .apply();
                        break;
                    case 14:
                        MainStatistic.editor
                                .putInt(MainStatistic.APP_STATISTIC_TrueVote_OGE_14_now, trueVotes)
                                .putInt(MainStatistic.APP_STATISTIC_FalseVote_OGE_14_now, falseVotes)
                                .apply();
                        break;
                    case 15:
                        MainStatistic.editor
                                .putInt(MainStatistic.APP_STATISTIC_TrueVote_OGE_15_now, trueVotes)
                                .putInt(MainStatistic.APP_STATISTIC_FalseVote_OGE_15_now, falseVotes)
                                .apply();
                        break;
                    case 16:
                        MainStatistic.editor
                                .putInt(MainStatistic.APP_STATISTIC_TrueVote_OGE_16_now, trueVotes)
                                .putInt(MainStatistic.APP_STATISTIC_FalseVote_OGE_16_now, falseVotes)
                                .apply();
                        break;
                    case 17:
                        MainStatistic.editor
                                .putInt(MainStatistic.APP_STATISTIC_TrueVote_OGE_17_now, trueVotes)
                                .putInt(MainStatistic.APP_STATISTIC_FalseVote_OGE_17_now, falseVotes)
                                .apply();
                        break;
                    case 18:
                        MainStatistic.editor
                                .putInt(MainStatistic.APP_STATISTIC_TrueVote_OGE_18_now, trueVotes)
                                .putInt(MainStatistic.APP_STATISTIC_FalseVote_OGE_18_now, falseVotes)
                                .apply();
                        break;
                    case 19:
                        MainStatistic.editor
                                .putInt(MainStatistic.APP_STATISTIC_TrueVote_OGE_19_now, trueVotes)
                                .putInt(MainStatistic.APP_STATISTIC_FalseVote_OGE_19_now, falseVotes)
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
                int id = getResources().getIdentifier("ic_up_arrow", "drawable", getActivity().getPackageName());
                arrow.setImageResource(id);
            } else if (upPresents < 0) {
                uppingResult_text.setText("Вы уухудшили свой результат, по сравнению с предыдущим на " + (-upPresents) + "%");
                uppingResult_text.setTextColor(getResources().getColor(R.color.red_false));
                int id = getResources().getIdentifier("ic_down_arrow", "drawable", getActivity().getPackageName());
                arrow.setImageResource(id);
            } else {
                uppingResult_text.setText("Вы не улучшили ствой результат, по сравнению с предыдущим");
                uppingResult_text.setTextColor(getResources().getColor(R.color.black));
                arrow.setVisibility(View.INVISIBLE);
            }
        } else {
            uppingResult_text.setText("Не плохо для первого раза");
            uppingResult_text.setTextColor(getResources().getColor(R.color.black));
        }
    }

}