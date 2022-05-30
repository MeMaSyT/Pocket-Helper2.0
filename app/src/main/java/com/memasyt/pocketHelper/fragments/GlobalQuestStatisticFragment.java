package com.memasyt.pocketHelper.fragments;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.transition.TransitionInflater;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.memasyt.pocketHelper.R;
import com.memasyt.pocketHelper.Tasks_OGE_Activity;

public class GlobalQuestStatisticFragment extends Fragment {
    private String yourAnswers;
    private int trueAnswers = 0;

    private TextView btn_back;

    private TextView trueAnswers_text, falseAnswers_text, yourAnswer_text;

    //images
    ImageView i1, i2, i3, i4, i5, i6, i7, i8, i9, i10, i11, i12, i13, i14, i15, i16, i17, i18, i19;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        return inflater.inflate(R.layout.fragment_global_quest_statistic, container, false);
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        TransitionInflater inflater = TransitionInflater.from(requireContext());
        setExitTransition(inflater.inflateTransition(R.transition.fade));
    }

    public GlobalQuestStatisticFragment(String yourAnswers) {
        this.yourAnswers = yourAnswers;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        i1 = view.findViewById(R.id.image_num1);
        i2 = view.findViewById(R.id.image_num2);
        i3 = view.findViewById(R.id.image_num3);
        i4 = view.findViewById(R.id.image_num4);
        i5 = view.findViewById(R.id.image_num5);
        i6 = view.findViewById(R.id.image_num6);
        i7 = view.findViewById(R.id.image_num7);
        i8 = view.findViewById(R.id.image_num8);
        i9 = view.findViewById(R.id.image_num9);
        i10 = view.findViewById(R.id.image_num10);
        i11 = view.findViewById(R.id.image_num11);
        i12 = view.findViewById(R.id.image_num12);
        i13 = view.findViewById(R.id.image_num13);
        i14 = view.findViewById(R.id.image_num14);
        i15 = view.findViewById(R.id.image_num15);
        i16 = view.findViewById(R.id.image_num16);
        i17 = view.findViewById(R.id.image_num17);
        i18 = view.findViewById(R.id.image_num18);
        i19 = view.findViewById(R.id.image_num19);
        ImageView[] array = {i1, i2, i3, i4, i5, i6, i7, i8, i9, i10, i11, i12, i13, i14, i15, i16, i17, i18, i19};

        trueAnswers_text = view.findViewById(R.id.trueAnswers_stats_oge_global);
        falseAnswers_text = view.findViewById(R.id.falseAnswers_stats_oge_global);
        yourAnswer_text = view.findViewById(R.id.yourAnswer);
        btn_back = view.findViewById(R.id.button_back_statistic_global_oge);
        btn_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getActivity().getSupportFragmentManager().beginTransaction()
                        .remove(getActivity().getSupportFragmentManager().findFragmentByTag("statsGlobalOge"))
                        .commit();
                Tasks_OGE_Activity.levels_layout.setVisibility(View.VISIBLE);
            }
        });

        int idFalse = getResources().getIdentifier("ic_button_oge", "drawable", getActivity().getPackageName());
        for (int i = 0; i < yourAnswers.length(); i++) {
            if (!String.valueOf(yourAnswers.charAt(i)).equals("1")) {
                array[i].setImageResource(idFalse);
            } else {
                trueAnswers++;
            }
        }

        trueAnswers_text.setText("Правильных ответов: " + trueAnswers);
        falseAnswers_text.setText("Правильных ответов: " + (19 - trueAnswers));

        if (trueAnswers < 8) {
            yourAnswer_text.setText("2");
            yourAnswer_text.setTextColor(getResources().getColor(R.color.red_false));
        }
        else if (trueAnswers < 12 && trueAnswers > 7) {
            yourAnswer_text.setText("3");
            yourAnswer_text.setTextColor(getResources().getColor(R.color.red_false));
        }
        else if (trueAnswers < 18 && trueAnswers > 11) {
            yourAnswer_text.setText("4");
            yourAnswer_text.setTextColor(getResources().getColor(R.color.green_true));
        }
        else if (trueAnswers > 17) {
            yourAnswer_text.setText("5");
            yourAnswer_text.setTextColor(getResources().getColor(R.color.green_true));
        }
    }
}