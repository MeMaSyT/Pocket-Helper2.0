package com.example.myapplication.fragments;

import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.os.Handler;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.SeekBar;
import android.widget.TextView;

import com.example.myapplication.R;
import com.example.myapplication.Tasks_OGE_Activity;
import com.example.myapplication.db.DBHelper;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

public class QuestFragment_oge_line extends Fragment {

    private TextView labelView, textView, textVote1, textVote2, textVote3, proverkaButton;
    private SeekBar seekBar;
    private int trueVote = -1;
    private int data_id;

    private int[] intent;
    private int i_intent = 0;
    ImageView isTrueVoteImage, falseVoteImage;

    String label, text, vote1, vote2, vote3;

    private ImageButton back_btn;

    private DBHelper db_helper;
    private SQLiteDatabase db;

    Cursor cursor_label;

    //statistic page
    int trueVotes = 0;

    public QuestFragment_oge_line(int[] intent, int data_id) {
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

        return inflater.inflate(R.layout.fragment_quest_oge_line, container, false);
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
        isTrueVoteImage = view.findViewById(R.id.isTrueVote_Image_oge_line);
        falseVoteImage = view.findViewById(R.id.isFalseVote_Image_oge_line);
        LoadDataLevel(label, text, vote1, vote2, vote3, trueVote);

        back_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FragmentManager fm = getActivity().getSupportFragmentManager();
                fm.popBackStack();
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
        if (idVote == trueVote - 1) {
            isTrueVoteImage.setVisibility(View.VISIBLE);
            trueVotes++;
        } else {
            falseVoteImage.setVisibility(View.VISIBLE);
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
                    isTrueVoteImage.setVisibility(View.INVISIBLE);
                    falseVoteImage.setVisibility(View.INVISIBLE);
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
                }
            }
        }, 2000);
    }
}