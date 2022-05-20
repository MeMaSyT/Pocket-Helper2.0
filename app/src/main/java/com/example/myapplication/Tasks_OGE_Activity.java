package com.example.myapplication;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import android.content.Intent;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.example.myapplication.db.DBHelper;
import com.example.myapplication.fragments.InfoFragment;
import com.example.myapplication.fragments.QuestFragment_oge;
import com.example.myapplication.fragments.QuestFragment_oge_line;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Random;
import java.util.Set;

public class Tasks_OGE_Activity extends AppCompatActivity {

    public static View levels_layout;

    private DBHelper db_helper;
    private SQLiteDatabase db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tasks_oge);

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
                    questFragment_oge = new QuestFragment_oge(generateIntent(), data_id);
                }

                ft.replace(R.id.fragmentContainerView, questFragment_oge);
                ft.addToBackStack("oge_task_vote");
                ft.commit();
                break;
            case 2:

                levels_layout = findViewById(R.id.container1);
                levels_layout.setVisibility(View.INVISIBLE);

                QuestFragment_oge_line questFragment_oge_line = null;
                if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.N) {
                    questFragment_oge_line = new QuestFragment_oge_line(generateIntent(), data_id);
                }

                ft.replace(R.id.fragmentContainerView, questFragment_oge_line);
                ft.addToBackStack("oge_task_vote_line");
                ft.commit();
                break;
        }


    }

    public void goBack(View view) {
        Intent intent = new Intent(Tasks_OGE_Activity.this, MainActivity.class);
        Tasks_OGE_Activity.this.startActivity(intent);
    }

    @RequiresApi(api = Build.VERSION_CODES.N)
    private int[] generateIntent() {
        final int N = 10;
        ArrayList<Integer> arrayList = new ArrayList<>(N);
        Random random = new Random();

        while (arrayList.size() < N) {
            int i = random.nextInt(N);
            if (!arrayList.contains(i)) {
                arrayList.add(i);
            }
        }
        System.out.println(arrayList.size());
        return arrayList.stream().mapToInt(i -> i).toArray();
    }
}