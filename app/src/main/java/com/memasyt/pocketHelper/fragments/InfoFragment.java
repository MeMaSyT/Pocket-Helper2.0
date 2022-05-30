package com.memasyt.pocketHelper.fragments;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.transition.TransitionInflater;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.memasyt.pocketHelper.MainActivity;
import com.memasyt.pocketHelper.R;

public class InfoFragment extends Fragment {

    private TextView btn_statistic;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        return inflater.inflate(R.layout.fragment_info, container, false);

    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        btn_statistic = view.findViewById(R.id.button_to_statistic);
        btn_statistic.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getActivity().getSupportFragmentManager()
                        .beginTransaction()
                        .replace(R.id.fragment_layout, new StatisticFragment())
                        .addToBackStack("stats")
                        .commit();
            }
        });
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        TransitionInflater inflater = TransitionInflater.from(requireContext());
        setExitTransition(inflater.inflateTransition(R.transition.fade));
    }

    public void btn_square_click(View v) {
        MainActivity ma = (MainActivity ) getActivity();
        ma.btn_square_click(v);
    }
    public void btn_formulas_click(View v) {
        MainActivity ma = (MainActivity ) getActivity();
        ma.btn_formulas_click(v);
    }

    public void btn_trigonometryBase_click(View v) {
        MainActivity ma = (MainActivity ) getActivity();
        ma.btn_formulas_click(v);
    }

    public void btn_geometryBase_click(View v) {
        MainActivity ma = (MainActivity ) getActivity();
        ma.btn_geometry_click(v);
    }
}