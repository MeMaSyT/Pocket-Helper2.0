package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.constraintlayout.widget.ConstraintSet;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ImageView;

import com.example.myapplication.fragments.InfoFragment;
import com.example.myapplication.fragments.MainFragment;
import com.example.myapplication.fragments.TasksFragment;
import com.example.myapplication.fragments.info.SquareCalcFragment;
import com.example.myapplication.fragments.info.SquareUravneniyaFragment;
import com.example.myapplication.statistic.MainStatistic;

public class MainActivity extends AppCompatActivity {

    private ImageButton main_button, tasks_button, info_button;

    private ImageView menuNowImage;

    public static int nowMenuID = 0;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        MainStatistic.activateStats(this);

        menuNowImage = findViewById(R.id.menuNow_image);
        if (nowMenuID == 0) {
            btn_main_click(main_button);
        } else if (nowMenuID == -1) {
            btn_task_click(tasks_button);
        }
    }


    public void btn_main_click(View v) {
        MainFragment main_frag = new MainFragment();
        nextFragment(main_frag);

        ConstraintLayout cl = (ConstraintLayout) findViewById(R.id.bottom_panel);
        ConstraintSet cs = new ConstraintSet();
        cs.clone(cl);
        cs.setHorizontalBias(R.id.menuNow_image, 0.5f);
        cs.applyTo(cl);

        nowMenuID = 0;
    }

    public void btn_task_click(View v) {
        TasksFragment tasks_frag = new TasksFragment();
        nextFragment(tasks_frag);

        ConstraintLayout cl = (ConstraintLayout) findViewById(R.id.bottom_panel);
        ConstraintSet cs = new ConstraintSet();
        cs.clone(cl);
        cs.setHorizontalBias(R.id.menuNow_image, 0.02f);
        cs.applyTo(cl);

        nowMenuID = -1;
    }

    public void btn_info_click(View v) {
        InfoFragment info_frag = new InfoFragment();
        nextFragment(info_frag);

        ConstraintLayout cl = (ConstraintLayout) findViewById(R.id.bottom_panel);
        ConstraintSet cs = new ConstraintSet();
        cs.clone(cl);
        cs.setHorizontalBias(R.id.menuNow_image, 0.98f);
        cs.applyTo(cl);

        nowMenuID = 1;
    }

    private void nextFragment(Fragment fragment) {
        FragmentTransaction ft = getSupportFragmentManager().beginTransaction();
        ft.replace(R.id.fragment_layout, fragment);
        ft.addToBackStack(null);
        ft.commit();
    }

    public void btn_square_click(View v) {
        SquareUravneniyaFragment squareInfo_frag = new SquareUravneniyaFragment();
        nextFragment(squareInfo_frag);
    }

    public void btn_square_calc(View v) {
        SquareCalcFragment squareCalc_frag = new SquareCalcFragment();
        nextFragment(squareCalc_frag);
    }
}