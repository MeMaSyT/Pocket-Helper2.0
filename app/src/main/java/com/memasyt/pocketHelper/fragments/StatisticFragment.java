package com.memasyt.pocketHelper.fragments;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;

import android.transition.TransitionInflater;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.TextView;

import com.memasyt.pocketHelper.R;
import com.memasyt.pocketHelper.statistic.MainStatistic;
import com.razerdp.widget.animatedpieview.AnimatedPieView;
import com.razerdp.widget.animatedpieview.AnimatedPieViewConfig;
import com.razerdp.widget.animatedpieview.data.SimplePieInfo;

public class StatisticFragment extends Fragment {

    private ImageButton back_btn;

    private TextView amount_played, liked_level_oge;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        return inflater.inflate(R.layout.fragment_statistic, container, false);
    }
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        TransitionInflater inflater = TransitionInflater.from(requireContext());
        setExitTransition(inflater.inflateTransition(R.transition.fade));
    }
    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        drawPie();
        back_btn = view.findViewById(R.id.buttonBack_inStats);
        amount_played = view.findViewById(R.id.label_stats_amount_play);
        back_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FragmentManager fm = getActivity().getSupportFragmentManager();
                fm.popBackStack();
            }
        });

        amount_played.setText("Решено примеров: " + MainStatistic.mStatistic.getInt(MainStatistic.APP_STATISTIC_LEVELS_PLAYED, 0));
    }
    private void drawPie(){
        AnimatedPieView mAnimatedPieView = getActivity().findViewById(R.id.statistic_pie_kd);
        AnimatedPieViewConfig config = new AnimatedPieViewConfig();
        config.startAngle(-90)// Starting angle offset
                .addData(new SimplePieInfo(MainStatistic.mStatistic.getInt("TrueVote_OGE", 0), getResources().getColor(R.color.green_true), "правильных"))
                .addData(new SimplePieInfo(MainStatistic.mStatistic.getInt("FalseVote_OGE", 0), getResources().getColor(R.color.red_false), "неправильных"))
                .strokeMode(false)// Whether to draw ring-chart(default:true)
                .duration(1500)// Animation drawing duration
                .startAngle(-90f)// Starting angle offset
                .autoSize(false)// Auto fit chart radius

                .pieRadius(149)// Set chart radius
                .textGravity(AnimatedPieViewConfig.BELOW)// Text Gravity 【
      .duration(500);// draw pie animation duration

// The following two sentences can be replace directly 'mAnimatedPieView.start (config); '
        mAnimatedPieView.applyConfig(config);
        mAnimatedPieView.start();
    }
}