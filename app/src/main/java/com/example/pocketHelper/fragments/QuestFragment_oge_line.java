package com.example.pocketHelper.fragments;

import android.animation.AnimatorInflater;
import android.animation.AnimatorSet;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;

import android.os.Handler;
import android.transition.TransitionInflater;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.SeekBar;
import android.widget.TextView;

import com.example.pocketHelper.R;
import com.example.pocketHelper.Tasks_OGE_Activity;
import com.example.pocketHelper.db.DBHelper;
import com.example.pocketHelper.statistic.MainStatistic;

import java.io.IOException;

public class QuestFragment_oge_line extends Fragment {
    public interface GlobalQuest_completeTaskListener {
        public void onTaskReady(String title);
    }
    private TextView labelView, textView, textVote1, textVote2, textVote3, proverkaButton;
    private SeekBar seekBar;
    private int trueVote = -1;
    private int data_id;

    private int[] intent;
    private int intent_forTest;
    private int i_intent = 0;

    View isTrueVoteImage, falseVoteImage;

    String label, text, vote1, vote2, vote3;

    private ImageButton back_btn;

    private DBHelper db_helper;
    private SQLiteDatabase db;

    Cursor cursor_label;

    //statistic page
    int trueVotes = 0;

    //anim
    AnimatorSet set = new AnimatorSet(), set1 = new AnimatorSet();

    private TextView falseTextVote, trueTextVote;

    Handler handler = new Handler();

    //globalQuest
    private boolean isGlobalQuest = false;
    private QuestFragment_oge_notVote.GlobalQuest_completeTaskListener listener;

    public QuestFragment_oge_line(int[] intent, int data_id) {
        this.intent = intent;
        this.data_id = data_id;
    }
    public void setListener(QuestFragment_oge_notVote.GlobalQuest_completeTaskListener listener) {
        this.listener = listener;
    }
    public QuestFragment_oge_line(int intent, int data_id) {
        this.intent_forTest = intent;
        this.data_id = data_id;
        isGlobalQuest = true;
        this.listener = null;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        TransitionInflater inflater = TransitionInflater.from(requireContext());
        setExitTransition(inflater.inflateTransition(R.transition.fade));
    }
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        db_helper = new DBHelper(getContext());

        try {
            db_helper.updateDataBase();
        } catch (IOException mIOException) {
            throw new Error("UnableToUpdateDatabase");
        }

        try {
            db = db_helper.getWritableDatabase();
        } catch (SQLException mSQLException) {
            throw mSQLException;
        }
        cursor_label = db.rawQuery("SELECT * FROM asks_tab WHERE numberTask = " + data_id, null);

        Tasks_OGE_Activity.onGame = true;

        return inflater.inflate(R.layout.fragment_quest_oge_line, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        cursor_label.moveToFirst();
        if(!isGlobalQuest) {
        cursor_label.move(intent[i_intent]);
        }else {
            cursor_label.move(intent_forTest);
        }

        label = cursor_label.getString(2);
        text = cursor_label.getString(3);
        vote1 = cursor_label.getString(4);
        vote2 = cursor_label.getString(5);
        vote3 = cursor_label.getString(6);
        trueVote = cursor_label.getInt(8);

        cursor_label.close();

        back_btn = view.findViewById(R.id.buttonBack_toLevels);
        labelView = view.findViewById(R.id.task_id_text);
        textView = view.findViewById(R.id.task_text);
        seekBar = view.findViewById(R.id.seekBar);
        textVote1 = view.findViewById(R.id.vote1_text);
        textVote2 = view.findViewById(R.id.vote2_text);
        textVote3 = view.findViewById(R.id.vote3_text);
        proverkaButton = view.findViewById(R.id.button_isTrueVote);
        isTrueVoteImage = view.findViewById(R.id.level_complite_quest_oge_line);
        falseVoteImage = view.findViewById(R.id.level_failed_quest_oge_line);
        falseTextVote = view.findViewById(R.id.falseVote_text_oge_line);
        trueTextVote = view.findViewById(R.id.trueVote_text_oge_line);
        LoadDataLevel(label, text, vote1, vote2, vote3, trueVote);


        back_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                handler.removeCallbacksAndMessages(null);
                set.cancel();
                set1.cancel();
                getActivity().getSupportFragmentManager().beginTransaction()
                        .remove(getActivity().getSupportFragmentManager().findFragmentByTag("oge_task_vote_line"))
                        .commit();
                Tasks_OGE_Activity.levels_layout.setVisibility(View.VISIBLE);
            }
        });
        proverkaButton.setOnClickListener(this::onClick_isTrueVote_button);

    }

    private void LoadDataLevel(String label, String text, String vote1, String vote2, String vote3, int trueVote) {
        labelView.setText(label);
        textView.setText(text);
        textVote1.setText(vote1);
        textVote2.setText(vote2);
        textVote3.setText(vote3);
        this.trueVote = trueVote;
    }

    public void onClick_isTrueVote_button(View view) {
        proverkaButton.setClickable(false);
        int idVote = seekBar.getProgress();
        if (!isGlobalQuest) {
            if (idVote == trueVote - 1) {
                //isTrueVoteImage.setVisibility(View.VISIBLE);
                trueVotes++;
                set = (AnimatorSet) AnimatorInflater.loadAnimator(getContext(), R.animator.vote_anim);
                set.setTarget(isTrueVoteImage);
                set.start();
                trueTextVote.setText((i_intent + 1) + " / 10");
            } else {
                //falseVoteImage.setVisibility(View.VISIBLE);
                set = (AnimatorSet) AnimatorInflater.loadAnimator(getContext(), R.animator.vote_anim);
                set.setTarget(falseVoteImage);
                set.start();
                falseTextVote.setText((i_intent + 1) + " / 10");
            }

            MainStatistic.plussedInt(MainStatistic.APP_STATISTIC_LEVELS_PLAYED, 1);

            handler.postDelayed(new Runnable() {
                @Override
                public void run() {
                    i_intent++;
                    if (i_intent >= 10) {
                        Statistic_last_task_oge_Fragment fragmentStatistic = new Statistic_last_task_oge_Fragment(trueVotes, data_id);
                        getActivity().getSupportFragmentManager().beginTransaction()
                                .replace(R.id.fragmentContainerView, fragmentStatistic, "statistic_oge")
                                .addToBackStack(null)
                                .commit();
                    } else {
                        Cursor cursor_restarted = db.rawQuery("SELECT * FROM asks_tab WHERE numberTask = " + data_id, null);
                        cursor_restarted.moveToFirst();
                        cursor_restarted.moveToPosition(intent[i_intent]);

                        label = cursor_restarted.getString(2);
                        text = cursor_restarted.getString(3);
                        vote1 = cursor_restarted.getString(4);
                        vote2 = cursor_restarted.getString(5);
                        vote3 = cursor_restarted.getString(6);
                        trueVote = cursor_restarted.getInt(8);

                        cursor_restarted.close();

                        LoadDataLevel(label, text, vote1, vote2, vote3, trueVote);

                        proverkaButton.setClickable(true);

                        set = (AnimatorSet) AnimatorInflater.loadAnimator(getContext(), R.animator.back_vote_anim);
                        set.setTarget(falseVoteImage);
                        set.start();
                        set1 = (AnimatorSet) AnimatorInflater.loadAnimator(getContext(), R.animator.back_vote_anim);
                        set1.setTarget(isTrueVoteImage);
                        set1.start();
                    }
                }
            }, 2000);
        }else{
            if (idVote == trueVote - 1) {
                trueVotes++;
                set = (AnimatorSet) AnimatorInflater.loadAnimator(getContext(), R.animator.vote_anim);
                set.setTarget(isTrueVoteImage);
                set.start();
                trueTextVote.setText(data_id + " / 19");
                Tasks_OGE_Activity.yourAnswers += "1";
            } else {
                set = (AnimatorSet) AnimatorInflater.loadAnimator(getContext(), R.animator.vote_anim);
                set.setTarget(falseVoteImage);
                set.start();
                falseTextVote.setText(data_id + " / 19");
                Tasks_OGE_Activity.yourAnswers += "0";
            }

            MainStatistic.plussedInt(MainStatistic.APP_STATISTIC_LEVELS_PLAYED, 1);

            handler.postDelayed(new Runnable() {
                @Override
                public void run() {
                    listener.onTaskReady("complete");
                }
            }, 2000);
        }
    }
}