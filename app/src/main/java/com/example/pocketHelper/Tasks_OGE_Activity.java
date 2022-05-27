package com.example.pocketHelper;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.content.Intent;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.example.pocketHelper.db.DBHelper;
import com.example.pocketHelper.fragments.GlobalQuestStatisticFragment;
import com.example.pocketHelper.fragments.QuestFragment_oge;
import com.example.pocketHelper.fragments.QuestFragment_oge_line;
import com.example.pocketHelper.fragments.QuestFragment_oge_moreVotes;
import com.example.pocketHelper.fragments.QuestFragment_oge_notVote;
import com.example.pocketHelper.listeners.GlobalQuest_completeTaskListener;
import com.example.pocketHelper.statistic.MainStatistic;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Random;

public class Tasks_OGE_Activity extends AppCompatActivity{

    public static View levels_layout;

    public static boolean onGame = false;

    private DBHelper db_helper;
    private SQLiteDatabase db;

    //globalQuest
    int numberQuest_inTask = 1;
    int[] intent;
    public static String yourAnswers;

    @RequiresApi(api = Build.VERSION_CODES.N)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tasks_oge);
        MainStatistic.activateStats(this);
        db_helper = new DBHelper(this);

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

        intent = generateIntent_globalQuest();
    }

    public void buttonClick(View view) {
        TextView thisButton = (TextView) view;
        int data_id = Integer.parseInt(thisButton.getText().toString());

        String label, text, vote1, vote2, vote3, vote4;
        int trueVote;


        Cursor cursor_label = db.rawQuery("SELECT * FROM asks_tab WHERE numberTask = " + data_id, null);
        FragmentTransaction ft = getSupportFragmentManager().beginTransaction();
        cursor_label.moveToFirst();
        switch (cursor_label.getInt(9)) {
            case 1:

                levels_layout = findViewById(R.id.container1);
                levels_layout.setVisibility(View.INVISIBLE);

                QuestFragment_oge questFragment_oge = null;
                if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.N) {
                    questFragment_oge = new QuestFragment_oge(generateIntent(10), data_id);
                }

                ft.replace(R.id.fragmentContainerView, questFragment_oge, "oge_task_vote");
                ft.addToBackStack(null);
                ft.commit();
                break;
            case 2:

                levels_layout = findViewById(R.id.container1);
                levels_layout.setVisibility(View.INVISIBLE);

                QuestFragment_oge_line questFragment_oge_line = null;
                if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.N) {
                    questFragment_oge_line = new QuestFragment_oge_line(generateIntent(10), data_id);
                }

                ft.replace(R.id.fragmentContainerView, questFragment_oge_line, "oge_task_vote_line");
                ft.addToBackStack(null);
                ft.commit();
                break;
            case 3:

                levels_layout = findViewById(R.id.container1);
                levels_layout.setVisibility(View.INVISIBLE);
                QuestFragment_oge_notVote questFragment_oge_nonVote = null;
                if (data_id > 5 && data_id != 17 && data_id != 18) {
                    if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.N) {
                        questFragment_oge_nonVote = new QuestFragment_oge_notVote(generateIntent(10), data_id);
                    }
                } else {
                    if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.N) {
                        questFragment_oge_nonVote = new QuestFragment_oge_notVote(generateIntent(5), data_id);
                    }
                }
                ft.replace(R.id.fragmentContainerView, questFragment_oge_nonVote, "oge_task_vote_nonVote");
                ft.addToBackStack(null);
                ft.commit();
                break;
            case 4:

                levels_layout = findViewById(R.id.container1);
                levels_layout.setVisibility(View.INVISIBLE);

                QuestFragment_oge_moreVotes questFragment_oge_more_votes = null;
                if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.N) {
                    questFragment_oge_more_votes = new QuestFragment_oge_moreVotes(generateIntent(10), data_id);
                }

                ft.replace(R.id.fragmentContainerView, questFragment_oge_more_votes,"oge_task_vote_more");
                ft.addToBackStack(null);
                ft.commit();
                break;
        }


    }

    public void buttonClick(int n, int intent_i) {
        int data_id = n;

        Cursor cursor_label = db.rawQuery("SELECT * FROM asks_tab WHERE numberTask = " + data_id, null);
        FragmentTransaction ft = getSupportFragmentManager().beginTransaction();
        cursor_label.moveToFirst();
        switch (cursor_label.getInt(9)) {
            case 1:

                levels_layout = findViewById(R.id.container1);
                levels_layout.setVisibility(View.INVISIBLE);

                QuestFragment_oge questFragment_oge = null;
                if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.N) {
                    questFragment_oge = new QuestFragment_oge(intent_i, data_id);
                }
                questFragment_oge.setListener(new QuestFragment_oge_notVote.GlobalQuest_completeTaskListener() {
                    @RequiresApi(api = Build.VERSION_CODES.N)
                    @Override
                    public void onTaskReady(String title) {
                        numberQuest_inTask++;
                        goGlobalQuest();
                    }
                });

                ft.replace(R.id.fragmentContainerView, questFragment_oge, "oge_task_vote");
                ft.addToBackStack(null);
                ft.commit();
                break;
            case 2:

                levels_layout = findViewById(R.id.container1);
                levels_layout.setVisibility(View.INVISIBLE);

                QuestFragment_oge_line questFragment_oge_line = null;
                if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.N) {
                    questFragment_oge_line = new QuestFragment_oge_line(intent_i, data_id);
                }
                questFragment_oge_line.setListener(new QuestFragment_oge_notVote.GlobalQuest_completeTaskListener() {
                    @RequiresApi(api = Build.VERSION_CODES.N)
                    @Override
                    public void onTaskReady(String title) {
                        numberQuest_inTask++;
                        goGlobalQuest();
                    }
                });

                ft.replace(R.id.fragmentContainerView, questFragment_oge_line, "oge_task_vote_line");
                ft.addToBackStack(null);
                ft.commit();
                break;
            case 3:

                levels_layout = findViewById(R.id.container1);
                levels_layout.setVisibility(View.INVISIBLE);

                QuestFragment_oge_notVote questFragment_oge_nonVote = null;
                if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.N) {
                    questFragment_oge_nonVote = new QuestFragment_oge_notVote(intent_i, data_id);
                }
                questFragment_oge_nonVote.setListener(new QuestFragment_oge_notVote.GlobalQuest_completeTaskListener() {
                    @RequiresApi(api = Build.VERSION_CODES.N)
                    @Override
                    public void onTaskReady(String title) {
                        numberQuest_inTask++;
                        goGlobalQuest();
                    }
                });

                ft.replace(R.id.fragmentContainerView, questFragment_oge_nonVote, "oge_task_vote_nonVote");
                ft.addToBackStack(null);
                ft.commit();
                break;
            case 4:

                levels_layout = findViewById(R.id.container1);
                levels_layout.setVisibility(View.INVISIBLE);

                QuestFragment_oge_moreVotes questFragment_oge_more_votes = null;
                if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.N) {
                    questFragment_oge_more_votes = new QuestFragment_oge_moreVotes(intent_i, data_id);
                }
                questFragment_oge_more_votes.setListener(new QuestFragment_oge_notVote.GlobalQuest_completeTaskListener() {
                    @RequiresApi(api = Build.VERSION_CODES.N)
                    @Override
                    public void onTaskReady(String title) {
                        numberQuest_inTask++;
                        goGlobalQuest();
                    }
                });

                ft.replace(R.id.fragmentContainerView, questFragment_oge_more_votes,"oge_task_vote_more");
                ft.addToBackStack(null);
                ft.commit();
                break;
        }


    }

    public void goBack(View view) {
        Intent intent = new Intent(Tasks_OGE_Activity.this, MainActivity.class);
        Tasks_OGE_Activity.this.startActivity(intent);
    }

    @RequiresApi(api = Build.VERSION_CODES.N)
    public void goGlobalQuest() {
        if(numberQuest_inTask <= 19) {
            buttonClick(numberQuest_inTask, intent[numberQuest_inTask - 1]);
        }else {
            getSupportFragmentManager()
                    .beginTransaction()
                    .replace(R.id.fragmentContainerView, new GlobalQuestStatisticFragment(yourAnswers))
                    .addToBackStack("statsGlobalOge")
                    .commit();
        }
    }
    @RequiresApi(api = Build.VERSION_CODES.N)
    public void globalQuest_btn_click(View view){
        yourAnswers = "";
        goGlobalQuest();
    }
    @RequiresApi(api = Build.VERSION_CODES.N)
    private int[] generateIntent(int N) {
        ArrayList<Integer> arrayList = new ArrayList<>(N);
        Random random = new Random();

        while (arrayList.size() < N) {
            int i = random.nextInt(N);
            if (!arrayList.contains(i)) {
                arrayList.add(i);
            }
        }
        return arrayList.stream().mapToInt(i -> i).toArray();
    }

    @RequiresApi(api = Build.VERSION_CODES.N)
    private int[] generateIntent_globalQuest() {
        ArrayList<Integer> arrayList = new ArrayList<>(19);
        Random random = new Random();
        for (int i = 0; i < 19; i++) {
            if (i < 5 || i == 16 || i == 17) {
                arrayList.add(random.nextInt(5));
            } else {
                arrayList.add(random.nextInt(10));
            }
        }
        return arrayList.stream().mapToInt(i -> i).toArray();
    }

    public void onBackPressed() {
        if (onGame) {
            onGame = false;
            FragmentManager fm = getSupportFragmentManager();
            fm.popBackStack();
            Tasks_OGE_Activity.levels_layout.setVisibility(View.VISIBLE);
        } else {
            super.onBackPressed();
        }
    }
}