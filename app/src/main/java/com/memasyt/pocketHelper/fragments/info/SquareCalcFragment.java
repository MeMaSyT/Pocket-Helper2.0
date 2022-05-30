package com.memasyt.pocketHelper.fragments.info;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;

import android.transition.TransitionInflater;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;

import com.memasyt.pocketHelper.R;

public class SquareCalcFragment extends Fragment {

    private EditText a_Text, b_Text, c_Text;
    private TextView btn_calc;

    private ImageButton btn_back;

    TextView resultText;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_square_calc, container, false);
    }
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        TransitionInflater inflater = TransitionInflater.from(requireContext());
        setExitTransition(inflater.inflateTransition(R.transition.fade));
    }
    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        a_Text = view.findViewById(R.id.calc_square_a);
        b_Text = view.findViewById(R.id.calc_square_b);
        c_Text = view.findViewById(R.id.calc_square_c);
        btn_calc = view.findViewById(R.id.calc_button);
        resultText = view.findViewById(R.id.result_square_calc);
        btn_back = view.findViewById(R.id.btn_back_square_calc);
        btn_calc.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                float a, b, c, D, x1, x2;
                String result;
                if (a_Text.getText().toString().equals("") || b_Text.getText().toString().equals("") || c_Text.getText().toString().equals("")) {

                } else {
                    a = Integer.parseInt(a_Text.getText().toString());
                    b = Integer.parseInt(b_Text.getText().toString());
                    c = Integer.parseInt(c_Text.getText().toString());

                    D = b * b - (4 * a * c);
                    result = "D = " + D;
                    if (D > 0) {
                        x1 = (float) ((-b + Math.sqrt(D)) / (2 * a));
                        x2 = (float) ((-b - Math.sqrt(D)) / (2 * a));
                        result += "\nТак как D > 0, то будет 2 корня";
                        result += "\nx1 = " + x1;
                        result += "\nx2 = " + x2;
                    } else if (D == 0) {
                        x1 = (float) ((-b) / (2 * a));
                        result += "\nТак как D = 0, то будет 1 корень";
                        result += "\nx = " + x1;
                    } else {
                        result += "\nТак как D < 0, то корней нет";
                    }
                    resultText.setText(result);
                }
            }
        });
        btn_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FragmentManager fm = getActivity().getSupportFragmentManager();
                fm.popBackStack();
            }
        });
    }
}