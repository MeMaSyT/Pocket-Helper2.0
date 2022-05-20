package com.example.myapplication.fragments;

import android.graphics.Color;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.DecelerateInterpolator;

import com.example.myapplication.R;
import com.example.myapplication.statistic.MainStatistic;
import com.razerdp.widget.animatedpieview.AnimatedPieView;
import com.razerdp.widget.animatedpieview.AnimatedPieViewConfig;
import com.razerdp.widget.animatedpieview.callback.OnPieSelectListener;
import com.razerdp.widget.animatedpieview.data.IPieInfo;
import com.razerdp.widget.animatedpieview.data.SimplePieInfo;

public class StatisticFragment extends Fragment {
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        return inflater.inflate(R.layout.fragment_statistic, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        drawPie();
    }
    private void drawPie(){
        AnimatedPieView mAnimatedPieView = getActivity().findViewById(R.id.statistic_pie_kd);
        AnimatedPieViewConfig config = new AnimatedPieViewConfig();
        config.startAngle(-90)// Starting angle offset
                .addData(new SimplePieInfo(MainStatistic.mStatistic.getInt("TrueVote_OGE", 0), getResources().getColor(R.color.green_true), "правильных"))
                .addData(new SimplePieInfo(MainStatistic.mStatistic.getInt("FalseVote_OGE", 0), getResources().getColor(R.color.red_false), "неправильных"))
                .floatExpandAngle(15f)// Selected pie's angle of expansion
                .floatShadowRadius(18f)// Selected pie's shadow of expansion
                .floatUpDuration(500)// Selected pie's floating animation duration
                .floatDownDuration(500)// Last selected pie's float down animation duration
                .floatExpandSize(15)// Selected pie's size of expansion(only for pie-chart,not ring-chart)
                .strokeMode(false)// Whether to draw ring-chart(default:true)
                .strokeWidth(15)// Stroke width for ring-chart
                .duration(2500)// Animation drawing duration
                .startAngle(-90f)// Starting angle offset
                .drawText(true)// Whether to draw a text description
                .textSize(50)// Text description size
                .textMargin(0)// Margin between text and guide line
                .autoSize(false)// Auto fit chart radius
                .pieRadius(200)// Set chart radius
                .pieRadiusRatio(0.8f)// Chart's radius ratio for parent ViewGroup
                .guidePointRadius(2)// Chart's radius
                .guideLineWidth(10)// Text guide line stroke width
                .guideLineMarginStart(8)// Guide point margin from chart
                .textGravity(AnimatedPieViewConfig.ABOVE)// Text Gravity 【

       .canTouch(true)// Whether to allow the pie click to enlarge
                .splitAngle(1)// Clearance angle
                .focusAlphaType(AnimatedPieViewConfig.FOCUS_WITH_ALPHA_REV)// Alpha change mode for selected pie
                .interpolator(new DecelerateInterpolator())// Set animation interpolator
                .focusAlpha(150) // Alpha for selected pie (depend on focusAlphaType)
      .duration(500);// draw pie animation duration

// The following two sentences can be replace directly 'mAnimatedPieView.start (config); '
        mAnimatedPieView.applyConfig(config);
        mAnimatedPieView.start();
    }
}