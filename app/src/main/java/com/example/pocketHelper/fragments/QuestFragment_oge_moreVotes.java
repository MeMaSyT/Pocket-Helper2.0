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
import android.widget.CheckBox;
import android.widget.ImageButton;
import android.widget.TextView;

import com.example.pocketHelper.R;
import com.example.pocketHelper.Tasks_OGE_Activity;
import com.example.pocketHelper.db.DBHelper;
import com.example.pocketHelper.statistic.MainStatistic;

import java.io.IOException;

public class QuestFragment_oge_moreVotes extends Fragment {
    public interface GlobalQuest_completeTaskListener {
        public void onTaskReady(String title);
    }
    private View trueVote_image, falseVote_image;
    private TextView labelView, textView, proverkaButton;
    private CheckBox vote1_btn, vote2_btn, vote3_btn;
    private int trueVote = -1;
    private int data_id;

    String label, text, vote1, vote2, vote3;

    private int[] intent;
    private int intent_forTest;
    private int i_intent = 0;

    private DBHelper db_helper;
    private SQLiteDatabase db;

    Cursor cursor_label;

    private ImageButton back_btn;

    //statistic page
    int trueVotes = 0;

    private TextView falseTextVote, trueTextVote;

    //anim
    AnimatorSet set = new AnimatorSet(), set1 = new AnimatorSet();

    Handler handler = new Handler();

    //globalQuest
    private boolean isGlobalQuest = false;
    private QuestFragment_oge_notVote.GlobalQuest_completeTaskListener listener;

    public QuestFragment_oge_moreVotes(int[] intent, int data_id) {
        this.intent = intent;
        this.data_id = data_id;
    }

    public void setListener(QuestFragment_oge_notVote.GlobalQuest_completeTaskListener listener) {
        this.listener = listener;
    }

    public QuestFragment_oge_moreVotes(int intent, int data_id) {
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

        View rootView = inflater.inflate(R.layout.fragment_quest_oge_more_votes, container, false);
        return rootView;
    }


    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        cursor_label.moveToFirst();
        if (!isGlobalQuest) {
        cursor_label.move(intent[i_intent]);
        } else {
            cursor_label.move(intent_forTest);
        }

        label = cursor_label.getString(2);
        text = cursor_label.getString(3);
        vote1 = cursor_label.getString(4);
        vote2 = cursor_label.getString(5);
        vote3 = cursor_label.getString(6);
        trueVote = cursor_label.getInt(8);

        cursor_label.close();

        back_btn = view.findViewById(R.id.buttonBack_toLevels3);
        labelView = view.findViewById(R.id.label_quest_oge_more_votes);
        textView = view.findViewById(R.id.text_quest_oge_more_votes);
        vote1_btn = view.findViewById(R.id.checkBox_oge1);
        vote2_btn = view.findViewById(R.id.checkBox_oge2);
        vote3_btn = view.findViewById(R.id.checkBox_oge3);
        falseTextVote = view.findViewById(R.id.falseVote_text_oge_more_votes);
        trueTextVote = view.findViewById(R.id.trueVote_text_oge_more_votes);
        trueVote_image = view.findViewById(R.id.level_complite_quest_oge_more_votes);
        falseVote_image = view.findViewById(R.id.level_failed_quest_oge_more_votes);
        proverkaButton = view.findViewById(R.id.button_proverka_more_votes_oge);
        LoadDataLevel(label, text, vote1, vote2, vote3, trueVote);

        back_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                handler.removeCallbacksAndMessages(null);
                set.cancel();
                set1.cancel();
                getActivity().getSupportFragmentManager().beginTransaction()
                        .remove(getActivity().getSupportFragmentManager().findFragmentByTag("oge_task_vote_more"))
                        .commit();
                Tasks_OGE_Activity.levels_layout.setVisibility(View.VISIBLE);
            }
        });

        proverkaButton.setOnClickListener(this::buttonVoteClick);
    }

    private void LoadDataLevel(String label, String text, String vote1, String vote2, String vote3, int trueVote) {
        labelView.setText(label);
        textView.setText(text);
        vote1_btn.setText(vote1);
        vote2_btn.setText(vote2);
        vote3_btn.setText(vote3);
        this.trueVote = trueVote;
    }

    public void buttonVoteClick(View view) {
        String answer = "";
        vote1_btn.setClickable(false);
        vote2_btn.setClickable(false);
        vote3_btn.setClickable(false);
        if(vote1_btn.isChecked()){
            answer += "1";
        }
        if(vote2_btn.isChecked()){
            answer += "2";
        }
        if(vote3_btn.isChecked()){
            answer += "3";
        }
        if (!isGlobalQuest) {
            if (!answer.equals("") && trueVote == Integer.parseInt(answer)) {
                trueVote_image.setVisibility(View.VISIBLE);
                trueVotes++;
                set = (AnimatorSet) AnimatorInflater.loadAnimator(getContext(), R.animator.vote_anim);
                set.setTarget(trueVote_image);
                set.start();
                trueTextVote.setText((i_intent + 1) + " / 10");
            } else {
                falseVote_image.setVisibility(View.VISIBLE);
                set = (AnimatorSet) AnimatorInflater.loadAnimator(getContext(), R.animator.vote_anim);
                set.setTarget(falseVote_image);
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

                        vote1_btn.setClickable(true);
                        vote2_btn.setClickable(true);
                        vote3_btn.setClickable(true);

                        vote1_btn.setChecked(false);
                        vote2_btn.setChecked(false);
                        vote3_btn.setChecked(false);

                        set = (AnimatorSet) AnimatorInflater.loadAnimator(getContext(), R.animator.back_vote_anim);
                        set.setTarget(falseVote_image);
                        set.start();
                        set1 = (AnimatorSet) AnimatorInflater.loadAnimator(getContext(), R.animator.back_vote_anim);
                        set1.setTarget(trueVote_image);
                        set1.start();
                    }
                }

            }, 2000);
        }else{
            if (!answer.equals("") && trueVote == Integer.parseInt(answer)) {
                trueVote_image.setVisibility(View.VISIBLE);
                trueVotes++;
                set = (AnimatorSet) AnimatorInflater.loadAnimator(getContext(), R.animator.vote_anim);
                set.setTarget(trueVote_image);
                set.start();
                trueTextVote.setText(data_id + " / 19");
                Tasks_OGE_Activity.yourAnswers += "1";
            } else {
                falseVote_image.setVisibility(View.VISIBLE);
                set = (AnimatorSet) AnimatorInflater.loadAnimator(getContext(), R.animator.vote_anim);
                set.setTarget(falseVote_image);
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