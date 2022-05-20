package com.example.myapplication.fragments;

import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;

import android.os.Handler;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.myapplication.R;
import com.example.myapplication.Tasks_OGE_Activity;
import com.example.myapplication.db.DBHelper;

import java.io.IOException;

public class QuestFragment_oge extends Fragment {

    private ImageView trueVote_image, falseVote_image;
    private TextView labelView, textView, vote1_btn, vote2_btn, vote3_btn, vote4_btn;
    private int trueVote = -1;
    private int data_id;

    String label, text, vote1, vote2, vote3, vote4;

    private int[] intent;
    private int i_intent = 0;

    private DBHelper db_helper;
    private SQLiteDatabase db;

    Cursor cursor_label;

    private ImageButton back_btn;

    //statistic page
    int trueVotes = 0;

    public QuestFragment_oge(int[] intent, int data_id) {
        this.intent = intent;
        this.data_id = data_id;
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

        View rootView = inflater.inflate(R.layout.fragment_quest_oge, container, false);
        return rootView;
    }


    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        cursor_label.moveToFirst();
        cursor_label.move(intent[i_intent]);

        label = cursor_label.getString(2);
        text = cursor_label.getString(3);
        vote1 = cursor_label.getString(4);
        vote2 = cursor_label.getString(5);
        vote3 = cursor_label.getString(6);
        vote4 = cursor_label.getString(7);
        trueVote = cursor_label.getInt(8);

        cursor_label.close();

        back_btn = view.findViewById(R.id.buttonBack_toLevels);
        labelView = view.findViewById(R.id.task_id_text);
        textView = view.findViewById(R.id.task_text);
        vote1_btn = view.findViewById(R.id.button_vote1);
        vote2_btn = view.findViewById(R.id.button_vote2);
        vote3_btn = view.findViewById(R.id.button_vote3);
        vote4_btn = view.findViewById(R.id.button_vote4);
        trueVote_image = view.findViewById(R.id.level_complite_quest_oge);
        falseVote_image = view.findViewById(R.id.level_failed_quest_oge);
        LoadDataLevel(label, text, vote1, vote2, vote3, vote4, trueVote);

        back_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FragmentManager fm = getActivity().getSupportFragmentManager();
                fm.popBackStack();
                Tasks_OGE_Activity.levels_layout.setVisibility(View.VISIBLE);
            }
        });

        vote1_btn.setOnClickListener(this::buttonVoteClick);
        vote2_btn.setOnClickListener(this::buttonVoteClick);
        vote3_btn.setOnClickListener(this::buttonVoteClick);
        vote4_btn.setOnClickListener(this::buttonVoteClick);
    }

    private void LoadDataLevel(String label, String text, String vote1, String vote2, String vote3, String vote4, int trueVote) {
        labelView.setText(label);
        textView.setText(text);
        vote1_btn.setText(vote1);
        vote2_btn.setText(vote2);
        vote3_btn.setText(vote3);
        vote4_btn.setText(vote4);
        this.trueVote = trueVote;
    }

    public void buttonVoteClick(View view) {
        TextView thisButton = (TextView) view;
        int id_btn = Integer.parseInt(thisButton.getHint().toString());

        vote1_btn.setClickable(false);
        vote2_btn.setClickable(false);
        vote3_btn.setClickable(false);
        vote4_btn.setClickable(false);

        if (id_btn == trueVote) {
            trueVote_image.setVisibility(View.VISIBLE);
            trueVotes++;
        } else {
            falseVote_image.setVisibility(View.VISIBLE);
        }

        Handler handler = new Handler();
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
                }
                else {
                    trueVote_image.setVisibility(View.INVISIBLE);
                    falseVote_image.setVisibility(View.INVISIBLE);
                    Cursor cursor_restarted = db.rawQuery("SELECT * FROM asks_tab WHERE numberTask = " + data_id, null);
                    cursor_restarted.moveToFirst();
                    cursor_restarted.moveToPosition(intent[i_intent]);

                    label = cursor_restarted.getString(2);
                    text = cursor_restarted.getString(3);
                    vote1 = cursor_restarted.getString(4);
                    vote2 = cursor_restarted.getString(5);
                    vote3 = cursor_restarted.getString(6);
                    vote4 = cursor_restarted.getString(7);
                    trueVote = cursor_restarted.getInt(8);

                    cursor_restarted.close();

                    LoadDataLevel(label, text, vote1, vote2, vote3, vote4, trueVote);

                    vote1_btn.setClickable(true);
                    vote2_btn.setClickable(true);
                    vote3_btn.setClickable(true);
                    vote4_btn.setClickable(true);
                }
            }
        }, 2000);

    }
}