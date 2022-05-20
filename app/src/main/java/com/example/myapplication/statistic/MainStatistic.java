package com.example.myapplication.statistic;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

public class MainStatistic  {
    public static final String APP_STATISTIC_FILE_NAME = "main_static";
    public static final String APP_STATISTIC_TrueVote_OGE = "TrueVote_OGE";
    public static final String APP_STATISTIC_FalseVote_OGE = "FalseVote_OGE";

    public static final String APP_STATISTIC_TrueVote_OGE_6_now = "TrueVote_OGE_6_now";
    public static final String APP_STATISTIC_FalseVote_OGE_6_now = "FalseVote_OGE_6_now";
    public static final String APP_STATISTIC_TrueVote_OGE_7_now = "TrueVote_OGE_7_now";
    public static final String APP_STATISTIC_FalseVote_OGE_7_now = "FalseVote_OGE_7_now";

    public static SharedPreferences mStatistic;
    public static SharedPreferences.Editor editor;

    public static void activateStats(Context context) {
        mStatistic =  context.getSharedPreferences(APP_STATISTIC_FILE_NAME, Context.MODE_PRIVATE);
        editor = mStatistic.edit();
    }

    public static void plussedInt(String key, int plussedNumber){
        editor.putInt(key, mStatistic.getInt(key, 0) + plussedNumber);
        editor.apply();
    }
}
