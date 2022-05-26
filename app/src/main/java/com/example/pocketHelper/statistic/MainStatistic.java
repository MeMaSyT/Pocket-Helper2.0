package com.example.pocketHelper.statistic;

import android.content.Context;
import android.content.SharedPreferences;

public class MainStatistic  {
    public static final String APP_STATISTIC_FILE_NAME = "main_static";

    public static final String APP_STATISTIC_LEVELS_PLAYED = "Levels_Played";

    public static final String APP_STATISTIC_TrueVote_OGE = "TrueVote_OGE";
    public static final String APP_STATISTIC_FalseVote_OGE = "FalseVote_OGE";

    public static final String APP_STATISTIC_TrueVote_OGE_1_now = "TrueVote_OGE_1_now";
    public static final String APP_STATISTIC_FalseVote_OGE_1_now = "FalseVote_OGE_1_now";
    public static final String APP_STATISTIC_TrueVote_OGE_2_now = "TrueVote_OGE_2_now";
    public static final String APP_STATISTIC_FalseVote_OGE_2_now = "FalseVote_OGE_2_now";
    public static final String APP_STATISTIC_TrueVote_OGE_3_now = "TrueVote_OGE_3_now";
    public static final String APP_STATISTIC_FalseVote_OGE_3_now = "FalseVote_OGE_3_now";
    public static final String APP_STATISTIC_TrueVote_OGE_4_now = "TrueVote_OGE_4_now";
    public static final String APP_STATISTIC_FalseVote_OGE_4_now = "FalseVote_OGE_4_now";
    public static final String APP_STATISTIC_TrueVote_OGE_5_now = "TrueVote_OGE_5_now";
    public static final String APP_STATISTIC_FalseVote_OGE_5_now = "FalseVote_OGE_5_now";
    public static final String APP_STATISTIC_TrueVote_OGE_6_now = "TrueVote_OGE_6_now";
    public static final String APP_STATISTIC_FalseVote_OGE_6_now = "FalseVote_OGE_6_now";
    public static final String APP_STATISTIC_TrueVote_OGE_7_now = "TrueVote_OGE_7_now";
    public static final String APP_STATISTIC_FalseVote_OGE_7_now = "FalseVote_OGE_7_now";
    public static final String APP_STATISTIC_TrueVote_OGE_8_now = "TrueVote_OGE_8_now";
    public static final String APP_STATISTIC_FalseVote_OGE_8_now = "FalseVote_OGE_8_now";
    public static final String APP_STATISTIC_TrueVote_OGE_9_now = "TrueVote_OGE_9_now";
    public static final String APP_STATISTIC_FalseVote_OGE_9_now = "FalseVote_OGE_9_now";
    public static final String APP_STATISTIC_TrueVote_OGE_10_now = "TrueVote_OGE_10_now";
    public static final String APP_STATISTIC_FalseVote_OGE_10_now = "FalseVote_OGE_10_now";
    public static final String APP_STATISTIC_TrueVote_OGE_11_now = "TrueVote_OGE_11_now";
    public static final String APP_STATISTIC_FalseVote_OGE_11_now = "FalseVote_OGE_11_now";
    public static final String APP_STATISTIC_TrueVote_OGE_12_now = "TrueVote_OGE_12_now";
    public static final String APP_STATISTIC_FalseVote_OGE_12_now = "FalseVote_OGE_12_now";
    public static final String APP_STATISTIC_TrueVote_OGE_13_now = "TrueVote_OGE_13_now";
    public static final String APP_STATISTIC_FalseVote_OGE_13_now = "FalseVote_OGE_13_now";
    public static final String APP_STATISTIC_TrueVote_OGE_14_now = "TrueVote_OGE_14_now";
    public static final String APP_STATISTIC_FalseVote_OGE_14_now = "FalseVote_OGE_14_now";
    public static final String APP_STATISTIC_TrueVote_OGE_15_now = "TrueVote_OGE_15_now";
    public static final String APP_STATISTIC_FalseVote_OGE_15_now = "FalseVote_OGE_15_now";
    public static final String APP_STATISTIC_TrueVote_OGE_16_now = "TrueVote_OGE_16_now";
    public static final String APP_STATISTIC_FalseVote_OGE_16_now = "FalseVote_OGE_16_now";
    public static final String APP_STATISTIC_TrueVote_OGE_17_now = "TrueVote_OGE_17_now";
    public static final String APP_STATISTIC_FalseVote_OGE_17_now = "FalseVote_OGE_17_now";
    public static final String APP_STATISTIC_TrueVote_OGE_18_now = "TrueVote_OGE_18_now";
    public static final String APP_STATISTIC_FalseVote_OGE_18_now = "FalseVote_OGE_18_now";
    public static final String APP_STATISTIC_TrueVote_OGE_19_now = "TrueVote_OGE_19_now";
    public static final String APP_STATISTIC_FalseVote_OGE_19_now = "FalseVote_OGE_19_now";

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
